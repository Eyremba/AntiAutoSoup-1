package com.therealspoljo.antiautosoup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.therealspoljo.antiautosoup.listeners.EntityDamage;
import com.therealspoljo.antiautosoup.listeners.EntityDamageByEntity;
import com.therealspoljo.antiautosoup.listeners.InventoryClick;
import com.therealspoljo.antiautosoup.utilities.Config;

public class Main extends JavaPlugin {

    private static Main instance;
    
    private Config config;

    public void onEnable() {
	instance = this;

	config = Config.createConfig(this, "config");
	
	registerListeners();
    }

    public void onDisable() {
	config = null;
	
	instance = null;
    }

    public static Main getInstance() {
	return instance;
    }
    
    public Config getConfig() {
	return config;
    }

    private void registerListeners() {
	PluginManager manager = Bukkit.getPluginManager();

	manager.registerEvents(new EntityDamageByEntity(), this);
	manager.registerEvents(new EntityDamage(), this);
	manager.registerEvents(new InventoryClick(), this);
    }
}
