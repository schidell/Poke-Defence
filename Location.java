
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * The Location is each invidivual tile on the grid. It can have different states that allow for differences in its image and function.
 * Each scene has a different image.
 * 
 * @author Maggie Lin, Catherine Lee
 * @version June 2018
 */
public class Location extends Actor
{
    //stores varaibles used to create the locations
    private int xSize;
    private int ySize;

    private char state; //1 --> obstacle, p --> path, a --> empty1, b --> empty2, s--> start, f --> finish

    private GreenfootImage myImage;

    private char scene;
    /**
     * Creates a location based on its scene and state.
     * 
     * @param x     Length of tile
     * @param y     Height of tile
     * @param state State of tile (1: obstacle, p: path, a: empty1, b: empty2, s: start, f: finish)
     * @param scene Scene of tile(u: underwater, f: fire, g: grass)
     */
    public Location(int x, int y, char state, char scene)
    {
        this.scene = scene; //set scene
        xSize = x;  //set length
        ySize = y;  //set height
        this.state = state; //set state
        setPicture();   //sets picture for location based on its state and scene
    }

    /**
     * Set state of location.
     * 
     * @param state New state to set location to
     */
    public void setState(char state)
    {
        this.state = state;

        setPicture();   //changes picture based on its state
    }

    /**
     * Get state of location.
     * 
     * @return char State of location (1: obstacle, p: path, a: empty1, b: empty2, s: start, f: finish)
     */
    public char getState()
    {
        return state;
    }

    //sets location's image based on its scene and state
    private void setPicture()
    {
        if(state == 'f')    //if finish state
        {
            GreenfootImage pokemonCentre = new GreenfootImage("pokecentre.png");
            pokemonCentre.scale((int)(xSize * 0.9), (int)(ySize * 0.9));
            myImage.drawImage(pokemonCentre, (xSize - pokemonCentre.getWidth()) / 2, (ySize - pokemonCentre.getHeight()) / 2);  //draw pokemon centre onto tile
        }
        else if(scene == 'g')   //if grass scene
        {
            if(state == 'a')    //if empty1
            {
                myImage = new GreenfootImage("g1.png");
                myImage.scale(xSize, ySize);    //set image to empty1 image of grass scene
            }
            else if(state == 'b')   //if empty 2
            {        
                myImage = new GreenfootImage("g2.jpg");
                myImage.scale(xSize, ySize);    //set image to empty2 image of grass scene
            }
            else if(state == 'p')   //if path
            {
                myImage = new GreenfootImage("gp.png");
                myImage.scale(xSize, ySize);    //image of path
            }
            else if(state == '1')   //if obstacle
            {
                GreenfootImage tree = new GreenfootImage("go.png");
                tree.scale((int)(xSize * 0.7), ySize);

                myImage.drawImage(tree, (int)(xSize * 0.15), 0);    //draw tree
            }
            else if(state == 's')   //start tile
            {
                GreenfootImage image = new GreenfootImage("grass.png");
                image.scale((int)(xSize * 1), (int)(ySize * 0.3));
                myImage.drawImage(image, (xSize - image.getWidth()) / 2, (ySize - image.getHeight() - 2));  //draw grass
            }
        }
        else if(scene == 'u')   //if underwater scene
        {
            if(state == 'a')    //if empty1
            {
                myImage = new GreenfootImage("u1.jpg");
                myImage.scale(xSize, ySize);    //set image to empty1 underwater
            }
            else if(state == 'b')   //if empty 2
            {        
                myImage = new GreenfootImage("u2.jpg");
                myImage.scale(xSize, ySize);    //set image to empty2 underwater
            }
            else if(state == 'p')   //path
            {
                myImage = new GreenfootImage("up.jpg");
                myImage.scale(xSize, ySize);    //path image
            }
            else if(state == '1')   //obstacle
            {
                GreenfootImage shell = new GreenfootImage("uo2.png");
                shell.scale((int)(xSize * 0.7), (int)(ySize * 0.7));

                myImage.drawImage(shell, (int)(xSize * 0.15), (int)(ySize * 0.15)); //draw starfish
            }
            else if(state == 's')   //start
            {
                GreenfootImage image = new GreenfootImage("water.png");
                image.scale((int)(xSize * 1), (int)(ySize * 0.4));
                myImage.drawImage(image, (xSize - image.getWidth()) / 2, (ySize - image.getHeight() - 2));  //draw water splash
            }
        }
        else if(scene == 'f')   //fire scene
        { 
            if(state == 'a')    //empty1
            {
                myImage = new GreenfootImage("f1.jpg");
                myImage.scale(xSize, ySize);    //set image to empty1
            }
            else if(state == 'b')   //empty2
            {        
                myImage = new GreenfootImage("f2.png");
                myImage.scale(xSize, ySize);    //set image to emtpy2
            }
            else if(state == 'p')   //path
            {
                myImage = new GreenfootImage("fp.jpg");
                myImage.scale(xSize, ySize);    //set image to path
            }
            else if(state == '1')   //obstacle
            {
                GreenfootImage shell = new GreenfootImage("fo.png");
                shell.scale((int)(xSize * 0.7), (int)(ySize * 0.7));

                myImage.drawImage(shell, (int)(xSize * 0.15), (int)(ySize * 0.15)); //draw volcano
            }
            else if(state == 's')   //start
            {
                GreenfootImage image = new GreenfootImage("fire.png");
                image.scale((int)(xSize * 1), (int)(ySize * 0.4));
                myImage.drawImage(image, (xSize - image.getWidth()) / 2, (ySize - image.getHeight() - 2));  //draw flames
            }
        }
        setImage(myImage);  //set image
    }

}