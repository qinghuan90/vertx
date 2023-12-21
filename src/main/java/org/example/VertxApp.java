package org.example;

import io.vertx.core.Launcher;
import org.example.server.HttpVertxServer;

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
        trayStart();

    }

    public static void trayStart() {
        // 检查是否支持系统托盘
        if (SystemTray.isSupported()) {
            // 获取系统托盘
            SystemTray tray = SystemTray.getSystemTray();

            // 创建弹出菜单
            PopupMenu popupMenu = new PopupMenu();

            // 创建弹出菜单项
            MenuItem showMessageItem = new MenuItem("version");
            MenuItem exitItem = new MenuItem("exit");

            // 添加菜单项到弹出菜单
            popupMenu.add(showMessageItem);
            popupMenu.add(exitItem);

            // 创建托盘图标
            Image trayIconImage = Toolkit.getDefaultToolkit().getImage(VertxApp.class.getClassLoader().getResource("img/vx.png"));
            TrayIcon trayIcon = new TrayIcon(trayIconImage, "vert.x", popupMenu);

            // 添加托盘图标到系统托盘
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            // 添加弹出菜单项的点击事件监听器
            showMessageItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 在这里执行显示提示信息的操作
                    JOptionPane.showMessageDialog(null, "vert.x 1.0");
                }
            });

            // 添加退出菜单项的点击事件监听器
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 在这里执行退出操作
                    System.exit(0);
                }
            });
        } else {
            System.out.println("系统不支持系统托盘");
        }

        // 在这里执行你的应用程序逻辑

        // 例如，模拟一些运行成功的操作
        JOptionPane.showMessageDialog(null, "运行成功！");
    }

}
