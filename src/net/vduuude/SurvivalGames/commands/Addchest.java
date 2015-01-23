package net.vduuude.SurvivalGames.commands;

import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Addchest implements CommandExecutor {
	public static SurvivalGames plugin;

	public Addchest(SurvivalGames instance) {
		plugin = instance;
	}


	@SuppressWarnings({ "static-access", "deprecation" })
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,  String[] args) {
		Player player = (Player) sender;
		if(args.length==1) {
			if(player.hasPermission("sg.admin") || player.isOp()) {
			plugin.arenamanager.addChest(args[0], player.getTargetBlock(null, 40).getLocation());
			player.sendMessage("Added Chest");
			} else {
				player.sendMessage(ChatColor.DARK_RED + "Insufficient Rank");
			}
		} else {
			
		}
	
		return false;
	}
	
	

}
