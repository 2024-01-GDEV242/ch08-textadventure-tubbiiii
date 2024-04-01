/**
 * The Item class represents an item with attributes such as weight, edibility, and name.
 * This class provides methods to retrieve information about the item.
 * 
 * @author Chris Compierchio
 * @version 3/29/24
 */
public class item {
    
    /** The weight of the item */
    public int weight;
    
    /** Indicates whether the item is edible or not */
    public boolean edible;
    
    /** The name of the item */
    public String name;

    /**
     * Constructs an Item object with the specified weight, edibility, and name.
     * 
     * @param weight The weight of the item
     * @param edible Indicates whether the item is edible or not
     * @param name The name of the item
     */
    public item(int weight, boolean edible, String name) {
        this.weight = weight;
        this.edible = edible;
        this.name = name;
    }
   
    /**
     * Retrieves the weight of the item.
     * 
     * @return The weight of the item
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Retrieves the name of the item.
     * 
     * @return The name of the item
     */
    public String getName() {
        return name;
    }
}
