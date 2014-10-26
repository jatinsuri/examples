# The Examples project
This project contains several examples to try out new things. The project itself is an example of how to setup a development setup.

## 1. Project Setup
Here are the steps to create a quick project mainly targeting java development using gradle for builds and eclipse as an IDE.

Create a template gradle project:
```Shell
gradle init --type java-library
```

Create a useful _.gitignore_ file (gitignore.io):
```Shell
gi java,eclipse,gradle > .gitignore
```

Initialize a empty git repository and make an initial commit:
```Shell
git init
git add .
git commit -m "Initial Commit"
```

## 2. Multi Project Setup
Most of the magic is in the _build.gradle_ file in the root project. For each new project, create a directory and include it in _settings.gradle_.

Using the `subprojects` section, common configuration options for all sub-projects can be applied. Checkout the _Hello_ subproject as an example of a simple java project containing a java application as well as a library.

Here are the steps to create a new project:
```Shell
mkdir NewProject
echo "include 'NewProject'" >> settings.gradle
gradle NewProject:initJavaProjectDirs
gradle NewProject:eclipse
```

Here is a better way to create a new project:
```Shell
echo "include 'NewProject'" >> settings.gradle
gradle NewProject:initProjectDirs
gradle NewProject:eclipse
```

The `initProjectDirs` task can be run after customizing Source Sets in the sub-project's _build.gradle_. It will create directories according to the Source Set configuration.

## 3. Curator

This is an example of a java application to run some simple tests for the Apache Curator APIs. It uses the gradle application plugin in this project's build.gradle.

Also illustrated here is a way to pass system properties to the runtime JVM without hard coding them in the build.gradle file.


## 4. Avro

This project consists entirely of test cases. Some important concepts illustrated here are: 

1. Test cases written in Groovy
2. Logback configuration using Groovy (see logback.groovy)
3. Handling generated sources from Avro schema files
4. Custom Source Sets in Gradle