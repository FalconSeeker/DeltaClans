package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanSetLevel implements CommandInterface {

	//Permission falconseeker.clans.admin
	private ClansManager clanManager;
	
	public CommandClanSetLevel(Clans main) {
		this.clanManager = main.getClanManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			Player p = (Player) sender;
			
			if (args.length < 4) {
				Messages.ARGS.send(p);
				return true;
			}
				if (!p.hasPermission("falconseeker.clans.admin")) {
					Messages.NO_PERMISSION.send(p);
					return true;
				}
				Clan c = null;
				
				if (Bukkit.getPlayer(args[1]) != null) c = clanManager.getClan(Bukkit.getPlayer(args[1]));
				
				if (clanManager.getClan(args[1]) != null) c = clanManager.getClan(args[1]);
				
				if (c == null) {
					Messages.CLAN_NOT_FOUND.send(p);
					return true;
				}
				Messages.SETLEVEL.send(p);
				c.setLevel(Integer.valueOf(args[2]));
				return true;
		}
}
