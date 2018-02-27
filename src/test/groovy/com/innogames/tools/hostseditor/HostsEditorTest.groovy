package com.innogames.tools.hostseditor


import com.innogames.tools.hostseditor.model.HostEntries
import com.innogames.tools.hostseditor.model.HostEntry
import org.assertj.core.api.Assertions
import org.gradle.internal.impldep.org.junit.Test
import org.junit.Test

class HostsEditorTest {

	@Test
	void readHostsFile() {

		def fileUri = this.getClass().getResource("/hosts")
		def file = new File(fileUri.getFile())

		HostEntries result = HostsEditor.readHostsFile(file)

		Assertions.assertThat(result.getEntries())
				.contains(new HostEntry(internetAddress: "127.0.0.1", name: "localhost"))
				.contains(new HostEntry(internetAddress: "255.255.255.255", name: "broadcasthost"))
				.contains(new HostEntry(internetAddress: "::1", name: "localhost"))
				.contains(new HostEntry(internetAddress: "8.8.8.8", name: "google", aliases: ["yahoo", "bing"]))
	}
}
