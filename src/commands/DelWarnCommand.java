package commands;

import main.Warn;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarnCommand implements CommandExecutor {

    public Warn plugin;

    public DelWarnCommand(Warn instance){
        plugin = instance;
    }

    public Integer getWarns(String playerName){
        int i = this.plugin.getConfig().getInt("Player." + playerName + ".Warns");
        return i;
    }

    public void removeWarns(String playerName, int howMuch){
        int i = this.plugin.getConfig().getInt("Player." + playerName + ".Warns");
        i -= howMuch;
        this.plugin.getConfig().set("Player." + playerName + ".Warns", i);
        this.plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("delwarn.command")) {
                if (label.equalsIgnoreCase("delwarn")) {
                    if (args.length == 0) {
                        player.sendMessage(Warn.prefix + "use /warn <player> - to Warn a player");
                        player.sendMessage(Warn.prefix + "use /warns - to see your warns");
                        player.sendMessage(Warn.prefix + "use /warns <player> - to see the warns of a player");
                        player.sendMessage(Warn.prefix + "use /delwarn <player> - to remove warns from a player");
                    } else if(args.length == 1) {
                        OfflinePlayer target = player.getServer().getOfflinePlayer(args[0]);
                        String targetName = target.getName();
                        removeWarns(targetName, 1);
                        player.sendMessage(Warn.prefix + "You removed a warn from §c" + targetName);
                    }
                }
            } else {
                player.sendMessage(Warn.noperms);
            }
        }
        return false;
    }
}
