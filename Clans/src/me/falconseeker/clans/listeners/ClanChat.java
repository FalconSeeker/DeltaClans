package me.falconseeker.clans.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import me.falconseeker.clans.Clans;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;

public class ClanChat implements Listener {

	private ClansManager clanManager;
	private Clans main;
	private Methods methods;
	
	public ClanChat(Clans main) {
		this.main = main;
		this.methods = main.getMethods();
		this.clanManager = main.getClanManager();
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	//NameTags
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Scoreboard sb = main.getScoreboard();
		p.setScoreboard(sb);
		if (clanManager.getClan(p) == null) return;
		Clan clan = clanManager.getClan(p);

		drawBoard(p, clan);
	}
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		if (main.getConfig().getStringList("blocked-worlds") == null) return;
		if (main.getConfig().getStringList("blocked-worlds").contains(p.getWorld().getName())) {
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
	}
	@EventHandler
	public void onchat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if (clanManager.getClan(p) == null) return;
		
		Clan clan = clanManager.getClan(p);
		if (methods.getDisabled().contains(p)) {
			p.sendMessage(ChatColor.RED + "You are muted by this clan!");
			return;
		}
		if (!clan.getChat(p)) return;
		
		e.setCancelled(true);
		
			for (UUID pl : clan.getPlayers()) {
				if (Bukkit.getPlayer(pl) == null) continue;
				if (Bukkit.getPlayer(pl).isOnline()) {
				Bukkit.getPlayer(pl).sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Clan Chat" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_AQUA + p.getName() + ChatColor.GRAY + " : " + e.getMessage());
				}
			}
	}
	/**
	 * 
	 * @param p - player to receive the packets
	 * @param player - player who the placeholders are set to
	 */
    public void drawBoard(Player p, Clan clan) {
    	ScoreboardManager manager = Bukkit.getScoreboardManager();
    	Scoreboard board = manager.getNewScoreboard();

    	Objective objective = board.registerNewObjective("showhealth", "health"); //Needed for older version support
    	objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
    	objective.setDisplayName(ChatColor.DARK_AQUA + clan.getName() + " " + clan.getChatLevel());

    	p.setScoreboard(board);

    }
}