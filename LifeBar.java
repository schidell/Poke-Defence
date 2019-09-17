import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * HealthBar is a Greenfoot Actor that displays a Health Bar.
 * The health bar can be used to determine an objects' life span and is designed to 
 * work in any scenario (in this case, for PokeDefense). Creates a stationary health bar of a customizable size and black border.
 * 
 * <p>
 * Provides methods that will have their own function in generating health bars 
 * such as creating a default (preset) health bar and can also handle specific 
 * values given by the user.
 * 
 * @author Kyle Chan 
 * @version March 2018
 */
public class LifeBar extends Actor
{
    // Declaring Instance Variables
    private int maxHealth = 10; // Change the number to change the maximum health of the object
    private int currHealth = 10;    // Change the number to change the current health of the object
    private int width = 100;    // Change the number to change the width of the health bar
    private int height = 20;    // Height is fixed and set to be 20 for all health bars
    private int healthPerPixel = width/maxHealth;   // Formula is used to determine how long the GREEN HP should be inside the HP bar per tick

    /**
     * Allows the program to run the default settings for the health bar.
     * <p>
     * Default Settings:
     * <p>
     * Maximum Health = 50
     * <p>
     * Current Health = 50
     * <p>
     * Rectangle Colour = Black
     * <p>
     * Health Colour = Green
     */
    public LifeBar ()
    {
        //Creates a standard health bar (set to the original settings)
        update();
    }

    /**
     * Allows the user to change the default settings to desired values.
     * If the Current Health is lower than or equal to the Maximum Health (determined by the User)
     * then the health bar will update. If condition does not match, no health bar will appear (Greenfoot icon).
     * 
     * @param width         Width of the health bar
     * @param currHealth    Current health of the object (determined by the user)
     * @param maxHealth     Maximum health value of the object (determined by the user)
     */
    public LifeBar (int width, int currHealth, int maxHealth)
    {
        // Takes in the value of the users' favourable width, current health and maximum health
        this.width = width;
        this.currHealth = currHealth;
        this.maxHealth = maxHealth;
        healthPerPixel = width/maxHealth;
        if (currHealth <= maxHealth)
        {
            update(width, currHealth, maxHealth);
        }
    }

    /**
     * Lists all the procedures for creating a default health bar and is called upon from the
     * default HealthBar() method to create the health bar.
     * <p>
     * Default Settings:
     * <p>
     * Maximum Health = 50
     * <p>
     * Current Health = 50
     * <p>
     * Rectangle Colour = Black
     * <p>
     * Health Colour = Green
     */
    public void update() 
    {
        //Sets default health bar (100% hp (no damage taken) and to normal hp width and length
        setImage(new GreenfootImage(width+2,height+2));
        GreenfootImage hp = getImage();
        hp.setColor(Color.BLACK);
        hp.drawRect(0,0,width+1,height+1);
        hp.setColor(Color.GREEN);
        hp.fillRect(1,1,currHealth*healthPerPixel,height);
    }

    /**
     * Takes in users' preferred values for the width of the health bar, current health of the object
     * and the maximum health of the object. After, the method creates a new health bar tailored to the
     * users' chosen values.
     * 
     * @param width         Width of the health bar (determined by the user)
     * @param currHealth    Current Health of the object (determined by the user)
     * @param maxHealth     Maximum Health of the object (determined by the user)
     */
    public void update(int width, int currHealth, int maxHealth)
    {
        //Setting the rectangle shape for the HP Bar
        setImage(new GreenfootImage(width+2,height+2));
        GreenfootImage hp = getImage();
        hp.setColor(Color.BLACK);
        hp.drawRect(0,0,width+1,height+1);
        //Sets the colour within the rectangle to be green, otherwise, the unfilled colour is white
        hp.setColor(Color.GREEN);
        hp.fillRect(1,1,currHealth*healthPerPixel,height);

        if (currHealth == 0)
        {
            currHealth = 0;
        }
    }
}    