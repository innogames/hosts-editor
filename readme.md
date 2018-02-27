[![Build Status](https://travis-ci.org/innogames/hosts-editor.svg?branch=master)](https://travis-ci.org/innogames/hosts-editor)

# Hosts Editor Plugin

The purpose of this plugin to make it easy to create a new hosts file with additional entries that you defined. 
This plugin will create a `hosts` file into the projects build folder. 

The original content stay but only additional entries will be added or updated. Comments will be lost in the new hosts file.

Supported Operating Systems are Windows, Linux and MacOS.

## Usage

The plugin is published on https://plugins.gradle.org/plugin/com.innogames.tools.hosts-editor

You can apply the plugin by using:

```groovy
plugins {
    id "com.innogames.tools.hosts-editor" version "1.0"
}
```
or
```groovy
buildscript {
    repositories {
        maven {
            url "https://plugins.gradle.org/m2/"
            }
        }
    dependencies {
        classpath "gradle.plugin.com.innogames.tools:hosts-editor:1.0"
    }
}

apply plugin: "com.innogames.tools.hosts-editor"
```

To configure the plugin use the following extension settings:

```groovy
hosts {
    destination "$project.buildDir.path/newHost"	//default: $project.buildDir.path/hosts
    entries {
        "myHost" {
            internetAddress = "127.0.0.1"
            aliases = ["firstAlias", "secondAlias"]
        }
        secondHost {
            internetAddress = "8.8.8.8"
        }
    }
}
```

The plugin configures a `editHosts` task which will create a `hosts` file in your configured destination.

## Contribution

Feel free to edit the plugin and make pull requests. When you discover any issues you're welcome to create an issue as well.
