package de.qRolex.commands;


import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.qRolex.main;
import de.qRolex.manager.FriendManager;
import de.qRolex.manager.InventoryManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;


public class CMD_friend implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		if(cs instanceof Player) {
			Player p = (Player) cs;
			if(args.length == 0) {
				InventoryManager.openInventory(p);
			}else
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("list")) {
					InventoryManager.openInventory(p);
				}
			}else
			if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("add")) {
					
					if(Bukkit.getOfflinePlayer(args[1]) == null) {p.sendMessage(main.prefixA+"Dieser Spieler war noch nie aufm Server!");return false;}
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
					
					if(p.getName().equalsIgnoreCase(target.getName())) {p.sendMessage(main.prefixA+"Du kannst dich nicht selber adden!");return false;}
					
					if(!FriendManager.isFriend(p, target)) {
						if(!FriendManager.isAddedFriend(p, target)) {
							if(FriendManager.isRAddedFriend(target, p)) {p.performCommand("friend accept "+target.getName());return false;}
							
							FriendManager.setOneSideOfFriend(p, target);
							if(target.isOnline()) {
								Player target1 = Bukkit.getPlayer(target.getName());
								target1.sendMessage("§8<");
								target1.sendMessage("§6"+p.getName()+" §ahat dich geaddet!");
								
								IChatBaseComponent comp = ChatSerializer
										.a("[{\"text\":\"§6[§aAnnehmen§6] \",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§aAnnehmen\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/friend accept "+p.getName()+"\"}},{\"text\":\" §6[§cAblehnen§6] \",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§cAblehnen\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/friend decline "+p.getName()+"\"}}]");
								PacketPlayOutChat chat = new PacketPlayOutChat(comp);
								((CraftPlayer)target1).getHandle().playerConnection.sendPacket(chat);
							}
						}else {
							p.sendMessage(main.prefixA+"Du hast ihn schon geaddet!");
						}
					}else {
						p.sendMessage(main.prefixA+"Ihr seid schon befreundet!");
					}
					
				}else if(args[0].equalsIgnoreCase("accept")) {
					
					if(Bukkit.getOfflinePlayer(args[1]) == null) {p.sendMessage(main.prefixA+"Dieser Spieler war noch nie aufm Server!");return false;}
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
					
					if(FriendManager.isRAddedFriend(target, p)) {
						FriendManager.setFriends(p, target);
						p.sendMessage("§8<");
						p.sendMessage(main.prefix+"Du bist nun mit §6"+target.getName()+" §abefreundet!");
						if(target.isOnline()) {
							Bukkit.getPlayer(target.getName()).sendMessage("§8<");
							Bukkit.getPlayer(target.getName()).sendMessage(main.prefix+"Du bist nun mit §6"+p.getName()+"§a befreundet!");
						}
					}else {
						p.sendMessage(main.prefixA+"Der Spieler hat dich nicht geaddet!");
					}
					
				}else if(args[0].equalsIgnoreCase("decline")) {
					
					if(Bukkit.getOfflinePlayer(args[1]) == null) {p.sendMessage(main.prefixA+"Dieser Spieler war noch nie aufm Server!");return false;}
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
					if(FriendManager.isRAddedFriend(target, p)) {
						FriendManager.declineFriend(target, p);
						p.sendMessage("§8<");
						p.sendMessage(main.prefix+"Du hast die Freundschaftsanfrage von §6"+target.getName()+" §aabgelehnt!");
						if(target.isOnline()) {
							Bukkit.getPlayer(target.getName()).sendMessage("§8<");
							Bukkit.getPlayer(target.getName()).sendMessage(main.prefixA+"§6"+p.getName()+"§a hat deine Freundschaftsanfrage abgelehnt!");
						}
					}else {
						p.sendMessage(main.prefixA+"Der Spieler hat dich nicht geaddet!");
					}
				}else if(args[0].equalsIgnoreCase("delete")) {
					
//					DELELTE MUSS NOCH GEMACHT WERDEN
					
					FriendManager.deleteFriend(p, Bukkit.getOfflinePlayer(args[1]));
				}else {
					p.sendMessage(main.prefixB);
				}
			}else {
				p.sendMessage(main.prefixB);
			}
		}
		return false;
	}
}
