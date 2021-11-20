package com.github.tanghuibo.remotedebug.utils;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.diagnostic.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * NotifyUtils
 *
 * @author tanghuibo
 * @date 2021/11/19 14:56
 */
public class NotifyUtils {

    static Logger log = Logger.getInstance(NotifyUtils.class);

    public static void info(String message) {
        log.info(message);
        Notifications.Bus.notify(new Notification(Notifications.SYSTEM_MESSAGES_GROUP_ID,
                "RemoteDebug 提示",
                message,
                NotificationType.INFORMATION, null));
    }

    public static void error(String message) {
        log.error(message);
        Notifications.Bus.notify(new Notification(Notifications.SYSTEM_MESSAGES_GROUP_ID,
                "RemoteDebug 告警",
                message,
                NotificationType.ERROR, null));

    }

    public static void error(String message, Throwable error) {
        log.error(message, error);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        error.printStackTrace(printStream);
        String data = outputStream.toString(StandardCharsets.UTF_8);
        printStream.close();
        Notifications.Bus.notify(new Notification(Notifications.SYSTEM_MESSAGES_GROUP_ID,
                "RemoteDebug 告警",
                message + ";\n" + data,
                NotificationType.ERROR, null));

    }
}
