package me.falconseeker.clans.commands;

import org.bukkit.entity.Player;

import me.falconseeker.clans.managements.Clan;

import org.bukkit.command.Command;
 
public interface ClanCommandInterface  {
 
    public boolean onCommand(Player p, Clan clan, Command cmd, String commandLabel, String[] args);
 
}
