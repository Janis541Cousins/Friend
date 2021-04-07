package de.qRolex.inventories;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.qRolex.items.ITEMHOLDER_friends;

public class INV_requests {
	public static Inventory inv(Player p, String target) {
		Inventory inv = Bukkit.getServer().createInventory(null, 54, "§b§lFreundschaftsanfragen");
		
		for(int i = 36; i<= 44; i++) {
			inv.setItem(i, ITEMHOLDER_friends.placeholder());
		}
		inv.setItem(45, ITEMHOLDER_friends.friends());
		inv.setItem(46, ITEMHOLDER_friends.party());
		
		inv.setItem(49, ITEMHOLDER_friends.requestsCLICK(p));
		inv.setItem(50, ITEMHOLDER_friends.delete());
		return inv;
	}
}
