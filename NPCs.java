/**
 * The NPCs class represents non-player characters (NPCs) in the game.
 * It initializes interactions players can have with NPCs, including introductory dialogue,
 * dialogue when talking to the NPC, and dialogue when using items on the NPC.
 * 
 * This class provides methods to set and get the dialogue for each interaction type.
 * 
 * @author Chris Compierchio
 * @version 3/19/24
 */
public class NPCs {
    
    /** The introductory dialogue of the NPC */
    private String introDialogue;
    
    /** The dialogue when talking to the NPC */
    private String talkToDialogue;
    
    /** The dialogue when using items on the NPC */
    private String useToDialogue;
    
    /**
     * Constructs an NPCs object with the specified introductory, talk-to, and use-to dialogues.
     * 
     * @param introDialogue The introductory dialogue of the NPC
     * @param talkToDialogue The dialogue when talking to the NPC
     * @param useToDialogue The dialogue when using items on the NPC
     */
    public NPCs(String introDialogue, String talkToDialogue, String useToDialogue) {
        this.introDialogue = introDialogue;
        this.talkToDialogue = talkToDialogue;
        this.useToDialogue = useToDialogue;
    }

    /**
     * Sets the introductory dialogue of the NPC.
     * 
     * @param introDialogue The introductory dialogue to set
     */
    public void setIntroDialogue(String introDialogue) {
        this.introDialogue = introDialogue;
    }

    /**
     * Sets the talk-to dialogue of the NPC.
     * 
     * @param talkToDialogue The talk-to dialogue to set
     */
    public void setTalkToDialogue(String talkToDialogue) {
        this.talkToDialogue = talkToDialogue;
    }
    
    /**
     * Sets the use-to dialogue of the NPC.
     * 
     * @param useToDialogue The use-to dialogue to set
     */
    public void setUseToDialogue(String useToDialogue) {
        this.useToDialogue = useToDialogue;
    }
    
    /**
     * Retrieves the introductory dialogue of the NPC.
     * 
     * @return The introductory dialogue of the NPC
     */
    public String getIntroDialogue() {
        return introDialogue;
    }

    /**
     * Retrieves the talk-to dialogue of the NPC.
     * 
     * @return The talk-to dialogue of the NPC
     */
    public String getTalkToDialogue() {
        return talkToDialogue;
    }
    
    /**
     * Retrieves the use-to dialogue of the NPC.
     * 
     * @return The use-to dialogue of the NPC
     */
    public String getUseToDialogue() {
        return useToDialogue;
    }
}
