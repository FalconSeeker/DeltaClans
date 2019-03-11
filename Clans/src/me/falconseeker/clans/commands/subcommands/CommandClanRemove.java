package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.ClanCommandInterface;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.listeners.Methods;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanRemove implements ClanCommandInterface {

	private ClansManager clanManager;
	private Methods methods;
	
	public CommandClanRemove(Clans main) {
		this.clanManager = main.getClanManager();
		this.methods = main.getMethods();
	}
	@Override
	public boolean onCommand(Player p, Clan clan, Command cmd, String commandLabel, String[] args) {
			
			if (clan.getOwner() != p.getUniqueId()) {
				Messages.CLAN_NOT_OWNER.send(p);
				return true;
			}
			clanManager.getClan(p).getPlayers().forEach(uuid -> {
				if (methods.getDisabled().contains(Bukkit.getPlayer(uuid))) {
					methods.remove(Bukkit.getPlayer(uuid));
				}
			});
			clanManager.removeClan(clan);
			Messages.CLAN_DELETED.send(p);
			return true;
	}
}
