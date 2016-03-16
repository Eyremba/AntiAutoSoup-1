package com.therealspoljo.antiautosoup.utilities;

import java.util.List;

import com.therealspoljo.antiautosoup.Main;

public class ConfigUtils {

    public static long getCheckTime() {
	return Main.getInstance().getConfig().getLong("check-time", 100);
    }

    public static long getMessageDelay() {
	return Main.getInstance().getConfig().getLong("message-delay", 5);
    }

    public static int getMaxAllowedViolationLevel() {
	return Main.getInstance().getConfig().getInt("maximum-allowed-violation-level", 100);
    }

    public static List<String> getCommandsRun() {
	return Main.getInstance().getConfig().getStringList("commands-to-run");
    }

    public static boolean shouldLogAlways() {
	return Main.getInstance().getConfig().getBoolean("logging.always", false);
    }

    public static boolean isLoggingEnabled() {
	return Main.getInstance().getConfig().getBoolean("logging.enabled", false);
    }

    public static int getLogLevel() {
	return Main.getInstance().getConfig().getInt("logging.at-level", 50);
    }

    public static int getDividingNumber() {
	return Main.getInstance().getConfig().getInt("logging.if-level-divisible-by", 5);
    }
}