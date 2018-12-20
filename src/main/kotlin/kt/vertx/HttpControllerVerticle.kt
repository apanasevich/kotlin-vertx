package kt.vertx

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router
import org.slf4j.LoggerFactory

class HttpControllerVerticle : AbstractVerticle() {
  private val log = LoggerFactory.getLogger(HttpControllerVerticle::class.java)

  override fun start(startFuture: Future<Void>) {
    super.start()
    val router = Router.router(vertx)
    router.post("/message").handler { e ->
      val messages = e.queryParam("m")
      vertx.eventBus().send("logging", messages.joinToString())
      e.response().end("ok")
    }

    val httpServer = vertx.createHttpServer()
    httpServer.requestHandler(router).listen(18080) { result ->
      if (result.succeeded()) {
        startFuture.complete()
        log.info("HTTP server started on http://localhost:" + httpServer.actualPort())
      } else {
        startFuture.fail(result.cause())
      }
    }
  }
}
