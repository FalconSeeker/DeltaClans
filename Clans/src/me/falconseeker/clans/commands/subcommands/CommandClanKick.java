package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.listeners.Methods;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanKick implements CommandInterface {

	private ClansManager clanManager;
	private Methods methods;
	
	public CommandClanKick(Clans main) {
		this.clanManager = main.getClanManager();
		this.methods = main.getMethods();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		
		if (args.length < 2) {
			Messages.ARGS.send(p);
			return true;
		}
			if (clanManager.getClan(p) == null) {
				p.sendMessage(ChatColor.RED + "You are not in a clan!");
				return true;
			}
			Clan clan = clanManager.getClan(p);

			if (Bukkit.getPlayer(args[1]) == null) {
				p.sendMessage(ChatColor.RED + "ERROR 404. Player not found.");
				return true;
			}
			Player newowner = Bukkit.getPlayer(args[1]);
			if (!clan.getPlayers().contains(newowner.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "ERROR 404. Player not in this clan.");
				return true;
			}
			if (!clan.getOwner().equals(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "You seem not to like this player. Mabye ask the owner instead of trying to kick him?");
				return true;
			}
			clan.getPlayers().forEach(player -> { if (Bukkit.getPlayer(player).isOnline()) Messages.CLAN_KICKED.send(Bukkit.getPlayer(player)); });
			clan.getPlayers().remove(newowner.getUniqueId());
			if (clan.getAdmins().contains(p.getUniqueId())) {
				clan.getAdmins().remove(p.getUniqueId());
			}
			if (methods.chatdisabled.contains(newowner)) {
				methods.chatdisabled.remove(newowner);
			}
			return true;
	}

}
