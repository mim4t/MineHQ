package dev.lugami.potpvp.party.command;

import dev.lugami.potpvp.PotPvPLang;
import dev.lugami.potpvp.PotPvPSI;
import dev.lugami.potpvp.party.Party;
import dev.lugami.qlib.command.Command;

import org.bukkit.entity.Player;

public final class PartyLeaveCommand {

    @Command(names = {"party leave", "p leave", "t leave", "team leave", "leave", "f leave"}, permission = "")
    public static void partyLeave(Player sender) {
        Party party = PotPvPSI.getInstance().getPartyHandler().getParty(sender);

        if (party == null) {
            sender.sendMessage(PotPvPLang.NOT_IN_PARTY);
        } else {
            party.leave(sender);
        }
    }

}