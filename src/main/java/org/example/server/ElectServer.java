package org.example.server;/**
 * @fileName ElectServer
 * @author pengfei
 * @date 2024/6/7
 * @description ElectServer
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import org.example.enums.ConfigKey;
import org.example.tool.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pengfei
 * @date 2024/6/7
 * @description ElectServer
 */
public class ElectServer extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(ElectServer.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        // 配置 NetServerOptions
        int port = (int) EnvConfig.getConfig(ConfigKey.ELECT_PORT.getKey());
        NetServerOptions options = new NetServerOptions()
                .setPort(port) // 设置监听端口
                .setHost("0.0.0.0"); // 设置监听地址

        // 创建 NetServer
        NetServer server = vertx.createNetServer(options);

        // 处理新连接
        server.connectHandler(socket -> {
            handleNewClient(socket);
        });

        // 启动服务器并监听端口
        server.listen(result -> {
            if (result.succeeded()) {
                logger.info("TCP server is now listening on port " + server.actualPort());
            } else {
                logger.error("Failed to bind!", result.cause());
            }
        });


    }


    private void handleNewClient(NetSocket socket) {
        logger.info("New client connected from " + socket.remoteAddress());

        // 处理从客户端接收到的数据
        socket.handler(buffer -> {
            String message = buffer.toString();
            logger.info("Received message: " + message);

            // Echo back the received data
            socket.write("Echo: " + message);
        });

        // 处理客户端关闭连接
        socket.closeHandler(v -> {
            logger.info("Client disconnected from " + socket.remoteAddress());
        });

        // 处理异常
        socket.exceptionHandler(e -> {
            logger.error("Socket error", e);
        });
    }
}
