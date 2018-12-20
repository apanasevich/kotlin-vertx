package kt.vertx

import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import org.slf4j.LoggerFactory
import picocli.CommandLine

object StartPoint {
  private val log = LoggerFactory.getLogger(StartPoint::class.java)

  @JvmStatic
  fun main(args: Array<String>) {
    val startConfig = CommandLine.populateCommand(StartConfig(), *args)

    val vertxOptions = VertxOptions()
      .setClusterHost(startConfig.host)
      .setClusterPublicHost(startConfig.host)
      .setClusterManager(HazelcastClusterManager())

    Vertx.clusteredVertx(vertxOptions) { res ->
      if (res.succeeded()) {
        val vertx = res.result()
        vertx.deployVerticle(HttpControllerVerticle())
        vertx.deployVerticle(LoggingVerticle())
        log.info("Cluster node started at " + startConfig.host)
      } else {
        log.error("Start failed!")
      }
    }

  }
}