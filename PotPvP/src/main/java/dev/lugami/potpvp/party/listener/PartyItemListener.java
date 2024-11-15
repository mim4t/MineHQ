package dev.lugami.potpvp.party.listener;

import dev.lugami.potpvp.PotPvPSI;
import dev.lugami.potpvp.party.Party;
import dev.lugami.potpvp.party.PartyHandler;
import dev.lugami.potpvp.party.PartyItems;
import dev.lugami.potpvp.party.command.PartyFfaCommand;
import dev.lugami.potpvp.party.command.PartyInfoCommand;
import dev.lugami.potpvp.party.command.PartyLeaveCommand;
import dev.lugami.potpvp.party.command.PartyTeamSplitCommand;
import dev.lugami.potpvp.party.menu.RosterMenu;
import dev.lugami.potpvp.party.menu.otherparties.OtherPartiesMenu;
import dev.lugami.potpvp.util.ItemListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public final class PartyItemListener extends ItemListener {

    public PartyItemListener(PartyHandler partyHandler) {
        addHandler(PartyItems.LEAVE_PARTY_ITEM, PartyLeaveCommand::partyLeave);
        addHandler(PartyItems.START_TEAM_SPLIT_ITEM, PartyTeamSplitCommand::partyTeamSplit);
        addHandler(PartyItems.START_FFA_ITEM, PartyFfaCommand::partyFfa);
        addHandler(PartyItems.OTHER_PARTIES_ITEM, p -> new OtherPartiesMenu().openMenu(p));
        addHandler(PartyItems.ASSIGN_CLASSES, p -> new RosterMenu(partyHandler.getParty(p)).openMenu(p));
    }

    // this item changes based on who your party leader is,
    // so we have to manually implement this one.
    @EventHandler
    public void fastPartyIcon(PlayerInteractEvent event) {
        if (!event.hasItem() || !event.getAction().name().contains("RIGHT_")) {
            return;
        }

        if (event.getItem().getType() != PartyItems.ICON_TYPE) {
            return;
        }

        boolean permitted = canUseButton.getOrDefault(event.getPlayer().getUniqueId(), 0L) < System.currentTimeMillis();

        if (permitted) {
            Player player = event.getPlayer();
            Party party = PotPvPSI.getInstance().getPartyHandler().getParty(player);

            if (party != null && PartyItems.icon(party).isSimilar(event.getItem())) {
                event.setCancelled(true);
                PartyInfoCommand.partyInfo(player, player);
            }

            canUseButton.put(player.getUniqueId(), System.currentTimeMillis() + 500);
        }
    }

}