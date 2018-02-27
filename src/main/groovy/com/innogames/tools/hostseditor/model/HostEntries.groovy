package com.innogames.tools.hostseditor.model

import groovy.transform.ToString
import groovy.util.logging.Slf4j

/**
 * Represents a collection of {@link HostEntry}s to operate on them. E.g. this class can represent the content of the
 * complete hosts file.
 */
@Slf4j
@ToString(includeFields = true)
class HostEntries {

	private Set<HostEntry> hostEntries = new ArrayList<>()

	static HostEntries of(Set<HostEntry> hostEntries) {
		return new HostEntries(hostEntries: hostEntries)
	}

	Set<HostEntry> getEntries() {
		return Collections.unmodifiableSet(hostEntries)
	}

	boolean update(HostEntries entriesToUpdate) {

		boolean updated = false

		entriesToUpdate.getEntries().each { newEntry ->
			def existing = hostEntries.find { it.name == newEntry.name }
			if (existing != null) {
				if (existing != newEntry) {
					log.info("Found $existing as entry. Update it to $newEntry")
					hostEntries.remove(existing)
					hostEntries.add(newEntry)
					updated = true
				}
			} else {
				log.info("Can not find $newEntry. Add it to hosts")
				hostEntries.add(newEntry)
				updated = true
			}
		}

		return updated

	}


}
