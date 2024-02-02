package cc.waiterspecialist;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class Connect implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("crystal_forest");
        Player player = Bukkit.getPlayerExact(e.getPlayer().getName());
        assert player != null;
        Plugin plugin = getPlugin(WaiterSpecialist.class);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (player.isOnline()) {
                TextComponent message = Component.text("Uh oh, something went wrong. Please try to connect again. If this is happening repeatedly, please tell us on Discord! (Error: ServerLobbyOfflineException)")
                    .color(NamedTextColor.RED);
                player.kick(message);
            }
        }, 200L);
    }
}
