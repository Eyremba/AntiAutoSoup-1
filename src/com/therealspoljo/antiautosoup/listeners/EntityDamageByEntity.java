package com.therealspoljo.antiautosoup.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.therealspoljo.antiautosoup.Main;

public class EntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
	if (!(event.getDamager() instanceof Player)) {
	    return;
	}

	Main.getInstance().setLastAttackTime(((Player) event.getDamager()).getUniqueId());
    }
}
