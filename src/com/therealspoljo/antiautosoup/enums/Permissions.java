package com.therealspoljo.antiautosoup.enums;

import org.bukkit.command.CommandSender;

public enum Permissions {

    BYPASS("aas.bypass"),
    NOTIFICATIONS("aas.notifications");

    private String node;

    Permissions(String node) {
	this.node = node;
    }

    public String getNode() {
	return node;
    }

    public static boolean isAllowed(CommandSender sender, Permissions type) {
	return sender.isOp() || sender.hasPermission(type.getNode());
    }
}