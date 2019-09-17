import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Where user can buy different shooters according to what scene they are in. 
 * The shop offers three different shooters based on the scene. Each shooter has a different shooter frequency depending on its price.
 * 
 * @author Catherine Lee, Maggie Lin
 * @version June 2018
 */
public class Shop extends Actor
{
    private GreenfootImage myImage;

    //buttons for shooters
    private Button one;
    private Button two;
    private Button three;

    int actCounter = 0;

    /**
     * Creates a shop. Appears as a horizontal bar and shows prices of shooters, and allows user to choose which shooter they'd like to add to the world.
     * 
     * @param scene Scene the shop will appear in
     */
    public Shop(char scene)
    {
        myImage = new GreenfootImage(800, 40);
        Color background = new Color(250,250,250,150);
        myImage.setColor(background);        
        myImage.fill(); //image is transluscent horizontal white bar

        GreenfootImage coin = new GreenfootImage("coin.png");
        coin.scale(30,30);
        //draw 3 coins onto shop to indicate the 3 prices
        myImage.drawImage(coin, -5, 6);
        myImage.drawImage(coin, 95, 6);
        myImage.drawImage(coin, 195, 6);

        char first = '0';   //cheapest pokemon
        char second = '0';  //middle grade pokemon
        char third = '0';   //expensive pokemon
        if(scene == 'g')    //grass scene
        {
            //sets which shooter it is based on its prices
            first = 'b';    //bulbasaur
            second = 'i';   //ivysaur
            third = 'v';    //venasaur
        }
        else if(scene == 'f')   //fire scene
        {
            //sets which shooter it is based on its prices
            first = 'r';    //charmander
            second = 'n';   //charmeleon
            third = 'd';    //charizard
        }
        else if(scene == 'u')   //underwater scene
        {
            //sets which shooter it is based on its prices
            first = 's';    //squirtle
            second = 'w';   //wartortle
            third = 'l';    //blastoise
        }

        //initializes buttons based on the scene and price
        one = new Button(first);
        two = new Button(second);
        three = new Button(third);

        myImage.setColor(Color.BLACK);
        myImage.setFont(new Font("Arial", Font.PLAIN, 18));

        //writes prices of each button
        myImage.drawString("100", 17, 27);
        myImage.drawString("200", 117, 27);
        myImage.drawString("300", 217, 27);

        setImage(myImage);  //set image
    }

    /**
     * During the first act, will add the buttons to the world to allow the user to choose a shooter.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(actCounter == 0) //on first act
        {
            actCounter++;
            getWorld().addObject(one, 70, 580); //add lowest shooter button
            getWorld().addObject(two, 170, 580);    //add middle shooter button
            getWorld().addObject(three, 270, 580);  //add most expensive shooter button
        }
    }    
}
