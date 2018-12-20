package kt.vertx

import io.vertx.core.AbstractVerticle
import org.slf4j.LoggerFactory

class LoggingVerticle : AbstractVerticle() {
  private val log = LoggerFactory.getLogger(LoggingVerticle::class.java)

  override fun start() {
    super.start()
    vertx.eventBus().consumer<String>("logging") { e -> log.info(e.body()) }
  }
}