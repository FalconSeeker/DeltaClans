package me.falconseeker.clans.commands.subcommands;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.commands.CommandInterface;
import me.falconseeker.clans.utils.Messages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClanHolo implements CommandInterface {
 
  private Clans main;
  
  public CommandClanHolo(Clans main) {
    this.main = main;
  }
  
  @Override
  public boolean onCommand(Player p, Command cmd, String arg, String[] args) {
	  
      if (!p.hasPermission("falconseeker.clans.admin")) {
        return false;
      }
      Messages.HOLO_CREATE.send(p);
      this.main.getConfig().set("holo-location", p.getLocation().add(0.0D, 1.0D, 0.0D));
      this.main.saveDefaultConfig();
      return true;
  }
}
