import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of class inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class inventory
{
     public ArrayList<inventory> inventory(String[] args)
    {
        ArrayList<String> inventory = new ArrayList<String>();
        inventory.add("Key");
        return(inventory);
    }
     public static void printInventory()
    {
        System.out.println(Arrays.toString(inventory));
    }
}
