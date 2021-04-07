package de.qRolex.listener;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.qRolex.main;
import de.qRolex.manager.InventoryManager;

public class LI_asyncChat implements Listener {
	
	@EventHandler
	public void onAsyncChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		if(InventoryManager.nextChatIsNameChange.containsKey(p)) {
			if(InventoryManager.isCustomNameAlreadyInUse(p, msg)) {InventoryManager.nextChatIsNameChange.remove(p);e.setCancelled(true);return;}
			
			
			p.sendMessage(main.prefix+"Sein Spitzname ist nun §6"+msg+"§a!");
			HashMap<String,String> changeName = new HashMap<String,String>();
			changeName.put(InventoryManager.nextChatIsNameChange.get(p), msg);
			InventoryManager.newName.put(p, changeName);
			InventoryManager.changeName(p,InventoryManager.nextChatIsNameChange.get(p));
			InventoryManager.nextChatIsNameChange.remove(p);
			e.setCancelled(true);
		}
	}
}