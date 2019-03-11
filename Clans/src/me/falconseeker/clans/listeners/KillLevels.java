package me.falconseeker.clans.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;
import net.md_5.bungee.api.ChatColor;

public class KillLevels implements Listener {

	private ClansManager clanManager;
	
	public KillLevels(Clans main) {
		this.clanManager = main.getClanManager();
		
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onKill(EntityDeathEvent e) {
		Entity killed = e.getEntity();
		if (!(killed instanceof Player)) return;
		Player p = (Player)killed;
		if (p.getKiller() == null) return;
		if (!(p.getKiller() instanceof Player)) return;
		Player killer = p.getKiller();
		if (clanManager.getClan(killer) == null) return;
		Clan clan = clanManager.getClan(killer);
		clan.addKill();
		if (clan.getKills() >= (1 + clan.getIntLevel() *4)) {
			clan.setLevel(clan.getIntLevel() + 1);
			for (UUID uuid : clan.getPlayers()) {
				Player PL = Bukkit.getPlayer(uuid);
				if (PL.isOnline()) {
					PL.sendMessage(ChatColor.GREEN + "Clan has leveled up!");
					PL.sendMessage(ChatColor.GOLD + "+" + String.valueOf(1 + clan.getKills() * 4));

				}
			}
		}
	}
	
}
