package commands;

import main.Warn;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WarnsCommand implements CommandExecutor {

    private Warn plugin;

    public WarnsCommand(Warn instance){
        plugin = instance;
    }

    public Integer getWarns(String playerName){
        return plugin.getConfig().getInt("Player." + playerName + ".Warns");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
                if (label.equalsIgnoreCase("warns")) {
                    if (args.length == 0) {
                        int playerWarns = getWarns(player.getName());
                        if (playerWarns == 0) {
                            player.sendMessage(Warn.prefix + "You don't have any warnings!");
                        } else if (playerWarns == 1) {
                            player.sendMessage(Warn.prefix + "You have 1 warning!");
                        } else {
                            player.sendMessage(Warn.prefix + "You have " + playerWarns + " warnings!");
                        }
                    } else if (args.length == 1 && player.hasPermission("warns.command.see.other")) {
                        OfflinePlayer target = player.getServer().getOfflinePlayer(args[0]);
                        String targetName = target.getName();
                        player.sendMessage("");
                        player.sendMessage(Warn.prefix + "Warnings of §a§l" + targetName);
                        player.sendMessage("");

                        int targetWarns = getWarns(targetName);
                        player.sendMessage(Warn.prefix + "Warnings: §a" + targetWarns);

                        // Check if player is banned
                        if (target.isBanned()) {
                            // Get BanID and BanDate from config
                            String banID = plugin.getConfig().getString("Player." + targetName + ".BanID");
                            String banDate = plugin.getConfig().getString("Player." + targetName + ".BanDate");

                            // Display BanID and BanDate
                            player.sendMessage(Warn.prefix + "Banned: §aTrue");
                            player.sendMessage(Warn.prefix + "BanID: §a" + banID);
                            player.sendMessage(Warn.prefix + "Ban Date: §a" + banDate);
                        } else {
                            player.sendMessage(Warn.prefix + "Banned: §cFalse");
                        }

                        if (target.isOnline()) {
                            player.sendMessage(Warn.prefix + "Online: §aTrue");
                        } else {
                            player.sendMessage(Warn.prefix + "Online: §cFalse");
                        }
                    } else if (!(player.hasPermission("warns.command.see.other"))) {
                        player.sendMessage(Warn.noperms);
                    }
                }
        }
        return false;
    }
}
