package org.example;

import io.vertx.core.Launcher;
import org.example.server.HttpVertxServer;

/**
 * Hello world!
 *
 */
public class VertxApp
{
    public static void main( String[] args )
    {
        Launcher.executeCommand("run", HttpVertxServer.class.getName());
    }
}
