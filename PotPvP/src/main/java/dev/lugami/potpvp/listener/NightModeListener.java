package dev.lugami.potpvp.listener;

import dev.lugami.potpvp.PotPvPSI;
import dev.lugami.potpvp.setting.Setting;
import dev.lugami.potpvp.setting.SettingHandler;
import dev.lugami.potpvp.setting.event.SettingUpdateEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class NightModeListener implements Listener {

    public static final int NIGHT_TIME = 18_000;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        SettingHandler settingHandler = PotPvPSI.getInstance().getSettingHandler();

        if (settingHandler.getSetting(event.getPlayer(), Setting.NIGHT_MODE)) {
            event.getPlayer().setPlayerTime(NIGHT_TIME, false);
        }
    }

    @EventHandler
    public void onSettingUpdate(SettingUpdateEvent event) {
        if (event.getSetting() != Setting.NIGHT_MODE) {
            return;
        }

        if (event.isEnabled()) {
            event.getPlayer().setPlayerTime(NIGHT_TIME, false);
        } else {
            event.getPlayer().resetPlayerTime();
        }
    }

}