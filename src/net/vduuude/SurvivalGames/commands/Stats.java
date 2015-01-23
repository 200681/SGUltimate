package net.vduuude.SurvivalGames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.vduuude.SurvivalGames.SurvivalGames;

public class Stats implements CommandExecutor {
	public static SurvivalGames plugin;

	public Stats(SurvivalGames instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if(args.length==0) {
			player.sendMessage(ChatColor.GRAY + player.getName() + "'s stats:");
			player.sendMessage(ChatColor.GRAY + "Points: " + plugin.getStats(player.getName(), "points"));
			player.sendMessage(ChatColor.GRAY + "Wins: " + plugin.getStats(player.getName(), "wins"));
			player.sendMessage(ChatColor.GRAY + "Kills: " + plugin.getStats(player.getName(), "kills"));
			player.sendMessage(ChatColor.GRAY + "Chests opened: " + plugin.getStats(player.getName(), "chests"));



		}
		if(args.length==1) {
			if(plugin.playerExists(args[0])) {

			player.sendMessage(ChatColor.GRAY + args[0] + "'s stats:");
			player.sendMessage(ChatColor.GRAY + "Points: " + plugin.getStats(args[0], "points"));
			player.sendMessage(ChatColor.GRAY + "Wins: " + plugin.getStats(args[0], "wins"));
			player.sendMessage(ChatColor.GRAY + "Kills: " + plugin.getStats(args[0], "kills"));
			player.sendMessage(ChatColor.GRAY + "Chests opened: " + plugin.getStats(args[0], "chests"));
			} else {
				player.sendMessage(ChatColor.RED + "This player does not exist!");
			}
			
		}
		return false;
	}
	
	
	
	

}
