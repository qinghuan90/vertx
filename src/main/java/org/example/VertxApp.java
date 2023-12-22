package org.example;

import io.vertx.core.Launcher;
import org.example.server.HttpVertxServer;
import org.example.tool.TrayTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Hello world!
 *
 */
public class VertxApp
{
    public static void main( String[] args )
    {

        Launcher.executeCommand("run", HttpVertxServer.class.getName());

        TrayTool.trayStart();

    }



}
