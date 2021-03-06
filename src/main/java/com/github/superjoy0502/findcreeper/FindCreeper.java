package com.github.superjoy0502.findcreeper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class FindCreeper extends JavaPlugin {


    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FindCreeperCommandExecutor executor = new FindCreeperCommandExecutor(this);
        executor.setConfigFile(this.getConfig());
        getLogger().info("CreeperFind enable");
        this.getCommand("findcreeper").setExecutor(executor);
        this.getCommand("fc").setExecutor(new FindCreeperCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("CreeperFind disable");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
