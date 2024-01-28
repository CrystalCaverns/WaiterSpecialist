package cc.waiterspecialist;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import static org.bukkit.Bukkit.getConsoleSender;

public final class WaiterSpecialist extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCommand command = getCommand("lobby");
        assert command != null;
        command.setExecutor(new Lobby());
        List<Listener> events = List.of(new Join(),new Quit(),new Connect());
        for (Listener event : events) {
            getServer().getPluginManager().registerEvents(event,this);
        }
        getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
        TextComponent message = Component.text("Specialist for the Waiting Room initiated. Please ensure the core Weaver protocol is not also onsite, as it is not needed for the Waiting Room.")
            .color(TextColor.fromHexString("#9944FF"));
        getConsoleSender().sendMessage(message);
    }
}
