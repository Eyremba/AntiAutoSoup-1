package com.therealspoljo.antiautosoup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.therealspoljo.antiautosoup.Main;
import com.therealspoljo.antiautosoup.enums.Lang;
import com.therealspoljo.antiautosoup.enums.Permissions;
import com.therealspoljo.antiautosoup.utilities.TempStorage;

public class AntiAutoSoup implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	if (!Permissions.isAllowed(sender, Permissions.ADMIN)) {
	    Lang.NO_PERMISSION.send(sender);
	    return true;
	}

	if (args.length == 1) {
	    switch (args[0].toLowerCase()) {
	    case "reload":
	    case "rl": {
		Main.getInstance().getConfig().reload();
		Main.getInstance().getLangConfig().reload();
		Lang.CONFIGS_RELOADED.send(sender);
		return true;
	    }
	    }

	    sender.sendMessage("§cUsage: §7" + command.getUsage().replaceAll("<command>", commandLabel));
	    return true;
	} else if (args.length == 2) {
	    switch (args[0].toLowerCase()) {
	    case "check":
	    case "c": {
		Player target = Main.getInstance().getServer().getPlayer(args[1]);

		if (target == null) {
		    String message = Lang.PLAYER_OFFLINE.toString();
		    message = message.replaceAll("%name", args[1]);

		    sender.sendMessage(message);
		    return true;
		}

		String message = Lang.LEVEL_CHECK.toString();

		message = message.replaceAll("%name", target.getName());
		message = message.replaceAll("%vLevel", String.valueOf(TempStorage.getViolation(target.getUniqueId()).getViolationLevel()));

		sender.sendMessage(message);
		return true;
	    }
	    }

	    sender.sendMessage("§cUsage: §7" + command.getUsage().replaceAll("<command>", commandLabel));
	    return true;
	}

	sender.sendMessage("§cUsage: §7" + command.getUsage().replaceAll("<command>", commandLabel));
	return true;
    }
}