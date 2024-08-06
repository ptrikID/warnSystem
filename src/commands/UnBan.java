package commands;

import main.Warn;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnBan implements CommandExecutor {

    public Warn plugin;

    public UnBan(Warn instance){
        plugin = instance;
    }

    public Integer getWarns(String playerName){
        return plugin.getConfig().getInt("Player." + playerName + ".Warns");
    }

    public void resetWarns(String playerName){
        plugin.getConfig().set("Player." + playerName + ".Warns", 0);
        plugin.getConfig().set("Player." + playerName + ".BanID", null);
        plugin.getConfig().set("Player." + playerName + ".BanDate", null);
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("unban.command")) {
                if (label.equalsIgnoreCase("unban")) {
                    if (args.length == 1) {
                        OfflinePlayer target = player.getServer().getOfflinePlayer(args[0]);
                        String targetName = target.getName();
                        if (Bukkit.getBanList(BanList.Type.NAME).isBanned(targetName)) {
                            Bukkit.getBanList(BanList.Type.NAME).pardon(targetName);
                            resetWarns(targetName);
                            player.sendMessage(Warn.prefix + "The player §a" + targetName + " §7is unbanned and their warnings have been reset!");
                        } else {
                            player.sendMessage(Warn.prefix + "The player §a" + targetName + " §7is not banned!");
                        }
                    } else {
                        player.sendMessage(Warn.prefix + "use /unban <player>");
                    }
                }
            } else {
                player.sendMessage(Warn.noperms);
            }
        }
        return false;
    }
}
