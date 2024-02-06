package cc.waiterspecialist;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class Main implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("§f\uDBF7\uDC35 §cCannot execute command.");
            return false;
        }
        Player p = Bukkit.getPlayer(args[1]);
        assert p != null;
        if (args[0].equals("welcome")) {
            Panel panel = new Panel(new File("/home/container/plugins/CommandPanels/panels/welcome.yml"), "welcome");
            Bukkit.getScheduler().runTaskLater(getPlugin(WaiterSpecialist.class), (overtime) -> {
                if (p.isOnline()) {
                    TextComponent message = Component.text("Uh oh, something went wrong. Please try to connect again. If this is happening repeatedly, please tell us on Discord! (Error: ServerLobbyOfflineException)")
                        .color(NamedTextColor.RED);
                    p.kick(message);
                }
            }, 12000L);
            panel.open(p, PanelPosition.Top);
        }
        return false;
    }
}