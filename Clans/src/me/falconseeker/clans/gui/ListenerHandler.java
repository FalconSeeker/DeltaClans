package me.falconseeker.clans.gui;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
 
public class ListenerHandler implements Listener
{
 
    private static HashMap<String, ListenerInterface> events = new HashMap<String, ListenerInterface>();
 
    public void register(String name, ListenerInterface event) {
        events.put(name, event);
    }
 
    public boolean exists(String name) {
         return events.containsKey(name);
    }
     public ListenerInterface getExecutor(String name) {
         return events.get(name);
    }
    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {
		
    	if (!e.getInventory().getName().equalsIgnoreCase("clan gui")) return;
    	
		if (e.getCurrentItem() == null) return;
	
		ItemStack i = e.getCurrentItem();
		
		if (!i.hasItemMeta()) return;
		
		ItemMeta meta = i.getItemMeta();
		
		if (meta.getDisplayName() == null) return;
		
		String displayName = ChatColor.stripColor(meta.getDisplayName());
		
		if (exists(displayName)) {
			e.setCancelled(true);
			
			getExecutor(displayName).onClick(e);
		}

    }
}