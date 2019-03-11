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
        
        clanSet.forEach(Clan -> {
            List<String> admin = new ArrayList<String>();

            if (Clan.getAdmins() != null) {
            Clan.getAdmins().forEach(uuid -> admin.add(String.valueOf(uuid)));
            config.set("Clans." + Clan.getName() + ".admins", admin);
            }
        }); //admins
        clanSet.forEach(Clan -> {
            List<String> players = new ArrayList<String>();
            
            Clan.getPlayers().forEach(uuid -> players.add(String.valueOf(uuid)));
            config.set("Clans." + Clan.getName() + ".players", players);
        }); //players
        clanSet.forEach(Clan -> config.set("Clans." + Clan.getName() + ".owner", String.valueOf(Clan.getOwner()))); //owner
        clanSet.forEach(Clan -> config.set("Clans." + Clan.getName() + ".tag", Clan.getTag()));
        clanSet.forEach(Clan -> config.set("Clans." + Clan.getName() + ".level", Clan.getIntLevel()));
        clanSet.forEach(Clan -> config.set("Clans." + Clan.getName() + ".kills", Clan.getKills()));
        
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