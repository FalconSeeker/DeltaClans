package me.falconseeker.clans;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import me.falconseeker.clans.commands.*;
import me.falconseeker.clans.commands.subcommands.*;
import me.falconseeker.clans.listeners.*;
import me.falconseeker.clans.managements.ClansManager;


public class Clans extends JavaPlugin {

	private ClansManager clanManager;
	private Methods methods;
	private Scoreboard sb;
	private final ConsoleCommandSender sender = Bukkit.getConsoleSender();
	
	@Override
	public void onEnable() {
	    
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		
		sb = Bukkit.getScoreboardManager().getNewScoreboard();

		sb.registerNewTeam("CanSee");
		
		clanManager = new ClansManager(this);		
		methods = new Methods();

		clanManager.serialise(); //Retrieves data from config to use in clans plugin

		new ClanChat(this);
		new Holograms(this);
		new KillLevels(this);
		//registerGUI(); GUI not added yet
		ClanCommandHandler clanHandler = new ClanCommandHandler();
       
		clanHandler.register("clan", new ClanBaseCommand(this));
        clanHandler.register("invite", new CommandClanInvite(this));
        clanHandler.register("addadmin", new CommandClanAddAdmin(this));
        clanHandler.register("settag", new CommandClanTag(this));
        clanHandler.register("removeadmin", new CommandClanRemoveAdmin(this));
        clanHandler.register("setowner", new CommandClanSetOwner(this));
        clanHandler.register("setlevel", new CommandClanSetLevel(this));
        clanHandler.register("remove", new CommandClanRemove(this));
        clanHandler.register("leave", new CommandClanLeave(this));
        clanHandler.register("broadcast", new CommandClanBroadcast(this));
      
        getCommand("clan").setExecutor(clanHandler);

        CommandHandler handler = new CommandHandler();

        handler.register("clan", new BaseCommand(this));
        handler.register("create", new CommandClanCreate(this));
        handler.register("join", new CommandClanJoin(this));
        handler.register("deny", new CommandClanDeny(this));

        handler.register("kick", new CommandClanKick(this));
        handler.register("leave", new CommandClanLeave(this));
        handler.register("list", new CommandClanList(this));
        //handler.register("mute", new CommandClanMute());
        handler.register("sethologram", new CommandClanHolo(this));
 
        getCommand("clan").setExecutor(handler);
        
        new CommandBlockedWorlds(this);
        new CommandClanChat(this); //Commands that don't start with /clan
        
		sender.sendMessage(ChatColor.GREEN + "Enabled");
	}
	@Override
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
   /* private void registerGUI() {
    	ListenerHandler handler = new ListenerHandler();

        handler.register("Invite Friend (Right Click)", new InviteFriend());

        Bukkit.getPluginManager().registerEvents(handler, this);
        
        new CommandBlockedWorlds(this);
    }*/
}
