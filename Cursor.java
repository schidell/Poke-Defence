import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The cursor moves around in the world and chooses a location to add the shooter. It will only add the shooter if space is available, and there is no other shooter occupying the space.
 * Its image will update depending on the shooter chosen to add to the world. It will deselect after the boss level waves.
 * 
 * @author Amber Chen, Maggie Lin, Catherine Lee
 * @version June 2018
 */
public class Cursor extends Actor
{
    private GreenfootImage mainImage;

    private int actCounter; //counts act

    private int tileSize;   //gets size of tiles of grid in world
    private Grid theGrid;   //gets grid in world

    private int tilesAcross;    //gets number of tiles across in world
    private int tilesDown;  //gets number of tiles down in world

    /**
     * Creates a cursor that will appear to place shooters in world.
     */
    public Cursor()
    {
        mainImage = getImage(); //get image
        tileSize = 0;   //no grid
    }

    /**
     * Updates the cursor. The size of the grid changes depending on the level, so the update method
     * can adjust the grid's properties accordingly.
     */
    public void update()
    {
        GameWorld w = (GameWorld)getWorld();    //get world cursor is in
        theGrid = w.getGrid();  //get grid in world
        tileSize = theGrid.getTileWidth();  //get tile size
        tilesAcross = theGrid.getTilesAcross(); //get num of tiles across
        tilesDown = theGrid.getTilesDown(); //get num of tiles down
        getImage().scale(tileSize, tileSize);   //scale image to tile size
    }

    /**
     * The cursor will snap to the grid in the world, as opposed to moving freely.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(actCounter == 0) //on first act
        {
            update();   //update cursor
            actCounter++;   //will no longer call update method from cursor class
        }

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo != null)   //if mouse exists
        {
            setLocation((mouseInfo.getX()+tileSize)/tileSize*tileSize-tileSize/2,(mouseInfo.getY()+tileSize)/tileSize*tileSize-tileSize/2);//snap to a 50x50 grid with integer division
        }
    }    

    /**
     * See if space is available at certain location in grid.
     * 
     * @return boolean  True if space is available, otherwise false
     */
    public boolean checkIfSpaceIsAvailable()
    {
        int currentGridX = getX() / tileSize;   //get which x tile cursor is on
        int currentGridY = getY() / tileSize;   //get which y tile cursor is on

        char gridState = theGrid.getState(currentGridY, currentGridX);  //find state of current grid

        if(gridState == '1'|| gridState == 'p'||gridState=='s'||gridState=='f') 
            return false; //returns false if the spot on the grid isn't empty
        return getOneObjectAtOffset(0,0,Shooter.class) == null; //return true if there are no objects where the cursor is
    }

    /**
     * Adds image of which shooter is being added to the image of cursor so user knows which shooter has been selected.
     * 
     * @param image Image to combine cursor image with
     */
    public void combineImage(GreenfootImage image)
    {
        GreenfootImage newImage = new GreenfootImage(mainImage.getWidth(), mainImage.getHeight());

        newImage.drawImage(mainImage, 0, 0);

        newImage.drawImage(image, mainImage.getWidth() / 2 - image.getWidth() / 2, mainImage.getHeight() / 2 - image.getHeight() / 2);

        setImage(newImage); //combines the cursor with an image
    }

    /**
     * Resets image so there is no shooter within the cursor.
     */
    public void resetImage()
    {
        setImage(mainImage);
    }
}
