package org.example.tool;

import org.example.VertxApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author qingh
 * @name: TrayTool
 * @description: windows系统托盘功能
 * @date 2023/12/21
 */

public class TrayTool {

    private static final Logger logger = LoggerFactory.getLogger(TrayTool.class);
    public static void trayStart() {
        // 检查是否支持系统托盘
        if (SystemTray.isSupported()) {
            // 获取系统托盘
            SystemTray tray = SystemTray.getSystemTray();

            // 创建弹出菜单
            PopupMenu popupMenu = new PopupMenu();

            // 创建弹出菜单项
            MenuItem exitItem = new MenuItem("Exit");

            // 添加菜单项到弹出菜单
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

            // 添加退出菜单项的点击事件监听器
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 在这里执行退出操作
                    System.exit(0);
                }
            });

            // 打印日志
            JOptionPane.showMessageDialog(null, "启动成功！");
        } else {
            logger.warn("系统不支持系统托盘");
        }


    }
}
