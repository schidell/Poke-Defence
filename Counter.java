import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Counter that tells the user their counter value.
 * 
 * @author Amber 
 * @version June 2018
 */
public class Counter extends Actor
{
    //variables used to create the image
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    //stores amounts used for the counter
    private int value;
    private int target;
    public int money;

    /**
     * Create a new counter, initialised to 0.
     */
    public Counter()
    {
        background = getImage();  // get image from class
        value = 0;
        target = 0;
        updateImage();

    }

    /**
     * Animate the display to count up (or down) to the current target value.
     */
    public void act() 
    {
        if (value < target) {
            value++;
            updateImage();
        }
        else if (value > target) {
            value--;
            updateImage();
        }
    }

    /**
     * Add a new score to the current counter value.
     * 
     * @param score Score to add to the current value
     */
    public void add(int score)
    {
        target += score;

    }

    /**
     * Return the current counter value.
     * 
     * @return int Value of the current counter
     */
    public int getValue()
    {
        return value;
        //return value = int money(Score.class);
    }

    /**
     * Set a new counter value.
     * 
     * @param newValue New value of the counter
     */
    public void setValue(int newValue)
    {
        target = newValue;
        value = newValue;
        updateImage();
    }

    /**
     * Set a new counter value without changing the target value.
     * 
     * @param newValue New value of the counter
     */
    public void setValueSmooth(int newValue)
    {
        target = newValue;
        updateImage();
    }

    /**
     * Update the image on screen to show the current value.
     */
    private void updateImage()
    {
        //changes the image to include the newest value
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage("" + value, 22, Color.BLACK, transparent);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);
    } 
}
