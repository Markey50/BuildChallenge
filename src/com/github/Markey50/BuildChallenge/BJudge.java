package com.github.Markey50.BuildChallenge;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BJudge implements CommandExecutor {
	
	String[] defaultMessage = new String[]{
		ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.GOLD + "INITIATOR " + ChatColor.AQUA + "help commands",
		ChatColor.RED + "----------",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/bjudge judgeregister " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Registers you as a judge for the current event",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/bjudge judgeresign " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Resign from being a judge for the current event",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/bjudge vote <cell number> " + ChatColor.WHITE + "-  " + ChatColor.AQUA + "Vote for a cell",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/bjudge comment <cell number> <comment> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Add a comment to a cell",
		ChatColor.RED + "----------"
	};
	
	String header = ChatColor.WHITE + "BuildChallenge " + ChatColor.DARK_BLUE + "// " + ChatColor.AQUA;

	BuildChallenge plugin;
	public BJudge(BuildChallenge instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Add arguments for buildchallenge.admin tools
		if(cmd.getName().equalsIgnoreCase("bjudge")) {
			
			switch (args.length) {
			//check for args length
			
			default:
				sender.sendMessage(defaultMessage);
			break;
			
			case 1: case 2: case 3:
				//check for length of 1/2
				switch(args[0].toLowerCase()){ //check for args after the base command
				
					default:
						sender.sendMessage(defaultMessage);
					break;
					
					case "help": case "?":
						
						if (args.length == 1){
							sender.sendMessage(defaultMessage);
							break;
						}
						
					break;
					
					case "register":
						//accept an invitation to be a judge
						if(sender.hasPermission("buildchallenge.judge")) {
							//Accept invitation as a judge
							List <String> judgeList = plugin.datacore.getStringList("Judges");
							judgeList.add(sender.getName());
							plugin.datacore.set("Judges", judgeList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "&cSuccessfully &aAdded &b " + (args[1]) + " as a judge for this event."));
						}else{
							sender.sendMessage(AS(header + "&cYou do not have permission to be a judge!"));
						}
					break;
					
					case "resign":
						//Resign as a judge from the current event
						if(sender.hasPermission("buildchallenge.judge")) {
							List <String> judgeList= plugin.datacore.getStringList("Judges");
							judgeList.remove(sender.getName());
							plugin.datacore.set("Judges", judgeList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "Successfuly &cremoved &b " + (args[1]) + " from initiator list."));
						}else{
							sender.sendMessage(AS(header + "&cYou do not have permission to be a judge!"));
						}
					break;
					
					case "vote":
						//vote for a designated cell number
						if(sender.hasPermission("buildchallenge.judge")) {
							//Add a number to voteList
							List <String> voteList = plugin.datacore.getStringList("Votes");
							voteList.add(sender.getName() + " : " + args[2]);
							plugin.datacore.set("Votes", voteList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "Successfully submited vote for cell &c " + (args[2])));
						}
					
					}
				}
			
			}
		
		
		return true;
	}
	
	public String AS(String textToBeTranslated){
		
		textToBeTranslated = ChatColor.translateAlternateColorCodes('&', textToBeTranslated);
		return textToBeTranslated;
	}

}