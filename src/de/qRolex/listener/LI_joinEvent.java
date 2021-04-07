package de.qRolex.listener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.qRolex.manager.FriendManager;
import de.qRolex.manager.InventoryManager;

public class LI_joinEvent implements Listener {

	@EventHandler
	public void onInvClickEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage("");
		if(FriendManager.hasFriends(p)) {
			ArrayList<String> uuids = InventoryManager.getFriendUUIDS(p);
			for(int i = 0; i < uuids.size();i++) {
				if(!(Bukkit.getPlayer(UUID.fromString(uuids.get(i))) == null)) {
					Player targ = Bukkit.getPlayer(UUID.fromString(uuids.get(i)));
					targ.sendMessage("§6"+InventoryManager.getOnlineCustomName(p, targ)+" §7ist nun §aonline");
				}
			}
		}
	}
}
