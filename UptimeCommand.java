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

public class UptimeCommand implements Command {
	private final String HELP = "Usage: !uptime\nDisplays the time the bot started up, and how much time has passed since then.";
	
	public boolean called(String commandArg, String extraArg) {
		if(commandArg.equals("uptime")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		long currentUtcTimeInMillis = System.currentTimeMillis() + 14400000;
		long elapsedTime = currentUtcTimeInMillis - Main.UtcTimeInMillis;
		event.getTextChannel().sendMessage("Bot has been up for: " + elapsedTime/3600000 + " hours, " + elapsedTime/60000 + " minutes, and " + elapsedTime/1000 + " seconds.").queue();
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
		Main.updateLog(event.getAuthor().getName() + " accessed the uptime command on: " + time);
	}

}
