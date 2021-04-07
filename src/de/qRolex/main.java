package de.qRolex;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.qRolex.commands.CMD_friend;
import de.qRolex.commands.CMD_party;
import de.qRolex.listener.LI_InventoryClick;
import de.qRolex.listener.LI_asyncChat;
import de.qRolex.listener.LI_joinEvent;
import de.qRolex.listener.LI_leaveEvent;
import de.qRolex.yaml.YAML_class;

public class main extends JavaPlugin {
	
	YAML_class file = new YAML_class();
	
	public static String prefix ="§6[§3Friend§6] §a";
	public static String prefixA ="§6[§3Friend§6] §4";
	public static String prefixR ="§6[§3Friend§6] §4Du kannst den Befehl nicht ausführen!";
	public static String prefixB ="§6[§3Friend§6] §4Diesen Befehl gibt es nicht!";
	
	public static String prefixP ="§6[§5Party§6] §a";
	public static String prefixAP ="§6[§5Party§6] §4";
	public static String prefixRP ="§6[§5Party§6] §4Du kannst den Befehl nicht ausführen!";
	public static String prefixBP ="§6[§5Party§6] §4Diesen Befehl gibt es nicht!";
	
	public void onEnable() {
		registerListener();
		registerCommands();
		
		file.setStandart();
	}
	
	public void onDisable() {
		
	}
	
	private void registerCommands() {
		getCommand("party").setExecutor(new CMD_party());
		getCommand("p").setExecutor(new CMD_party());
		getCommand("friend").setExecutor(new CMD_friend());
		getCommand("f").setExecutor(new CMD_friend());

	}
	
	private void registerListener() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new LI_joinEvent(), this);
		pm.registerEvents(new LI_leaveEvent(), this);
		pm.registerEvents(new LI_InventoryClick(), this);
		pm.registerEvents(new LI_asyncChat(), this);
	}
}
