package main;

import commands.DelWarnCommand;
import commands.UnBan;
import commands.WarnCommand;
import commands.WarnsCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Warn extends JavaPlugin {

    public static String prefix = "§c§lWarn §8➜ §7";
    public static String noperms = prefix + "You dont have the permission to do this!";

    public static Warn plugin;

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        getCommands(pluginManager);
        register();

        plugin = this;
    }

    public void getCommands(PluginManager pluginManager){
        pluginManager.registerEvents(new WarnCommand(this), this);
        getConfig().options().copyDefaults(true);
        saveConfig();
        loadConfig();
    }

    public void register(){
        this.getCommand("warn").setExecutor(new WarnCommand(this));
        this.getCommand("warns").setExecutor(new WarnsCommand(this));
        this.getCommand("delwarn").setExecutor(new DelWarnCommand(this));
        this.getCommand("unban").setExecutor(new UnBan(this));
    }

    private void loadConfig(){

    }
}
