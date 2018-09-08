package br.com.configmanager;

import java.lang.reflect.Field;

import org.bukkit.plugin.Plugin;

import br.com.configmanager.interfaces.Config;

/**
 * Load all patches from config.yml to cache in this class using Annotation
 */
public class ConfigLoader{

	@Config(patch = "messages.enabled")
	public static String enabled;
	
	@Config(patch = "messages.disabled")
	public static String disabled;
	
	@Config(patch = "numbers.number")
	public static Integer number;
	
	private Plugin plugin;
	public ConfigLoader(Plugin plugin) {
		this.plugin = plugin;
		loadConfig();
	}
	
	/**
	 * This method is responsible for load every patches
	 */
	private void loadConfig() {
		for(Field field : this.getClass().getFields()) {
			if(field.isAnnotationPresent(Config.class)) {
				Config message = field.getAnnotation(Config.class);
				try {
					Object patch = this.plugin.getConfig().get(message.patch());
					if(patch instanceof String) {
						patch = ((String) patch).replace("&", "§");
					}
					field.setAccessible(true);
					field.set(this, patch);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
