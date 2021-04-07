package de.qRolex.listener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.qRolex.main;
import de.qRolex.manager.FriendManager;
import de.qRolex.manager.InventoryManager;

public class LI_InventoryClick implements Listener{
	
	@EventHandler
	public void onInvClickEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equalsIgnoreCase("§b§lFriends")) {
			if(e.getCurrentItem() == null) {return;}
			Material item = e.getCurrentItem().getType();
			if(item.equals(Material.AIR)) {e.setCancelled(true);return;}
			
			
			if(!item.equals(Material.SKULL_ITEM)) {
				if(item.equals(Material.EMPTY_MAP)) {
//					FRIENDREQUESTS
					InventoryManager.openRequestInventory(p, "");
				}else {
					p.sendMessage(e.getCurrentItem().getItemMeta().getDisplayName());
				}
			}else {
				//SKULL CLICK EVENT
				String[] spl = e.getCurrentItem().getItemMeta().getDisplayName().split("§6");
				InventoryManager.openSkullInventory(p, spl[1]);
			}
		}else
		if(e.getInventory().getName().equalsIgnoreCase("§6§lnull")) {
			e.setCancelled(true);
			p.closeInventory();
			return;
		}else {
			if(!e.getInventory().getName().startsWith("§6§l")) {e.setCancelled(false);return;}
//			UM DEN REALNAME DES SPIELERS HERAUSZUFINDEN
			String[] invName = e.getInventory().getName().split("§6§l");
			String customNameOfPlayer = invName[1];
			ArrayList<String> uuids = InventoryManager.getFriendUUIDS(p);
			String uuid = "";
			
			for(int i = 0; i< uuids.size();i++) {
				if(FriendManager.getFriendNameByUUID(p, uuids.get(i)).equalsIgnoreCase(customNameOfPlayer)) {
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
			
			
//			BEZOGEN AUF DAS ITEM
			if(e.getCurrentItem() == null) {return;}
			Material item = e.getCurrentItem().getType();
			if(item.equals(Material.AIR)) {e.setCancelled(true);return;}
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")) {
				InventoryManager.openInventory(p);
				e.setCancelled(true);
				return;
			}else
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aNamen ändern")) {
				InventoryManager.nextChatIsNameChange.put(p,name);
				p.sendMessage("§8<<");
				p.sendMessage("§aSchreib jetzt den Spitznamen von §6"+name+"§a rein!");
				p.sendMessage("§8<<");
				
				p.closeInventory();
			}else
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bNachricht schreiben")) {
				p.sendMessage(main.prefix+"Mit §6/msg "+name+" <nachricht> §akann man schreiben!");
				p.closeInventory();
//				Baustelle
			}
			else
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cLöschen")) {
				p.performCommand("friend delete "+name);
			}
		}
		e.setCancelled(true);
	}
}
