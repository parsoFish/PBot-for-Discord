package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class EightBallCommand implements Command {
    private final String HELP = "Usage: !8ball\nAnswers a question with an 8-ball type response at random.";
    
    
    public boolean called(String commandArg, String extraArg) {
        if(commandArg.equals("8ball")){
            return true;
        }
        return false;
    }

    public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
        Scanner input = null;
        Random rng = new Random();
        ArrayList<String> responses = new ArrayList<String>();
        //Open file
        try{
            input = new Scanner(new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\Discord Bot\\8Ball.txt"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        //Delimiter set to comma, and add trimmed quotes
        input.useDelimiter("\\|");
        while(input.hasNext()){
            responses.add(input.next().trim());
        }
        input.close();
        
        if(extraArg.isEmpty()){
            event.getTextChannel().sendMessage("Invalid - ask me a question after typing !8ball.").queue();
        }else{
            event.getTextChannel().sendMessage(responses.get(rng.nextInt(responses.size()))).queue();
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
