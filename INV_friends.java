package de.qRolex.inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.qRolex.items.ITEMHOLDER_friends;
import de.qRolex.manager.FriendManager;
import de.qRolex.manager.InventoryManager;

public class INV_friends  {
	
	public static Inventory inv(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 54, "§b§lFriends");
		
		if(FriendManager.hasFriends(p)) {
			ArrayList<String> friends = InventoryManager.getFriendUUIDS(p);
			for(int i = 0; i < friends.size(); i++) {
				String uu = friends.get(i);
				inv.addItem(ITEMHOLDER_friends.skull(uu,p));
				if(i==35) {break;}
			}
		}
		for(int i = 36; i<= 44; i++) {
			inv.setItem(i, ITEMHOLDER_friends.placeholder());
		}
		inv.setItem(45, ITEMHOLDER_friends.friendsCLICK());
		inv.setItem(46, ITEMHOLDER_friends.party());
		
		inv.setItem(49, ITEMHOLDER_friends.requests(p));
		inv.setItem(50, ITEMHOLDER_friends.delete());
		return inv;
		
	}
}
