package me.falconseeker.clans.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.utils.Messages;
 
public class CommandHandler implements CommandExecutor
{
 
    private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();
 
    public void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);
    }
 
    public boolean exists(String name) {
         return commands.containsKey(name.toLowerCase());
    }
     public CommandInterface getExecutor(String name) {
         return commands.get(name.toLowerCase());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
         if(sender instanceof Player) {
            if(args.length == 0) {
                getExecutor("clan").onCommand(sender, cmd, commandLabel, args);
                return true;
            }
             if(args.length > 0) {
                 if(exists(args[0])){
                     getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
                    return true;
                } else {
                     Messages.NOT_COMMAND.send(sender);
                    return true;
                }
            }
        } else {
            Messages.NOT_PLAYER.send(sender);
            return true;
        }
        return false;
    }
 
}
 
