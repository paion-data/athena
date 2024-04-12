Athena Spring Boot Starter
==========================

For more information on Elide, visit [athena.io].

Opinionated jar which packages dependencies to get started with Elide and Spring Boot.  The starter includes:

1. athena-filestore-alioss
2. athena-spring-boot-autoconfigure

Maven Dependency
----------------

```xml
   <dependency>
       <groupId>com.paiondata.athena</groupId>
       <artifactId>athena-spring-boot-starter</artifactId>
       <version>${version.athena}</version>
   </dependency>
```

Configuration
-------------

Athena can be configured in `application.yaml` with settings like this:

```yaml
athena:
  spring:
    alioss:
      enabled: true
```

For more information on custom configuration, see the [athena-spring-boot-autoconfigure documentation].

License
-------

This project is licensed under the terms of the [Apache 2.0] open source license.

[athena.io]: https://paion-data.github.io/athena/
[athena-spring-boot-autoconfigure documentation]: https://github.com/paion-data/athena/tree/master/athena-spring/athena-spring-boot-autoconfigure/README.md
[Apache 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
