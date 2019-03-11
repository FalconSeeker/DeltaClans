package me.falconseeker.clans.commands.subcommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.utils.Messages;

public class CommandBlockedWorlds implements CommandExecutor {

	private Clans main;
	
	public CommandBlockedWorlds(Clans main) {
		this.main = main;
		
		main.getCommand("addblockedworld").setExecutor(this);
		main.getCommand("removeblockedworld").setExecutor(this);

	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) { //Checked for player because this class implements CommandExecutor to handle 2 commands
			sender.sendMessage("You must be a player!");
			return true;
		}
		Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("addblockedworld")) {
				List<String> blocked = main.getConfig().getStringList("blocked-worlds");
				blocked.add(p.getWorld().getName());
				main.getConfig().set("blocked-worlds", blocked); //Did not need to use main.saveConfig() because this method is called on disable
				Messages.BLOCKED_WORLD_ADDED.send(p);
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("removeblockedworld")) {
				List<String> blocked = main.getConfig().getStringList("blocked-worlds");
				blocked.remove(p.getWorld().getName());
				main.getConfig().set("blocked-worlds", blocked);
				Messages.BLOCKED_WORLD_REMOVED.send(p);
				return true;
			}	
		return true;
	}
}
