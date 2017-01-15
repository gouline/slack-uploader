package net.gouline.slackuploader

import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory
import org.apache.commons.cli.*

private val OPTIONS = Options()
        .addOption(Option.builder("p")
                .argName("properties")
                .desc("path to properties file")
                .hasArg()
                .required()
                .build())
        .addOption(Option.builder("f")
                .argName("file")
                .desc("target file to upload")
                .hasArg()
                .required()
                .build())
        .addOption(Option.builder("h")
                .argName("help")
                .desc("display help")
                .build())

fun main(args: Array<String>) {
    val parser = DefaultParser()
    try {
        start(parser.parse(OPTIONS, args))
    } catch (e: ParseException) {
        println("Error: ${e.message}")
        HelpFormatter().printHelp("slack-uploader", OPTIONS)
    }

    val session = SlackSessionFactory.createWebSocketSlackSession("")
    session.connect()
}

private fun start(line: CommandLine) {
    if (line.hasOption("h")) {
        HelpFormatter().printHelp("slack-uploader", OPTIONS)
        return
    }
}
