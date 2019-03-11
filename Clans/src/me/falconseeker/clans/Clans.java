package me.falconseeker.clans;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import me.falconseeker.clans.commands.BaseCommand;
import me.falconseeker.clans.commands.CommandHandler;
import me.falconseeker.clans.commands.subcommands.*;
import me.falconseeker.clans.listeners.*;
import me.falconseeker.clans.listeners.ClanChat;
import me.falconseeker.clans.managements.ClansManager;


public class Clans extends JavaPlugin {

	private ClansManager clanManager;
	private Methods methods;
	private Scoreboard sb;
	private final ConsoleCommandSender sender = Bukkit.getConsoleSender();
	
	public void onEnable() {
	    
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		
		sb = Bukkit.getScoreboardManager().getNewScoreboard();

		sb.registerNewTeam("CanSee");
		
		clanManager = new ClansManager(this);		
		methods = new Methods();

		clanManager.serialise(); //Retrieves data from config to use in clans plugin

		register();
		//registerGUI(); GUI not added yet
		registerCommands(); 
		sender.sendMessage(ChatColor.GREEN + "Enabled");
	}
	public void onDisable() {
		clanManager.deserialise();
		sender.sendMessage(ChatColor.RED + "Disabled");
	}
	public ClansManager getClanManager() {
		return clanManager;
	}
	public Scoreboard getScoreboard() {
		return sb;
	}
	public Methods getMethods() {
		return methods;
	}

	private void registerCommands() {
        CommandHandler handler = new CommandHandler();

        handler.register("clan", new BaseCommand(this));
        handler.register("broadcast", new CommandClanBroadcast(this));
        handler.register("addadmin", new CommandClanAddAdmin(this));
        handler.register("broadcast", new CommandClanBroadcast(this));
        handler.register("create", new CommandClanCreate(this));
        handler.register("invite", new CommandClanInvite(this));
        handler.register("join", new CommandClanJoin(this));
        handler.register("deny", new CommandClanDeny(this));

        handler.register("kick", new CommandClanKick(this));
        handler.register("leave", new CommandClanLeave(this));
        handler.register("list", new CommandClanList(this));
        //handler.register("mute", new CommandClanMute());
        handler.register("sethologram", new CommandClanHolo(this));
        handler.register("remove", new CommandClanRemove(this));
        handler.register("demote", new CommandClanRemove(this));
        handler.register("setlevel", new CommandClanSetLevel(this));
        handler.register("setowner", new CommandClanSetOwner(this));
        handler.register("removeadmin", new CommandClanRemoveAdmin(this));
        handler.register("settag", new CommandClanTag(this));
 
        getCommand("clan").setExecutor(handler);
        
        new CommandBlockedWorlds(this);
        new CommandClanChat(this); //Commands that don't start with /clan
    }
   /* private void registerGUI() {
    	ListenerHandler handler = new ListenerHandler();

        handler.register("Invite Friend (Right Click)", new InviteFriend());

        Bukkit.getPluginManager().registerEvents(handler, this);
        
        new CommandBlockedWorlds(this);
    }*/
    
	private void register() {
		new ClanChat(this);
		new Holograms(this);
		new KillLevels(this);
	}
}
