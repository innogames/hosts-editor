package com.innogames.tools.hostseditor

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This plugin will create a default task called {@value HostsEditorPlugin#TASK_NAME} which creates a hosts file in the
 * configured location. You can configure the location in {@link HostsEditorExtension}.
 */
class HostsEditorPlugin implements Plugin<Project> {

	static final TASK_NAME = "editHosts"

	void apply(Project project) {

		def extension = project.extensions.create("hosts", HostsEditorExtension, project)

		project.getTasks().create(TASK_NAME, HostsEditor.class, { task ->
			task.description = "Creates a hosts file."
			project.afterEvaluate {
				task.setEntries(extension.getEntries())
				task.setDestination(extension.getDestination())
			}

		})
	}

}
