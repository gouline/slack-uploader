package net.gouline.slackuploader

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cmd = Commands()

    val commander = JCommander(cmd)
    commander.setProgramName("slack-uploader")

    try {
        commander.parse(*args)
    } catch (e: ParameterException) {
        println("ERROR: ${e.message}")
        exitProcess(1)
    }

    if (cmd.help) {
        commander.usage()
    } else {
        println("Token: ${cmd.token}")
        println("Title: ${cmd.title}")
        println("Channels: ${cmd.channels} (${cmd.channels.size})")
        println("Files: ${cmd.files} (${cmd.files.size})")
    }
}

class Commands {
    @Parameter(names = arrayOf("--help", "-h"), description = "Displays this usage.", help = true)
    var help = false

    @field:Parameter(names = arrayOf("--token", "-a"), description = "Authentication token (requires scope 'files:write:user').", required = true)
    var token: String? = null

    @field:Parameter(names = arrayOf("--title", "-t"), description = "Title of file.", required = true)
    var title: String? = null

    @field:Parameter(names = arrayOf("--channel", "-c"), description = "Channel names or IDs where the file will be shared.", required = true)
    var channels: List<String> = ArrayList()

    @field:Parameter(description = "FILE", required = true)
    var files: List<String> = ArrayList()
}
