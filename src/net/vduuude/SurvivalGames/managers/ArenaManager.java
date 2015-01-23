package net.vduuude.SurvivalGames.managers;

import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;

public class ArenaManager {
	public static SurvivalGames plugin;
	
	public ArenaManager(SurvivalGames instance) {
		plugin = instance;
	}


	@SuppressWarnings("deprecation")
	public void createArena(String name, String worldname, String PlayerName) {
		plugin.getConfig().set("Arenas." + name + ".name", name);
		plugin.getConfig().set("Arenas." + name + ".world", worldname);
		plugin.arenas.add(name);
		plugin.getConfig().set("ArenaList", plugin.getArenas());
		plugin.saveConfig();
		 plugin.getServer().createWorld(new WorldCreator(worldname));
		 Bukkit.getPlayer(PlayerName).teleport(Bukkit.getWorld(worldname).getSpawnLocation());
	}
	
	public void setDmatchCentre(Location loc, String arenaname) {
		plugin.getConfig().set("Arenas." + arenaname + ".DeathmatchCentre.x", loc.getX());
		plugin.getConfig().set("Arenas." + arenaname + ".DeathmatchCentre.z", loc.getZ());
		plugin.getConfig().set("Arenas." + arenaname + ".DeathmatchCentre.distance", 25);
		plugin.saveConfig();
	}
	
	
	public static void addSpawn(String arenaname, Location spawn, int spawnnumber) {
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + spawnnumber + ".x", spawn.getX());
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + spawnnumber + ".y", spawn.getY());
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + spawnnumber + ".z", spawn.getZ());
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + spawnnumber + ".yaw", spawn.getYaw());
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + spawnnumber + ".pitch", spawn.getPitch());
		plugin.saveConfig();


	}
	public static void addSpectatorSpawn(String arenaname, Location spawn) {
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + "Specspawn" + ".x", spawn.getX());
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + "Specspawn" + ".y", spawn.getY());
		plugin.getConfig().set("Arenas." + arenaname + ".Spawns." + "Specspawn" + ".z", spawn.getZ());
	    plugin.saveConfig();

	
	}
	public static void setLobbySpawn(Location loc) {
		plugin.getConfig().set("LobbySpawn.x", loc.getX());
		plugin.getConfig().set("LobbySpawn.y", loc.getY());
		plugin.getConfig().set("LobbySpawn.z", loc.getZ());
	    plugin.saveConfig();
	}
	public static void addChest(String arenaname, Location location) {
		String number = "" + location.getBlockX() + location.getBlockY() + location.getBlockZ();
		plugin.getConfig().set("Arenas." + arenaname + ".Chests." + number + ".x" , location.getX());
		plugin.getConfig().set("Arenas." + arenaname + ".Chests." + number + ".y" , location.getY());
		plugin.getConfig().set("Arenas." + arenaname + ".Chests." + number + ".z" , location.getZ());
		plugin.saveConfig();		



	}

}
