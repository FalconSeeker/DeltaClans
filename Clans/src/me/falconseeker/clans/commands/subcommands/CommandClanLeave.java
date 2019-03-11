package me.falconseeker.clans.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.ClanCommandInterface;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanLeave implements ClanCommandInterface {

	private ClansManager clanManager;
	
	public CommandClanLeave(Clans main) {
		this.clanManager = main.getClanManager();
	}
	@Override
	public boolean onCommand(Player p, Clan clan, Command cmd, String commandLabel, String[] args) {

			clanManager.getClan(p).getPlayers().remove(p.getUniqueId());
			
			Messages.LEAVE_CLAN.send(p);
			return true;
	}

}
