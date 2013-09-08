package com.github.Markey50.BuildChallenge;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BAdmin implements CommandExecutor {
	
	String menu = ChatColor.GREEN + "| " + ChatColor.AQUA;
	int x;
	String menu2 = ChatColor.WHITE + "- " + ChatColor.AQUA;
	
	String[] defaultMessage = new String[] {
		ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.RED + "ADMIN " + ChatColor.AQUA + "help commands",
		ChatColor.RED + "----------",
		menu + "/badmin arenacreate " + menu2 + "Walks through the steps of creating an arena",
		menu + "/badmin setinitiator " + menu2 + "Add a player as a designated event initiator",
		menu + "/badmin reminitiator " + menu2 + "Removes a player from the designated event initiator list",
		ChatColor.RED + "----------"
	};
	
	String header = ChatColor.WHITE + "BuildChallenge " + ChatColor.DARK_BLUE + "// " + ChatColor.AQUA;

	BuildChallenge plugin;
	public BAdmin(BuildChallenge instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Check for arguments and display help menu if no arguments are matched
		if(cmd.getName().equalsIgnoreCase("badmin")) {
			
			switch (args.length){
			//check for args length
			
			default: 
				sender.sendMessage(defaultMessage);
			break;
			
			case 1: case 2: case 3:
				
				switch (args[0].toLowerCase()){
					
					default:
						sender.sendMessage(defaultMessage);
					break;
					
					case "help": case "?":
						
						if (args.length == 1){
							sender.sendMessage(defaultMessage);
						break;
						}
						
					break;
					
					case "arenacreate":
						//walk user through the steps of creating an arena ./badmin arenacreate
						if (!(sender instanceof Player)) {
							sender.sendMessage(AS(header + "&cThis command can only be run by a player!"));
						} else if(sender.hasPermission("buildchallege.admin")) {
						
							sender.sendMessage(AS(header + "Use the WorldEdit wand to select the region of the first cell"));
						
							Bukkit.dispatchCommand(sender, "//wand");
							
							sender.sendMessage(AS(header + "Then use &e/badmin setcell &bto create the cell."));
							sender.sendMessage(AS(header + "To select another cell use &e/badmin next&b."));
									
						} else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));							
						}
						break;
						
					case "setcell":
						if (!(sender instanceof Player)) {
							sender.sendMessage(AS(header + "&cThis command can only be run by a player!"));
						} else if (sender.hasPermission("buildcahllenge.admin")) {
							setCell(sender);						
						} else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}

						break;
						
					case "next":
						if (!(sender instanceof Player)) {
							sender.sendMessage(AS(header + "&cThis command can only be run by a player!"));
						} else if (sender.hasPermission("buildchallenge.admin")) {
							sender.sendMessage(AS(header + "Select the next cell and use &e/badmin setcell&b. Then &e/badmin next &bto add another cell."));
							sender.sendMessage(AS(header + "Use &e/badmin done &bto finish making cells and move on to the next step."));
						} else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
						break;
						
					case "done":
						if (!(sender instanceof Player)) {
							sender.sendMessage(AS(header + "&cThis command can only be run by a player!"));
						} else if (sender.hasPermission("buildchallenge.admin")) {
							sender.sendMessage(AS(header + "Now use the WorldEdit wand to select the desired lobby area. Use &e/badmin setlobby &bto define the lobby."));
						} else {
							sender.sendMessage(AS(header + "You do not have permission to do this!"));
						}
						
					case "setlobby":
						if (!(sender instanceof Player)) {
							sender.sendMessage(AS(header + "&cThis command can only be run by a player!"));
						} else if (sender.hasPermission("buildchallenge.admin")) {
							setLobby(sender);
							sender.sendMessage(AS(header + "Arena Created! Enjoy!"));
						} else {
							sender.sendMessage(AS(header + "You do not have permission to do this!"));
						}
						
					case "setinitiator":
						//sets a user as an authorized initiator ./badmin setinitiator <playername>
						if (sender.hasPermission("buildchallenge.admin")) {
							if (args.length == 0){
								sender.sendMessage(AS(header + "&cYou must specify a player name!"));
							} else if (args.length == 1) {
							List <String> initiatorList = plugin.datacore.getStringList("Initiators");
							initiatorList.add(args[1]);
							plugin.datacore.set("Initiators", initiatorList);
							plugin.saveYamls();
							Bukkit.dispatchCommand(sender, "/pex user " + args[1] + "add buildchallenge.initiator");
							sender.sendMessage(AS(header + "&bSuccessfully &aadded &b" + (args[1]) + " to initiator list."));
							}						
						}else {	
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
						
					break;
						
					case "reminitiator":
						//remove a user from the authorized initiator list ./badmin reminitiator <playername>
						if (sender.hasPermission("buildchallenge.admin")) {
							if (args.length == 0){
								sender.sendMessage(AS(header + "&cYou must specify a player name!"));
							} else if (args.length == 1) {
							//Remove a name from initiatorList
							List <String> initiatorList = plugin.datacore.getStringList("Initiators");
							initiatorList.remove(args[1]);
							plugin.datacore.set("Initiators", initiatorList);
							plugin.saveYamls();
							Bukkit.dispatchCommand(sender, "/pex user " + args[1] + "remove buildchallenge.initiator");
							sender.sendMessage(AS(header + "Successfuly &cremoved &b " + (args[1]) + "from initiator list."));
							}
						}else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
					break;
						
					}
				}
			
			}
		
		return true;
	
	}
	
	private void setCell(CommandSender s) {
		x = plugin.datacore.getInt("Regions.Incremental");
		x++;
		plugin.datacore.set("Regions.Incremental", x);
		if (s.isOp()){
			plugin.datacore.set("Users." + s.getName() + ".OP", true);
		}
		s.setOp(true);
		Bukkit.dispatchCommand(s, "/rg define Booth" + x);
		Bukkit.dispatchCommand(s, "/rg flag Booth" + x + " game-mode creative");
		Bukkit.dispatchCommand(s, "/rg flag Booth" + x + " build");
		Bukkit.dispatchCommand(s, "/setwarp Booth" + x);
		if (plugin.datacore.getBoolean("Users." + s.getName() + ".OP") == false){
			s.setOp(false);
		}
		
	}
	
	private void setLobby(CommandSender s) {
		if (s.isOp()) {
			plugin.datacore.set("Users." + s.getName() + ".OP", true);
		}
		s.setOp(true);
		Bukkit.dispatchCommand(s, "rg define BuildChallengeLobby");
		Bukkit.dispatchCommand(s, "rg flag BuildChallengeLobby build");
		Bukkit.dispatchCommand(s, "/setwarp BuildChallengeLobby");
		if (plugin.datacore.getBoolean("Users." + s.getName() + ".OP") == false) {
			s.setOp(false);
		}
	}

	public String AS(String textToBeTranslated){
		
		textToBeTranslated = ChatColor.translateAlternateColorCodes('&', textToBeTranslated);
		return textToBeTranslated;
		
	}

}

