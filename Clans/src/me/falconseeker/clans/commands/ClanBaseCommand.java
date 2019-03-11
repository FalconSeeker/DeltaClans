package me.falconseeker.clans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.falconseeker.clans.Clans;
import me.falconseeker.clans.managements.Clan;
import me.falconseeker.clans.managements.ClansManager;
import me.falconseeker.clans.utils.Messages;
 
public class ClanBaseCommand implements ClanCommandInterface
{
 
	private ClansManager clanManager;
	
	public ClanBaseCommand(Clans main) {
		this.clanManager = main.getClanManager();
	}
    @Override
    public boolean onCommand(Player p, Clan clan, Command cmd, String commandLabel, String[] args) {

     /*   GUI g = new GUI(p, 27, "Clan GUI"); 
        
		g.setItem(11, Material.FEATHER, "&bInvite Friend &8(Right Click)",new ArrayList<String>(Arrays.asList("&7Click to invite another player to this clan")));
		g.setItem(12, Material.PAPER, "&bClan Tag &8(Right Click)",new ArrayList<String>(Arrays.asList("&7Click to change this clan's current tag")));
		g.setItem(13, Material.GRASS, "&ePromote Player &8(Right Click)",new ArrayList<String>(Arrays.asList("&7Promotes a player to &eADMIN &7rank")));
		g.setItem(14, Material.BARRIER, "&cPunish Player &8(Right Click)",new ArrayList<String>(Arrays.asList("&7Click to punish a player")));
		g.setItem(15, Material.CLOCK, "&c&lDELETE CLAN &8(Right Click)",new ArrayList<String>(Arrays.asList("&7Delete current clan")));
		g.setItem(4, Material.CLOCK, "&eLeave Clan &8(Right Click)",new ArrayList<String>(Arrays.asList("&7Leave current clan")));

		g.setItem(18, Material.RED_BED, "&c&lCLOSE INVENTORY",new ArrayList<String>(Arrays.asList("&7Click to close the current inventory")));

		g.open();
		*/
        Messages.HELP.send(p);
        return true;
    }
}