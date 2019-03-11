package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.ClansManager;

public class CommandClanList implements CommandInterface {

	private ClansManager clanManager;
	
	public CommandClanList(Clans main) {
		this.clanManager = main.getClanManager();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
					
			if (clanManager.getClan(p) == null) {
				p.sendMessage(ChatColor.RED + "You are not in a clan!");
				return true;
			}
			
			p.sendMessage(color("&7---&9Clan List&8(&7" + String.valueOf(clanManager.getClan(p).getPlayers().size()) + "&8/&71000&8)&7---"));
			
			StringBuilder online = new StringBuilder();
			StringBuilder offline = new StringBuilder();

			clanManager.getClan(p).getPlayers().forEach(uuid -> {
			
			OfflinePlayer pl = Bukkit.getOfflinePlayer(uuid);
			
			if (pl.isOnline()) {
			
				Player player = Bukkit.getPlayer(pl.getName());
				if (clanManager.getClan(player).getOwner().equals(uuid)) {
					online.append(ChatColor.GREEN + " ✪" + player.getName());
					return;
				}
				if (clanManager.getClan(player).getAdmins().contains(uuid)) {
					online.append(ChatColor.GREEN + " ✰" + player.getName());
					return;
				}
				online.append(ChatColor.GREEN + " " + player.getName());
			}
			if (!pl.isOnline()) {
				if (clanManager.getClan(p).getOwner().equals(uuid)) {
					offline.append(ChatColor.RED + " ✪" + pl.getName());
					return;
				}	
				if (clanManager.getClan(p).getAdmins().contains(uuid)) {
					offline.append(ChatColor.RED + " ✰" + pl.getName());
					return;
				}

				offline.append(ChatColor.RED + " " + pl.getName());
				}
			});;
			 
			String allOnline = online.toString().trim();
			String allOffline = offline.toString().trim();
			
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Online" + ChatColor.GRAY + "]");
			p.sendMessage(allOnline);
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Offline" + ChatColor.GRAY + "]");
			p.sendMessage(allOffline);
			return true;
	}

	private String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
