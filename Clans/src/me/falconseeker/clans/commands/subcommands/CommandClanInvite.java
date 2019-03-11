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

public class CommandClanInvite implements CommandInterface {

	private ClansManager clanManager;
	
	public CommandClanInvite(Clans main) {
		this.clanManager = main.getClanManager();
	}
	@Override
	public boolean onCommand(Player p, Command cmd, String commandLabel, String[] args) {
			
			if (clanManager.getClan(p) == null) {
				p.sendMessage(ChatColor.RED + "You are not in a clan!");
				return true;
			}
			if (Bukkit.getPlayer(args[1]) == null) {
				p.sendMessage(ChatColor.RED + "Player not found.");
				return true;
			}
			Player target = Bukkit.getPlayer(args[1]);
			clanManager.getClan(p).invitePlayer(target);
			Messages.CLAN_INVITE.send(p);
			return true;
	}

}
