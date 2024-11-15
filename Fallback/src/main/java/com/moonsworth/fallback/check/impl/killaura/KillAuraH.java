// 
// Decompiled by Procyon v0.5.36
// 

package com.moonsworth.fallback.check.impl.killaura;

import com.moonsworth.fallback.alert.AlertData;
import com.moonsworth.fallback.event.AlertType;
import com.moonsworth.fallback.player.PlayerData;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;
import com.moonsworth.fallback.check.checks.PacketCheck;

public class KillAuraH extends PacketCheck
{
    private boolean sent;
    
    public KillAuraH(final PlayerData playerData) {
        super(playerData, "Kill Aura (H)");
    }
    
    @Override
    public void handleCheck(final Player player, final Packet packet) {
        if (packet instanceof PacketPlayInBlockDig) {
            final PacketPlayInBlockDig.EnumPlayerDigType digType = ((PacketPlayInBlockDig)packet).c();
            if ((digType == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK || digType == PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM) && this.sent && this.alert(player, AlertType.RELEASE, new AlertData[0], true) && !this.playerData.isFakeDigging() && !this.playerData.isInstantBreakDigging()) {
                final int violations = this.playerData.getViolations(this, 60000L);
                if (!this.playerData.isBanning() && violations > 2) {
                    this.ban(player);
                }
            }
        }
        else if (packet instanceof PacketPlayInUseEntity) {
            this.sent = true;
        }
        else if (packet instanceof PacketPlayInFlying) {
            this.sent = false;
        }
    }
}
