package com.projects.discordbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/*
 * PBot v1.3 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class BotListener extends ListenerAdapter {
	
	CommandParser cmd = new CommandParser();
	
	public void onMessageReceived(MessageReceivedEvent event){
		if(event.getChannel().getId().equals("273989023108825090") || event.getChannel().getId().equals("291145108953497601") || event.getChannel().getId().equals("292516603067629569")){
			if(event.getMessage().getContent().startsWith("!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
				Main.handleCommand(cmd.parse(event.getMessage().getContent().toLowerCase(), event));
			}
		}
	}
}
