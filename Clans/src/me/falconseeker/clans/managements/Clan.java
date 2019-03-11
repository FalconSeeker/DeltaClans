package me.falconseeker.clans.managements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Clan {

    private String name;
    private Set<UUID> players;
    private Set<UUID> admin;
    private int level;
    private int kills;
    private UUID owner;
    private String tag;
    private ArrayList<Player> invite = new ArrayList<>();
    private HashMap<Player, Boolean> chat = new HashMap<>();

    public Clan(UUID owner, String name, String tag, Set<UUID> players, Set<UUID> pl, Integer level, Integer kills) {
    	this.name = name;
    	this.owner = owner;
    	this.players = players;
    	this.admin = pl;
    	this.level = level;
    	this.tag = tag;
    	this.kills = kills;
    }
    public void setLevel(Integer level) {
        if (this.level >= 100) return;
        this.level = level;
    }
    public void addKill() {
        kills = kills + 1;
    }
    public Integer getKills() {
        return kills;
    }
    public String getName() {
        return name;
    }
    public String getTag() {
    	return tag;
    }
    public Set<UUID> getPlayers(){
    	return players;
    }
    public UUID getOwner() {
    	return owner;
    }
    public Set<UUID> getAdmins() {
    	return admin;
    }
    public void addAdmin(UUID p) {
    	this.admin.add(p);
    }
    public void removeAdmin(UUID p) {
    	this.admin.remove(p);
    }
    public void setOwner(UUID p) {
    	this.owner = p;
    }
    public void removeUUID(UUID p) {
    	this.players.remove(p);
    }
	public void setChat(Player p, Boolean c) {
		chat.put(p, c);
	}
	public void setTag(String string) {
		this.tag = string;
	}
	public Integer getIntLevel() {
		return this.level;
	}
	public boolean getChat(Player p) {
		if (chat.get(p) == null) return false;
		if (chat.get(p).equals(true)) return true;
		return false;
	}
	public void invitePlayer(Player invited) {
		invite.add(invited);
		
		TextComponent message = new TextComponent(" Accept");
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a&lAccept")).create() ) );
		message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/clan join " + getName()) );
		TextComponent deny = new TextComponent("Deny");
		deny.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/clan deny " + getName()) );
		deny.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&4&lDeny")).create() ) );
		
		invited.spigot().sendMessage(new ComponentBuilder(ChatColor.GRAY + getName() + " has invited you to join : ").color(ChatColor.BLUE).append( message).color(ChatColor.GREEN).bold(true).append(ChatColor.GRAY + " or ").append(deny).color(ChatColor.DARK_RED).bold(true).create());
		}
	public void denyInvite(Player p) {
		
		if (players.contains(p.getUniqueId())) {
			p.sendMessage(ChatColor.RED + "You are already in this clan!");
			return;
		}
		if (!invite.contains(p)) {
			p.sendMessage(ChatColor.RED + "You have not been invited to this clan!");
			return;
		}
		invite.remove(p);
		p.sendMessage(ChatColor.RED + "Invite rejected!");
	}
	public void acceptInvite(Player p) {
		
		if (players.contains(p.getUniqueId())) {
			p.sendMessage(ChatColor.RED + "You are already in this clan!");
			return;
		}
		if (!invite.contains(p)) {
			p.sendMessage(ChatColor.RED + "You have not been invited to this clan!");
			return;
		}
		players.add(p.getUniqueId());
		invite.remove(p);
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "You have joined the clan &9&l" + getName()));
		TextComponent message = new TextComponent(" Click here for guild stats");
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a&lAccept")).create() ) );
		message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/clan playerlist ") );
	
		p.spigot().sendMessage(new ComponentBuilder(ChatColor.GOLD + "" + ChatColor.BOLD).color(ChatColor.GOLD).append( message).create());
	}
	public String getChatLevel() { 
		String lvl = "Level " + String.valueOf(getIntLevel());
		if (getIntLevel() >= 100) return ChatColor.WHITE.toString() + ChatColor.BOLD + lvl;
		
		int firstDigit = Integer.parseInt(Integer.toString(getIntLevel()).substring(0, 1));
		
		List<ChatColor> chatcolor = new ArrayList<ChatColor>();
		
		for (ChatColor cs : ChatColor.values()) {
			chatcolor.add(cs);
		}
		return chatcolor.get(firstDigit) + lvl;
	
	}
}