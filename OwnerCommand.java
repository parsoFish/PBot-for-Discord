package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class OwnerCommand implements Command {
	private final String HELP = "Usage: !owner\nReturns the nickname of the owner of the server.";
	
	public boolean called(String commandArg, String extraArg, MessageReceivedEvent event) {
		if(commandArg.equals("owner")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		
		if(extraArg.equalsIgnoreCase("help")){
        	help(event);
        	return;
        }
		
		event.getTextChannel().sendMessage(event.getGuild().getOwner().getNickname()).queue();
	}

	public void help(MessageReceivedEvent event) {
		event.getTextChannel().sendMessage(HELP).queue();
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

	public void logEvent(MessageReceivedEvent event) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("M/dd/yyyy, h:mm:ss a 'UTC'");
		OffsetDateTime messageTime = event.getMessage().getCreationTime();
		String time = messageTime.format(format);
		Main.updateLog(event.getAuthor().getName() + " accessed the owner command on: " + time);
	}

}
