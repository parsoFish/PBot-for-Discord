package com.projects.discordbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class CommandParser {
	
	private String withoutIdentifier;
	private String[] textSplit;
	protected String commandArg;
	protected String extraArg;
	protected MessageReceivedEvent event;
	
	public CommandParser(){
		withoutIdentifier = "";
		textSplit = null;
		commandArg = "";
		extraArg = "";
		event = null;
	}
	
	public CommandParser(String commandArg, String extraArg, MessageReceivedEvent e){
		this.commandArg = commandArg;
		this.extraArg = extraArg;
		this.event = e;
	}
	
	public CommandParser parse(String raw, MessageReceivedEvent e) {
		withoutIdentifier = raw.replaceFirst("!", "");
		textSplit = withoutIdentifier.split(" ");
		commandArg = textSplit[0];
		if(textSplit.length > 1){
			extraArg = textSplit[1];
		}else{
			extraArg = "";
		}
		
		return new CommandParser(commandArg, extraArg, e);
	}
}
