package net.vduuude.SurvivalGames.commands;

import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Addspawn implements CommandExecutor {
	public static SurvivalGames plugin;
	public Addspawn(SurvivalGames instance) {
		plugin = instance;
	}
	
	@SuppressWarnings("static-access")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)  {
		
		Player player = (Player) sender;
		if(args.length==2) {
			
			if(player.hasPermission("sg.admin") || player.isOp()) {	
			plugin.arenamanager.addSpawn(args[0], player.getLocation(),Integer.parseInt(args[1]));
			player.sendMessage("Added spawn");
			} else {
				player.sendMessage(ChatColor.DARK_RED + "Insufficient Rank");
			}
		} else 	{
			
		}
		return false;
	}

}
