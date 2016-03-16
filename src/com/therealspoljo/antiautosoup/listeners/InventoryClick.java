package com.therealspoljo.antiautosoup.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.therealspoljo.antiautosoup.utilities.ConfigUtils;
import com.therealspoljo.antiautosoup.utilities.TempStorage;
import com.therealspoljo.antiautosoup.utilities.Utils;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
	if (!(event.getWhoClicked() instanceof Player)) {
	    return;
	}

	if (event.isCancelled()) {
	    return;
	}

	if (event.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
	    return;
	}

	Player player = (Player) event.getWhoClicked();

	if (player.hasPermission("aas.bypass")) {
	    return;
	}

	if (event.getSlot() == -1) {
	    return;
	}

	long calculatedTime = System.currentTimeMillis() - TempStorage.getLastAttackTime(player.getUniqueId());
	int bowls = 0;

	for (ItemStack itemStack : player.getInventory().getContents()) {
	    if ((itemStack != null) && (itemStack.getType() == Material.BOWL)) {
		bowls += itemStack.getAmount();
	    }
	}

	if (calculatedTime > ConfigUtils.getCheckTime()) {
	    return;
	}

	if (bowls < 5) {
	    return;
	}

	int level = TempStorage.raiseViolationLevel(player.getUniqueId());

	if (TempStorage.getViolation(player.getUniqueId()).shouldNotify()) {
	    Utils.notifyStaff(player, level, calculatedTime);
	}

	Utils.performAction(player);

	if (!ConfigUtils.isLoggingEnabled()) {
	    return;
	}

	if (ConfigUtils.shouldLogAlways()) {
	    if (level % ConfigUtils.getDividingNumber() == 0) {
		Utils.performLogging(player.getName() + " reached level " + level + ", diff=" + calculatedTime);
	    }
	} else if (level == ConfigUtils.getLogLevel()) {
	    Utils.performLogging(player.getName() + " reached level " + level + ", diff=" + calculatedTime);
	}
    }
}
