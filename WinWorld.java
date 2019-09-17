import greenfoot.*;
/**
 * The world displays an end screen to tell the user that they have won the game by surviving all 40 waves.
 * <p>
 * IMAGE CREDIT: <a href=https://wallpapercave.com/pokemon-wallpaper>https://wallpapercave.com/pokemon-wallpaper</a>
 * 
 * @author Kyle Chan
 * @version June 2018
 */
public class WinWorld extends FinishWorld
{
    /**
     * Constructor for objects of class WinWorld. It is created when the user successfully completes all 40 waves.
     * It creates an 800x600 size world, and adds a return to menu button that will take the user back to the start world.
     * 
     */
    public WinWorld()
    {    
        super();    //creates world with 800x600 pixels, and adds a return to menu button that will take the user back to the start world 

        getBackground().scale(getWidth(), getHeight()); //scales image to fit
    }
}
