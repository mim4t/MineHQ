package dev.lugami.bridge.global.packetlogger;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Location;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class PacketLog {
    private Packet packet;
    private Location location;
    private long time;
    private boolean flagged;
}
