# Project: Lorenzo il Magnifico

The game is set in the beautiful landscape of Renaissance Florence. The players objective is to earn the highest number of victory points putting four familiars in differents game board's areas taking resources, raising buildings, taking on ventures and meeting important characters.
  
##Features implemented
We have implemented:
####User Interface
- Cli
- Gui

####Network Technology
- Socket
- RMI

####Game Rules
- Advanced

####End match commands
- Show player's statistics
- Show global ranking
- Play a new match
- Close the game
## Run commands

For running the software, firstly run the main method in class /project/server/Server.java. This main starts RMIServer and Socket Server.

Start the client running the main method in /project/client/ClientSetter.java. When you run this main method, the software ask to you of typing the kind of User interface that you wants to use, later the kind of connection's technology, the server's IP address and finally the nickname that you want to use. The only IP address tested and surely functioning is 127.0.0.1

##Pattern used 

- Factory Pattern for constructing the game's cards and all the configurable game's features. Parsing Json files using the Gson API, we have built generic objects for storing the json parameters and, passing these parameters to specific methods, we have built all the concrete objects once for all when the match starts.
- Decorator Pattern for acting some Leader cards, excommunications and development cards effects. When these particular cards are played, some supports and check methods are trasformed run time using this pattern calling the usual methods.

##Network implementation
- Socket: socketClient and SocketPlayerHandler class comunicate directly using the usual socket's streams. When they receive a message, using an HashMap containing the string message as key, the method's reference of a Functional interface is overwritten for performing always the right method's call.
- RMI: there is an RMI server that receives all the methods'calls from the clients and calls methods on the dedicated RMIPlayerHandler Class.
- Is possible disconnect the client in various cases: 
during the Leader cards and tiles' draft, if the player is disconnected is choosen the firt card/tile in the list automatically; during a permanent effect's choice, a bonus privilege and a bonus development card, no action is performed; during a payment choice, is choosen a random payment; during the prayer time, the prayer is not performed. A player is considered disconnected when the turn time is up and when he stops the main client's thread. In both cases is possible do a reconnection using the same nickname for the login.
 
 ####Credits
 
 we are grateful for the opportunity of working on this project because we achived every day new skills and we learned how to work as a team dealing with the every day's problems. We have never done a real programming experience before this and we are satisfyed ( conscious that obviously we have still a lot to learn...)
   
   ####Developers
   Raffaele Bongo
   
   Federico Betti
   
   Project LM43 - Politecnico di Milano - 10/07/17 - 06:35