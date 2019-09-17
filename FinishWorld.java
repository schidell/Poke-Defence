import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * FinishWorld is an abstract class that is inherited by WinWorld and LoseWorld.
 * It adds a return to menu button that will take the user back to the start menu.
 * 
 * @author Maggie Lin
 * @version June 2018
 */
public abstract class FinishWorld extends World
{
    private TextButton toMenu;  //button to take user to start world
    private StartWorld s;   //start world that user will be taken to
    /**
     * Constructor for objects of class FinishWorld.
     * 
     */
    public FinishWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        toMenu = new TextButton("Return to Menu");  //creates button
        addObject(toMenu, 730,570); //adds button to world
    }

    /**
     * If the return to menu button is clicked, it will create a StartWorld
     * and take the user there. 
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(toMenu))  //if return to menu button is pressed
        {
            s = new StartWorld();   //creates new start world
            Greenfoot.setWorld(s);  //sets screen as start world
        }
    }
}
