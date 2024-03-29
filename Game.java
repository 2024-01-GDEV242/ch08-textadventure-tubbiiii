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
    private NPCs professor, machine, guard;
    private Room currentRoom;
    private player player;
    item floorSandwich, normalSandwich, soda, ironball, theaterkey, pencil, heavypencil;
    String[] Health = {"Healthy", "Damaged", "Dead"};
    Room outside, theater, pub, lab, office, kitchen;
    String roomName;
    int hp = 0;
    int playerRoomNum;
    boolean  finished = false;
    boolean guardfed = false;
    boolean transported = false;
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
        createRooms();
        createNPCs();
        createItems();
        createPlayer();
        parser = new Parser();
        
    }
    public void createPlayer()
    {
         player = new player(50);
        }
    public void createItems()
    {
         floorSandwich = new item(1, false, "Floor Sandwich");
         normalSandwich = new item(3, true, "Normal Sandwich");
         soda = new item(4, true, "Soda");
         ironball = new item(30, false, "Iron ball");
         theaterkey = new item(2, false, "Theater Key");
         pencil = new item(1, false, "pencil");
         heavypencil = new item(20, false, "oddly heavy pencil");
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
             + "When the light fades you see that you are in the theater!"
        );

    }
    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
        
         Room busStop, outside, theater, pub, lab, office;
        
        // create the rooms
        busStop = new Room("at the bus station","busStop", 0);
        outside = new Room("outside the main entrance of the university", "outside", 100);
        theater = new Room("in a lecture theater", "theater", 100);
        pub = new Room("in the campus pub", "pub", 100);
        lab = new Room("in a computing lab", "lab", 100);
        office = new Room("in the computing admin office" , "office", 100);
        kitchen = new Room("in the pub's kitchen" , "kitchen" , 100);
        
        // initialise room exits
        busStop.setExit("south", outside);
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("machine", theater);
        
        // add items to rooms
        
        currentRoom = busStop;  // start game at the bus stop
        
    
        
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
    
    public ArrayList<String> inventory = new ArrayList<>();
    {
        ArrayList<String> inventory = new ArrayList<String>();
        
    
    }
    /**
     * Prints the player's inventory
     */
        public void printInventory()
    {
          System.out.println("Inventory:");
        for (String item : inventory) {
            System.out.println(item);
        }
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
        }
        return wantToQuit;
    }
    private void take (Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String item = command.getSecondWord();
        if ("floorsandwich".equalsIgnoreCase(item) && Room.roomContainsItem(floorSandwich)){
                
            }
        }
    
        
        
    /**
     * Check if player has item
     * 
     * @return true If the player has the item, false otherwise.
     */
        private boolean hasItem(String item) {
        for (String inventoryItem : inventory) {
        //this is a for loop that tests if the player has an item in the inventory array list
        if (inventoryItem.equalsIgnoreCase(item)) {
            return true; // Item found in inventory
        }
        }
        return false; // Item not found in inventory
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
        else if ("sandwich".equalsIgnoreCase(object) && "outside".equalsIgnoreCase(roomName)) {
            String useguard = guard.getUseToDialogue();
            System.out.println(useguard);
            guardfed = true;
        } 
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
     * The player examines the current room
     * 
     */
    public void look()
    {
        if("pub".equals(roomName) && !hasItem("Sandwich")){
            System.out.println("You find a sandwich on the floor!\n" + "You pick it up and put it in your bag!");
            inventory.add("Sandwich");
        }
        else if ("pub".equals(roomName) && hasItem("Sandwich"))
        {
            System.out.println("Theres nothing left but a dash of mayonaise on the floor...");
        }
        else if("lab".equals(roomName)){
            System.out.println("You hear an odd cranking noise coming from a door to the EAST");    
        }
        else if("outside".equals(roomName) && !guardfed){
            String guardintro = guard.getIntroDialogue();
            System.out.println(guardintro);
        }
        
        else{
            System.out.println("Nothing catches your interest...");
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
        else if ("outside".equals(roomName) && "north".equalsIgnoreCase(direction) ) {
            System.out.println("There's no reason to get back on the bus!");
        }
        else if (!guardfed && "outside".equals(roomName) && "south".equalsIgnoreCase(direction) ) {
            System.out.println("'I said halt!!'\n" + "The guard pushes you to the floor!");
            damage();
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

