package me.falconseeker.clans.utils;

import static org.bukkit.ChatColor.*;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import me.falconseeker.clans.Clans;
public enum Messages {
	
	HELP(GREEN + "Type '/world options' to create and edit worlds! (Plugin made by Falcon_Seeker)"), 
	CLAN_NOT_FOUND(getConfig("Clan-Not-Found")), 
	CLAN_NOT_OWNER(getConfig("Clan-Not-Owner")), 
	CLAN_DELETED(getConfig("Clan-Delete")), 
	LEAVE_CLAN(getConfig("Clan-Leave")), 
	CLAN_CREATE(getConfig("Clan-Create")), 
	CLAN_TAG_CHANGED(getConfig("Clan-Set-Tag")), 
	ARGS(getConfig("TOO-FEW-ARGUMENTS")), 
	CLAN_KICKED(getConfig("Clan-Kicked")), 
	CLAN_TRANSFER_OWNERSHIP(getConfig("Transfer-Owner")), 
	NOT_COMMAND(getConfig("Not-Command")), 
	NOT_PLAYER(getConfig("Not-Player")), 
	CLAN_ADD_ADMIN(getConfig("Set-Admin")), 
	CLAN_INVITE(getConfig("Clan-Invite")), 
	NO_PERMISSION(getConfig("No-Permission")), 
	SETLEVEL(getConfig("Set-Level")), 
	BLOCKED_WORLD_REMOVED(getConfig("Blocked-World-Remove")), 
	BLOCKED_WORLD_ADDED(getConfig("Blocked-World-Add")), 
	HELP_CLAN_INVITE(getConfig("Clan-Invite-Usage")), 
	PLAYER_NOT_FOUND(getConfig("Player-Not-Found")), 
	CLAN_REMOVE_ADMIN(getConfig("Demote-Player")), 
	HOLO_CREATE(getConfig("Create-Hologram"));

	private static String getConfig(String s) {
		FileConfiguration config = Clans.getPlugin(Clans.class).getConfig();
		
		String section = "Messages." + s;
		
		if (config.get(section) == null) return s;
		
		return ChatColor.translateAlternateColorCodes('&', Clans.getPlugin(Clans.class).getConfig().getString(section));
	}
    private final String message;
    
    Messages(String... messages)
    {
        if (messages == null || messages.length == 0) { message = ""; return; }
        StringBuilder builder = new StringBuilder();
        for (String m : messages) builder.append(RESET.toString()).append(m);
        this.message = builder.toString();
    }
   
    @Override
    public String toString() { return message; }
    
    public void send(CommandSender sender) { sender.sendMessage(toString()); }
}