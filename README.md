# Java Presentation Manager (jPM)

Home page: http://jpaoletti.github.com/java-presentation-manager/

Java Presentation Manager is a CRUD system with extended tools to fast build an administrative and
monitoring web site based in xml definition files, with data source and interface independece so
you can define your model in xml terms and choose the data sources and visualization that you like most.

This project take its ideas from jPOS Presentation Manager project and is a generalization of the idea 
to be used in any web project and not only in jPOS based projects.

## How to use

This is a maven based project with several modules. A main core module contain the main functionality and 
definition tools. The other modules are implementations of some of the core aspect, normally the view or
the data access.

The default visual implementation is based on Struts 1. This is going to change soon to a pure jsp implementation
with a custom MVC system. To use it you need to use the following maven plugin:

```xml
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>2.1.1</version>
        <configuration>
          ...
        </configuration>
      </plugin>
    </plugins>
  </build>
  ...

  ...
  <dependencies>
    <dependency>
      <groupId>com.github.jpaoletti</groupId>
      <artifactId>jpm-struts1</artifactId>
      <version>1.0-SNAPSHOT</version>
      <type>war</type>
      <scope>runtime</scope>
    </dependency>
    ...
  </dependencies>
  ...
  
```
For further reference check http://maven.apache.org/plugins/maven-war-plugin/overlays.html

You can check the repositories at

https://oss.sonatype.org/index.html#nexus-search;quick~jpaoletti


## License

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
