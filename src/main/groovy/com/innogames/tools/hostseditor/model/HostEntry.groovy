package com.innogames.tools.hostseditor.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Represents a single line of the hosts file.
 */
@EqualsAndHashCode
@ToString(includePackage = false)
class HostEntry implements Serializable {

	String internetAddress

	String name

	List<String> aliases = []

	HostEntry() {

	}

	HostEntry(String name) {
		this.name = name
	}

	/**
	 * Returns the string in a form that is compatible with the hosts file contract.
	 */
	String getHostEntryString() {

		def aliasesString = aliases.inject("") { result, i -> "$result\t$i" }

		return "$internetAddress\t$name$aliasesString"
	}

}
