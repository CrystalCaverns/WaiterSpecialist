package cc.waiterspecialist;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.File;

public class Join implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.joinMessage(Component.empty());
        Player p = e.getPlayer();
        p.setGameMode(GameMode.ADVENTURE);
        Location spawn = new Location(Bukkit.getWorld("world"), 0.5, -63, 0.5, 0, 0);
        p.teleport(spawn);
        if (!p.hasPermission("suffix.1.&f")) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            TextComponent detected = Component.text("New player (" + p.getName() + ") detected! Adding permissions...")
                .color(TextColor.fromHexString("#9944FF"));
            console.sendMessage(detected);
            LuckPerms lp = LuckPermsProvider.get();
            User user = lp.getPlayerAdapter(Player.class).getUser(p);
            String[] permissions = {"meta.color.#ffffff", "meta.color_name.Pure White", "meta.profile_theme.#ffffff;\uDBEE\uDD3A;Simple White", "meta.nameplate.\uDBE2\uDCB1\uDBC2\uDD72", "meta.title.#ffffffBeginner", "meta.crystal_pass_points.0", "suffix.1.&f"};
            for (String permission : permissions) {
                user.data().add(Node.builder(permission).build());
            }
            lp.getUserManager().saveUser(user);
            TextComponent added = Component.text("Permissions added to " + p.getName() + "!")
                .color(TextColor.fromHexString("#9944FF"));
            console.sendMessage(added);
        }
        Panel panel = new Panel(new File("/home/container/plugins/CommandPanels/panels/welcome.yml"), "welcome");
        Bukkit.getScheduler().runTaskLater(WaiterSpecialist.getPlugin(WaiterSpecialist.class), (overtime) -> {
            if (p.isOnline()) {
                TextComponent message = Component.text("Uh oh, something went wrong. Please try to connect again. If this is happening repeatedly, please tell us on Discord! (Error: ServerLobbyOfflineException)")
                    .color(NamedTextColor.RED);
                p.kick(message);
            }
        }, 12000L);
        panel.open(p, PanelPosition.Top);
    }
}