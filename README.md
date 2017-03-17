# PBot-for-Discord
Discord bot created by Phoenix for general usage.

Currently only used in "The Cute Squad" Discord server for general commands.

*Current list of commands:*
!list - Returns a list of commands accessible to the user.
!log - Sends a PM of events that have occurred since the bot's startup.
!owner - Returns the nickname of the owner of the server.
!ping - Returns Pong! if successful, as well as the time the ping request was sent.
!roles - Returns a list of the roles in this server, as well as a count of how many people are assigned each role.
!roll - Rolls a 6-sided dice and returns the result.
!shutdown - Shuts down the bot and PMs the event log to the sender. Admins only.
!uptime - Displays the time the bot started up, and how much time has passed since then.
!users - Returns the number of members in the guild.
<br />
<br />
# **Technical overview of the bot's code**

Via a listener class, each message sent in a channel (currently any channel, may restrict to specific channels in the future) is picked up if it begins with !. The message is then parsed and compared with the list of commands known to the bot. If it matches, the action() method of the command is called and the command is ran. 
<br />
<br />
<br />
Request any commands you feel like, I get a block of creativity very often and need suggestions on where to go next :3
