package me.falconseeker.clans.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;
 
public class NameTag {
	
	public enum TeamAction { CREATE, UPDATE, DESTROY }
	
    private static Team team;
    private static Scoreboard scoreboard;
 
    /**
     * Change the player's name over their head
     * 
     * @param player - Player who's nametag is getting edited
     * @param prefix - String before player's nametag
     * @param suffix - String after player's nametag
     * @param action - TeamAction UPDATE, CREATE, OR DESTROY
     */
    public static void changePlayerName(Player player, String prefix, String suffix, TeamAction action) {
        if (prefix == null || suffix == null || action == null) {
            return;
        }
        if (player.getScoreboard() == null) {
                Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective a = sb.registerNewObjective("test", "dummy");

                a.setDisplayName("Test");
                a.setDisplaySlot(DisplaySlot.SIDEBAR);

                a.getScore("Clan").setScore(1);
                a.getScore("Plugin By Falcon_Seeker").setScore(2);
                a.getScore("Hope you enjoy!").setScore(3);

                player.setScoreboard(sb);
        }
        scoreboard = player.getScoreboard();
 
        if (scoreboard.getTeam(player.getName()) == null) {
            scoreboard.registerNewTeam(player.getName());
        }
 
        team = scoreboard.getTeam(player.getName());
        team.setPrefix(Color(prefix));
        team.setSuffix(Color(suffix));
        team.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
 
        switch (action) {
            case CREATE:
                team.addPlayer(player); //Used add player for lower version support instead of .addEntry
                break;
            case UPDATE:
                team.unregister();
                scoreboard.registerNewTeam(player.getName());
                team = scoreboard.getTeam(player.getName());
                team.setPrefix(Color(prefix));
                team.setSuffix(Color(suffix));
                team.setNameTagVisibility(NameTagVisibility.ALWAYS); //Using depracated messages for older version support
                team.addPlayer(player);
                break;
            case DESTROY:
                team.unregister();
                break;
        }
    }
 
    private static String Color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
 
}
