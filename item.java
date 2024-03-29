
/**
 * This creates an object that will act as items, storing values about 
 * them like wieght and edibility
 */
public class item
{
    // instance variables - replace the example below with your own
    public int weight;
    public boolean edible;
    public String name;

    /**
     * Constructor for objects of class inventory
     */
    public item(int weight, boolean edible, String name)
    {
        this.weight = weight;
        this.edible = edible;
        this.name = name;
    }
    public int getWeight(){
        return weight;
    }
    }

