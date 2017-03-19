package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.3 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class UsersCommand implements Command {
	private final String HELP = "USAGE: !users\nReturns the number of users in the server.";
	
	public boolean called(String commandArg, String extraArg) {
		if(commandArg.equals("users")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		int numberOfUsers = event.getGuild().getMembers().size();
		event.getTextChannel().sendTyping().queue();
		event.getTextChannel().sendMessage("Number of users in server: " + numberOfUsers).queue();
	}

	public String help() {
		return HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

	public void logEvent(MessageReceivedEvent event) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("M/dd/yyyy, h:mm:ss a 'UTC'");
		OffsetDateTime messageTime = event.getMessage().getCreationTime();
		String time = messageTime.format(format);
		Main.updateLog(event.getAuthor().getName() + " accessed the users command on: " + time);
	}

	
}
