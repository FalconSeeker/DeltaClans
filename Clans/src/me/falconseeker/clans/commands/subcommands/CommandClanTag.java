package me.falconseeker.clans.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanTag implements CommandInterface {

	private ClansManager clanManager;
	
	public CommandClanTag(Clans main) {
		this.clanManager = main.getClanManager();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		
		if (args.length < 2) {
			Messages.ARGS.send(p);
			return true;
		}
		if (args[0].equalsIgnoreCase("settag")) {
			
			if (clanManager.getClan(p) == null) {
				Messages.CLAN_NOT_FOUND.send(p);
				return true;
			}
			
			Messages.CLAN_TAG_CHANGED.send(p);
			clanManager.getClan(p).setTag(args[1]);
			return true;

		}		return false;
	}

}
