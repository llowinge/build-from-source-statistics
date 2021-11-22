# Build from source statistics

## Description

We would like to count how many of artifacts are being productized (redhat suffix) from Maven's dependecny:tree.

## What the extension does

It creates object tree from dependency:tree txt file which is then processed (count artifacts with redhat suffix) and saved to
json for further calculations.

## Example
### Dependency:tree

```
+- org.apache.camel.quarkus:camel-quarkus-microprofile-health:jar:2.2.0.fuse-800021-redhat-00001:compile
|  +- io.quarkus:quarkus-smallrye-health:jar:2.2.3.Final-redhat-00013:compile
|  |  +- io.smallrye:smallrye-health:jar:3.1.1.redhat-00008:compile
|  |  |  +- org.eclipse.microprofile.health:microprofile-health-api:jar:3.1:compile
|  |  |  \- io.smallrye:smallrye-health-api:jar:3.1.1.redhat-00008:compile
|  |  +- io.smallrye:smallrye-health-provided-checks:jar:3.1.1.redhat-00008:compile
|  |  +- io.quarkus:quarkus-vertx-http:jar:2.2.3.Final-redhat-00013:compile
|  |  |  +- io.quarkus:quarkus-security-runtime-spi:jar:2.2.3.Final-redhat-00013:compile
|  |  |  +- io.quarkus:quarkus-vertx-http-dev-console-runtime-spi:jar:2.2.3.Final-redhat-00013:compile
|  |  |  \- io.quarkus:quarkus-vertx-core:jar:2.2.3.Final-redhat-00013:compile
|  |  |     \- io.netty:netty-codec-haproxy:jar:4.1.67.Final-redhat-00002:compile
|  |  \- io.quarkus:quarkus-jsonp:jar:2.2.3.Final-redhat-00013:compile
|  |     \- org.glassfish:jakarta.json:jar:1.1.6.redhat-00003:compile
|  \- org.apache.camel:camel-microprofile-health:jar:3.11.1.fuse-800025-redhat-00001:compile
|     +- org.apache.camel:camel-health:jar:3.11.1.fuse-800025-redhat-00001:compile
|     \- javax.enterprise:cdi-api:jar:2.0.0.redhat-1:compile
|        +- javax.el:javax.el-api:jar:3.0.0:compile
|        +- javax.interceptor:javax.interceptor-api:jar:1.2:compile
|        \- javax.inject:javax.inject:jar:1:compile
```
### Json output
```
{
  "name" : "org.jboss.fuse.maven:build-from-source-statistics:jar:1.0-SNAPSHOT",
  "groupId" : "org.jboss.fuse.maven",
  "artifactId" : "build-from-source-statistics",
  "version" : "1.0-SNAPSHOT",
  "productized" : false,
  "productizedCount" : 14,
  "communityCount" : 5,
  "children" : [ {
    "name" : "io.quarkus:quarkus-smallrye-health:jar:2.2.3.Final-redhat-00013:compile",
    "groupId" : "io.quarkus",
    "artifactId" : "quarkus-smallrye-health",
    "version" : "2.2.3.Final-redhat-00013",
    "productized" : true,
    "productizedCount" : 11,
    "communityCount" : 1,
    "children" : [ {
      "name" : "io.smallrye:smallrye-health:jar:3.1.1.redhat-00008:compile",
      "groupId" : "io.smallrye",
      "artifactId" : "smallrye-health",
      "version" : "3.1.1.redhat-00008",
      "productized" : true,
      "productizedCount" : 2,
      "communityCount" : 1,
      "children" : [ {
        "name" : "org.eclipse.microprofile.health:microprofile-health-api:jar:3.1:compile",
        "groupId" : "org.eclipse.microprofile.health",
        "artifactId" : "microprofile-health-api",
        "version" : "3.1",
        "productized" : false,
        "productizedCount" : 0,
        "communityCount" : 1,
        "children" : [ ]
      }, {
        "name" : "io.smallrye:smallrye-health-api:jar:3.1.1.redhat-00008:compile",
        "groupId" : "io.smallrye",
        "artifactId" : "smallrye-health-api",
        "version" : "3.1.1.redhat-00008",
        "productized" : true,
        "productizedCount" : 1,
        "communityCount" : 0,
        "children" : [ ]
      } ]
    }, {
      "name" : "io.smallrye:smallrye-health-provided-checks:jar:3.1.1.redhat-00008:compile",
      "groupId" : "io.smallrye",
      "artifactId" : "smallrye-health-provided-checks",
      "version" : "3.1.1.redhat-00008",
      "productized" : true,
      "productizedCount" : 1,
      "communityCount" : 0,
      "children" : [ ]
    }, {
      "name" : "io.quarkus:quarkus-vertx-http:jar:2.2.3.Final-redhat-00013:compile",
      "groupId" : "io.quarkus",
      "artifactId" : "quarkus-vertx-http",
      "version" : "2.2.3.Final-redhat-00013",
      "productized" : true,
      "productizedCount" : 5,
      "communityCount" : 0,
      "children" : [ {
        "name" : "io.quarkus:quarkus-security-runtime-spi:jar:2.2.3.Final-redhat-00013:compile",
        "groupId" : "io.quarkus",
        "artifactId" : "quarkus-security-runtime-spi",
        "version" : "2.2.3.Final-redhat-00013",
        "productized" : true,
        "productizedCount" : 1,
        "communityCount" : 0,
        "children" : [ ]
      }, {
        "name" : "io.quarkus:quarkus-vertx-http-dev-console-runtime-spi:jar:2.2.3.Final-redhat-00013:compile",
        "groupId" : "io.quarkus",
        "artifactId" : "quarkus-vertx-http-dev-console-runtime-spi",
        "version" : "2.2.3.Final-redhat-00013",
        "productized" : true,
        "productizedCount" : 1,
        "communityCount" : 0,
        "children" : [ ]
      }, {
        "name" : "io.quarkus:quarkus-vertx-core:jar:2.2.3.Final-redhat-00013:compile",
        "groupId" : "io.quarkus",
        "artifactId" : "quarkus-vertx-core",
        "version" : "2.2.3.Final-redhat-00013",
        "productized" : true,
        "productizedCount" : 2,
        "communityCount" : 0,
        "children" : [ {
          "name" : "io.netty:netty-codec-haproxy:jar:4.1.67.Final-redhat-00002:compile",
          "groupId" : "io.netty",
          "artifactId" : "netty-codec-haproxy",
          "version" : "4.1.67.Final-redhat-00002",
          "productized" : true,
          "productizedCount" : 1,
          "communityCount" : 0,
          "children" : [ ]
        } ]
      } ]
    }, {
      "name" : "io.quarkus:quarkus-jsonp:jar:2.2.3.Final-redhat-00013:compile",
      "groupId" : "io.quarkus",
      "artifactId" : "quarkus-jsonp",
      "version" : "2.2.3.Final-redhat-00013",
      "productized" : true,
      "productizedCount" : 2,
      "communityCount" : 0,
      "children" : [ {
        "name" : "org.glassfish:jakarta.json:jar:1.1.6.redhat-00003:compile",
        "groupId" : "org.glassfish",
        "artifactId" : "jakarta.json",
        "version" : "1.1.6.redhat-00003",
        "productized" : true,
        "productizedCount" : 1,
        "communityCount" : 0,
        "children" : [ ]
      } ]
    } ]
  }, {
    "name" : "org.apache.camel:camel-microprofile-health:jar:3.11.1.fuse-800025-redhat-00001:compile",
    "groupId" : "org.apache.camel",
    "artifactId" : "camel-microprofile-health",
    "version" : "3.11.1.fuse-800025-redhat-00001",
    "productized" : true,
    "productizedCount" : 3,
    "communityCount" : 3,
    "children" : [ {
      "name" : "org.apache.camel:camel-health:jar:3.11.1.fuse-800025-redhat-00001:compile",
      "groupId" : "org.apache.camel",
      "artifactId" : "camel-health",
      "version" : "3.11.1.fuse-800025-redhat-00001",
      "productized" : true,
      "productizedCount" : 1,
      "communityCount" : 0,
      "children" : [ ]
    }, {
      "name" : "javax.enterprise:cdi-api:jar:2.0.0.redhat-1:compile",
      "groupId" : "javax.enterprise",
      "artifactId" : "cdi-api",
      "version" : "2.0.0.redhat-1",
      "productized" : true,
      "productizedCount" : 1,
      "communityCount" : 3,
      "children" : [ {
        "name" : "javax.el:javax.el-api:jar:3.0.0:compile",
        "groupId" : "javax.el",
        "artifactId" : "javax.el-api",
        "version" : "3.0.0",
        "productized" : false,
        "productizedCount" : 0,
        "communityCount" : 1,
        "children" : [ ]
      }, {
        "name" : "javax.interceptor:javax.interceptor-api:jar:1.2:compile",
        "groupId" : "javax.interceptor",
        "artifactId" : "javax.interceptor-api",
        "version" : "1.2",
        "productized" : false,
        "productizedCount" : 0,
        "communityCount" : 1,
        "children" : [ ]
      }, {
        "name" : "javax.inject:javax.inject:jar:1:compile",
        "groupId" : "javax.inject",
        "artifactId" : "javax.inject",
        "version" : "1",
        "productized" : false,
        "productizedCount" : 0,
        "communityCount" : 1,
        "children" : [ ]
      } ]
    } ]
  } ]
}
```


## Build and use

Firstly build the extension.

    mvn clean install

Then use it 

     java -jar target/build-from-source-statistics-1.0-SNAPSHOT.jar <path-to-dep-tree.txt> <path-to-output-json>
