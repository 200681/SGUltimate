package net.vduuude.SurvivalGames.commands;

import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Addarena implements CommandExecutor {
	public static  SurvivalGames plugin;

	public Addarena(SurvivalGames instance) {
		plugin = instance;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)  {
		Player player = (Player) sender;
		
		if(args.length==2) {
			if(player.hasPermission("sg.admin") || player.isOp()) {
				
			plugin.arenamanager.createArena(args[0], args[1], player.getName());
			player.sendMessage(ChatColor.GREEN + "Created arena " + args[0]);
			} else {
				player.sendMessage(ChatColor.DARK_RED + "Insufficient Rank");
			}
		}else {
			
		}
		
			return false;
		} 
	
}
