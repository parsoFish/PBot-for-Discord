package commands;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class ShutdownCommand implements Command {
	private final String HELP = "Usage: !shutdown\n**Admin Only** Shuts down the bot and PMs the event log to the sender.";
	
	public boolean called(String commandArg, String extraArg, MessageReceivedEvent event) {
		if(commandArg.equals("shutdown")){
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
		
		if(event.getMember().isOwner()){
			event.getTextChannel().sendMessage("Yes sir! Shutting down.").queue();
			event.getAuthor().openPrivateChannel().queue(m -> {	
				try{
					m.sendFile(Main.log, null).queue(); 
				}catch(IOException e){	
					e.printStackTrace();
				}
			});
			event.getJDA().shutdown();
		}else{
			event.getTextChannel().sendMessage("Nope! Not an admin.").queue();
		}
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
		Main.updateLog(event.getAuthor().getName() + " accessed the shutdown command on: " + time);
	}

}
