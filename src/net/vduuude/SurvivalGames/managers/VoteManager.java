package net.vduuude.SurvivalGames.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class VoteManager {

	public static SurvivalGames plugin;
	public List<String> arenavotelist = new ArrayList<String>();
	public List<String> voted = new ArrayList<String>();
	public String resultarena;
	public HashMap<Integer ,Integer> hmaps = new HashMap<Integer, Integer>();
	int result;

	public VoteManager(SurvivalGames instance) {
		plugin = instance;
	}
	
	public void randomizeArenaList(List<String> l) {
		for(int x = 1; x<=5; x++) {
			Random random = new Random();
			int y = random.nextInt(l.size());
			HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
			hmap.put(x, y);
			if(arenavotelist.contains(l.get(hmap.get(x)))) {
				x--;
			} else {
				arenavotelist.add((String) l.get(hmap.get(x)));
			}
		}
		
		
	}
	
	public void addVote(int votenumber, Player p) {
		if(votenumber>arenavotelist.size()) {
			p.sendMessage(ChatColor.AQUA + "Please do /vote 1-4");
		} else {
			if(voted.contains(p.getName())) {
		
				p.sendMessage(ChatColor.AQUA + "You have already voted");
			} else {
				if(hmaps.containsKey(votenumber)) {
					hmaps.put(votenumber, hmaps.get(votenumber) + 1 );
					p.sendMessage(ChatColor.AQUA + "Thank You for voting for " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(votenumber) + ".name"));
					p.sendMessage(ChatColor.AQUA + "Your map now has " + plugin.votemanager.hmaps.get(votenumber) + " votes.");
					voted.add(p.getName());
				} else {
					hmaps.put(votenumber, 1);
					p.sendMessage(ChatColor.AQUA + "Thank You for voting for " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(votenumber) + ".name"));
					p.sendMessage(ChatColor.AQUA + "Your map now has " + plugin.votemanager.hmaps.get(votenumber) + " votes.");
					voted.add(p.getName());
				}
			}
	}
		getGameArena();

		
	}
	
	public void getGameArena() {
		List<Integer> list = new ArrayList<Integer>();
		for(int x = 1; x<=5; x++) {
			if(hmaps.containsKey(x)) {
				list.add(hmaps.get(x));
			} else {
				
			}
		}
		if(list.size()>0) {
			int maxvotes = Collections.max(list);
			for(int x = 1; x<=5; x++) {
				if(hmaps.get(x)!=null) {
					if(maxvotes == hmaps.get(x)) {
						result = x;
						resultarena = arenavotelist.get(result);
					}
		}
		
			}
			
		} else {
		//	result = new Random().nextInt(4+1);
			result = 1;
			resultarena = arenavotelist.get(result);
		}
		
	}

}
