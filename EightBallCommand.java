package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */
public class EightBallCommand implements Command {
    private final String HELP = "Usage: !8ball\nResponds with an 8-ball type response.\nFormat: !8ball question_here";
    
    
    public boolean called(String commandArg, String extraArg, MessageReceivedEvent event) {
        if(commandArg.equals("8ball")){
            return true;
        }
        return false;
    }

    public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
        Random rng = new Random();
        logEvent(event);
        
        if(extraArg.equalsIgnoreCase("help")){
        	help(event);
        	return;
        }
        
        if(extraArg.isEmpty()){
            event.getTextChannel().sendMessage("Invalid - ask me a question after typing !8ball.").queue();
        }else{
            event.getTextChannel().sendMessage(Main.eightBallResponses.get(rng.nextInt(Main.eightBallResponses.size()))).queue();
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
        Main.updateLog(event.getAuthor().getName() + " accessed the log command on: " + time);
    }

}
