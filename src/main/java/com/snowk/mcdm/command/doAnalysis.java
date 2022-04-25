package com.snowk.mcdm.command;

import com.snowk.mcdm.util.SortMap;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.*;

public class doAnalysis {
	
	public static List<Location> run (CommandSender sender, String label, Boolean report) {

		List<Location> locationList = new ArrayList<>(); //for invoked by k-means

		if (Objects.equals(label, "mob")) {
			sender.sendMessage("=====================================");
			List<World> worlds = Bukkit.getWorlds();
			Map<String, Integer> entityMap = new HashMap<>();
			for (World w : worlds) {
				int count = 0;
				List<Entity> allEntitiesInAworld = w.getEntities();
//	    		List<Location> locationList = new ArrayList<Location>();
				for (Entity entities : allEntitiesInAworld) {
					// k-v map for counting Entities by type (name)
					if (!(entities instanceof Player) && !(entities instanceof Item)) {
						count++;
						String entityName = entities.getName();
						if (entityMap.containsKey(entityName)) {
							int valueCount = entityMap.get(entityName);
							valueCount++;
							entityMap.put(entityName, valueCount);
						} else {
							entityMap.put(entityName, 1);
						}
						// logging the location for further clustering
						locationList.add(entities.getLocation());
						entityMap = SortMap.sortDescend(entityMap); //sort
					}
				}
				//report statistical result to server
				if (report) {
					sender.sendMessage("世界: " + w.getName() + " 中存在 " + count + " 个生物实体");
					for (Map.Entry<String, Integer> entry : entityMap.entrySet()) {
						sender.sendMessage("生物: " + entry.getKey() + ", 总数: " + entry.getValue());
					}
					sender.sendMessage("=====================================");
					entityMap.clear(); // clear for the next world
				}
			}
		} else if (Objects.equals(label, "drop")) {
			sender.sendMessage("=====================================");
			List<World> worlds = Bukkit.getWorlds();
			Map<String, Integer> entityMap = new HashMap<>();
			for (World w : worlds) {
				int count = 0;
				List<Entity> allEntitiesInAworld = w.getEntities();
//	    		List<Location> locationList = new ArrayList<Location>();
				for (Entity entities : allEntitiesInAworld) {
					// k-v map for counting Entities by type (name)
					if (entities instanceof Item) {
						count++;
						String entityName = entities.getName();
						if (entityMap.containsKey(entityName)) {
							int valueCount = entityMap.get(entityName);
							valueCount++;
							entityMap.put(entityName, valueCount);
						} else {
							entityMap.put(entityName, 1);
						}
						// logging the location for further clustering
						locationList.add(entities.getLocation());
						entityMap = SortMap.sortDescend(entityMap); //sort
					}
				}
				//report statistical result to server
				if (report) {
					sender.sendMessage("世界: " + w.getName() + " 中存在 " + count + " 个掉落物实体");
					for (Map.Entry<String, Integer> entry : entityMap.entrySet()) {
						sender.sendMessage("掉落物: " + entry.getKey() + ", 总数: " + entry.getValue());
					}
					sender.sendMessage("=====================================");
					entityMap.clear(); // clear for the next world
				}
			}
		} else if (Objects.equals(label, "tiles")) {
			sender.sendMessage("=====================================");
			List<World> worlds = Bukkit.getWorlds();
			Map<String, Integer> tileMap = new HashMap<>();
			for (World w : worlds) {
				int count = 0;
				Chunk[] chunks = w.getLoadedChunks();
//				List<Location> locationList = new ArrayList<Location>();
				for (Chunk c : chunks) {
					for (BlockState tile : c.getTileEntities()) {
						count++;
						String tileName = tile.getBlock().getType().toString();
						if (tileMap.containsKey(tileName)) {
							int valueCount = tileMap.get(tileName);
							valueCount++;
							tileMap.put(tileName,valueCount);
						} else {tileMap.put(tileName,1);}
						// logging the location for further clustering 
		    			locationList.add(tile.getLocation());
		    			tileMap = SortMap.sortDescend(tileMap); //sort
					}	
				}
	   			//report statistical result to server
	    		if (report) {
					sender.sendMessage("世界: " + w.getName() + " 中存在 " + count + " 个tiles方块");
					for (Map.Entry<String, Integer> entry : tileMap.entrySet()) {
						sender.sendMessage("tiles: " + entry.getKey() + ", 总数: " + entry.getValue());
					}
					sender.sendMessage("=====================================");
					tileMap.clear(); // clear for the next world
	    		} 
			}
		}
		return locationList;
	}
}
