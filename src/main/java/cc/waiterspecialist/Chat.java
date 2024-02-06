package cc.waiterspecialist;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent e) {
        e.setCancelled(true);
    }
}
