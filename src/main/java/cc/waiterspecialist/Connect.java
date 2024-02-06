package cc.waiterspecialist;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

import java.time.Duration;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class Connect implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = getPlayer(e.getPlayer().getName());
        assert p != null;
        connect(p);
    }
    public static void connect(Player p) {
        p.showTitle(Title.title(Component.text("\uDBEA\uDDE8"), Component.empty(), Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(500))));
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("crystal_forest");
        Plugin plugin = getPlugin(WaiterSpecialist.class);
        p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (p.isOnline()) {
                TextComponent message = Component.text("Uh oh, something went wrong. Please try to connect again. If this is happening repeatedly, please tell us on Discord! (Error: ServerLobbyOfflineException)")
                    .color(NamedTextColor.RED);
                p.kick(message);
            }
        }, 200L);
    }
}
