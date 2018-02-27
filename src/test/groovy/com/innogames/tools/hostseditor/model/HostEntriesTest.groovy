package com.innogames.tools.hostseditor.model

import org.assertj.core.api.Assertions
import org.gradle.internal.impldep.org.junit.Test
import org.junit.Test

class HostEntriesTest {

	@Test
	void update() {

		def entry1 = new HostEntry(name: "test", internetAddress: "12345")
		def entry2 = new HostEntry(name: "test2", internetAddress: "98765")

		def existing = new HostEntries(hostEntries: [entry1, entry2])

		def entryToUpdate = new HostEntry(name: "test2", internetAddress: "new")

		existing.update(new HostEntries(hostEntries: [entryToUpdate]));

		Assertions.assertThat(existing.getEntries())
			.contains(entry1, entryToUpdate)
			.doesNotContain(entry2)

	}

}
