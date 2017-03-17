package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PingCommand implements Command {
	private final String HELP = "USAGE: !ping\nReturns Pong! if successful, as well as the time the ping request was sent.";
	
	public boolean called(String commandArg, String extraArg) {
		if(commandArg.equals("ping")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("M/dd/yyyy, h:mm:ss a 'UTC'");
		OffsetDateTime messageTime = event.getMessage().getCreationTime();
		String time = messageTime.format(format);
		Main.updateLog(event.getAuthor().getName() + " accessed the ping command at " + time);
		event.getTextChannel().sendTyping().queue();
		event.getTextChannel().sendMessage("Pong!\nThe above ping request was sent on: " + time).queue();
	}

	public String help() {
		return HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

	public void logEvent(MessageReceivedEvent event) {
		return;
	}

}
