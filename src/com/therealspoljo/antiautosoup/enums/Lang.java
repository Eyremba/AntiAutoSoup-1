package com.therealspoljo.antiautosoup.enums;

import org.bukkit.command.CommandSender;

import com.therealspoljo.antiautosoup.Main;
import com.therealspoljo.antiautosoup.utilities.Utils;

public enum Lang {

    TITLE("title", "&4[&6AAS&4]&r "),
    NO_PERMISSION("no-permission", "&cYou don't have permission to perform this action."),
    CONFIGS_RELOADED("configs-reloaded", "&7Configs have been reloaded."),
    NOTIFICATION("notification", "&e%name &7might be using AutoSoup hack! (diff=&e%diff&7) VL: &e%vLevel");

    private String message;

    Lang(String path, String defaultMessage) {
	this.message = Main.getInstance().getLangConfig().getString(path, defaultMessage);
    }

    public String getMessage() {
	return message;
    }

    @Override
    public String toString() {
	return Utils.colorize(TITLE.getMessage()) + Utils.colorize(getMessage());
    }

    public void send(CommandSender sender) {
	sender.sendMessage(this.toString());
    }
}