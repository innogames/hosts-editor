package com.innogames.tools.hostseditor

import com.innogames.tools.hostseditor.model.HostEntry
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

/**
 * The Gradle DSL definition of our task.
 */
class HostsEditorExtension {

	String destination

	final NamedDomainObjectContainer<HostEntry> entries

	HostsEditorExtension(Project project) {
		destination = project.buildDir.path + "/hosts"
		entries = project.container(HostEntry)
	}

	def entries(Closure closure) {
		entries.configure(closure)
	}

}
