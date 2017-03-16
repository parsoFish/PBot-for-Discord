package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LogCommand implements Command {
	private final String HELP = "Usage: !log\nSends a PM of events that have occurred since the bot's startup.";
	
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		logEvent(event);
		if(event.getMember().isOwner()){
			event.getAuthor().openPrivateChannel().queue(m -> m.sendMessage(Main.log).queue());
		}
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
		Main.updateLog(event.getAuthor().getName() + " accessed the log command on: " + time);
	}

}
