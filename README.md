# The Examples project

This project contains several examples to try out new things. The project itself is an example of how to setup a development setup.

## 1. Project Setup

Here are the steps to create a quick project mainly targetting java development using gradle for builds and eclipse as an IDE.

Create a template gradle project:
```
gradle init --type java-library
```

Create a useful gitignore file (gitignore.io):
```
gi java,eclipse,gradle > .gitignore
```

Initialize a empty git repo and make an initial commit:
```
git init
git add .
git commit -m "Initial Commit"
```

## 2. Multi Project Setup

Most of the magic is in the build.gradle in the root project. For each new project, create a directory and include it in settings.gradle.

Using the 'subprojects' section, common configuration options for all subprojects can be applied. Checkout the 'Hello' subproject as an example of a simple java project containing a java application aas well as a library.

Here are the steps to create a new project:
```
mkdir NewProject
echo "include 'NewProject'" >> settings.gradle
gradle NewProject:initJavaProject
gradle NewProject:eclipse
```

## 3. Curator

This is an example of a java application to run some simple tests for the Apache Curator APIs. It uses the gradle application plugin in this project's build.gradle.

TODO: Need to find a nice way to pass some specific System properties to the application JVM. Currently we just dump all of the system properties of the gradle JVM to the application JVM.


