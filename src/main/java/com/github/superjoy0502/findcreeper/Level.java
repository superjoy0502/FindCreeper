package com.github.superjoy0502.findcreeper;

import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public String name;
    public List<Entity> list = new ArrayList<Entity>();

    public Level(String name){
        this.name = name;
    }

}
