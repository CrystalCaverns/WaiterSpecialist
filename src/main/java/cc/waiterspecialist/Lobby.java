package cc.waiterspecialist;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.time.Duration;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class Lobby implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly a player can execute this command!");
            return false;
        }
        p.showTitle(Title.title(Component.text("\uDBEA\uDDE8"), Component.empty(), Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(500))));
        Bukkit.getScheduler().runTaskLater(getPlugin(WaiterSpecialist.class), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"warp Spawn " + p.getName()), 20L);
        return false;
    }
}