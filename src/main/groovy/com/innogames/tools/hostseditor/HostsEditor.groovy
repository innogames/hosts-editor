package com.innogames.tools.hostseditor

import com.innogames.tools.hostseditor.model.HostEntries
import com.innogames.tools.hostseditor.model.HostEntry
import groovy.util.logging.Slf4j
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 * This task reads the host file of the system and analyzes if the configured entries are in there. If these entries do
 * not exist they are added and together written into a new file. If an entry with the same name changed the record is
 * updated.
 */
@Slf4j
class HostsEditor extends DefaultTask {

	@Input
	Set<HostEntry> entries

	@Input
	String destination

	@InputFile
	File getHostsFile() {
		if (Os.isFamily(Os.FAMILY_MAC) || Os.isFamily(Os.FAMILY_UNIX)) {
			return new File("/etc/hosts")
		} else if (Os.isFamily(Os.FAMILY_WINDOWS)) {
			return new File("${System.getenv("WINDIR")}\\System32\\drivers\\etc\\hosts")
		} else {
			throw new UnsupportedOperationException("Can not determine host system.")
		}
	}

	@OutputFile
	File getOutputFile() {
		project.file(destination)
	}

	@TaskAction
	void editHosts() {

		HostEntries newEntries = HostEntries.of(entries)

		def hostsEntries = readHostsFile(getHostsFile())
		hostsEntries.update(newEntries)
		writeHostsFile(getOutputFile(), hostsEntries)

	}

	/**
	 * Reads the host file and converts it into {@link HostEntries}.
	 *
	 * @param hostFile - The hosts file
	 * @return - The {@link HostEntries} populated with the hosts file content.
	 */
	static HostEntries readHostsFile(File hostFile) {

		def entries = hostFile.readLines()
				.findAll { !it.isAllWhitespace() && it.trim().charAt(0) != '#' }
				.collect { it.split() as List }
				.collect {
					new HostEntry(name: it[1], internetAddress: it[0], aliases: it.size() > 2 ? it.subList(2, it.size()) : new ArrayList<>())
		}

		return new HostEntries(hostEntries: entries)

	}

	/**
	 * Writes the given entries into the outputFile in the format of a hosts file.
	 *
	 * @param outputFile - The file in which the entries should be written
	 * @param entries - The entries to be written.
	 */
	static void writeHostsFile(File outputFile, HostEntries entries) {

		def stringToWrite = entries.getEntries().inject("") { result, i ->
			result + "${i.getHostEntryString()}\n"
		}

		outputFile.parentFile.mkdirs()
		outputFile.write stringToWrite
		log.info("New host file written\n$outputFile.text")

	}

}
