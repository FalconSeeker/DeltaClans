package me.falconseeker.clans.gui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.falconseeker.clans.gui.ListenerInterface;
import me.falconseeker.clans.utils.Messages;

public class InviteFriend implements ListenerInterface {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Messages.HELP_CLAN_INVITE.send(e.getWhoClicked());
	}

}
