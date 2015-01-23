
package net.vduuude.SurvivalGames;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class SGListener implements Listener {
	public HashMap<String, Integer> specutil = new HashMap<String, Integer>();
	public static SurvivalGames plugin;
	public HashMap<String, String> lasthit = new HashMap<String, String>();
	public SGListener(SurvivalGames instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		if(plugin.getState()==GameState.Lobby) {
			

			if(event.getPlayer().isOp() || event.getPlayer().hasPermission("sg.admin")) {
				event.setJoinMessage(ChatColor.RED + event.getPlayer().getName() + ChatColor.AQUA + " has joined the lobby.");
				plugin.getGroup("Tribute").addPlayer(event.getPlayer().getName());


			} else {
				event.setJoinMessage(plugin.getGroup("Tribute").getColor() + event.getPlayer().getName() + ChatColor.AQUA + " has joined the lobby.");
				plugin.getGroup("Tribute").addPlayer(event.getPlayer().getName());


			}
			event.getPlayer().getInventory().clear();
			event.getPlayer().getInventory().setArmorContents(null);
			event.getPlayer().setHealth(20);
			event.getPlayer().setFoodLevel(20);
			event.getPlayer().sendMessage(ChatColor.RED + "This server is running Survival Games Ultimate v1.0 coded by vduuude");
			
			if(plugin.game) {
			Location loc = new Location(Bukkit.getWorld("world"), plugin.getConfig().getDouble("LobbySpawn.x"), plugin.getConfig().getDouble("LobbySpawn.y"), plugin.getConfig().getDouble("LobbySpawn.z"));
			event.getPlayer().teleport(loc);
			}
			 
			
			

		} else if(plugin.getState()==GameState.Deathmatch || plugin.getState()==GameState.PreDeathmatch || plugin.getState()==GameState.Starting) {
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					event.getPlayer().kickPlayer(ChatColor.RED + "You cannot join at this time");
					
				}
					
				}, 3);
				}  else if(plugin.getState()==GameState.Ingame) {
					World world = Bukkit.getWorld(plugin.getConfig().getString("Arenas." + plugin.votemanager.resultarena + ".world"));
					double x = plugin.getConfig().getDouble("Arenas." + plugin.votemanager.resultarena + ".Spawns.Specspawn.x");
					double y = plugin.getConfig().getDouble("Arenas." + plugin.votemanager.resultarena + ".Spawns.Specspawn.y");
					double z = plugin.getConfig().getDouble("Arenas." + plugin.votemanager.resultarena + ".Spawns.Specspawn.z");


					event.getPlayer().teleport(new Location(world, x, y, z));
					event.getPlayer().setAllowFlight(true);
					event.getPlayer().setHealth(20);
					event.getPlayer().setFoodLevel(20);
					event.getPlayer().sendMessage(ChatColor.AQUA + "Do /spec <target tribute> to spectate a player!");
					plugin.getGroup("Spectator").addPlayer(event.getPlayer().getName());
					for(Player p: Bukkit.getOnlinePlayers()) 
						p.hidePlayer(event.getPlayer());
					
				}
		if(!plugin.playerExists(event.getPlayer().getName())) {
			plugin.updateStats(event.getPlayer(), 100, "points");
		}
		
		
		
	}
	
	@EventHandler
	public void onServerPing(ServerListPingEvent event) {
		if(plugin.getState()==GameState.Lobby) {
			event.setMotd(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "SG" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + " Now in Lobby!");
		} 
		if(plugin.getState()==GameState.Starting) {
			event.setMotd(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "SG" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + " Now in Pregame!");
		}
		if(plugin.getState()==GameState.Ingame) {
			event.setMotd(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "SG" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + " Now Ingame!");
		}
		if(plugin.getState()==GameState.PreDeathmatch) {
			event.setMotd(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "SG" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + " Now Ingame!");
		}
		
		
	}
	
	
	@EventHandler
	public void onEntityDamagebyEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			Player damager = (Player) event.getDamager();
			Player victim = (Player) event.getEntity();

			if(plugin.getGroup("Spectator").getPlayers().contains(damager.getName())) {
				event.setCancelled(true);
			} else {
				if(plugin.getGroup("Tribute").getPlayers().contains(victim.getName()) &&plugin.getGroup("Tribute").getPlayers().contains(damager.getName()) ) {
					lasthit.put(victim.getName(), damager.getName());
				}
			}
			
		} else if(event.getDamager() instanceof Arrow && event.getEntity() instanceof Player) {
			Arrow arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() instanceof Player) {
				if(plugin.getGroup("Tribute").getPlayers().contains(((Player) event.getEntity()).getName())) {
					Player shooter = (Player) arrow.getShooter();
					Player player = (Player) event.getEntity();
					lasthit.put(player.getName(), shooter.getName());
				} else {
					event.setCancelled(true);
				}
		

			}
		}
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
	 if(plugin.getGroup("Tribute").getPlayers().contains(event.getPlayer().getName())) {
		Player p = event.getPlayer();
		event.setCancelled(true);
		Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + plugin.getStats(event.getPlayer().getName(), "points") + ChatColor.GRAY + "] " + plugin.getGroup("Tribute").getColor() + p.getName() + " " + ChatColor.AQUA + event.getMessage());
		} else if(plugin.getGroup("Spectator").getPlayers().contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			for(String s : plugin.getGroup("Spectator").getPlayers())
				Bukkit.getPlayer(s).sendMessage(plugin.getGroup("Spectator").getColor() + event.getPlayer().getName() + " " + ChatColor.AQUA + event.getMessage());
		}
	 


		

	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {

		if(plugin.getState()==GameState.Starting || plugin.getState()==GameState.PreDeathmatch) {
			if(plugin.getGroup("Tribute").getPlayers().contains(event.getPlayer().getName())) {
			
		  Location from= event.getFrom();
		    Location to=event.getTo();
		    double x=Math.floor(from.getX());
		    double z=Math.floor(from.getZ());
		    if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z) {
		        x+=.5;
		        z+=.5;
		        event.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
		    }
			}
		}
		
		if(plugin.getState()==GameState.Deathmatch) {
			double x1 = plugin.getConfig().getDouble("Arenas." + plugin.votemanager.resultarena + ".DeathmatchCentre.x");
			double z2 = plugin.getConfig().getDouble("Arenas." + plugin.votemanager.resultarena + ".DeathmatchCentre.z");


			Location loc = new Location(Bukkit.getWorld(plugin.getConfig().getString("Arenas." + plugin.votemanager.resultarena + ".world")), x1,event.getPlayer().getLocation().getY(), z2);
			int maxdistance = plugin.getConfig().getInt("Arenas." + plugin.votemanager.resultarena + ".DeathmatchCentre.distance");
				if(event.getPlayer().getLocation().distance(loc) > maxdistance) {
					if(plugin.getGroup("Tribute").getPlayers().contains(event.getPlayer().getName())) {
					
						Random random = new Random();
						int rand = random.nextInt(5);
						if(rand == 2) {
							event.getPlayer().getWorld().strikeLightning(event.getPlayer().getLocation());
							event.getPlayer().sendMessage(ChatColor.RED + "Return to spawn!");
							event.getPlayer().sendMessage("" + loc.distance(event.getPlayer().getLocation()));

						}
					}
				}
			
		}
		
	}
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event)   {
		Entity entity = event.getEntity();
		if(entity instanceof Monster)   {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(plugin.getState()==GameState.Lobby) {
			plugin.getGroup("Tribute").removePlayer(event.getPlayer().getName());
		} else {
			if(plugin.getGroup("Tribute").getPlayers().contains(player.getName())) {
			Location loc = player.getLocation().clone();
			Inventory inv = player.getInventory();
			for (ItemStack item : inv.getContents()) {
			    if (item != null) {
			        loc.getWorld().dropItemNaturally(loc, item.clone());
			    }
			}
			if(player.getInventory().getHelmet()!=null) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getHelmet()));
			}
			if(player.getInventory().getChestplate()!=null) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getChestplate()));
				
			}
			if(player.getInventory().getLeggings()!=null) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getLeggings()));
			}
			if(player.getInventory().getBoots()!=null) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getBoots()));
			}
			player.getInventory().setArmorContents(null);
			inv.clear();
			plugin.getGroup("Tribute").removePlayer(player.getName());
			player.sendMessage(ChatColor.AQUA + "You have died!");
			player.setHealth(20);
			player.setFoodLevel(20);

			plugin.timemanager.win();
			if(lasthit.get(player.getName())!=null) {
				Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have killed " + player.getName() + "!");
				if(plugin.getStats(player.getName(), "points") > 119) {
					int x = plugin.getStats(player.getName(), "points")/10;
					int takeaway = Math.round(x/2);
					plugin.updateStats(player, plugin.getStats(player.getName(), "points")-takeaway, "points");
					if(lasthit.get(player.getName())!=null) {
						plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "points")+takeaway, "points");
	    				Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have killed " + player.getName() + "!");
    					Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have gained " + ChatColor.GRAY + "[" + ChatColor.GOLD + takeaway + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
    					plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "kills") +1, "kills");


	    			
	    				}
				} else if(plugin.getStats(player.getName(), "points") < 120 && plugin.getStats(player.getName(), "stats") > 50) {
					plugin.updateStats(player, plugin.getStats(player.getName(), "points")-5, "points");
					if(lasthit.get(player.getName())!=null) {
	    				Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have killed " + player.getName() + "!");
    					Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have gained " + ChatColor.GRAY + "[" + ChatColor.GOLD + 5 + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
						plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "points")+5, "points");
    					plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "kills") +1, "kills");

	    			
	    				}
					
				} else {
					if(lasthit.get(player.getName())!=null) {
	    				Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have killed " + player.getName() + "!");
    					Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have gained " + ChatColor.GRAY + "[" + ChatColor.GOLD + 5 + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
						plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(),"points" )+5, "points");   
    					plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "kills") +1, "kills");

	    				}
			}
			Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " has been eliminated");
			if(plugin.getGroup("Tribute").getPlayers().size() > 1) {
				Bukkit.broadcastMessage(ChatColor.AQUA + "There are " + ChatColor.GRAY + "[" + ChatColor.GOLD + plugin.getGroup("Tribute").getPlayers().size() + ChatColor.GRAY + "] " + ChatColor.AQUA +  "players left");
						}
			}  else {
				Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " has been eliminated");
				if(plugin.getGroup("Tribute").getPlayers().size() > 1) {
					Bukkit.broadcastMessage(ChatColor.AQUA + "There are " + ChatColor.GRAY + "[" + ChatColor.GOLD + plugin.getGroup("Tribute").getPlayers().size() + ChatColor.GRAY + "] " + ChatColor.AQUA +  "players left");
							}
			}

		}else if(plugin.getGroup("Spectator").getPlayers().contains(event.getPlayer().getName())) {
			plugin.getGroup("Spectator").removePlayer(event.getPlayer().getName());
		}
		}
		
		
	}
	@EventHandler
	public void fireDamageControl(BlockSpreadEvent event){
		  if(event.getNewState().getType()==Material.FIRE){
		    event.setCancelled(true);
		  }
		}
	
	@EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent e){
		if(!(plugin.getState()==GameState.Lobby))  {
        if (e.getInventory().getHolder() instanceof Chest){

        	BlockState chest = (Chest) e.getInventory().getHolder();
        	Block block = chest.getBlock();
        	String s = block.getLocation().getBlockX() + "" + block.getLocation().getBlockY() + block.getLocation().getBlockZ();
        	if(!plugin.chests.contains(s)) {
        		e.getInventory().clear();

        		if(plugin.getConfig().getString("Arenas." + plugin.votemanager.resultarena + ".Chests." + s + ".x")!=null) {
        			 for(int i = 0; 6 > i; i++){ //or make items.size to how many max items you want to spawn in the chest
         				Random rand = new Random();
         				ItemStack current = plugin.tier2items.get(Math.abs(rand.nextInt(plugin.tier2items.size()))); //The Math.abs is just incase its -1 which seems to happen sometimes for some reason.
         				e.getInventory().setItem(rand.nextInt(27), current); //This is picking a random slot and setting the item to the random item it got from the list.
         			}
        		} else {
        			for(int i = 0; 6 > i; i++){ 
        				Random rand = new Random();
        				ItemStack current = plugin.tier1items.get(Math.abs(rand.nextInt(plugin.tier1items.size())));
        				e.getInventory().setItem(rand.nextInt(27), current); 
        		}
            	
    			}
    			plugin.chests.add(s);

    			plugin.updateStats((Player) e.getPlayer(), plugin.getStats(e.getPlayer().getName(), "chests") + 1, "chests");

        	}
        	
        } else if(e.getInventory().getHolder() instanceof DoubleChest) {
        	DoubleChest doublechest = (DoubleChest) e.getInventory().getHolder();
        	Block block = doublechest.getLocation().getBlock();
        	String s = block.getLocation().getBlockX() + "" + block.getLocation().getBlockY() + block.getLocation().getBlockZ();
        	if(!plugin.chests.contains(s)) {
        		e.getInventory().clear();

        		if(plugin.getConfig().getString("Arenas." + plugin.votemanager.resultarena + ".Chests." + s + ".x")!=null) {
        			 for(int i = 0; 10 > i; i++){ //or make items.size to how many max items you want to spawn in the chest
         				Random rand = new Random();
         				ItemStack current = plugin.tier2items.get(Math.abs(rand.nextInt(plugin.tier2items.size()))); //The Math.abs is just incase its -1 which seems to happen sometimes for some reason.
         				e.getInventory().setItem(rand.nextInt(54), current); //This is picking a random slot and setting the item to the random item it got from the list.
         			}
        		} else {
        			for(int i = 0; 10 > i; i++){ 
        				Random rand = new Random();
        				ItemStack current = plugin.tier1items.get(Math.abs(rand.nextInt(plugin.tier1items.size())));
        				e.getInventory().setItem(rand.nextInt(54), current); 
        		}
            	
    			}
    			plugin.chests.add(s);
    			plugin.updateStats((Player) e.getPlayer(), plugin.getStats( e.getPlayer().getName(), "chests") + 1, "chests");
        	
        }
		}
		}
    }
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
		if(plugin.getState()==GameState.Lobby|| plugin.getState()==GameState.Starting) {
			event.setCancelled(true);
		}
		if(plugin.getGroup("Spectator").getPlayers().contains(player.getName())){
			event.setCancelled(true);
		}
		}
	}
	
	@EventHandler
	public void onPlayerItemPickup(PlayerPickupItemEvent event) {
		if(plugin.getGroup("Spectator").getPlayers().contains(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
		
	}
	

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(plugin.getState()==GameState.Ingame) {
    		List<Material> items;
    		items = Arrays.asList(Material.LEAVES, Material.LEAVES_2, Material.LONG_GRASS, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM);
    		if(items.contains(event.getBlock().getType()) && plugin.getGroup("Tribute").getPlayers().contains(event.getPlayer().getName())) {
    			
    		} else {
    			event.setCancelled(true);
    		}
    		
		}  else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		if(plugin.getGroup("Spectator").getPlayers().contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			Player player = event.getPlayer();
			if(!plugin.timemanager.finished) {
			if(specutil.get(player.getName())!=null) {
				if((specutil.get(player.getName()) < plugin.getGroup("Tribute").getPlayers().size())) {
					event.getPlayer().teleport(Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(specutil.get(player.getName()))).getLocation());
					event.getPlayer().sendMessage(ChatColor.AQUA + "Now spectating " + Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(specutil.get(player.getName()))).getName() + "!");
					int y = specutil.get(player.getName());
					specutil.remove(player.getName());
					specutil.put(player.getName(), y + 1);
				} else {
					event.getPlayer().teleport(Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(0)).getLocation());
					event.getPlayer().sendMessage(ChatColor.AQUA + "Now spectating " + Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(0)).getName() + "!");
					specutil.put(event.getPlayer().getName(), 1);
				}
		
			} else {
				event.getPlayer().teleport(Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(0)).getLocation());
				event.getPlayer().sendMessage(ChatColor.AQUA + "Now spectating " + Bukkit.getPlayer(plugin.getGroup("Tribute").getPlayers().get(0)).getName() + "!");
				specutil.put(event.getPlayer().getName(), 1);
			}
			}
		}
		if(plugin.getState()==GameState.Lobby) {
			event.setCancelled(true);
		}
	}
	

	@EventHandler
	public void onDamage(EntityDamageEvent e ) {
	        if(e.getEntity() instanceof Player) {
	        	final Player player = (Player) e.getEntity();

	        	if(!(plugin.getState()==GameState.Lobby))  {
	        		if(e.getDamage()>= player.getHealth()) {
	        			if(plugin.getGroup("Tribute").getPlayers().contains(player.getName())) {
		        			
		        		
		        		e.setCancelled(true);
		    				Location loc = player.getLocation().clone();
		    				Inventory inv = player.getInventory();
		    				for (ItemStack item : inv.getContents()) {
		    				    if (item != null) {
		    				        loc.getWorld().dropItemNaturally(loc, item.clone());
		    				    }
		    				}
		    				if(player.getInventory().getHelmet()!=null) {
		    					player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getHelmet()));
		    				}
		    				if(player.getInventory().getChestplate()!=null) {
		    					player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getChestplate()));
		    					
		    				}
		    				if(player.getInventory().getLeggings()!=null) {
		    					player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getLeggings()));
		    				}
		    				if(player.getInventory().getBoots()!=null) {
		    					player.getWorld().dropItemNaturally(loc, new ItemStack(player.getInventory().getBoots()));
		    				}
		    				player.getInventory().setArmorContents(null);
		    				inv.clear();
		    				plugin.getGroup("Tribute").removePlayer(player.getName());
		    				plugin.getGroup("Spectator").addPlayer(player.getName());
		    				player.setAllowFlight(true);
		    				for(Player pl : Bukkit.getOnlinePlayers())
		    					pl.hidePlayer(player);
		    				if(plugin.getStats(player.getName(), "points") > 119) {
		    					int x = plugin.getStats(player.getName(), "points")/10;
		    					int takeaway = Math.round(x/2);
		    					plugin.updateStats(player, plugin.getStats(player.getName(), "points")-takeaway, "points");
		    					player.sendMessage(ChatColor.AQUA + "You have lost " + ChatColor.GRAY + "[" + ChatColor.GOLD + takeaway + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
		    					if(lasthit.get(player.getName())!=null) {
		    						plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "points")+takeaway, "points");
				    				Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have killed " + player.getName() + "!");
			    					Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have gained " + ChatColor.GRAY + "[" + ChatColor.GOLD + takeaway + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
			    					plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "kills") +1, "kills");

				    			
				    				}
		    				} else if(plugin.getStats(player.getName(), "points") < 120 && plugin.getStats(player.getName(), "points") > 50) {
		    					plugin.updateStats(player, plugin.getStats(player.getName(), "points")-5, "points");
		    					player.sendMessage(ChatColor.AQUA + "You have lost " + ChatColor.GRAY + "[" + ChatColor.GOLD + 5 + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
		    					if(lasthit.get(player.getName())!=null) {
				    				Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have killed " + player.getName() + "!");
			    					Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have gained " + ChatColor.GRAY + "[" + ChatColor.GOLD + 5 + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
		    						plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "points")+5, "points");
			    					plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "kills") +1, "kills");


				    			
				    				}
		    					
		    				} else {
		    					player.sendMessage(ChatColor.AQUA + "You have lost " + ChatColor.GRAY + "[" + ChatColor.GOLD + 0 + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
		    					if(lasthit.get(player.getName())!=null) {
				    				Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have killed " + player.getName() + "!");
			    					Bukkit.getPlayer(lasthit.get(player.getName())).sendMessage(ChatColor.AQUA + "You have gained " + ChatColor.GRAY + "[" + ChatColor.GOLD + 5 + ChatColor.GRAY + "] " + ChatColor.AQUA + "points!");
		    						plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "points")+5, "points");
			    					plugin.updateStats(Bukkit.getPlayer(lasthit.get(player.getName())), plugin.getStats(Bukkit.getPlayer(lasthit.get(player.getName())).getName(), "kills") +1, "kills");

				    				}
		    				}
		    				Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
								public void run() {
									player.sendMessage(ChatColor.AQUA + "You have died!");
									player.sendMessage(ChatColor.AQUA + "Do /spec <target tribute> to spectate a player!");
				    				player.setHealth(20);
				    				player.setFoodLevel(20);
				    				Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " has been eliminated");
				    				if(plugin.getGroup("Tribute").getPlayers().size() > 1) {
				    					Bukkit.broadcastMessage(ChatColor.AQUA + "There are " + ChatColor.GRAY + "[" + ChatColor.GOLD + plugin.getGroup("Tribute").getPlayers().size() + ChatColor.GRAY + "] " + ChatColor.AQUA +  "players left");

				    				}
								}
		    				}, 4);
		    			
		    				plugin.timemanager.win();
		    				
		        	} else if(plugin.getGroup("Spectator").getPlayers().contains(player.getName())) {
		        		e.setCancelled(true);
		        	}
	        	} 
	               
	        	}
	        	if(plugin.getState()==GameState.Lobby || plugin.getState()==GameState.Starting || plugin.getState()==GameState.PreDeathmatch) {
	        		e.setCancelled(true);
	        	}
	        }
	    }
	

}

