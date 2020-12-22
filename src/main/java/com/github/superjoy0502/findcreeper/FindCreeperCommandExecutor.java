package com.github.superjoy0502.findcreeper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FindCreeperCommandExecutor implements CommandExecutor {
    private final FindCreeper fc;

    public FindCreeperCommandExecutor(FindCreeper fc){
        this.fc = fc;
    }

    public List<Level> levelList = new ArrayList<Level>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("findcreeper") || command.getName().equalsIgnoreCase("fc")){

            if (!(sender instanceof Player)) {
                sender.sendMessage("This is a player only command!");
                return false;
            }

            Player player = (Player) sender;
            if (args.length >= 3){
                return false;
            }
            else {
                switch (args[0]) {
                    case "create":
                        levelList.add(new Level(args[1].toString()));
                        break;
                    case "remove":
                        for (int i =0; i < levelList.size(); i++){
                            if (levelList.get(i).name == args[1].toString()){
                                levelList.remove(i);
                            }
                        }
                        break;
                    case "list":
                        sender.sendMessage(levelList.toString());
                        break;
                }
            }

        }
        return false;
    }
}
