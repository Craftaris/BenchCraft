package me.benchcraft.bcf.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.benchcraft.bcf.BenchCraft;

public class ListenerClass implements Listener {
	
	BenchCraft plugin;
	  
	public ListenerClass(BenchCraft plugin)
	{
		  
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;

	}
	  
	/**
	* AsyncPlayerChatEvent odpowiada za synchronizację czatu.
	* W tym miejscu zostaje zmieniony format czatu.
	*/
	  
	@EventHandler (priority = EventPriority.HIGH)
	public void chatFormat(AsyncPlayerChatEvent event){
		Player p = event.getPlayer();
	
		event.setFormat(ChatColor.GRAY + p.getDisplayName() + ChatColor.WHITE + ": " + event.getMessage());	
	  
	}
	  
	/**
	* PlayerJoinEvent odpala się przy wejściu gracza na serwer.
	* Metoda setJoinMessage ustawia wiadomość wejściową.
	* Bardzo ważne jest oddzielenie OPa od reszty graczy, gdyż OP połyka wszystkie uprawnienia.
	* Gracz z uprawnieniem benchcraft.girl będzie miał inną wiadomość - WYMAGA PLUGINU NA UPRAWNIENIA.
	*/
	  
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		  
		Player p = event.getPlayer();
		  
		if(p.isOp()) {
			  
			event.setJoinMessage(ChatColor.GOLD + event.getPlayer().getName() + " dołączył do gry!");
			  
		} else if (!(p.isOp())) {
			  
			if (p.hasPermission("benchcraft.girl")) {

				event.setJoinMessage(ChatColor.GOLD + event.getPlayer().getName() + " dołączyła do gry!");
				  
			} else if (!(p.hasPermission("benchcraft.girl"))) {
				  
				event.setJoinMessage(ChatColor.GOLD + event.getPlayer().getName() + " dołączył do gry!");
				  
			}
			  
		}
		  
	}
	  
	/**
	 * PlayerQuitEvent odpala się przy wyjściu gracza z serwera.
	 * Metoda setQuitMessage ustawia wiadomość wyjściową.
	 * Bardzo ważne jest oddzielenie OPa od reszty graczy, gdyż OP połyka wszystkie uprawnienia.
	 * Gracz z uprawnieniem benchcraft.girl będzie miał inną wiadomość - WYMAGA PLUGINU NA UPRAWNIENIA.
	 */
	  
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		  
		Player p = event.getPlayer();
		  
		if(p.isOp()) {
			  
			event.setQuitMessage(ChatColor.GRAY + event.getPlayer().getName() + " wyszedł z gry.");
			  
		  } else if (!(p.isOp())) {
			  
			  if (p.hasPermission("benchcraft.girl")) {

				  event.setQuitMessage(ChatColor.GRAY + event.getPlayer().getName() + " wyszła z gry.");
				  
			  } else if (!(p.hasPermission("benchcraft.girl"))) {
				  
				  event.setQuitMessage(ChatColor.GRAY + event.getPlayer().getName() + " wyszedł z gry.");
				  
			  }
			  
		  }
  
	  }
	
	/**
	 * Poniższa część kodu pozwala na tworzenie kolorowych tekstów na tabliczkach.
	 */
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		String[] lines = e.getLines();
		for(int i = 0; i < 4; i++) {
			String line = lines[ i ];
	  		line = ChatColor.translateAlternateColorCodes('&', line);
	  		e.setLine(i, line);
	  	}
	  } 	  
	
}
