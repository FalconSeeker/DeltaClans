package me.falconseeker.clans.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.command.Command;
 
public interface CommandInterface  {
 
    public boolean onCommand(Player p, Command cmd, String commandLabel, String[] args);
 
}
