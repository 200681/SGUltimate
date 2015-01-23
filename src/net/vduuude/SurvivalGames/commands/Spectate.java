package net.vduuude.SurvivalGames.commands;

import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectate implements CommandExecutor {
	public static SurvivalGames plugin;

	
	public Spectate(SurvivalGames instance) {
		plugin = instance;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)  {
		Player player = (Player) sender;
		if(plugin.getGroup("Spectator").getPlayers().contains(player.getName())) {
			if(args.length==1) {
				if(Bukkit.getPlayer(args[0])!=null) {
					if(plugin.getGroup("Tribute").getPlayers().contains(Bukkit.getPlayer(args[0]).getName())) {
						player.teleport(Bukkit.getPlayer(args[0]).getLocation());
						player.sendMessage(ChatColor.AQUA + "Now spectating " + args[0] + "!");
					} else {
						player.sendMessage("This player isn't a tribute");
					}
				} else {
					player.sendMessage("This player doesn't exist");
				}
			}
			
		} else {
			player.sendMessage("You need to be a spectator to use this command");
		}
		return false;
	}


}
