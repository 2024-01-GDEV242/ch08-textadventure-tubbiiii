import java.util.ArrayList;
import java.util.Arrays;

/**
 *  This is the main class for my text adventure project, most features exist here.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Chris Compierchio
 * @version 2024 3/27/24
 */

public class Game 
{
    private Parser parser;
    private NPCs professor, machine, guard, rat;
    private Room currentRoom;
    private player player;
    item floorSandwich, normalSandwich, soda, ironball, theaterkey, pencil, heavypencil, piratesbooty, book;
    String[] Health = {"Healthy", "Damaged", "Dead"};
    
    String roomName;
    int hp = 0;
    int playerRoomNum;
    boolean  finished = false;
    boolean guardfed = false;
    boolean transported = false;
    boolean theaterUnlocked = false;
    boolean ratDefeated = false;
    /**
     * Make the game run outside of blueJ
     */
     public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createItems();
        createRooms();
        createNPCs();
        createPlayer();
        parser = new Parser();
        
    }
    /**
     * Create the player
    */
    public void createPlayer()
    {
         player = new player(50);
        }
        /**
     * Create items
    */
    public void createItems()
    {
         floorSandwich = new item(1, false, "FloorSandwich");
         normalSandwich = new item(3, true, "NormalSandwich");
         soda = new item(4, true, "Soda");
         ironball = new item(30, false, "Ironball");
         theaterkey = new item(2, false, "TheaterKey");
         pencil = new item(1, false, "pencil");
         heavypencil = new item(20, false, "blackpencil");
         piratesbooty = new item(1, true, "Piratesbooty");
         book = new item(2, true, "book?");
         
        }
    /**
     * Create all the NPCs and give them dialogue
     */
    public void createNPCs()
    {
         guard = new NPCs(
            "A guard stands firm at the entrance to the lab",
            "'Halt!' The large man stands tall at the doorway to the lab\n"
                + "You have no clue why there's a man in full plate armor at your school,\n"
                + "nor do you understand why he's blocking the path to your class.\n"
                + "'No one goes past this point!' as you step back you hear his\n"
                + "stomach grumble through the armor.\n"
                + "'Man.. I could really USE a SANDWICH'",
            "Guard: 'Oh! Great! A sandwich! Thanks, you can go through!'"
        );
        
         professor = new NPCs(
             "A spindly old man in a lab coat is toying with an odd machine",
             "'Oh hello there! I was just about to test my new \n"
             + "M A T T E R  T R A N S P O R T E R !\n"
             + "Would you like to take it for a spin? \n"
             + "All you need to do is USE the MACHINE!",
             "The professor is waiting for you in the lab\n"
             +"'Your back! So, how was it? Wow! you went to the theater?\n"
             + "Interesting...\n"
             + "YOU WIN"
        );
        
         machine = new NPCs(
             "sampletext",
             "sampletext",
             "You are enveloped in a green light! \n"
             + "When the light fades you see that you are in the theater \n!"
             + "Maybe theres a way out!"
        );
        
         rat = new NPCs(
             "A massive rat stands in your way! Its golding something shiny...",
             "The rat is CRAZED",
             "The rat skitters off into the darkness... ...somehow"
             );

    }
    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
        
         Room busStop, outside, theater, pub, lab, office, kitchen, understage, labCloset, secretDungeon, dungeonBossRoom, guardBarracks, kitchenVents, sewers, library;
        
        // create the rooms
        busStop = new Room("at the bus station","busStop", 100);
        outside = new Room("outside the main entrance of the university", "outside", 100);
        theater = new Room("in a lecture theater", "theater", 100);
        pub = new Room("in the campus pub", "pub", 100);
        lab = new Room("in a computing lab", "lab", 100);
        office = new Room("in the computing admin office" , "office", 100);
        kitchen = new Room("in the pub's kitchen" , "kitchen" , 100);
        understage = new Room("in the crawlpace under the lecturetheater's stage","under stage",100);
        labCloset = new Room("in the closet in the lab","lab closet",100);
        secretDungeon = new Room("in a mysterious secret dungeon","secret dungeon",100);
        dungeonBossRoom = new Room("in a supposed boss room...","boss room",100);
        guardBarracks = new Room("in a barracks full of armored guards just like the one outside the lab","guardBarracks",100);
        kitchenVents = new Room("in the vents of the pub's kitchen","kitchen vents",100);
        sewers = new Room("in the sewers under the university","sewers",100);
        library = new Room("in the school library","library",100);
        
        // initialise room exits
        busStop.setExit("south", outside);
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);
        theater.setExit("east", understage);
        
        understage.setExit("west",theater);
        understage.setExit("east", secretDungeon);
        
        secretDungeon.setExit("west", understage);
        secretDungeon.setExit("east", dungeonBossRoom);
        secretDungeon.setExit("north", sewers);
        
        dungeonBossRoom.setExit("west", secretDungeon);
        

        pub.setExit("east", outside);
        pub.setExit("north", kitchen);
        
        kitchen.setExit("south", pub);
        kitchen.setExit("west",kitchenVents);
        
        kitchenVents.setExit("east", kitchen);
        kitchenVents.setExit("west", sewers);
        
        sewers.setExit ("north", guardBarracks);
        sewers.setExit ("south", secretDungeon);
        
        guardBarracks.setExit("south", sewers);
        guardBarracks.setExit("north", outside);

        lab.setExit("north", outside);
        lab.setExit("west", library);
        lab.setExit("south", labCloset);
        lab.setExit("east", office);
        
        library.setExit("east", lab);
        
        labCloset.setExit("north", lab);

        office.setExit("west", lab);
        office.setExit("machine", theater);
        
        // add items to rooms
        addItemToRoom(pub , floorSandwich);
        addItemToRoom(busStop , floorSandwich);
        addItemToRoom(dungeonBossRoom, theaterkey);
        addItemToRoom(kitchenVents, piratesbooty);
        addItemToRoom(kitchenVents, soda);
        addItemToRoom(labCloset, pencil);
        addItemToRoom(labCloset, pencil);
        addItemToRoom(labCloset, pencil);
        addItemToRoom(labCloset, pencil);
        addItemToRoom(labCloset, heavypencil);
        addItemToRoom(library, book);
        addItemToRoom(guardBarracks, ironball);
        
        currentRoom = busStop;  // start game at the bus stop
        
    
        
    }
    /**
     * The player will use items specified by the second word of the command
     * @param The room the player is in
     * @return the room name parameter as a string
     */
        public static String checkItem(item item) {
        String itemName = item.getName();
        return itemName;
    }
    /**
     * adds items to rooms
     * @param the room the item is to be added to
     * @param the item in question
     * 
    */
    public void addItemToRoom(Room room, item item) {
        room.addItemToRoom(item);
    }
    /**
     * The player will use items specified by the second word of the command
     * @param The room the player is in
     * @return the room name parameter as a string
     */
        public static String checkRoom(Room room) {
        String roomName = room.getName();
        return roomName;
    }
    /**
     * retrieves item name
     * @param the item
     * @return the item name string
    */
    public String getItemName(item item){
        String itemName = item.getName();
        return itemName;
    }
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        finished = false;
        

        while (! finished && hp < 2) {
            if (hp >= 2){
                System.out.println("You Died!!!");
                boolean dead = true;
            }
            Command command = parser.getCommand();
            finished = processCommand(command);
            roomName = checkRoom(currentRoom);
        }
        if (hp >= 2){
            System.out.println("You Died!!!");
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println("And make sure you use the LOOK command often! You never know what you might find!\n");
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * retrieves the player weight limit
     * 
     * @param the player
     * @return the weight limit int
    */
        public int getPlayerWeightLimit(player player)
    {
        int weightLimit = player.getPlayerWeightLimit();
        return weightLimit;
    }
    
    /**
     * Prints the player's inventory
     */
        public void printInventory()
    {
         player.displayPlayerInventory();
         int weightLimit = 50;
         int totalWeight = player.inventory.calculateTotalWeight();
         System.out.println("You can carry " + weightLimit + " lbs");
        System.out.println("Your bag weighs " + totalWeight + " lbs");
    }
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();
        
        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;
            
            case HEALTH:
                System.out.println("You are " + Health[hp]);
                break;
                
            case DAMAGE:
                transported = true;
               break;
            case INVENTORY:
                printInventory();
                break;
            case GO:
                goRoom(command);
                break;
            case TALK:
               talkNPC();
                break;
            case USE:
                use(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                look();
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                drop(command);
                break;
            case EAT:
                eat(command);
                break;
        }
        return wantToQuit;
    }
    /**
     * takes the item, removing from the room's inventory and adding it to the players. The player must use a second keyword that == the itemName string
     * 
    */
     public void take(Command command) {
        // Check if the command has a second word
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
    
        // Extract the item name from the command
        String itemNameCommand = command.getSecondWord();
    
        // Find the item in the current room's inventory
        item item = currentRoom.inventory.findItemByName(itemNameCommand);
    
        // Check if the item exists in the room
        if (item != null) { 
            boolean added = player.addItemToPlayer(item);
        if (added) {
            // If added successfully, remove the item from the room's inventory
            currentRoom.removeItemFromRoom(item);
            System.out.println("You took the " + itemNameCommand + ".");
        } else {
            // If the player's inventory is full, inform the player
            System.out.println("Your inventory is full. You can't take the " + itemNameCommand + ".");
        }
        } else {
            // If the item does not exist in the room, inform the player
            System.out.println("There's no " + itemNameCommand + " in the room.");
        }
    }
    /**
     * drops the item in question, removing from the player's inventory and adding to the current rooms' also requires a keyword == to itemName
    */
     public void drop(Command command) {
        // Check if the command has a second word
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }

        // Extract the item name from the command
        String itemNameCommand = command.getSecondWord();

        // Find the item in the player's inventory
        item item = player.findItemByName(itemNameCommand);

        // Check if the item exists in the player's inventory
        if (item != null) {
            // Add the item to the current room's inventory
            currentRoom.addItemToRoom(item);
            // Remove the item from the player's inventory
            player.removeItemFromPlayer(item);
            System.out.println("You dropped the " + itemNameCommand + ".");
        } else {
            // If the item does not exist in the player's inventory, inform the player
            System.out.println("You don't have a " + itemNameCommand + " in your inventory.");
        }
    }   
    /**
     * allows the player to eat food in thier inventory to regain health
     * @param command the command to be processed
     */
        public void eat(Command command) {
        // Check if the command has a second word
        if (!command.hasSecondWord()) {
        System.out.println("Eat what?");
        return;
        }

        // Extract the item name from the command
        String itemNameCommand = command.getSecondWord();
    
        // Find the item in the player's inventory
        item item = player.findItemByName(itemNameCommand);

        // Check if the item exists in the player's inventory and if it is edible
        if (item != null && item.edible && hp == 0) {
            if ("piratesbooty".equalsIgnoreCase(itemNameCommand)){
            System.out.println("You ate the " + itemNameCommand + ".\n" + "Its agressively mid!!!");
            // Assuming you want to remove the item from the player's inventory after eating
            player.removeItemFromPlayer(item);}
            else if ("book?".equalsIgnoreCase(itemNameCommand) || "book".equalsIgnoreCase(itemNameCommand)){
                System.out.println("You ate the " + itemNameCommand + ".\n" + "It was a realistic cake all along!?!?");
            // Assuming you want to remove the item from the player's inventory after eating
            player.removeItemFromPlayer(item);
            }
            else{    System.out.println("You ate the " + itemNameCommand + ".");
            // Assuming you want to remove the item from the player's inventory after eating
            player.removeItemFromPlayer(item);
            }
        }
        else if (item != null && item.edible && hp != 0){
            if ("piratesbooty".equalsIgnoreCase(itemNameCommand)){
            hp = --hp;
                System.out.println("You ate the " + itemNameCommand + ".\n" + "Its agressively mid!!!");
            // Assuming you want to remove the item from the player's inventory after eating
            player.removeItemFromPlayer(item);}
            else if ("book?".equalsIgnoreCase(itemNameCommand) || "book".equalsIgnoreCase(itemNameCommand)){
                hp = --hp;
                System.out.println("You ate the " + itemNameCommand + ".\n" + "It was a realistic cake all along!?!?");
            // Assuming you want to remove the item from the player's inventory after eating
            player.removeItemFromPlayer(item);
            }
            else{   
                hp = --hp;
                System.out.println("You ate the " + itemNameCommand + ".");
            // Assuming you want to remove the item from the player's inventory after eating
            player.removeItemFromPlayer(item);
            }
        }
        else {
            // If the item does not exist in the player's inventory or is not edible, inform the player
            System.out.println("Thats inedible");
        }
    }
    /**
     * The player will use items specified by the second word of the command
     * @param command The command to be processed.
     */
    public void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }

        String object = command.getSecondWord();

        // This is going to make the use command require a second word to work, also defines which words work and what they do
        if ("machine".equalsIgnoreCase(object) && "office".equalsIgnoreCase(roomName)) {
            String machineuse = machine.getUseToDialogue();
            System.out.println(machineuse);
            transported = true;
            // Send player straight to theater.
            Room nextRoom = currentRoom.getExit(object);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        } 
        else if ("floorsandwich".equalsIgnoreCase(object)||"sandwich".equalsIgnoreCase(object) && "outside".equalsIgnoreCase(roomName)) {
            item floorsandwich = player.findItemByName("floorsandwich");
            if (floorSandwich != null) {
            String useguard = guard.getUseToDialogue();
            System.out.println(useguard);
            guardfed = true;
            // Assuming you want to remove the floorsandwich after using it
            player.removeItemFromPlayer(floorSandwich);
        }}
        else {
            System.out.println("You can't use that here.");
        }
    }
    /**
     * The player takes damage
     */    
    public void damage()
        {
        System.out.println("You were hurt!");
                hp += 1;       
    }
    /**
     * The player examines the current room, printing the rooms' inventory
     * 
     */
    public void look()
    {
        currentRoom.displayRoomInventory();
        if (roomName == null){
            System.out.println("Nothings there!");
        }
        else if("lab".equals(roomName)){
            System.out.println("You hear an odd cranking noise coming from a door to the EAST");    
        }
        else if("outside".equals(roomName) && !guardfed){
            String guardintro = guard.getIntroDialogue();
            System.out.println(guardintro);
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     * also the room name for debug purposes
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Room.name = " + roomName);
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    /**
     * causes the player to talk to npcs triggering thier "talkto" dialogue
     */
    private void talkNPC(){
        if ("outside".equals(roomName) && !guardfed) {
            String guardtalkTo = guard.getTalkToDialogue();
            System.out.println(guardtalkTo);
        }
        else if(!transported && "office".equals(roomName)){
            String proftalkTo = professor.getTalkToDialogue();
            System.out.println("Professor:" + proftalkTo);
        }
        else{
            System.out.println("No ones there!");
        }
    }
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
        
            System.out.println("There is no door!");
            
        }
        else if ("outside".equals(roomName) && "east".equalsIgnoreCase(direction) ) {
            System.out.println("The door is locked!!!");
        }
        else if ("outside".equals(roomName) && "north".equalsIgnoreCase(direction) ) {
            System.out.println("There's no reason to get back on the bus!");
        }
        else if (!guardfed && ("busStop".equals(roomName) || roomName == null) && "south".equalsIgnoreCase(direction) ) {
            String guardIntro = guard.getIntroDialogue();
            System.out.println(guardIntro);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        else if (!guardfed && "outside".equals(roomName) && "south".equalsIgnoreCase(direction) ) {
            System.out.println("'I said halt!!'\n" + "The guard pushes you to the floor!\n" + "You take damage");
            damage();
        }
        else if ("kitchenVents".equals(roomName) && "west".equalsIgnoreCase(direction)){
            System.out.println("The vent suddenly gives out from under you and you fall into the mysterious sewer!\n" + "You take damage");
            damage();
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        else if (guardfed && "theater".equals(roomName) && "west".equalsIgnoreCase(direction) ) {
            System.out.println("'What the!?' Says the guard with the floor sandwich in his mouth\n" + "'Didnt you just go in the lab!? How did you do that!?'");
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        else if (!transported && "lab".equals(roomName) && "east".equalsIgnoreCase(direction) ) {
            String profintro = professor.getIntroDialogue();
            System.out.println(profintro);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getOfficeDescription());
        }
        else if (!ratDefeated && "secretDungeon".equals(roomName) && "east".equalsIgnoreCase(direction) ) {
            String ratintro = rat.getIntroDialogue();
            System.out.println(ratintro);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        else if ("guardBarracks".equals(roomName) && "north".equalsIgnoreCase(direction) ) {
            System.out.println("Theres a secret exit to the outdoors!");
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        else if(transported && "outside".equals(roomName) && "south".equalsIgnoreCase(direction)){
            String profuseTo = professor.getUseToDialogue();
            System.out.println(profuseTo);
            finished = true;
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

