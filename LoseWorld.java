import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;
/**
 * The world displays an end screen to tell the user that they have lost, and displays their statistics
 * including their money, enemies killed, and what wave they were on when they lost.
 * <p>
 * IMAGE CREDIT: <a href=https://images7.alphacoders.com/310/310611.jpg>https://images7.alphacoders.com/310/310611.jpg</a>
 * 
 * @author Maggie Lin, Shivani Chidella, Kyle Chan
 * @version June 2018
 */
public class LoseWorld extends FinishWorld
{
    /**
     * Constructor for objects of class FinishWorld. It is created when a user loses their lives
     * and cannot complete all the waves. It creates an 800x600 size world, 
     * and adds a return to menu button that will take the user back to the start world.
     * 
     * @param game  The game that has the statistics to display on the screen
     */
    public LoseWorld(GameWorld game)
    {    
        super();    //creates world with 800x600 pixels, and adds a return to menu button that will take the user back to the start world 

        GreenfootImage background = getBackground();    //gets background image

        Color textColor = new Color(72, 117, 188);
        background.setColor(textColor); //set background color

        Font textFont = new Font("Showcard Gothic", Font.BOLD, 20);
        background.setFont(textFont);   //set background font

        //display their statistics
        background.drawString("" + game.getMoney(), 158, 233);  //displaying the number of coins the player obtained
        background.drawString("" + game.getWave(), 160, 327);   //displaying the number of waves the player beated
        background.drawString("" + game.getEnemiesKilled(), 282, 279);  ////displaying the number of total enemies killed in the game
    }
}
