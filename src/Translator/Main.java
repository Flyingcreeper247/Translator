package Translator;

import java.util.Locale;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import Translator.Util;

public class Main extends JavaPlugin
  implements Listener
{
  public void onDisable()
  {
  }

  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    saveDefaultConfig();
  }

  @EventHandler(priority=EventPriority.LOWEST)
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    String lang = getConfig().getString("lang");
    Boolean uppercase = Boolean.valueOf(getConfig().getBoolean("uppercase"));
    Boolean bigben = Boolean.valueOf(false);
    String message = event.getMessage();
    if (message.startsWith("bigben: ")) {
      bigben = Boolean.valueOf(true);
      message = message.substring(8);
    }
    message = Util.getTranslation(message, lang);
    if (bigben.booleanValue() == true) {
      message = "bigben: " + message;
    }
    if (uppercase.booleanValue() == true) {
      message = message.toUpperCase(new Locale(lang));
    }
    event.setMessage(message);
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((cmd.getName().equalsIgnoreCase("translator")) && (sender.hasPermission("translator.reload"))) {
      reloadConfig();
      sender.sendMessage(ChatColor.GREEN + "Translator has been reloaded.");
    }
  if ((cmd.getName().equalsIgnoreCase("translatorhelp")) && (sender.hasPermission("translator.help"))) {
      sender.sendMessage(ChatColor.GRAY + "----------" + ChatColor.DARK_RED + "You are Running" + ChatColor.RED + " Translator" + ChatColor.GREEN + " v.1.0" + ChatColor.GRAY + "----------");
      sender.sendMessage(ChatColor.GOLD + "/translator help" + ChatColor.BLUE + "          Shows information about the commands");
      sender.sendMessage(ChatColor.GOLD + "/translator" + ChatColor.BLUE + "          Reloads the config file");
      sender.sendMessage(ChatColor.GREEN + "Dedicated to" + ChatColor.AQUA + "          --=" + ChatColor.GOLD + "pvp.archonmc.com" + ChatColor.AQUA + "=--");
      sender.sendMessage(ChatColor.GOLD + "Made By:" + ChatColor.YELLOW + "FlyinCreeper24_7");
      return true;
  }
      return true;
    
    }
}