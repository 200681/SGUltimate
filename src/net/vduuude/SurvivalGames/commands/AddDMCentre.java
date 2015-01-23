package net.vduuude.SurvivalGames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.vduuude.SurvivalGames.SurvivalGames;

public class AddDMCentre implements CommandExecutor {
	public static SurvivalGames plugin;

	public AddDMCentre(SurvivalGames instance) {
		plugin = instance;
		}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if(args.length==1) {
			if(player.hasPermission("sg.admin") || player.isOp()) {
				plugin.arenamanager.setDmatchCentre(player.getLocation(), args[0]);
				player.sendMessage("Added Deathmatch Center location");
			} else{
				player.sendMessage(ChatColor.RED + "Insufficient Rank");
			}
		}
		return false;
	}
	
	

}
