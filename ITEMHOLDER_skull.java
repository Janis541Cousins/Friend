package de.qRolex.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ITEMHOLDER_skull {
	
	public static ItemStack delete() {
		ItemStack is = new ItemStack(Material.BARRIER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("�cL�schen");
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack msg() {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("�bNachricht schreiben");
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack name() {
		ItemStack is = new ItemStack(Material.NAME_TAG);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("�aNamen �ndern");
		is.setItemMeta(im);
		return is;
	}
}
