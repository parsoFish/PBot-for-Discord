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

public class ListCommand implements Command {
	private final String HELP = "Usage: !list\nReturns a list of commands accessible to the user.";
	
	public boolean called(String commandArg, String extraArg) {
		if(commandArg.equals("list")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		String outputMessage = "!list - Returns a list of commands accessible to the user.\n!log - Sends a PM of events that have occurred since the bot's startup.\n"
				+ "!owner - Returns the nickname of the owner of the server.\n!ping - Returns Pong! if successful, as well as the time the ping request was sent.\n"
				+ "!quote - Returns a random quote from a Sprash member.\n!roles - Returns a list of the roles in this server, as well as a count of how many people are assigned each role.\n"
				+ "!roll - Rolls a 6-sided dice and returns the result.\n";
		if(event.getMember().isOwner()){
			outputMessage += "!shutdown - Shuts down the bot and PMs the event log to the sender. Will only work for admins!\n";
		}
		outputMessage += "!uptime - Displays the time the bot started up, and how much time has passed since then.\n!users - Returns the number of members in the guild.";
		event.getTextChannel().sendTyping().queue();
		event.getTextChannel().sendMessage(outputMessage).queue();
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
		Main.updateLog(event.getAuthor().getName() + " accessed the list command on: " + time);
	}

}
