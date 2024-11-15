package dev.lugami.potpvp.setting.command;

import dev.lugami.potpvp.setting.menu.SettingsMenu;
import dev.lugami.qlib.command.Command;

import org.bukkit.entity.Player;

/**
 * /settings, accessible by all users, opens a {@link SettingsMenu}
 */
public final class SettingsCommand {

    @Command(names = {"settings", "preferences", "prefs", "options"}, permission = "")
    public static void settings(Player sender) {
        new SettingsMenu().openMenu(sender);
    }

}