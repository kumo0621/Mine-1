package com.github.kumo0621.mine;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.*;

@lombok.Data
public class Data {
    
    private Location home;
    
    private final List<UUID> joinedPlayerList = new ArrayList<>();
    
    private Map<UUID, Integer> moneyMap = new HashMap<>();
}
