package com.therealspoljo.antiautosoup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.therealspoljo.antiautosoup.listeners.EntityDamage;
import com.therealspoljo.antiautosoup.listeners.EntityDamageByEntity;
import com.therealspoljo.antiautosoup.listeners.InventoryClick;
import com.therealspoljo.antiautosoup.utilities.Violation;

public class Main extends JavaPlugin {

    private static Main instance;

    private Map<UUID, Long> lastAttacked = new HashMap<>();
    public Map<UUID, Violation> violations = new HashMap<>();

    public void onEnable() {
	instance = this;

	registerListeners();
    }

    public void onDisable() {
	instance = null;
    }

    public static Main getInstance() {
	return instance;
    }

    private void registerListeners() {
	PluginManager manager = Bukkit.getPluginManager();

	manager.registerEvents(new EntityDamageByEntity(), this);
	manager.registerEvents(new EntityDamage(), this);
	manager.registerEvents(new InventoryClick(), this);
    }

    public long getLastAttackTime(UUID uuid) {
	if (!this.lastAttacked.containsKey(uuid)) {
	    this.lastAttacked.put(uuid, System.currentTimeMillis());
	}

	return this.lastAttacked.get(uuid);
    }

    public void setLastAttackTime(UUID uuid) {
	this.lastAttacked.put(uuid, System.currentTimeMillis());
    }

    public int raiseViolationLevel(UUID uuid) {
	Violation violation = getViolation(uuid);

	violation.raiseViolationLevel();
	this.violations.put(uuid, violation);

	return violation.getViolationLevel();
    }

    public Violation getViolation(UUID uuid) {
	if (!this.violations.containsKey(uuid)) {
	    this.violations.put(uuid, new Violation());
	}

	return (Violation) this.violations.get(uuid);
    }
}
