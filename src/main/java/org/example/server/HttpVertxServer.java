package org.example.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import org.example.enums.ConfigKey;
import org.example.tool.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author qingh
 * @name: HttpVertxApp
 * @description: TODO
 * @date 2023/12/19
 */
public class HttpVertxServer extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(HttpVertxServer.class);

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

        int port = (int) EnvConfig.getConfig(ConfigKey.SERVER_PORT.getKey());
        vertx.createHttpServer().requestHandler(router).listen(port, listenHander(port));
    }

    public Handler<AsyncResult<HttpServer>> listenHander(int port){
       return listenResult -> {
           if (listenResult.failed()) {
               logger.error("Could not start HTTP server");
               listenResult.cause().printStackTrace();
               System.exit(0);
           } else {
               logger.info("Server started int {}", port);
           }
       };
    }

}
