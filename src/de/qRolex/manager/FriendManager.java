package de.qRolex.manager;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.qRolex.main;
import de.qRolex.yaml.YAML_class;

public class FriendManager {
	
	public static boolean hasFriends(Player asker) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.isSet("friends."+asker.getUniqueId().toString()+".friends");
	}
	
	public static boolean hasRequests(Player asker) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.isSet("friends."+asker.getUniqueId().toString()+".receive");
	}
	
	public static boolean isAddedFriend(Player adder, OfflinePlayer target) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.isSet("friends."+adder.getUniqueId().toString()+".add."+target.getUniqueId().toString());
	}
	
	public static boolean isRAddedFriend(OfflinePlayer adder, Player target) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.isSet("friends."+adder.getUniqueId().toString()+".add."+target.getUniqueId().toString());
	}
	
	public static boolean isFriend(Player asker, OfflinePlayer target) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.isSet("friends."+asker.getUniqueId().toString()+".friends."+target.getUniqueId().toString());
	}
	
	public static String getFriendNameByUUID(Player p,String uuid) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.getString("friends."+p.getUniqueId().toString()+".friends."+uuid+".name");
	}
	
	public static String getFriendCustomNameByUUID(Player p,String uuid) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.getString("friends."+p.getUniqueId().toString()+".friends."+uuid+".CustomName");
	}
	
	public static void setOneSideOfFriend(Player adder, OfflinePlayer target) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		
		if(!isAddedFriend(adder, target)) {
			cfg.set("friends."+adder.getUniqueId().toString()+".name", adder.getName());
			cfg.set("friends."+adder.getUniqueId().toString()+".status", adder.isOnline());
			cfg.set("friends."+adder.getUniqueId().toString()+".add."+target.getUniqueId().toString()+".name" ,target.getName());
			cfg.set("friends."+adder.getUniqueId().toString()+".add."+target.getUniqueId().toString()+".status" ,target.isOnline());
			
			cfg.set("friends."+target.getUniqueId().toString()+".name", target.getName());
			cfg.set("friends."+target.getUniqueId().toString()+".status", target.isOnline());
			cfg.set("friends."+target.getUniqueId().toString()+".receive."+adder.getUniqueId().toString()+".name", adder.getName());
			cfg.set("friends."+target.getUniqueId().toString()+".receive."+adder.getUniqueId().toString()+".status", adder.isOnline());
			
			try {
				cfg.save(YAML_class.getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			adder.sendMessage(main.prefix+"Du hast den Spieler geaddet");
		}else {
			Bukkit.getServer().broadcastMessage("§4FEHLER!");
		}
	}
	
	public static void setFriends(OfflinePlayer p, OfflinePlayer adder) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		cfg.set("friends."+adder.getUniqueId().toString()+".add."+p.getUniqueId().toString() ,null);
		cfg.set("friends."+p.getUniqueId().toString()+".receive."+adder.getUniqueId().toString(), null);
			
		cfg.set("friends."+adder.getUniqueId().toString()+".friends."+p.getUniqueId().toString()+".name", p.getName());
		cfg.set("friends."+p.getUniqueId().toString()+".friends."+adder.getUniqueId().toString()+".name", adder.getName());
		cfg.set("friends."+adder.getUniqueId().toString()+".friends."+p.getUniqueId().toString()+".CustomName", p.getName());
		cfg.set("friends."+p.getUniqueId().toString()+".friends."+adder.getUniqueId().toString()+".CustomName", adder.getName());
			
		try {
			cfg.save(YAML_class.getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void declineFriend(OfflinePlayer adder, Player p) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		cfg.set("friends."+adder.getUniqueId().toString()+".add."+p.getUniqueId().toString() ,null);
		cfg.set("friends."+p.getUniqueId().toString()+".receive."+adder.getUniqueId().toString(), null);
		try {
			cfg.save(YAML_class.getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void deleteFriend(Player p, OfflinePlayer target) {
		if(isFriend(p, target)) {
			FileConfiguration cfg = YAML_class.getFileConfiguration();
			cfg.set("friends."+p.getUniqueId().toString()+".friends."+target.getUniqueId().toString(), null);
			cfg.set("friends."+target.getUniqueId().toString()+".friends."+p.getUniqueId().toString(), null);
			
			
			try {
				cfg.save(YAML_class.getFile());
				p.sendMessage(main.prefix+"§cDu hast §6"+target.getName()+" §caus deiner FL entfernt!");
				p.closeInventory();
				if(!(Bukkit.getPlayer(target.getName()) == null)) {
					Bukkit.getPlayer(target.getName()).closeInventory();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				for(Player ps : Bukkit.getOnlinePlayers()) {
					if(ps.isOp()) {
						ps.sendMessage("");
						ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						ps.sendMessage("§4§lBei §r§c"+target+"§4§l ist ein Fehler aufgetreten");
						ps.sendMessage(e.getMessage());
						ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						ps.sendMessage("");
					}
				}
				e.printStackTrace();
			}
		}else {
			p.sendMessage(main.prefixA+"Ihr seid nicht befreundet!");
		}
	}
}
