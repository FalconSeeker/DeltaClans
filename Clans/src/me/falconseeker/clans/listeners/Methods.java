package me.falconseeker.clans.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Methods {

	private ArrayList<Player> chatdisabled = new ArrayList<Player>();

	public List<Player> getDisabled() {
		return chatdisabled;
	}
	public void remove(Player p) {
		chatdisabled.remove(p);
	}
	public void add(Player p) {
		chatdisabled.add(p);
	}
}
