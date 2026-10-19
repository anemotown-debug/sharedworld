package link.sharedworld.versioned;

import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameType;

/** Version-specific LAN publish; 26.3 dropped the game-mode parameter from publishServer. */
public final class ServerPublishCompat {
    private ServerPublishCompat() {
    }

    /** Publish without cheats. gameMode is ignored on 26.3 (SharedWorld always passes null). */
    public static boolean publish(IntegratedServer server, GameType gameMode, int port) {
        return server.publishServer(MinecraftServer.MultiplayerScope.LAN, false, port);
    }
}
