package cc.waiterspecialist;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import static org.bukkit.Bukkit.getConsoleSender;

public final class WaiterSpecialist extends JavaPlugin {
    @Override
    public void onEnable() {
        // Commands
        List<String> commandNames = List.of("crystalcaverns","lobby");
        List<CommandExecutor> commandClasses = List.of(new Main(),new Lobby());
        for(int i = 0; i < commandNames.size(); i++) {
            PluginCommand pluginCommand = getCommand(commandNames.get(i));
            assert pluginCommand != null;
            pluginCommand.setExecutor(commandClasses.get(i));
        }
        // Events
        List<Listener> events = List.of(new Join(),new Quit(),new Connect(), new Chat());
        for (Listener event : events) {
            getServer().getPluginManager().registerEvents(event,this);
        }
        // Cross-server connection
        getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
        // Welcome message
        TextComponent message = Component.text("Specialist for the Waiting Room initiated. Please ensure the core Weaver protocol is not also onsite, as it is not needed for the Waiting Room.")
            .color(TextColor.fromHexString("#9944FF"));
        getConsoleSender().sendMessage(message);
        // Yay, we're up and running!
    }
}
