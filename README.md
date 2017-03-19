# PBot-for-Discord v1.3
Discord bot created by Phoenix for general usage.

Currently only used in "The Cute Squad" Discord server for general commands.

**Current list of commands:**<br />
!8ball - Responds with an 8-ball type answer.<br />
!list - Returns a list of commands accessible to the user.<br />
!log - Sends a PM of events that have occurred since the bot's startup.<br />
!owner - Returns the nickname of the owner of the server.<br />
!ping - Returns Pong! if successful, as well as the time the ping request was sent.<br />
!quote - Returns a random quote from a Sprash member.<br />
!roles - Returns a list of the roles in this server, as well as a count of how many people are assigned each role.<br />
!roll - Rolls a 6-sided dice and returns the result. Input a number 1-100 to roll that many times.<br />
!shutdown - Shuts down the bot and PMs the event log to the sender. Admins only.<br />
!uptime - Displays the time the bot started up, and how much time has passed since then.<br />
!users - Returns the number of members in the guild.
<br />
<br />
# **Technical overview of the bot's code**

Via a listener class, each message sent in #bot-stuff or #testing-bot is picked up if it begins with !. The message is then parsed and compared with the list of commands known to the bot. If it matches, the action() method of the command is called and the command is ran. 
<br />
<br />
<br />
Request any commands you feel like, I get a block of creativity very often and need suggestions on where to go next :3
