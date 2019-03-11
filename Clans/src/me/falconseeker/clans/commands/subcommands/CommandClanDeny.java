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

public class CommandClanDeny implements CommandInterface {

	private ClansManager clanManager;
	
	public CommandClanDeny(Clans main) {
		this.clanManager = main.getClanManager();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			Player p = (Player) sender;
			
			if (args.length < 2) {
				Messages.ARGS.send(p);
			}
			if (clanManager.getClan(args[1]) == null) {
				p.sendMessage(ChatColor.RED + "Clan not found.");
				return true;
			}
			Bukkit.getPlayer(clanManager.getClan(args[1]).getOwner()).sendMessage(ChatColor.RED + "Invite denied by " + p.getName());
			return true;
	}
	

}
