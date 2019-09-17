import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

//imports to read file
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * StartWorld is the world the player starts in. It allows the player to navigate to different scenes.
 * They also have the option of choosing a previously saved game to load.
 * The different scenes are fire, underwater, or grass. They vary in the aesthetics as well as the pokemon that appear.
 * 
 * @author Maggie Lin
 * @version June 2018
 */
public class StartWorld extends World
{
    //declare objects

    //variables for background image
    private GreenfootImage background;
    private Font textFont;
    private Color foreground;

    //variables for sound
    private static GreenfootSound music;

    //game that will be created
    private GameWorld game;

    //buttons that allow user to choose scene
    private ImageButton grassButton;
    private ImageButton fireButton;
    private ImageButton waterButton;

    //allows user chance to load previously saved game
    private TextButton loadGame;

    //scanner to read text file
    private static Scanner scan;
    /**
     * Constructor for objects of class StartWorld. This is the first world the user will see.
     * User can choose to go to the grass, fire, or underwater scene, or choose a previously loaded game.
     */
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        // set background image
        background = new GreenfootImage("backgroundStart.png");
        setBackground(background);
        background.scale(getWidth(), getHeight());  //scales to size

        //adds name of the game to the world
        GreenfootImage text = new GreenfootImage("generatedtext.png");
        text.scale((int)(text.getWidth() / 1.1), (int)(text.getHeight() / 1.1));
        background.drawImage(text, getWidth() / 2 - text.getWidth() / 2, getHeight() / 2 - text.getHeight() / 2 - 100);

        //adds text to tell user to choose world
        foreground = new Color(72, 117, 188);
        background.setColor(foreground);    //set color

        textFont = new Font("Showcard Gothic", Font.BOLD, 20);
        background.setFont(textFont);   //set font

        background.drawString("Choose a world", getWidth() / 2 - 92, 350);  //draw text to world

        //adds buttons for user to navigate game

        //creates grass button
        GreenfootImage grass = new GreenfootImage("g_icon.png");
        grass.scale(120,120);
        grassButton = new ImageButton(grass);
        //creates water button
        GreenfootImage water = new GreenfootImage("u_icon2.png");
        water.scale(120,120);
        waterButton = new ImageButton(water);
        //creates fire button
        GreenfootImage fire = new GreenfootImage("f_icon.png");
        fire.scale(120, 120);
        fireButton = new ImageButton(fire);

        //adds button to world
        addObject(grassButton, getWidth() / 2, 430);
        addObject(fireButton, getWidth() / 2 - 200, 430);
        addObject(waterButton, getWidth() / 2 + 200, 430);

        loadGame = new TextButton("Load Game");
        addObject(loadGame, getWidth() / 2, 550);
        //plays music

        if (music == null)
        { 
            //add background music
            music = new GreenfootSound("gamemusic2.mp3");
            music.setVolume(50);
            music.playLoop();
        }
    }

    /**
     *  Will change to the game world. Depending on which button is pressed, it will create a game world with the specified scene.
     *  If the user chooses to load a previously saved game, the world will create a new game world of the previous game.
     *  This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(grassButton)) //if grass button clicked
        {
            game = new GameWorld(1, 'g');   //create grass world
            Greenfoot.setWorld(game);
        }
        else if(Greenfoot.mouseClicked(fireButton)) //if fire button clicked
        {
            game = new GameWorld(1, 'f');   //create fire world
            Greenfoot.setWorld(game);
        }
        else if(Greenfoot.mouseClicked(waterButton))    //if water button clicked
        {
            game = new GameWorld(1, 'u');   //create underwater world
            Greenfoot.setWorld(game);
        }
        else if(Greenfoot.mouseClicked(loadGame))   //reads and uses text file
        {
            ArrayList<String> chars = new ArrayList<String>();  //the lines of each grid state
            String wave = "";   //wave of saved game
            String scene = "";  //scne of saved game
            String lives = "";  //number of lives the user had when game was saved
            String money = "";  //money of saved game
            char[][] states = null; //2D array of the grid states to use to construct grid
            ArrayList<String> xCoords = new ArrayList<String>();    //x coordinates of shooters of saved game
            ArrayList<String> yCoords = new ArrayList<String>();    //y coordinates of shooters of saved game
            ArrayList<String> shooterTypes = new ArrayList<String>();   //shooter types of saved shooters
            int enemiesKilled = 0;  //enemies killed by user

            try {    //try to open saved file
                scan = new Scanner(new File("game.txt"));   //reads game.txt file
                //has not saved any values yet
                boolean savedChar = false;
                boolean savedWave = false;
                boolean savedScene = false;
                boolean savedLives = false;
                boolean savedMoney = false;
                boolean savedX = false;
                boolean savedY = false;
                boolean savedStates = false;
                boolean savedDeadEnemies = false;

                while(scan.hasNext())   //while more lines exist
                {
                    String nextLine = scan.nextLine();
                    if(!nextLine.equals("---") && !savedChar)   //if char grid hasn't been saved and line is not ---
                    {
                        chars.add(nextLine);    //add to chars arraylist
                    }
                    else if(!savedChar && nextLine.equals("---"))   //if char grid hasn't been saved and line is ---
                    {
                        savedChar = true;   //char grid has been saved
                    }
                    else if(savedChar && !savedScene)   //if char grid is saved and scene has not been saved
                    {
                        scene += nextLine;  //save scene
                        savedScene = true;  //scene has been saved from text file
                    }
                    else if(savedScene && !savedWave)
                    {
                        wave += nextLine;   //save wave
                        savedWave = true;   //wave has been saved from text file
                    }
                    else if(savedWave && !savedLives)
                    {
                        lives += nextLine;  //save lives
                        savedLives = true;  //lives has been saved from text file
                    }
                    else if(savedLives && !savedMoney)
                    {
                        money += nextLine;  //save money
                        savedMoney = true;  //money has been saved from text file
                    }
                    else if(savedMoney && !savedX && !nextLine.equals("***"))   //if money has been saved and x coord have not been saved and line is not ***
                    {
                        xCoords.add(nextLine);  //add to list of x coordinates
                    }
                    else if(!savedX && nextLine.equals("***"))  //if x coord have not been saved and line is ***
                    {
                        savedX = true;  //x coordinates saved
                    }
                    else if(!savedY && !nextLine.equals("***")) //if y coord have not been saved and line is not ***
                    {
                        yCoords.add(nextLine);  //add to list of y coordinates
                    }
                    else if(!savedY && nextLine.equals("***"))  //if y coord have not been saved and line is ***
                    {
                        savedY = true;  //y coordinates saved
                    }
                    else if(!savedStates && !nextLine.equals("==="))    //if states of shooters have not been saved and line is not ===
                    {
                        shooterTypes.add(nextLine); //add to list of shooter states
                    }
                    else if(!savedStates && nextLine.equals("===")) //if states of shooters have not been saved and line is ===
                    {
                        savedStates = true; //shooter states have been saved
                    }
                    else if(savedStates && !savedDeadEnemies)    //if states have been saved and dead enemy count is not saved
                    {
                        enemiesKilled = Integer.parseInt(nextLine); //save dead enemy count
                        savedDeadEnemies = true;    //saved
                    }
                }

                states = new char[chars.size()][chars.get(0).length()]; //initialize char grid
                for(int i = 0; i < states.length; i++)
                {
                    for(int j = 0; j < states[0].length; j++)
                    {
                        states[i][j] = chars.get(i).charAt(j);  //save char from String in chars arraylist
                    }
                }
            }

            catch(FileNotFoundException e)  //catch file not found exception
            {

            }
            finally {
                if(scan != null)
                    scan.close();   //close scan if scan exists
            }

            //creates game based on given variables
            game = new GameWorld(states, scene.charAt(0), Integer.parseInt(wave), Integer.parseInt(lives), Integer.parseInt(money), xCoords, yCoords, shooterTypes, enemiesKilled);
            Greenfoot.setWorld(game);   //sets world as game world with saved variables
        }
    }
}
