package com.projects.discordbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class BotListener extends ListenerAdapter {
	
	CommandParser cmd = new CommandParser();
	
	public void onMessageReceived(MessageReceivedEvent event){
		if(Main.whitelist.contains(event.getChannel().getId().toString())){
			if(event.getMessage().getContent().startsWith("!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
				Main.handleCommand(cmd.parse(event.getMessage().getContent().toLowerCase(), event));
			}
		}
	}
}
