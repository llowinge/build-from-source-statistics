package org.jboss.fuse.maven;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Path dependencyTreeFile;

    public Parser(final String dependencyTreeFilePath) {
        dependencyTreeFile = Paths.get(dependencyTreeFilePath);
    }

    public static void main(String[] args) throws IOException {
        final String dependencyTreeFile = args[0];
        final String outputFile = args[1];
        final Parser parser = new Parser(dependencyTreeFile);
        final MavenArtifact parent = parser.parse();
        parser.countStatistics(parent);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.ANY)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(outputFile), parent);
    }

    public Statistics countStatistics(MavenArtifact artifact) {
        if (artifact.children.isEmpty()) {
            Statistics freshCounting = artifact.freshStatistics();
            artifact.productizedCount = freshCounting.productized;
            artifact.communityCount = freshCounting.community;
            return freshCounting;

        }
        int sumProductizedChildren = 0;
        int sumCommunity = 0;
        for (MavenArtifact child : artifact.children) {
            Statistics counting = countStatistics(child);
            sumProductizedChildren += counting.productized;
            sumCommunity += counting.community;

        }
        artifact.productizedCount = artifact.freshStatistics().productized + sumProductizedChildren;
        artifact.communityCount = artifact.freshStatistics().community + sumCommunity;
        return artifact.actualStatistics();
    }

    class Statistics {
        int productized;
        int community;

        Statistics(int productized, int community) {
            this.productized = productized;
            this.community = community;
        }
    }

    public MavenArtifact parse() throws IOException {
        final List<String> lines = Files.readAllLines(dependencyTreeFile);
        MavenArtifact parent = new MavenArtifact("org.jboss.fuse.maven:build-from-source-statistics:jar:1.0-SNAPSHOT");
        final MavenArtifact superParent = parent;
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            MavenArtifact child = new MavenArtifact(prepareGav(line));
            child.parent = parent;
            int detectedLevel = detectLevel(line);
            parent.addChild(child);

            if (i < lines.size() - 1) {
                String nextLine = lines.get(i + 1);
                int nextLineLevel = detectLevel(nextLine);
                if (nextLineLevel > detectedLevel) { // going deeper
                    parent = child;
                } else if (nextLineLevel < detectedLevel) { // going up
                    for (int j = 0; j < detectedLevel - nextLineLevel; j++) {
                        parent = parent.parent;
                    }
                }
            }
        }
        return superParent;
    }

    private String prepareGav(String line) {
        return line.substring(indentationLength(line));
    }

    private int detectLevel(String line) {
        return (indentationLength(line) / 3) - 1;
    }

    private int indentationLength(String line) {
        final Matcher matcher = Pattern.compile("([\\\\+-\\\\| ]*)").matcher(line);
        if (matcher.find()) {
            return matcher.group(1).length();
        }
        throw new IllegalStateException("We couldn't fine indentation");
    }

    class MavenArtifact {
        private String name;
        private String groupId;
        private String artifactId;
        private String version;
        private boolean productized;
        private int productizedCount = 0;
        private int communityCount = 0;
        @JsonIgnore
        private MavenArtifact parent;
        private List<MavenArtifact> children = new ArrayList<>();

        MavenArtifact(final String gavString) {
            final String[] gav = gavString.split(":");
            name = gavString;
            groupId = gav[0];
            artifactId = gav[1];
            version = gav[3];
        }

        public void addChild(MavenArtifact artifact) {
            children.add(artifact);
        }

        @Override
        public String toString() {
            return groupId + ":" + artifactId + ":" + version;
        }

        Statistics freshStatistics() {
            int productized = getProductized() ? 1 : 0;
            return new Statistics(productized, (productized + 1) % 2);
        }

        Statistics actualStatistics() {
            return new Statistics(productizedCount, communityCount);
        }

        boolean getProductized() {
            return version.contains("redhat");
        }

    }

}
