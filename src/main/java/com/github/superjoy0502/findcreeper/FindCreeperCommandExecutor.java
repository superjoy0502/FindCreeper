package com.github.superjoy0502.findcreeper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FindCreeperCommandExecutor implements CommandExecutor {
    private final FindCreeper fc;
    public List<Level> levelList = new ArrayList<Level>();
    public FileConfiguration config;
    public boolean isEditing = false;

    public FindCreeperCommandExecutor(FindCreeper fc) {
        this.fc = fc;
    }

    public void setConfigFile(FileConfiguration config){
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("findcreeper") || command.getName().equalsIgnoreCase("fc")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("This is a player only command!");
                return false;
            }

            Player player = (Player) sender;
            if (args.length >= 3) {
                return false;
            } else {
                switch (args[0]) {
                    case "create":
                        levelList.add(new Level(args[1].toString()));
                        sender.sendMessage("Level \"" + args[1].toString() + "\" has been added!");
                        return true;
                    case "remove":
                        try {
                            levelList.remove(Integer.parseInt(args[1]) - 1);
                        } catch (NumberFormatException e) {
                            sender.sendMessage("Usage: /findcreeper remove [index]");
                        } catch (IndexOutOfBoundsException e) {
                            sender.sendMessage("Usage: /findcreeper remove [index starting from 1]");
                        }
                        return true;
                    case "list":
                        List<String> levelNames = new ArrayList<String>();
                        for (int i = 0; i < levelList.size(); i++) {
                            if (i == 0){
                                levelNames.add("1: " + levelList.get(i).name);
                                continue;
                            }
                            levelNames.add("\n" + (i + 1) + ": " + levelList.get(i).name);
                        }
                        sender.sendMessage(levelNames.toString());
                        return true;
                    case "summon":
                        try
                        {
                            Integer.parseInt(args[1]);
                            try {
                                levelList.get(Integer.parseInt(args[1]));
                            }
                            catch (IndexOutOfBoundsException e){
                                return false;
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            return false;
                        }
                        Level level = levelList.get(Integer.parseInt(args[1]));
                        isEditing = true;
                        Entity creeper = player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                        creeper.setGravity(false);
                        level.list.add(creeper);
                        editCreeper(player, level, creeper);
                        return true;
                    case "confirm":
                        isEditing = false;
                        return true;
                }
            }

        }
        return false;
    }

    public void editCreeper(Player player, Level level, Entity creeper){
        while (isEditing){
            creeper.teleport(new Location(player.getWorld(), player.getLocation().getX(), 0, 0, 0, 0));
            player.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute as " + player.getName() + " at @s run tp @e[tag=" + level.name + (level.list.size()-1) + ",limit=1] ^ ^0.5 ^5");
        }
    }
}
