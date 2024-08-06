package commands;

import main.Warn;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class WarnCommand implements CommandExecutor, Listener {

    private Warn plugin;

    public WarnCommand(Warn instance) {
        plugin = instance;
    }

    public Integer getWarns(String playerName) {
        return plugin.getConfig().getInt("Player." + playerName + ".Warns");
    }

    public void removeWarns(String playerName, int howMuch) {
        int currentWarns = getWarns(playerName);
        plugin.getConfig().set("Player." + playerName + ".Warns", currentWarns - howMuch);
        plugin.saveConfig();
    }

    public void addWarns(String playerName, int warn) {
        int currentWarns = getWarns(playerName);
        plugin.getConfig().set("Player." + playerName + ".Warns", currentWarns + warn);
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("warn.command")) {
                if (label.equalsIgnoreCase("warn")) {
                    if (args.length == 0) {
                        player.sendMessage(Warn.prefix + "use /warn <player> - to warn a player");
                        player.sendMessage(Warn.prefix + "use /warns - to see your warnings");
                        player.sendMessage(Warn.prefix + "use /warns <player> - to see the warnings of a player");
                        player.sendMessage(Warn.prefix + "use /delwarn <player> - to remove warnings from a player");
                        player.sendMessage(Warn.prefix + "use /unban <player> - to unban a player");
                    } else if (args.length == 1) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                        String targetName = target.getName();
                        addWarns(targetName, 1);
                        player.sendMessage(Warn.prefix + "You warned the player §c" + targetName + " §7(" + getWarns(targetName) + "§7)");

                        int warns = getWarns(targetName);

                        if (warns == 2) {
                            if (target.isOnline()) {
                                ((Player) target).sendMessage(Warn.prefix + "You have 2 warnings, please follow the rules!");
                            }
                        }

                        if (warns >= 3) {
                            // Generate 5-digit random number for BanID
                            String banID = generateBanID();

                            // Get current date and time for ban
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                            String currentDate = dateFormat.format(new Date());

                            // Add BanID and ban date to player's config
                            plugin.getConfig().set("Player." + targetName + ".BanID", banID);
                            plugin.getConfig().set("Player." + targetName + ".BanDate", currentDate);
                            plugin.saveConfig();

                            // Ban the player
                            Bukkit.getBanList(BanList.Type.NAME).addBan(targetName, "You have been banned for accumulating 3 warnings. BanID: " + banID, null, player.getName());

                            // Kick the player if online
                            if (target.isOnline()) {
                                ((Player) target).kickPlayer("You have been banned for accumulating 3 warnings. BanID: " + banID);
                            }

                            // Broadcast ban message
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission("warns.broadcast")) {
                                    all.sendMessage(Warn.prefix + "§7---------------------");
                                    all.sendMessage(Warn.prefix + "The player §a§l" + targetName + " §7was banned §7(§c" + warns + "§7)");
                                    all.sendMessage(Warn.prefix + "From: §a§lNetwork");
                                    all.sendMessage(Warn.prefix + "Date: §a§l" + currentDate);
                                    all.sendMessage(Warn.prefix + "BanID: §a§l" + banID);
                                    all.sendMessage(Warn.prefix + "§7---------------------");
                                }
                            }
                        }
                    }
                }
            } else {
                player.sendMessage(Warn.noperms);
            }
        }
        return false;
    }

    private String generateBanID() {
        Random random = new Random();
        int banID = random.nextInt(90000) + 10000; // Generate a random number between 10000 and 99999
        return "#" + banID;
    }
}
