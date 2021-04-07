package de.qRolex.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.qRolex.main;
import de.qRolex.inventories.INV_friends;
import de.qRolex.inventories.INV_requests;
import de.qRolex.inventories.INV_skullClick;
import de.qRolex.yaml.YAML_class;


public class InventoryManager {
	
	public static HashMap<Player,String> nextChatIsNameChange = new HashMap<Player,String>();
	public static HashMap<Player,HashMap<String,String>> newName = new HashMap<Player, HashMap<String,String>>();
	
	public static String getOfflineCustomName(OfflinePlayer target,Player p) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.getString("friends."+p.getUniqueId().toString()+".friends."+target.getUniqueId().toString()+".CustomName");
	}
	
	public static String getOnlineCustomName(Player target,Player p) {
		FileConfiguration cfg = YAML_class.getFileConfiguration();
		return cfg.getString("friends."+p.getUniqueId().toString()+".friends."+target.getUniqueId().toString()+".CustomName");
	}
	
	
	public static ArrayList<String> getFriendUUIDS(Player p) {
		if(FriendManager.hasFriends(p)) {
			FileConfiguration cfg = YAML_class.getFileConfiguration();
			ArrayList<String> friends = new ArrayList<String>();
			
			for(String names : cfg.getConfigurationSection("friends."+p.getUniqueId().toString()+".friends").getKeys(false)) {
				friends.add(names);
			}
			
			return friends;
		}
		return null;
	}
	
	public static ArrayList<String> getRequestUUIDS(Player p) {
		if(FriendManager.hasRequests(p)) {
			FileConfiguration cfg = YAML_class.getFileConfiguration();
			ArrayList<String> friends = new ArrayList<String>();
			
			for(String names : cfg.getConfigurationSection("friends."+p.getUniqueId().toString()+".receive").getKeys(false)) {
				friends.add(names);
			}
			
			return friends;
		}
		return null;
	}
	
	public static Integer getReceiveNumber(Player p) {
		return (getRequestUUIDS(p) != null) ? getRequestUUIDS(p).size() : 0 ;
	}
	
	public static boolean isCustomNameAlreadyInUse(Player p, String name) {
		ArrayList<String> uuids = getFriendUUIDS(p);
		for(int i = 0; i<uuids.size(); i++) {
			if(FriendManager.getFriendCustomNameByUUID(p, uuids.get(i).toString()).equalsIgnoreCase(name)) {
				p.sendMessage(main.prefixA+"Dieser CustomName ist schon vergeben!");
				return true;
			}
		}
		
		return false;
	}
	
	public static void openInventory(Player p) {
		Inventory inv = INV_friends.inv(p);
		
		p.openInventory(inv);
	}
	
	
	public static void openSkullInventory(Player p, String target) {
		Inventory inv = INV_skullClick.skullInv(p, target);
		if(inv.getName().equalsIgnoreCase("§6§lnull")) {
			for(Player ps : Bukkit.getOnlinePlayers()) {
				if(ps.isOp()) {
					ps.sendMessage("");
					ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					ps.sendMessage("§4§lBei §r§c"+target+"§4§l ist ein Fehler aufgetreten");
					ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					ps.sendMessage("");
				}
			}
			return;
		}
		
		p.openInventory(inv);
	}
	
	public static void openRequestInventory(Player p, String target) {
		Inventory inv = INV_requests.inv(p, target);
		if(inv.getName().equalsIgnoreCase("§6§lnull")) {
			for(Player ps : Bukkit.getOnlinePlayers()) {
				if(ps.isOp()) {
					ps.sendMessage("");
					ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					ps.sendMessage("§4§lBei §r§c"+target+"§4§l ist ein Fehler aufgetreten");
					ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					ps.sendMessage("");
				}
			}
			return;
		}
		
		p.openInventory(inv);
	}
	
	public static void sendJoinMessage(Player p) {
		ArrayList<String> friends = InventoryManager.getFriendUUIDS(p);
		for(Player target : Bukkit.getOnlinePlayers()) {
			if(target == null) {return;}
			if(friends.contains(target.getUniqueId().toString())) {
				target.sendMessage("§6"+p.getName()+" §7ist jetzt §aonline.");
			}
		}
	}
	
	public static void sendQuitMessage(Player p) {
		ArrayList<String> friends = InventoryManager.getFriendUUIDS(p);
		for(Player target : Bukkit.getOnlinePlayers()) {
			if(friends.contains(target.getUniqueId().toString())) {
				target.sendMessage("§6"+p.getName()+" §7ist jetzt §coffline.");
			}
		}
	}
	
	public static void changeName(Player p,String old) {
		HashMap<String, String> oldNew = newName.get(p);
		@SuppressWarnings("deprecation")
		OfflinePlayer target = Bukkit.getOfflinePlayer(old);
		
		if(!(target == null)) {
			FileConfiguration cfg = YAML_class.getFileConfiguration();
			cfg.set("friends."+p.getUniqueId().toString()+".friends."+target.getUniqueId().toString()+".CustomName", oldNew.get(old));
			
			try {
				cfg.save(YAML_class.getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			p.sendMessage(main.prefixA+"EIN FEHLER IST AUFGETRETEN!");
			
			for(Player ps : Bukkit.getOnlinePlayers()) {
				if(ps.isOp()) {
					ps.sendMessage("");
					ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					ps.sendMessage("§4§lBei §r§c"+target+"§4§l ist ein Fehler aufgetreten");
					ps.sendMessage("§8§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					ps.sendMessage("");
				}
			}
		}
	}
}
