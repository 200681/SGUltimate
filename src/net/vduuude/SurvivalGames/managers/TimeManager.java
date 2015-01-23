package net.vduuude.SurvivalGames.managers;

import java.io.File;
import java.io.IOException;

import net.vduuude.SurvivalGames.GameState;
import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class TimeManager {
	public static SurvivalGames plugin;
	public boolean deathmatchcountdown = false;
	public boolean finished = false;
	public int exacttime;
	public TimeManager(SurvivalGames instance) {
		plugin = instance;
	}
	public void initLobby() {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.2Minute")));
		Bukkit.broadcastMessage(ChatColor.AQUA + "Voting Options:");
		if(plugin.votemanager.hmaps.get(1)!=null) {
			Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + plugin.votemanager.hmaps.get(1) + " votes");
		} else {
			Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) +  ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

		}
		if(plugin.votemanager.hmaps.get(2)!=null) {
			Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY  + plugin.votemanager.hmaps.get(2) + " votes");
		} else {
			Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

		}
		if(plugin.votemanager.hmaps.get(3)!=null) {
			Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +plugin.votemanager.hmaps.get(3) + " votes");
		} else {
			Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

		}
		if(plugin.votemanager.hmaps.get(4)!=null) {
			Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + plugin.votemanager.hmaps.get(4) + " votes");
		} else {
			Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +"0" + " votes");

		}
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.AQUA + "Voting Options:");
				if(plugin.votemanager.hmaps.get(1)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + plugin.votemanager.hmaps.get(1) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) +  ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(2)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY  + plugin.votemanager.hmaps.get(2) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(3)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +plugin.votemanager.hmaps.get(3) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(4)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + plugin.votemanager.hmaps.get(4) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +"0" + " votes");

				}

			}
			
		}, 400);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.AQUA + "Voting Options:");
				if(plugin.votemanager.hmaps.get(1)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + plugin.votemanager.hmaps.get(1) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) +  ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(2)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY  + plugin.votemanager.hmaps.get(2) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(3)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +plugin.votemanager.hmaps.get(3) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(4)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + plugin.votemanager.hmaps.get(4) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +"0" + " votes");

				}			
			}
			
		}, 800);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.1Minute")));

			}
			
		}, 1200);	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.AQUA + "Voting Options:");
				if(plugin.votemanager.hmaps.get(1)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + plugin.votemanager.hmaps.get(1) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) +  ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(2)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY  + plugin.votemanager.hmaps.get(2) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(3)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +plugin.votemanager.hmaps.get(3) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(4)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + plugin.votemanager.hmaps.get(4) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +"0" + " votes");

				}		
			}
			
		}, 1200);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.AQUA + "Voting Options:");
				if(plugin.votemanager.hmaps.get(1)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + plugin.votemanager.hmaps.get(1) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) +  ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(2)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY  + plugin.votemanager.hmaps.get(2) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(3)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +plugin.votemanager.hmaps.get(3) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(4)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + plugin.votemanager.hmaps.get(4) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +"0" + " votes");

				}		
			}
			
		}, 1600);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.30Secs")));

			}
			
		}, 1800);	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.AQUA + "Voting Options:");
				if(plugin.votemanager.hmaps.get(1)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + plugin.votemanager.hmaps.get(1) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) +  ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(2)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY  + plugin.votemanager.hmaps.get(2) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(3)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +plugin.votemanager.hmaps.get(3) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

				}
				if(plugin.votemanager.hmaps.get(4)!=null) {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + plugin.votemanager.hmaps.get(4) + " votes");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +"0" + " votes");

				}		
			}
			
		}, 2000);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.10Secs")));
			}
			
		}, 2200);	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.5Secs")));
			}
			
		}, 2300);	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.4Secs")));
			}
			
		}, 2320);	Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.3Secs")));
			}
			
		}, 2340);	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.2Secs")));
			}
			
		}, 2360);	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Countdown.1Secs")));
			}
			
		}, 2380);	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			public void run() {
				if(plugin.getGroup("Tribute").getPlayers().size()>3) {
					plugin.votemanager.getGameArena();
					start();
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "Not enough players... Restarting Timer");
					initLobby();
				}
			}
			
		}, 2400);	
		
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void tptoCentre() {
		World world = Bukkit.getWorld(plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".world"));
		for (int x = 0; x<=plugin.getGroup("Tribute").getPlayers().size(); x++) {
			if(x == plugin.getGroup("Tribute").getPlayers().size()) {
				
			} else {
				float yaw = (plugin.getConfig().getInt("Arenas." +plugin.votemanager.resultarena + ".Spawns." + x + ".yaw"));
				float pitch = (plugin.getConfig().getInt("Arenas." +plugin.votemanager.resultarena + ".Spawns." + x + ".pitch"));

				Location loc = new Location(world, plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns." + x + ".x" ),plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns." + x + ".y") , plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns." + x + ".z"), yaw, pitch);
				Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(x)).teleport(loc);



			}
		}
		for (int x = 0; x<=plugin.getGroup("Spectator").getPlayers().size(); x++) {
			if(x == plugin.getGroup("Spectator").getPlayers().size()) {
				
			} else {


				Location loc = new Location(world, plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns.Specspawn" + ".x" ),plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns.Specspawn" + ".y") , plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns.Specspawn" + ".z"));
				Bukkit.getPlayer(plugin.getGroup("Spectator").getPlayers().get(x)).teleport(loc);



			}
		}
		plugin.setState(GameState.PreDeathmatch);
		finalDeathmatchCountdown();
		
		}

	@SuppressWarnings("deprecation")
	public void start() {
		plugin.getServer().createWorld(new WorldCreator(plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".world")));
		World world = Bukkit.getWorld(plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".world"));


		for (int x = 0; x<=plugin.getGroup("Tribute").getPlayers().size(); x++) {
			if(x == plugin.getGroup("Tribute").getPlayers().size()) {
				
			} else {
				float yaw = (plugin.getConfig().getInt("Arenas." +plugin.votemanager.resultarena + ".Spawns." + x + ".yaw"));
				float pitch = (plugin.getConfig().getInt("Arenas." +plugin.votemanager.resultarena + ".Spawns." + x + ".pitch"));

				Location loc = new Location(world, plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns." + x + ".x" ),plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns." + x + ".y") , plugin.getConfig().getDouble("Arenas." +plugin.votemanager.arenavotelist.get(plugin.votemanager.result) + ".Spawns." + x + ".z"), yaw, pitch);
				Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(x)).teleport(loc);

			}
		
		}
		plugin.setState(GameState.Starting);
		pregameCountdown();
	}
	
	@SuppressWarnings("deprecation")
	public void win() {
		if(plugin.getGroup("Tribute").getPlayers().size()==1) {
		for(String p : plugin.getGroup("Tribute").getPlayers()) {
			Firework f = (Firework) Bukkit.getPlayer(p).getWorld().spawn(Bukkit.getPlayer(p).getLocation(), Firework.class);
			FireworkMeta fm = f.getFireworkMeta();
			fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).withColor(Color.RED, Color.BLUE).with(Type.CREEPER).build());
			f.setFireworkMeta(fm);
			Bukkit.broadcastMessage(ChatColor.RED + p + " has won the Survival Games. The server is restarting in 5 seconds");
			plugin.updateStats(Bukkit.getPlayer(p), plugin.getStats(Bukkit.getPlayer(p).getName(), "points") + 25, "points");
			plugin.updateStats(Bukkit.getPlayer(p), plugin.getStats(Bukkit.getPlayer(p).getName(), "wins") + 1, "wins");
			finished = true;
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					for(Player p : Bukkit.getOnlinePlayers())
						p.kickPlayer("The Server is Restarting.");
				       World worldtodelete = Bukkit.getWorld(plugin.getConfig().getString("Arenas." + plugin.votemanager.resultarena + ".world"));
				       Bukkit.getServer().unloadWorld((worldtodelete), plugin.isInitialized()); 
			       File folder = worldtodelete.getWorldFolder();
			       plugin.zipmanager.deleteDirectory(folder); 
			        try {
						plugin.zipmanager.extractZIP(new File(plugin.getConfig().getString("Arenas." + plugin.votemanager.resultarena + ".world") + ".zip"), new File(plugin.getServer().getWorldContainer().getAbsolutePath()));
						Bukkit.getServer().shutdown();
					} catch (IOException e) {
						e.printStackTrace();
					}
			       Bukkit.getServer().shutdown();

				}	
			}, 100);
		}
		} else if(plugin.getGroup("Tribute").getPlayers().size()==3) {
			begindeathmatch();
		}
		}
	

	
	public void finalDeathmatchCountdown() {
		if(!finished) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.15Secs")));
		}
			
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.10Secs")));
				}
			}
		},100);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.5Secs")));
				}
			}
		},200);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.4Secs")));
			}
			}
		},220);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.3Secs")));
			}
			}
		},240);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.2Secs")));
			}
			}
		},260);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.1Secs")));
			}
			}
		},280);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
				
				plugin.setState(GameState.Deathmatch);
				Bukkit.broadcastMessage(ChatColor.AQUA + "Deathmatch has begun");
				}
			}
		},300);

	

	
	}
	public void pregameCountdown() {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.30Secs")));
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.15Secs")));
			}
		},300);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.10Secs")));
			}
		},400);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.5Secs")));
			}
		},500);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.4Secs")));
			}
		},520);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.3Secs")));
			}
		},540);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.2Secs")));
			}
		},560);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.1Secs")));
			}
		},580);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.GameCountdown.0Secs")));
				plugin.setState(GameState.Ingame);
				begingame();

			}
		},600);

	

	
	}
	
	public void begindeathmatch() {
		deathmatchcountdown = true;
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.1Minute")));	
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.30Secs")));	
				}
			}
		}, 600);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.15Secs")));	
				}
			}
		}, 900);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.10Secs")));	
				}
			}
		}, 1000);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.5Secs")));	
				}
			}
		}, 1100);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.4Secs")));	
				}
			}
		}, 1120);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.3Secs")));	
				}
			}
		}, 1140);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.2Secs")));	
				}
			}
		}, 1160);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.1Secs")));	
				}
			}
		}, 1180);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				if(!finished) {
					Bukkit.broadcastMessage(ChatColor.AQUA + "Deathmatch has begun");	
					tptoCentre();
				}
				
			}
		}, 1200);
		

		
	}



	public void begingame() {
		Bukkit.getWorld(plugin.getConfig().getString("Arenas."+ plugin.votemanager.resultarena + ".world")).setTime(0L);
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.20Mins")));		
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {		
			public void run() {
				if(!deathmatchcountdown) {
 
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.15Mins")));		
				}
			}		
		}, 6000);
	Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {	
			public void run() {
				if(!deathmatchcountdown) {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.10Mins")));		
				}
			}			
		}, 12000);
	Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {	
		public void run() {
			if(!deathmatchcountdown) {
				plugin.chests.clear();
				Bukkit.broadcastMessage(ChatColor.AQUA + "Sponsors have refilled chests!");
			}
		}			
	}, 14400);
	Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {	
		public void run() {
			if(!deathmatchcountdown) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.5Mins")));		
			}
		}			
	}, 18000);
	Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {	
		public void run() {
			if(!deathmatchcountdown) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DeathmatchCountdown.3Mins")));		
			}
		}			
	}, 20400);
	Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {	
		public void run() {
			if(!deathmatchcountdown) {
				begindeathmatch();
			}
		}			
	}, 22800);
	
	
	

}
}
