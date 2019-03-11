package me.falconseeker.clans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.utils.Hologram;

public class Holograms implements Listener {

	private Clans main;
	
	public Holograms(Clans main) {
		this.main = main;
		
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onHoloJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (main.getConfig().get("holo-location") == null) return;
		if (main.getClanManager().getClan(p) == null) {
			Hologram holo = new Hologram((Location) main.getConfig().get("holo-location"), color("&8&k::&9Clan Statistics&8&k::"), " ", color("&7Clan Owner &8» &9No Clan"), color("&7Clan Name &8» &9No Clan"), color("&7Clan Tag &8» &9No Clan"), color("&7Clan Level &8» &9No Clan"), color("&7Players In Clan &8» &9No Clan"), " ", color("&7Clan Level &8»&f Level 0"),  color("&ePlay.SymbioticPvP.Net"));
			holo.displayTo(p);	
			return;
		}
		Hologram holo = new Hologram((Location) main.getConfig().get("holo-location"), color("&8&k::&9Clan Statistics&8&k::"), " ", color("&7Clan Owner &8» &9" + Bukkit.getPlayer(main.getClanManager().getClan(p).getOwner()).getName()), color("&7Clan Name &8» &9" + main.getClanManager().getClan(p).getName()), color("&7Clan Tag &8» &9" + ChatColor.stripColor(main.getClanManager().getClan(p).getTag())), color("&7Clan Level &8» &9" + String.valueOf(main.getClanManager().getClan(p).getIntLevel())), color("&7Players In Clan &8» &9" + String.valueOf(main.getClanManager().getClan(p).getPlayers().size())), " ", color("&7Clan Level &8»&f " + main.getClanManager().getClan(p).getChatLevel()),  color("&ePlay.SymbioticPvP.Net"));
		holo.displayTo(p);
	}
	private String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}

