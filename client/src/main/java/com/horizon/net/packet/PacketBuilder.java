package com.horizon.net.packet;

import com.sun.jna.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;
import oshi.util.FormatUtil;

import java.net.SocketException;

@Component
public class PacketBuilder {

    @Autowired
    private SystemInfo systemInfo;

    public Packet getClientInitPacket() {
        Packet packet = PacketType.CLIENT_INIT.newPacket();

        /**
         * Client unique identifier.
         */
        String id = systemInfo.getHardware().getProcessor().getProcessorID();
        packet.writeString(id);

        packet.writeString(getLan());

        String hostname = systemInfo.getOperatingSystem().getNetworkParams().getHostName();
        packet.writeString(hostname);

        packet.writeString(System.getProperty("user.name"));

        String osName = System.getProperty("os.name");
        packet.writeString(osName + " (" + Platform.ARCH + ")");
        return packet;
    }

    public Packet getClientUpdatePacket() {
        Packet packet = PacketType.CLIENT_UPDATE.newPacket();

        /**
         * CPU usage.
         */
        int cpuUsage = (int) Math.round(systemInfo.getHardware().getProcessor().getSystemCpuLoad() * 100);
        packet.getPayload().writeInt(cpuUsage);

        /**
         * RAM usage.
         */
        long total = systemInfo.getHardware().getMemory().getTotal();
        long available = systemInfo.getHardware().getMemory().getAvailable();

        String ramUsage = FormatUtil.formatBytes(total - available);
        String ramTotal = FormatUtil.formatBytes(total);

        packet.writeString(ramUsage + "/" + ramTotal);
        packet.getPayload().writeInt((int) (((total - systemInfo.getHardware().getMemory().getAvailable()) * 100) / total));

        /**
         * CPU uptime.
         */
        long uptime = systemInfo.getHardware().getProcessor().getSystemUptime();
        packet.writeString(FormatUtil.formatElapsedSecs(uptime));

        return packet;
    }

    //TODO: Remove from here
    private String getLan() {
        NetworkIF[] networkIFs = systemInfo.getHardware().getNetworkIFs();
        for (NetworkIF net : networkIFs) {
            try {
                if(!net.getNetworkInterface().isLoopback()) {
                    return String.join("", net.getIPv4addr());
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        return "N/A";
    }

}
