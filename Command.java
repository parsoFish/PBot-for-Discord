package com.projects.discordbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.3 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public interface Command {
	
	public boolean called(String commandArg, String extraArg);
	public void action(String commandArg, String extraArg, MessageReceivedEvent event);
	public String help();
	public void executed(boolean success, MessageReceivedEvent event);
	public void logEvent(MessageReceivedEvent event);
	
}
