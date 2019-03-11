package me.falconseeker.clans.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.base.Preconditions;

public class GUI {

	private Player p;
	private Inventory inv;

	public GUI(Player p, Integer invsize, String title) {
		this.p = p;
		this.inv = Bukkit.createInventory(p, invsize, title);
	}

	public void addItem(Material mat, String name, ArrayList<String> lore) {
		Preconditions.checkState(inv != null, "Cannot add items while the inventory is null");
		
		List<String> lore2 = new ArrayList<String>();
		for (String s : lore) {
			lore2.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		ItemStack is = new ItemStack(mat);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		meta.setLore(lore2);
		is.setItemMeta(meta);
		inv.addItem(is);
	}

	public void open() {
		if (inv == null) {
			p.sendMessage("INVENTORY NULL");
			return;
		}
		p.closeInventory();
		p.openInventory(inv);
	}

	public Inventory getInventory() {
		return inv;
	}

	public void setItem(Integer slot, Material mat, String name, ArrayList<String> lore) { //Custom item builder
		Preconditions.checkState(inv != null, "Cannot add items while the inventory is null");
		
		List<String> lore2 = new ArrayList<String>();
		for (String s : lore) {
			lore2.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		ItemStack is = new ItemStack(mat);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		meta.setLore(lore2);
		is.setItemMeta(meta);
		inv.setItem(slot, is);
	}
}
