package com.innogames.tools.hostseditor.model

import org.assertj.core.api.Assertions
import org.gradle.internal.impldep.org.junit.Test
import org.junit.Test

class HostEntryTest {


	@Test
	void getHostEntryString_noAliases() {

		HostEntry entry = new HostEntry(name: "test", internetAddress: "123")

		Assertions.assertThat(entry.getHostEntryString())
			.isEqualTo("123\ttest")

	}

	@Test
	void getHostEntryString_aliases() {

		HostEntry entry = new HostEntry(name: "test", internetAddress: "123", aliases: ["alias1", "alias2"])

		Assertions.assertThat(entry.getHostEntryString())
			.isEqualTo("123\ttest\talias1\talias2")

	}

}
