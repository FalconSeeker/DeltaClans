package me.falconseeker.clans.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanLeave implements CommandInterface {

	private ClansManager clanManager;
	
	public CommandClanLeave(Clans main) {
		this.clanManager = main.getClanManager();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
					
			if (clanManager.getClan(p) == null) {
				Messages.CLAN_NOT_FOUND.send(p);
				return true;
			}

			clanManager.getClan(p).getPlayers().remove(p.getUniqueId());
			
			Messages.LEAVE_CLAN.send(p);
			return true;
	}

}
