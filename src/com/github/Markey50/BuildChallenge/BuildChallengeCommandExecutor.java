package com.github.Markey50.BuildChallenge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BuildChallengeCommandExecutor implements CommandExecutor {
	
	String header = ChatColor.WHITE + "BuildChallenge " + ChatColor.DARK_BLUE + "// ";
	String menu1 = ChatColor.GREEN + "| " + ChatColor.AQUA;
	String menu2 = ChatColor.WHITE + "- " + ChatColor.AQUA;
	
	String[] defaultMessage = new String[]{
		ChatColor.WHITE + "Build Challege " + ChatColor.DARK_BLUE + "// " + ChatColor.AQUA + "Help Menu",
		ChatColor.RED + "----------",
		menu1+ "/build help admin " + menu2 + "Admin help commands",
		menu1+ "/build help initiator " + menu2 + "Event initiator help commands",
		menu1+ "/build help judge " + menu2 + "Judge help commands",
		menu1+ "/build help builder " + menu2 + "Player help commands",
		ChatColor.RED + "----------",
		ChatColor.DARK_GRAY + "BuildChallenge plugin coded by " + ChatColor.DARK_RED + "M" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "r" + ChatColor.GREEN + "k" + ChatColor.AQUA + "e" + ChatColor.DARK_BLUE + "y" + ChatColor.DARK_PURPLE + "5" + ChatColor.RED + "0"
		};
	
	
	BuildChallenge plugin;
	public BuildChallengeCommandExecutor(BuildChallenge instance){
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("build")) {
			
			switch (args.length){
			// check for args length
			
			default:
				sender.sendMessage(defaultMessage);			
			break;
			
			case 1: case 2:
				// for length of 1/2
				switch (args[0].toLowerCase()){ // check for args after the base command
					
					default: 			
						sender.sendMessage(defaultMessage);
					break;
						
					case "help": case "?":
						
						if (args.length == 1){		
							sender.sendMessage(defaultMessage);
							break;
						}
					
					switch (args[1].toLowerCase()){
					
					default:
						sender.sendMessage(defaultMessage);
					break;
					
					case "admin":
					//displays admin help menu
						sender.sendMessage(new String[] {
								header + ChatColor.RED + "ADMIN " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								menu1 + "/badmin arenacreate " + menu2 + "Walks through the steps of creating an arena",
								menu1 + "/badmin setinitiator " + menu2 + "Add a player as a designated event initiator",
								menu1 + "/badmin reminitiator " + menu2 + "Removes a player from the designated event initiator list",
								ChatColor.RED + "----------"
								});
					break;
					
					case "initiator":
					//displays initiator/moderator help menu
						sender.sendMessage(new String[] {
								ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.BLUE + "INITIATOR " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								menu1 + "/bini initiate " + menu2 + "Start a build challenge event",
								menu1 + "/bini invite <playername> " + menu2 + "Invite a player to the event",
								menu1 + "/bini inviteall " + menu2 + "Invite all players to event",
								menu1 + "/bini settime <time> <s, m, h> " + menu2 + "Set time limit for build challenge",
								menu1 + "/bini start " + menu2 + "Officially start the challenge",
								menu1 + "/bini timechange <new time> <s, m , h> " + menu2 + "Set the timer to the time specified",
								menu1 + "/bini stop " + menu2 + "Force stop an event",
								menu1 + "/bini viewjudges " + menu2 + "See list of current judges",
								menu1 + "/bini invitejudge <player> " + menu2 + "Invite a player to judge",
								menu1 + "/bini kickjudge <player> " + menu2 + "Kick a player from the judge list",
								menu1 + "/bini winners <first> <second> <third>" + menu2 + "Select event winners (by cell number)",
								ChatColor.RED + "----------"
								});
						break;
						
					case "judge":
					//displays judges help menu
						sender.sendMessage(new String[] {
								ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.GOLD + "INITIATOR " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								menu1 + "/bjudge register " + menu2 + "Registers you as a judge for the current event",
								menu1 + "/bjudge resign " + menu2 + "Resign from being a judge for the current event",
								menu1 + "/bjudge vote <cell number> " + ChatColor.WHITE + "-  " + ChatColor.AQUA + "Vote for a cell",
								menu1 + "/bjudge comment <cell number> <comment> " + menu2 + "Add a comment to a cell",
								ChatColor.RED + "----------"
								});
					break;
					
					case "builder":
					//displays general builders help menu
						sender.sendMessage(new String[] {
								ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.DARK_GREEN + "BUILDER " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								menu1 + "/builder accept " + menu2 + "Accept an invitation to a build challenge",
								menu1 + "/builder decline " + menu2 + "Decline an invitation to a build challenge",
								menu1 + "/builder info " + menu2 + "View information on the current event",
								menu1 + "/builder leave " + menu2 + "Leave the current event",
								menu1 + "/builder seecomments " + menu2 + "View the comments the judges have left you",
								menu1 + "/builder winnings " + menu2 + "Collect your winnings!",
								ChatColor.RED + "----------"
								});
					break;
					}
				}
			break;
			}
		}
		return true;
	}
}
