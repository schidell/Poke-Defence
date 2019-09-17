import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Displays text in world.
 * 
 * @author Maggie Lin
 * @version April 2, 2018
 */
public class Text extends Actor
{
    private GreenfootImage t;

    private String text;
    private String waveLabel;

    /**
     * Creates text with a specified color and specified background with Arial Bold font.
     * 
     * @param  text Text to display
     * @param  size Size of text
     * @param fontColor Color of the font
     * @param backColor Color of the background of the text 
     */
    public Text(String text, int size, Color fontColor, Color backColor)
    {
        t = new GreenfootImage(text, size, fontColor, backColor);
        t.setFont(new Font("Arial", Font.BOLD, size));
        setImage(t);    //set text image
    }

    /**
     * Creates text with white letters and transparent background with Arial Bold font.
     * 
     * @param text  text to display
     * @param size  size of text
     */
    public Text(String text, int size)
    {
        this("Wave: " + text,size, Color.WHITE, new Color(0,0,0,0));
    }

    /**
     * Creates text to show the wave number. 
     * 
     * @param wave Wave number
     * @param size Size of the font, must be greater than 0
     * @param fontColor Color of the font
     * @param backColor Color of the background of the text
     */
    public Text(int wave, int size, Color fontColor, Color backColor)
    {
        waveLabel = Integer.toString(wave);
        t = new GreenfootImage (waveLabel, size, fontColor, backColor);
        t.setFont(new Font("Arial", Font.BOLD, size));
        setImage(t);    //set text image
    }

    /**
     * Updates the wave number in the text
     * @param wave Wave number
     */
    public void update (int wave)
    {
        this.waveLabel = Integer.toString(wave);
    }
}
