package me.falconseeker.clans.managements;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;

public class ClansManager {

    private final Clans plugin;
    private final FileConfiguration config;
    private final Set<Clan> clanSet;
    
    public ClansManager(Clans plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.clanSet = new HashSet<>();
    }

    
    public void serialise() {
    	
        ConfigurationSection configSection = config.getConfigurationSection("Clans");        
        if(configSection != null) {
        configSection.getKeys(false).forEach(s -> {
        	if (s != null) {
        	Set<UUID> adminset = new HashSet<>();
        	Set<UUID> playerset = new HashSet<>();

        	configSection.getStringList(s + ".admins").forEach(u -> adminset.add(UUID.fromString(u))); 
    	    configSection.getStringList(s + ".players").forEach(u -> playerset.add(UUID.fromString(u))); 
		
        	clanSet.add(new Clan(UUID.fromString(configSection.getString(s + ".owner")), 
        			s.toUpperCase(), 
        			configSection.getString(s + ".tag"), 
        			playerset,
        			adminset, 
        			configSection.getInt(s + ".level"),
        			configSection.getInt(s + ".kills")));
        	}
        });
        }
        plugin.saveDefaultConfig();

    }

    public void deserialise() {
        if(!clanSet.isEmpty()) {
        
        for (Clan clan : clanSet) {
            List<String> admin = new ArrayList<String>();

            if (clan.getAdmins() != null) {
            clan.getAdmins().forEach(uuid -> admin.add(String.valueOf(uuid)));
            config.set("Clans." + clan.getName() + ".admins", admin);
            }
            List<String> players = new ArrayList<String>();
            
            clan.getPlayers().forEach(uuid -> players.add(String.valueOf(uuid)));
            config.set("Clans." + clan.getName() + ".players", players);
            
            config.set("Clans." + clan.getName() + ".owner", String.valueOf(clan.getOwner()));
            config.set("Clans." + clan.getName() + ".tag", clan.getTag());
            config.set("Clans." + clan.getName() + ".level", clan.getIntLevel());
            config.set("Clans." + clan.getName() + ".kills", clan.getKills());
        }
        
        plugin.saveConfig();

        plugin.saveDefaultConfig();
        }
    }

    public Clan getClan(Player player) {
        return clanSet.stream().filter(Clan -> Clan.getPlayers().contains(player.getUniqueId())).findFirst().orElse(null);
    }
    public Clan getClan(String key) {
        return clanSet.stream().filter(Clan -> Clan.getName().equals(key)).findFirst().orElse(null);
    }
    public Set<Clan> getClanList() {
        return Collections.unmodifiableSet(clanSet);
    }
    public void addClan(Clan Clan) {
        this.clanSet.add(Clan);
    }
    public void removeClan(Clan Clan) {
        this.clanSet.remove(Clan);
    }
}