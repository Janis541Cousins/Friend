package de.qRolex.items;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.qRolex.manager.InventoryManager;

public class ITEMHOLDER_friends {
	
	@SuppressWarnings("deprecation")
	public static ItemStack party() {
		ItemStack is = new ItemStack(Material.WOOL, 1, DyeColor.MAGENTA.getData());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§5Party");
		List<String> lore = new ArrayList<String>();
		lore.add("§o§7Partymenü");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack friends() {
		ItemStack is = new ItemStack(Material.GOLD_INGOT);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§6Freunde");
		List<String> lore = new ArrayList<String>();
		lore.add("§o§7Freundemenü");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack partyCLICK() {
		ItemStack is = new ItemStack(Material.WOOL, 1, DyeColor.MAGENTA.getData());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§5§lParty");
		List<String> lore = new ArrayList<String>();
		lore.add("§o§7Partymenü");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack friendsCLICK() {
		ItemStack is = new ItemStack(Material.GOLD_INGOT);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§6§lFreunde");
		List<String> lore = new ArrayList<String>();
		lore.add("§o§7Freundemenü");
		im.setLore(lore);
		im.addEnchant(Enchantment.ARROW_DAMAGE, -1, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack delete() {
		ItemStack is = new ItemStack(Material.BARRIER);
		ItemMeta im = is.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§o§7Freunde löschen");
		im.setLore(lore);
		im.setDisplayName("§4Löschen");
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack requests(Player p) {
		ItemStack is = new ItemStack(Material.EMPTY_MAP);
		ItemMeta im = is.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§o§7Freundschaftsanfragen: §r§a"+InventoryManager.getReceiveNumber(p));
		im.setDisplayName("§bFreundschaftsanfragen");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack requestsCLICK(Player p) {
		ItemStack is = new ItemStack(Material.EMPTY_MAP);
		ItemMeta im = is.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§o§7Freundschaftsanfragen: §r§a"+InventoryManager.getReceiveNumber(p));
		im.setDisplayName("§bFreundschaftsanfragen");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addEnchant(Enchantment.ARROW_DAMAGE, -1, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack placeholder() {
		@SuppressWarnings("deprecation")
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§8 ");
		is.setItemMeta(im);
		return is;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack skull(String uuid, Player owner) {	
		if(!(Bukkit.getPlayer(UUID.fromString(uuid)) == null)) {
			Player p = Bukkit.getPlayer(UUID.fromString(uuid));
			ItemStack is = new ItemStack(397, 1, (short) 3);
			SkullMeta im = (SkullMeta) is.getItemMeta();
			List<String> lore = new ArrayList<String>();
			
			lore.add("§aOnline");
			
			
			im.setOwner(p.getName());
//			im.setDisplayName("§6"+p.getName());
			im.setDisplayName("§6"+InventoryManager.getOnlineCustomName(p,owner));
			im.setLore(lore);
			is.setItemMeta(im);
			return is;
		}else {
			ItemStack is = new ItemStack(397, 1, (short) 0);
			SkullMeta im = (SkullMeta) is.getItemMeta();
			OfflinePlayer p = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
			List<String> lore = new ArrayList<String>();
			
			lore.add("§cOffline");
			
//			im.setDisplayName("§6"+p.getName());
			im.setDisplayName("§6"+InventoryManager.getOfflineCustomName(p,owner));
			im.setLore(lore);
			is.setItemMeta(im);
			
			return is;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack back() {
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§cZurück");
		is.setItemMeta(im);
		return is;
	}
}
