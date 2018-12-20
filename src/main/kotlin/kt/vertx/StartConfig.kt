package kt.vertx

import picocli.CommandLine

class StartConfig {
  @CommandLine.Option(names = arrayOf("-h", "--host"), description = arrayOf("Host"))
  var host: String = ""
}