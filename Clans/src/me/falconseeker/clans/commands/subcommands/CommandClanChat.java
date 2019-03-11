package me.falconseeker.clans.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.managements.ClansManager;
import net.md_5.bungee.api.ChatColor;

public class CommandClanChat implements CommandExecutor {
	
	private ClansManager clanManager;
	private Clans main;
	
	public CommandClanChat(Clans main) {
		this.main = main;
		this.clanManager = main.getClanManager();
		
		main.getCommand("clanchat").setExecutor(this);
		main.getCommand("cc").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getLabel().equalsIgnoreCase("clanchat") || cmd.getLabel().equalsIgnoreCase("cc")) {
			if (main.getMethods().chatdisabled.contains(p)) {
				p.sendMessage(ChatColor.RED + "You are muted by this clan!");
				return true;
			}
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED + "Too few arguments. Try '/clanchat <enabled/disabled>'");
				return true;
			}
			if (args.length >= 1) {
				if (clanManager.getClan(p) == null) {
					p.sendMessage(ChatColor.RED + "You are not in a clan!");
					return true;
				}
				if (args[0].equalsIgnoreCase("enable")) {
					clanManager.getClan(p).setChat(p, true);
					p.sendMessage(ChatColor.GREEN + "Clan chat enabled!");
					return true;
				}
				if (args[0].equalsIgnoreCase("disable")) {
					clanManager.getClan(p).setChat(p, false);
					p.sendMessage(ChatColor.GREEN + "Clan chat disabled!");
					return true;
				}
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < args.length; i++){
				sb.append(args[i]).append(" ");
				}
				 
				String message = sb.toString().trim();
			        clanManager.getClan(p).getPlayers().forEach(uuid -> Bukkit.getPlayer(uuid).sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Clan Chat" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_AQUA + p.getName() + ChatColor.GRAY + " : " + message));
				return true;
			}
		}
		return false;
	}

}
