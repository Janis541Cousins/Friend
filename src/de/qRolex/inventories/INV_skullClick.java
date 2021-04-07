package de.qRolex.inventories;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.qRolex.items.ITEMHOLDER_friends;
import de.qRolex.items.ITEMHOLDER_skull;
import de.qRolex.manager.FriendManager;
import de.qRolex.manager.InventoryManager;

public class INV_skullClick {
	
	public static Inventory skullInv(Player p, String target) {
		ArrayList<String> uuids = InventoryManager.getFriendUUIDS(p);
		String uuid = "";
		for(int i = 0; i< uuids.size();i++) {
			if(FriendManager.getFriendCustomNameByUUID(p, uuids.get(i)).equalsIgnoreCase(target)) {
				uuid = uuids.get(i);
				break;
			}
		}
		
		
		
		String name = "";
		if(Bukkit.getPlayer(UUID.fromString(uuid)) == null) {
			OfflinePlayer targ = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
			name = targ.getName();
		}else {
			Player targ = Bukkit.getPlayer(UUID.fromString(uuid));
			name = targ.getName();
		}
		
		if(name == null) {
			p.sendMessage("§4KONTAKTIEREN SIE UMGEHEND EINEN ADMIN");
		}
		
		Inventory inv = Bukkit.getServer().createInventory(null, 27, "§6§l"+name);
		
		
		for(int i = 0; i<= 7; i++) {
			inv.setItem(i, ITEMHOLDER_friends.placeholder());
		}
		for(int i = 18; i<= 26; i++) {
			inv.setItem(i, ITEMHOLDER_friends.placeholder());
		}
		
		inv.setItem(8, ITEMHOLDER_friends.back());
		inv.setItem(11, ITEMHOLDER_skull.name());
		inv.setItem(13, ITEMHOLDER_skull.msg());
		inv.setItem(15, ITEMHOLDER_skull.delete());
		return inv; 
	}
}
