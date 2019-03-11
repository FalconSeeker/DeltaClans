package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanAddAdmin implements CommandInterface {

	private ClansManager clanManager;
	
	public CommandClanAddAdmin(Clans main) {
		this.clanManager = main.getClanManager();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			Player p = (Player) sender;
			
			if (args.length < 2) {
				Messages.ARGS.send(p);
				return true;
			}
			if (clanManager.getClan(p) == null) {
				p.sendMessage(ChatColor.RED + "Clan not found.");
				return true;
			}
			if (Bukkit.getPlayer(args[1]) == null) {
				p.sendMessage(ChatColor.RED + "Sweet niblets, we couldn't find this player!");
				return true;
			}
			if (clanManager.getClan(p).getOwner() != p.getUniqueId()) return true;
			clanManager.getClan(p).addAdmin(Bukkit.getPlayer(args[1]).getUniqueId());
			clanManager.getClan(p).getPlayers().forEach(play -> Messages.CLAN_ADD_ADMIN.send(Bukkit.getPlayer(play)));
			return true;
	}

}
