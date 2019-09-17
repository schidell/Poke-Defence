import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a button used to buy pokemon.
 * 
 * @author Amber  
 * @version June 2018
 */
public class Button extends Actor
{
    //stores variables used to create the buton
    protected int price;
    private char whichPokemon;
    /**
     * Creates a button using the pokemon
     * 
     * @param whichPokemon Character that correlates to a pokemon to be used to make the button. b:bulbasaur i:ivysaur v:venusaur s:squirtle w:wartortle B:blastoise r:charmander n:charmelion d:charizard 
     */
    public Button(char whichPokemon)
    {
        setImage("s" + whichPokemon + "2.png");//sets the image according to the pokemon
        getImage().scale((int)(getImage().getWidth() * 1.5), (int)(getImage().getHeight() * 1.5));
        this.whichPokemon = whichPokemon;
        price = 50;
        //sets the price according to the pokemon
        if(whichPokemon == 'b' || whichPokemon == 'r' || whichPokemon == 's')
            price = 100;
        else if(whichPokemon == 'i' || whichPokemon == 'n' || whichPokemon == 'w')
            price = 200;
        else if(whichPokemon == 'v' || whichPokemon == 'd' || whichPokemon == 'l')
            price = 300;
    }

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Checks if the mouse clicked on this button
        if(Greenfoot.mouseClicked(this))
        {
            buttonPressed();
        }
    }    

    /**
     * Tells the world this button is selected if the mouse clicks on this button
     */
    protected void buttonPressed()
    {
        //if the mouse gets clicked on us, tell the world that we're the currently selected shooter-buying button.
        if(GameWorld.money >= price)
        {
            //if they can afford us
            GameWorld world = (GameWorld) getWorld();
            world.setCurrentButtonSelected(this);
            world.getCursor().combineImage(getImage());
        }
    }

    /**
     * Sets the image to look deselected.
     */
    public void deselected()
    {
        //setLocation(getX(),getY()+25); //we got deselected, make it look like we're deselected
        ((GameWorld)getWorld()).getCursor().resetImage();
    }

    /**
     * Getter method to get the price of the shooter
     * 
     * @return int Price of pokemon shooter
     */
    public int getPrice()
    {
        //will return how much a shooter costs.
        return price;
    }

    /**
     * Getter method to get the type of pokemon for the button
     * 
     * @return char Character that correspnds to the pokemon. b:bulbasaur i:ivysaur v:venusaur s:squirtle w:wartortle B:blastoise r:charmander n:charmelion d:charizard
     */
    public char getWhichPokemon()
    {
        return whichPokemon;
    }

}
