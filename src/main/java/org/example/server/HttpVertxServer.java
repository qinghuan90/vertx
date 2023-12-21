package org.example.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * @author qingh
 * @name: HttpVertxApp
 * @description: TODO
 * @date 2023/12/19
 */
public class HttpVertxServer extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.route("/").handler(routingContext -> {
            routingContext.response()
                    .putHeader("content-type", "application/json")
                    .end("{\"code\":200,\"message\":\"root path\"}");
        });

        router.get("/health").handler(routingContext -> {
            routingContext.response().putHeader("content-type", "application/json")
                    .end("{\"code\":200}");
        });

        vertx.createHttpServer().requestHandler(router).listen(8090, listenHander());
    }

    public Handler<AsyncResult<HttpServer>> listenHander() {
       return listenResult -> {
           if (listenResult.failed()) {
               System.out.println("Could not start HTTP server");
               listenResult.cause().printStackTrace();
               System.exit(0);
           } else {
               System.out.println("Server started");
           }
       };
    }

}
