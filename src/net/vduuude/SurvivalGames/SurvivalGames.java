
package net.vduuude.SurvivalGames;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.vduuude.SurvivalGames.commands.AddDMCentre;
import net.vduuude.SurvivalGames.commands.Addarena;
import net.vduuude.SurvivalGames.commands.Addchest;
import net.vduuude.SurvivalGames.commands.Addspawn;
import net.vduuude.SurvivalGames.commands.Addspectatorspawn;
import net.vduuude.SurvivalGames.commands.Edit;
import net.vduuude.SurvivalGames.commands.Ping;
import net.vduuude.SurvivalGames.commands.SetLobbySpawn;
import net.vduuude.SurvivalGames.commands.Spectate;
import net.vduuude.SurvivalGames.commands.Stats;
import net.vduuude.SurvivalGames.commands.Vote;
import net.vduuude.SurvivalGames.managers.ArenaManager;
import net.vduuude.SurvivalGames.managers.TimeManager;
import net.vduuude.SurvivalGames.managers.VoteManager;
import net.vduuude.SurvivalGames.managers.ZipManager;
import net.vduuude.SurvivalGames.usergroups.Spectator;
import net.vduuude.SurvivalGames.usergroups.Tribute;
import net.vduuude.SurvivalGames.usergroups.UserGroups;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalGames extends JavaPlugin implements Listener{
	public static SurvivalGames plugin;
	public String name = "[SurvivalGames] ";
	public List<UserGroups> groups = new ArrayList<UserGroups>();
	public final ArenaManager arenamanager = new ArenaManager(this);
	public final VoteManager votemanager = new VoteManager(this);
	public final Edit edit = new Edit(this);
	public List<String> votealiases = new ArrayList<String>();
	public List<String> spectatealiases = new ArrayList<String>();
	public final SetLobbySpawn setlobbyspawn = new SetLobbySpawn(this);
	public final ZipManager zipmanager = new ZipManager(this);
	public final Ping ping = new Ping(this);
	public final Stats stats = new Stats(this);
	public final SGListener sglistener = new SGListener(this);
	public final AddDMCentre adddmcentre = new AddDMCentre(this);
	public final Addchest addchest = new Addchest(this);
	public final Vote vote = new Vote(this);
	public final Addspawn addspawn = new Addspawn(this);
	public final TimeManager timemanager = new TimeManager(this);
	public final Addspectatorspawn addspectatorspawn = new Addspectatorspawn(this);
	public final Spectate spectate = new Spectate(this);
	public List<String> arenas;
	public List<ItemStack> tier1items = new ArrayList<ItemStack>();
	public List<ItemStack> tier2items = new ArrayList<ItemStack>();
	public List<String> chests = new ArrayList<String>();
	public boolean game;
	Logger logger = Logger.getLogger("Minecraft");
	public GameState state;
	File usersFile;
	FileConfiguration users;
	public final Addarena addarena = new Addarena(this);
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		logger.info(name + "has been enabled");
		groups.add(new Tribute());
		groups.add(new Spectator());
		state = GameState.Lobby;
		Bukkit.getPluginManager().registerEvents(this.sglistener, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		loadConfig();
		 usersFile = new File(getDataFolder(), "users.yml");
		if(!usersFile.exists()) {
			try {
				usersFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 users = YamlConfiguration.loadConfiguration(usersFile);
		users.options().copyDefaults(true);
		saveUsers();
	    arenas = this.getConfig().getStringList("ArenaList");
	    game = this.getConfig().getBoolean("Game");
	    tier1items = (List<ItemStack>) this.getConfig().get("Tier1items");
	    tier2items = (List<ItemStack>) this.getConfig().get("Tier2items");
	    votealiases.add("v");
	    spectatealiases.add("spec");
		getConfig().addDefault("ArenaList", arenas);
		getConfig().addDefault("Game", game);
		getCommand("vote").setExecutor(this.vote);
		getCommand("vote").setAliases(votealiases);
		getCommand("addchest").setExecutor(this.addchest);
		getCommand("addarena").setExecutor(this.addarena);
		getCommand("edit").setExecutor(this.edit);
		getCommand("stats").setExecutor(this.stats);
		getCommand("ping").setExecutor(this.ping);
		getCommand("spectate").setExecutor(this.spectate);
		getCommand("adddmcentre").setExecutor(this.adddmcentre);
		getCommand("setlobbyspawn").setExecutor(this.setlobbyspawn);
		getCommand("addspawn").setExecutor(this.addspawn);
		getCommand("addspectatorspawn").setExecutor(this.addspectatorspawn);
		saveConfig();
		if(game) {
			Bukkit.getScheduler().runTaskLater(this, new Runnable() {
				public void run() {
					votemanager.randomizeArenaList(arenas);
					timemanager.initLobby();
				}
			}, 2);
			
			
		}		
	}
	
	public List<String> getArenas() {
		return arenas;
	}
	
	public void loadConfig() {
		getConfig().set("Messages.Countdown.2Minute", "&7[&6120&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.1Minute", "&7[&660&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.30Secs", "&7[&630&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.10Secs", "&7[&610&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.5Secs", "&7[&65&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.4Secs", "&7[&64&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.3Secs", "&7[&63&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.2Secs", "&7[&62&7] &bseconds until lobby ends!");
		getConfig().set("Messages.Countdown.1Secs", "&7[&61&7] &bsecond until lobby ends!");
		getConfig().set("Messages.GameCountdown.30Secs", "&7[&630&7] &bseconds until the game begins!");
		getConfig().set("Messages.GameCountdown.15Secs", "&7[&615&7] &bseconds until the game begins!");
		getConfig().set("Messages.GameCountdown.10Secs", "&7[&610&7] &bseconds until the game begins!");
		getConfig().set("Messages.GameCountdown.5Secs", "&7[&65&7] &bseconds until the game begins!");
		getConfig().set("Messages.GameCountdown.4Secs", "&7[&64&7] &bseconds until the game begins!");
		getConfig().set("Messages.GameCountdown.3Secs", "&7[&63&7] &bseconds until the game begins!");
		getConfig().set("Messages.GameCountdown.2Secs", "&7[&62&7] &bseconds until the game begins!");
		getConfig().set("Messages.GameCountdown.1Secs", "&7[&61&7] &bsecond until the game begins!");
		getConfig().set("Messages.GameCountdown.0Secs", "&7The Games have begun!");
		getConfig().set("Messages.DeathmatchCountdown.20Mins", "&7[&620&7] &bminutes until the deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.15Mins", "&7[&615&7] &bminutes until the deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.10Mins", "&7[&610&7] &bminutes until the deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.5Mins", "&7[&65&7] &bminutes until the deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.3Mins", "&7[&63&7] &bminutes until the deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.1Minute", "&7[&660&7] &bseconds until deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.30Secs", "&7[&630&7] &bseconds until deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.15Secs", "&7[&615&7] &bseconds until deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.10Secs", "&7[&610&7] &bseconds until deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.5Secs", "&7[&65&7] &bseconds until deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.4Secs", "&7[&64&7] &bseconds until deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.3Secs", "&7[&63&7] &bseconds deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.2Secs", "&7[&62&7] &bseconds until deathmatch begins!");
		getConfig().set("Messages.DeathmatchCountdown.1Secs", "&7[&61&7] &bsecond until deathmatch begins!");
		saveConfig();

	}
	public GameState getState() {
		return state;
	}
	public void setState(GameState state) {
		this.state = state;
	}
	
	
	@Override
	public void onDisable() {
		logger.info(name + "has been disabled");
	}
	
	public void saveUsers() {
		try {
			users.save(usersFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public UserGroups getGroup(String name) {
		for(UserGroups g : groups ) {
			if(name.equalsIgnoreCase(g.getName())) {
				return g;
			}
		}
		return null;
		
	}
	
	public int getStats(String name, String value) {
		if(value.equalsIgnoreCase("points")) {
		int points = users.getInt("Players." + name + ".points");
		if(points == 0) {
			return 0;
		}
		return points;
		}
		
		if(value.equalsIgnoreCase("wins")) {
			int wins = users.getInt("Players." + name + ".wins");
			if(wins==0) {
				return 0;
			}
			return wins;
		}
		if(value.equalsIgnoreCase("kills")) {
			int kills = users.getInt("Players." + name + ".kills");
			if(kills == 0) {
				return 0;
			}
			return kills;
		}
		if(value.equalsIgnoreCase("chests")) {
			int chests = users.getInt("Players." + name + ".chests");
			if(chests == 0) {
				return 0;
			}
			return chests;
		}
		return 0;
		
		
				
	}
	
	public boolean playerExists(String name) {
		if(users.contains("Players." + name)){
			return true;
		}
		return false;
	}
	
	public void updateStats(Player p, int input, String value) {
		if(value.equalsIgnoreCase("points")) {
			users.set("Players." + p.getName() + ".points", input);
			saveUsers();
		}
		if(value.equalsIgnoreCase("wins")) {
			users.set("Players." + p.getName() + ".wins", input);
			saveUsers();
		}
		if(value.equalsIgnoreCase("kills")) {
			users.set("Players." + p.getName() + ".kills", input);
			saveUsers();
		}
		
		if(value.equalsIgnoreCase("chests")) {
			users.set("Players." + p.getName() + ".chests", input);
			saveUsers();
		}
	
		
	}
	


}
