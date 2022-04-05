package com.planetgallium.kitpvp.command;

import com.planetgallium.kitpvp.Game;
import com.planetgallium.kitpvp.util.Resource;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AliasCommand implements CommandExecutor, Listener {

    private Resource config;

    public AliasCommand(Game plugin) {
        this.config = plugin.getResources().getConfig();
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        String message = e.getMessage();

        String[] words = message.split(" ");

        if (message.equals("/kits") && config.getBoolean("Commands.Alias.Kits")) {

            e.setCancelled(true);
            p.performCommand("kp kits");

        } else if (message.startsWith("/kit") && config.getBoolean("Commands.Alias.Kit")) {

            if (words.length == 1) {
                e.setCancelled(true);
                p.performCommand("kp kit");
            } else if (words.length == 2) {
                e.setCancelled(true);
                p.performCommand("kp kit " + words[1]);
            }

        } else if (message.startsWith("/stats") && config.getBoolean("Commands.Alias.Stats")) {

            if (words.length == 1) {
                e.setCancelled(true);
                p.performCommand("kp stats");
            } else if (words.length == 2) {
                e.setCancelled(true);
                p.performCommand("kp stats " + words[1]);
            }

        }

    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,
            @Nonnull String label, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cannot run this command as non player.");
            return true;
        }
        Player player = (Player) sender;
        if ("spawn".equals(label)) {
            player.performCommand("kp spawn");
            return true;
        }
        return false;
    }
}
