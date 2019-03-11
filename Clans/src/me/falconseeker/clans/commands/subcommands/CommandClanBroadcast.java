package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class CommandClanBroadcast implements CommandInterface {
//not registered
	private ClansManager clanManager;
	
	public CommandClanBroadcast(Clans main) {
		this.clanManager = main.getClanManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;
		
		if (args.length < 2) {
			Messages.ARGS.send(p);
		}
			StringBuilder broadcastedMessage = new StringBuilder(ChatColor.RED + "[BROADCAST]");
			
			for (int i = 1; i < args.length - 1; i++) {
				broadcastedMessage.append(" " + args[i]);
			}
			clanManager.getClan(p).getPlayers().forEach(pl -> Bukkit.getPlayer(pl).sendMessage(broadcastedMessage.toString()));
			return true;
	}

}
