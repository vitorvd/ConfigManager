package br.com.configmanager;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
		saveDefaultConfig();
		new ConfigLoader(this);
		Bukkit.getConsoleSender().sendMessage(ConfigLoader.enabled);
		Bukkit.getConsoleSender().sendMessage("" + ConfigLoader.number);
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ConfigLoader.disabled);
	}
	
	@EventHandler
	private void onJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage(ConfigLoader.enabled);
		event.getPlayer().sendMessage(ConfigLoader.disabled);
		event.getPlayer().sendMessage("" + ConfigLoader.number);
	}
	
}
