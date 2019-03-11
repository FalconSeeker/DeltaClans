package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.listeners.Methods;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanRemove implements CommandInterface {

	private ClansManager clanManager;
	private Methods methods;
	
	public CommandClanRemove(Clans main) {
		this.clanManager = main.getClanManager();
		this.methods = main.getMethods();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender; //Note: it is safe to cast as player because we already checked
		
			if (clanManager.getClan(p) == null) {
				Messages.CLAN_NOT_FOUND.send(p);
				return true;
			}
			if (clanManager.getClan(p).getOwner() != p.getUniqueId()) {
				Messages.CLAN_NOT_OWNER.send(p);
				return true;
			}
			clanManager.getClan(p).getPlayers().forEach(uuid -> {
				if (methods.chatdisabled.contains(Bukkit.getPlayer(uuid))) {
					methods.chatdisabled.remove(Bukkit.getPlayer(uuid));
				}
			});
			clanManager.removeClan(clanManager.getClan(p));
			Messages.CLAN_DELETED.send(p);
			return true;
	}
}
