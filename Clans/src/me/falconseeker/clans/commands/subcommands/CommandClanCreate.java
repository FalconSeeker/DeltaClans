package me.falconseeker.clans.commands.subcommands;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;

public class CommandClanCreate implements CommandInterface {

	private ClansManager clanManager;
	private Clans main;
	
	public CommandClanCreate(Clans main) {
		this.clanManager = main.getClanManager();
		this.main = main;
	}
	@Override
	public boolean onCommand(Player p, Command cmd, String commandLabel, String[] args) {
		
		if (args.length < 2) {
			Messages.ARGS.send(p);
			return true;
		}
			if (clanManager.getClan(p) != null) {
				p.sendMessage(ChatColor.RED + "You must first leave your current clan!");
				return true;
			}
			if (main.getConfig().getStringList("blocked-tags").contains(args[1])){
				p.sendMessage(ChatColor.RED + "You will get coal in your stocking for using these words, " + args[1]);
				return true;
			}
			HashSet<UUID> pl = new HashSet<UUID>();
			pl.add(p.getUniqueId());
			clanManager.addClan(new Clan(p.getUniqueId(), args[1].toUpperCase(), args[1].toUpperCase(), pl, null, 0, 0));
			Messages.CLAN_CREATE.send(p);
			return true;
	}

}
