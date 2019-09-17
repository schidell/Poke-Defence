import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.util.List;

//imports to write to file
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

import java.util.ArrayList;

/**
 * The GameWorld is where the main action of the world takes place. The user will attempt to place shooters on available tiles in the world
 * and try to prevent all the enemies from reaching the end. 
 * <p> The world creates a grid that contains a path to a finish line. The enemies will move along this path, trying to reach the finish point.
 * The user's goal is to stop them with shooters that shoot projectiles at the enemies to kill them. Each scene provides three different enemy types
 * that cost different amounts and shoots at different frequency. To add a shooter to the world, the user must click on the shooter they want to add to the world,
 * and then click on the location they want to add the shooter. Provided that the location is empty, the user can add their shooter. The shooters have a firing range of 500 pixels,
 * so location is very important. To add another shooter,
 * the user will need to click on the button again to add the same or another shooter. An image in the cursor shows which shooter has been selected
 * to be added to the world.
 * <p> Every enemy killed will earn a certain number of coins. If an enemy reaches the end, the user will lose a life. Boss level enemies cause the user to lose more lives.
 * When the user has no lives left, they will be directed to the finish screen. If they manage to complete all 40 waves, they will be directed to a win screen.
 * <p> The user has the option in the main game world to save their current game, or exit to the menu. If the game world is saved, the user
 * can open it by clicking load game in the main start world.
 * <p> Currently, one bug is that when a shooter is added to the world, there are two copies added. This has no effect on the gameplay.
 * This affects saving the game to a text file, so to save the file, the array lists that store shooter information are cut in half to get rid of the extra copy;
 * however, if the shooters were added to the world normally, this part of the saveToTextFile() method would not be included.
 * <p>
 * <h3>CREDITS</h3>
 * <div>CODE <br>
 * &nbsp;&nbsp;&nbsp;&nbsp; TextButton class:   Jordan Cohen<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; Cursor class:       http://www.greenfoot.org/scenarios/6871<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; Shooter class:   http://www.greenfoot.org/scenarios/6871<br>
 * 
 * <p>IMAGES<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; All Pokemon sprites:    https://www.spriters-resource.com/fullview/34110/<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; backgroundStart.png:    https://hdqwalls.com/download/pixel-art-2-pic-3840x2400.jpg <br>
 * &nbsp;&nbsp;&nbsp;&nbsp; bullet.png:             https://lh3.googleusercontent.com/otPPxYwjUTN8WaEleuU48WbZJ4woIHjNUw1as9joBb0vwEgXBb1e4lb4hjVRu7vQjN5bJg=s85<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; coin.png:               http://pixelartmaker.com/art/52ea0019957650a.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; endScreen.png:          https://images7.alphacoders.com/310/310611.jpg<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; exit.png:               https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Stop_x_nuvola_blue.svg/2000px-Stop_x_nuvola_blue.svg.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; f_icon.png:             https://s.ecrater.com/stores/242540/4f252c4b4da0b_242540n.jpg<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; f1.jpg, f2.png:         https://lh3.googleusercontent.com/gi_kOLAduSq_2aCXxDwWywFT-q1Cjty27t5eucsXPgsPHq2aiQ5UnqoCiNLu5nwHvSIN6g=s85<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; fire.png:               https://www.freeiconspng.com/uploads/flames-png-16.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; fo.png:                 https://d1u5p3l4wpay3k.cloudfront.net/dontstarve_gamepedia_en/thumb/c/cf/Volcano.png/300px-Volcano.png?version=992b4f08b3848074504f3ec4a0b7e751<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; fp.png:                 https://lh3.googleusercontent.com/FcA9MnwccY2oxJBMF6iDcLSmR9H8JV1L5gP27Cly8DNklJd4yZStam4EO_OD1vmqI_vQ=s85 <br>
 * &nbsp;&nbsp;&nbsp;&nbsp; g_icon.png:             http://41.media.tumblr.com/dffb232f11f10df7a7b0cbbf4f89de28/tumblr_mmi88btyBi1spn836o6_400.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; g1.png, g2.jpg:         https://orig00.deviantart.net/6579/f/2011/078/6/2/seamless_cartoon_grass_texture_by_mbrockwell-d3byyeg.jpg<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; go.png:                 https://orig00.deviantart.net/3d5f/f/2014/276/5/4/tons_of_tileset_3_10___gaia_trees_by_phyromatical-d81epok.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; gp.png:                 https://i.pinimg.com/originals/cd/a5/6b/cda56bcf72ecb6bfb95f718f6126065e.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; grass.png:              http://www.freepngimg.com/download/grass/3-grass-png-image-green-picture.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; pika2.jpg:              https://wallpapercave.com/pokemon-wallpaper<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; pokecentre.png:         http://orig00.deviantart.net/3410/f/2015/135/c/9/c90336fa14688ac4940e66b88299ccad-d8tffwo.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; scoreCounter.png:       https://lh3.googleusercontent.com/59oXvHEZn9kdBcchRS9qA0ucqYik21gLE8F2_8Z02WIA1674vj0bR0ylUKwGaWiRaWzZow=s88<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; u_icon2.png:            https://s.ecrater.com/stores/242540/4f24c70e5434b_242540b.jpg<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; u1.jpg, u2.jpg:         https://www.filterforge.com/filters/11889.jpg<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; uo2.png:                http://hatenylo.com/wp-content/uploads/ktz/starfish-clipart-colorful-seashell-clipart-png-ocean-tides-pinterest-art-science-clipart-361a6se4vz326mq3dw93wg.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; up.jpg:                 http://www.psd-dude.com/tutorials/resources-images/over-50-sand-textures-free-download/877-sand-dune-seamless-texture.jpg<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; water.png:              http://www.clker.com/cliparts/o/P/T/3/R/W/blue-water-splash-few-more-drops-hi.png<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; generatedtext.png, other text with "Pokemon font" generated by: https://www.font-generator.com/fonts/Pokemon-Solid/?size=82&color=fff200&bg=0072bc <br>
 * &nbsp;&nbsp;&nbsp;&nbsp;
 * 
 * <p>SOUND<br>
 * &nbsp;&nbsp;&nbsp;&nbsp; gamemusic2.mp3:         https://www.youtube.com/watch?v=NdJQopRuH1E<br>
 * 
 * @author Maggie, Shivani, Catherine, Amber, Kyle
 * @version June 2018
 */
public class GameWorld extends World
{
    //leads to new world once user has lost or won
    private LoseWorld lost;
    private WinWorld win;

    StartWorld s;   //new start world if exit is clicked

    //enemy states
    private int actCounter;
    private int enemyCount;
    private int enemySpawned;
    private static int enemiesKilled;

    //user states
    private int lives;
    private int bossSpawned;

    private LifeBar heartBar;
    private Counter counter;
    private Shop shop;
    public static int money;

    //grid variables
    private Grid grid;
    private int tileSize;
    private int tilesAcross;
    private int tilesDown;
    private Grid[] grids;

    //world variables
    private char scene;
    private int wave;

    private Cursor cursor; //the cursor
    private Button currentButtonSelected = null; //the current shooter button selected by the player
    //determines what gets placed when the mouse gets clicked
    private boolean cursorExists = true;    //true if cursor is in world, otherwise false

    //timer variables
    private SimpleTimer timer;
    private boolean waitBefore = false;
    private boolean waitAfter = false;
    private boolean startTimer = false;

    //buttons
    private TextButton save;
    private ImageButton exit;

    //text variables
    private Text counterWave;   //displays the world WAVE
    private Text waveCount; //displays wave number

    /**
     * Increases the amount of money user has and will display it on the counter. 
     * It will return the new money value.
     * 
     * @param increaseCoin  Amount money is increased by
     * @return int          Amount of money user currently has
     */
    public int getCoin(int increaseCoin)
    {
        money += increaseCoin;
        counter.setValue(money);
        return money;
    }

    /**
     * Constructor for objects of class GameWorld. This constructor is called when the game is created from a previously saved game.
     * 
     * @param states        A character array that stores the state of each location in the grid
     * @param scene         What scene has been chosen (u: underwater, f: fire, g: grass)
     * @param wave          What wave the object is in (goes up to 4)
     * @param lives         The lives the user previously had
     * @param xCoords       X coordinates of shooters that were saved
     * @param yCoords       Y coordinates of shooters that were saved
     * @param shooterTypes  Which pokemon each shooter was
     * @param enemiesKilled Number of enemies that were killed
     */
    public GameWorld(char[][] states, char scene, int wave, int lives, int money, ArrayList<String> xCoords, ArrayList<String> yCoords, ArrayList<String> shooterTypes, int enemiesKilled)
    {
        this(wave, scene);  //creates world based on another constructor

        this.lives = lives; //saves number of lives user has
        heartBar.update(100, lives, 10);    //updates user's life bar

        this.money = money;
        counter.setValue(money);    //set amount of money user has

        this.enemiesKilled = enemiesKilled; //set number of enemies killed

        grid = new Grid(states, getWidth() / states[0].length, getHeight() / states.length, scene); //creates new grid with char array
        loadGrid(grid); //loads grid and adds to world

        if(cursorExists)    //if cursor is in world
            cursor.update();    //update cursor
        else    //if cursor not in world
        {
            addObject(cursor, 0, 0);    //add cursor to world
            cursor.update();    //update cursor
            removeObject(cursor);   //remove cursor from world
        }

        //for every x coordinate in xCoords
        for(int i = 0; i < xCoords.size(); i++)
        {
            addObject(new Shooter(shooterTypes.get(i).charAt(0)), Integer.parseInt(xCoords.get(i)), Integer.parseInt(yCoords.get(i)));  //create a new shooter based on xCoords, yCoords, and shooterTypes
        }

        timer.mark();   //starts timer
    }

    //loads grid sizes for each wave
    private void loadLevelGrids()
    {
        //load grid sizes for each wave
        grids = new Grid[5];    //grids is an array of Grid
        grids[1] = new Grid(12, 16, getWidth() / 16, getHeight() / 12, scene);  //12 by 16 tiles
        grids[2] = new Grid(12, 16, getWidth() / 16, getHeight() / 12, scene);  //12 by 16 tiles
        grids[3] = new Grid(15, 20, getWidth() / 20, getHeight() / 15, scene);  //15 by 20 tiles
        grids[4] = new Grid(15, 20, getWidth() / 20, getHeight() / 15, scene);  //15 by 20 tiles
    }

    /**
     * Constructor for objects of class GameWorld.
     * 
     * @param wave  What wave the object is in (goes up to 4)
     * @param scene What scene has been chosen (u: underwater, f: fire, g: grass)
     */
    public GameWorld(int wave, char scene)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 

        this.wave = wave;   //sets wave
        this.scene = scene; //sets scene

        loadLevelGrids();   //loads grid sizes for each level

        bossSpawned = 1;    //number of bosses spawned

        //sets grid that should be added to world
        if(wave <= 10)
        {
            grid = grids[1];
        }

        else if(wave <= 20)
        {
            grid = grids[2];
        }
        else if(wave <= 30)
        {
            grid = grids[3];
        }
        else if(wave <= 40)
        {
            grid = grids[4];
        }

        if(wave % 10 == 0)  //if boss levels
        {
            enemyCount = bossSpawned;   //only 1 enemy spawned
        }
        else   //if game is not over
        {
            enemyCount = wave % 10; //enemy count is the ones digit of number
        }

        loadGrid(wave); //adds the grid to the world

        actCounter = 0;   //counts acts
        enemySpawned = 0;   //enemies spawned
        enemiesKilled = 0;  //total enemies killed

        lives = 10; //lives user has

        //what objects should show up at the top
        setPaintOrder(Shooter.class, Text.class, ImageButton.class, TextButton.class, LifeBar.class, Button.class, Shop.class, Projectile.class, Shooter.class, HealthBar.class, Enemy.class, Counter.class, Cursor.class, Grid.class, Location.class);

        cursor = new Cursor(); //create a cursor
        addObject(cursor,0,0);

        counter = new Counter();    //adds counter to top left corner
        addObject(counter,50,20);
        counter.setValue(money);    //counter displays the amount of money the user has

        //displays a stationary "wave" sign
        counterWave = new Text("WAVE:", 35, new Color(0, 0, 0), new Color(0,0,0,0));
        addObject(counterWave, 400, 580);
        //displays the current wave number
        waveCount = new Text(wave, 35, new Color(0, 0, 0), new Color(0,0,0,0));
        addObject(waveCount, 470, 580);                                                                                    

        shop = new Shop(scene);  //adds shop to world
        addObject(shop, getWidth() / 2, getHeight() - 20);

        heartBar = new LifeBar();
        addObject(heartBar, 730, 580);  //adds user's health bar to world

        save = new TextButton("SAVE");  //adds save button to top right corner
        addObject(save, 725, 26);

        GreenfootImage exitButton = new GreenfootImage("exit.png"); //adds exit button to top right corner
        exitButton.scale(30, 30);
        exit = new ImageButton(exitButton);
        addObject(exit, 770, 26);

        money = 5000;   //sets starting amount of money
        counter.setValue(money);

        timer = new SimpleTimer();  //creates timer so there is a pause before enemies appear
        timer.mark();   //starts timer

    }

    /**
     * Constructor for objects of class GameWorld. Builds a world with wave 1, grass scene.
     * 
     */
    public GameWorld()
    {    
        this(1, 'g');
    }

    /**
     * Returns the grid that is currently in the world.
     * 
     * @return Grid Grid that is currently in world
     */
    public Grid getGrid()
    {
        return grid;
    }

    /**
     *  Coordinates everything wihtin the world.
     *  This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //System.out.println(getObjects(null).size());
        //enemy count should change after every wave and should reset enemySpawned back to 0. 

        if(!waitBefore) //makes enemies wait 1 seconds before showing up if the wait has not been finished
        {
            if(timer.millisElapsed() > 4000)    //if 1 second has passed
            {
                timer.mark();   //reset timer
                waitBefore = true;  //time has been waited
            }
        }

        if (waitBefore && enemySpawned < enemyCount)  //if not all the enemies have been spawned
        {
            if (actCounter % (70 / Enemy.getSpeed(wave)) == 0)  //enemies come out uniformly no matter their speed
            {
                spawnEnemies(); //spawn enemies
                enemySpawned++;    //increase number of enemies spawned
            }
        }

        if(cursorExists && Greenfoot.mouseClicked(null))    //if the cursor is in world and the mouse is clicked
        {
            mouseClicked(); //tell ourselves when the mouse gets clicked
        }

        if (lives > 0 && wave > 40) //if user makes it to the end
        {
            //display win screen
            win = new WinWorld();
            Greenfoot.setWorld(win);
        }

        //controls cursor
        MouseInfo mouse = Greenfoot.getMouseInfo(); //mouse info
        if(mouse != null)
        {
            int currentGridX = mouse.getX() / tileSize; //x coordinate of mouse on grid
            int currentGridY = mouse.getY() / tileSize; //y coordinate of mouse on grid

            if(currentGridY < tilesAcross - 1)  //if not on bottom row of grid
            {
                if(!cursorExists)   //if cursor is not currently in world
                {
                    addObject(cursor, mouse.getX(), mouse.getY());  //add cursor to world
                    cursorExists = true;    //cursor exists
                }
            }
            else if(cursorExists)   //if mouse is in bottom row
            {
                removeObject(cursor);   //remove cursor from world
                cursorExists = false;   //cursor is no longer in world
            }
        }

        //changes to new wave
        if(lives > 0 && enemySpawned > 0)  //if the user is alive and the enemies have been spawned
        {
            if(enemiesGone())   //if there are no enemies in world 
            {
                if(waitAfter && wave <= 40)   //if user has waited for half a second
                {
                    changeWave();   //change wave
                }
                else
                {
                    if(!startTimer) //if has not waited and timer hasn't been started
                    {
                        timer.mark();   //start timer
                        startTimer = true;  //timer has been started
                    }
                    else
                    {
                        if(timer.millisElapsed() > 1000)    //if a second have passed
                        {
                            waitAfter = true;   //user has waited
                        }
                    }
                }
            }
        }

        //if user clicks save
        if(Greenfoot.mouseClicked(save))
        {
            //save world data to text file
            saveToTextFile();
        }
        else if(Greenfoot.mouseClicked(exit))   //if user clicks exit
        {
            //go back to start world
            s = new StartWorld();
            Greenfoot.setWorld(s);
        }

        if (lives <= 0) //if all lives have been lost
        {
            //display lose screen
            lost = new LoseWorld(this);
            Greenfoot.setWorld(lost);   //set world to you lose screen
        }

        actCounter++;   //act count goes up
    }

    /**
     * Returns the amount of money the user has.
     * 
     * @return int  Amount of money
     */
    public int getMoney ()
    {
        return money;   //returns money
    }

    /**
     * Returns wave user is on.
     * 
     * @return int  Current wave
     */
    public int getWave ()
    {
        return wave;    //return wave number
    }

    /**
     * Increase enemiesKilled count.
     * 
     * @param deaths    Number of enemies that have died to increase enemiesKilled by
     */
    public void increaseEnemiesKilled (int deaths)
    {
        enemiesKilled += deaths;
    }

    /**
     * Get number of enemies that have been killed.
     * 
     * @return int  Number of enemies that have been killed
     */
    public int getEnemiesKilled ()
    {
        return enemiesKilled;
    }

    /**
     * Saves grid as character array. The character array stores the states of each location in the grid.
     * 
     * @return char[][] Character array that stores the grid's locations' states
     */
    private char[][] saveGridAsChar()
    {
        char[][] gridAsChar = new char[tilesAcross][tilesDown]; //initialize 2D array
        for(int x = 0; x < tilesAcross; x++)
        {
            for(int y = 0; y < tilesDown; y++)
            {
                gridAsChar[x][y] = grid.getState(x, y); //save 2D array as state at each location on grid
            }
        }

        return gridAsChar;  //return 2D char array
    }

    //saves game to text file
    private void saveToTextFile()
    {
        List<Shooter> shooters = getObjects(Shooter.class); //gets all shooters currently in world
        ArrayList<String> xCoords = new ArrayList<String>();    //x coords of shooters
        ArrayList<String> yCoords = new ArrayList<String>();    //y coords of shooters
        ArrayList<String> shooterTypes = new ArrayList<String>();   //which pokemon shooter is

        for(Shooter a : shooters)   //for every shooter
        {
            xCoords.add("" + a.getX()); //save x coord
            yCoords.add("" + a.getY()); //save y coord
            shooterTypes.add("" + a.getWhichPokemon()); //save which pokemon shooter is
        }

        /**
         * The following code is only necessary due to bug that causes shooters to be created twice when added to world. Otherwise, arraylist would not need to be cut in half.
         */
        int originalSize = xCoords.size();  //original size of 2D array
        //cuts arraylists in half
        while(xCoords.size() > originalSize / 2)    
        {
            xCoords.remove(originalSize / 2);
            yCoords.remove(originalSize / 2);
            shooterTypes.remove(originalSize / 2);
        }

        //write game data to text file "game.txt"
        try{
            char[][] states = saveGridAsChar(); //save grid as 2d char array
            writeToFile("game.txt", states);    //writes char array to file
            writeToFile("game.txt", "---", true);   //break to distinguish between grid and next line of text
            writeToFile("game.txt", scene + "", true);  //writes scene to file
            writeToFile("game.txt", wave + "", true);   //writes wave to file
            writeToFile("game.txt", lives + "", true);  //writes lives user has to file
            writeToFile("game.txt", money + "", true);  //writes money to file
            writeToFile("game.txt", xCoords);   //writes all the x coordinates to file
            writeToFile("game.txt", "***", true);   //denotes b/w x coordinates and y coordinates
            writeToFile("game.txt", yCoords);   //writes all y coordinates to file
            writeToFile("game.txt", "***", true);   //separates y coordinates from which pokemon shooter is
            writeToFile("game.txt", shooterTypes);  //writes shooter types to file
            writeToFile("game.txt", "===", true);   //separates shooter types and enemies killed
            writeToFile("game.txt", enemiesKilled + "", true);  //writes enemies killed to file
            writeToFile("game.txt", "===", true);   //denotes no more information
        }
        catch(IOException e)    //catch IOException
        {

        }
    }

    //writes an arraylist of strings to file, appending to file
    private void writeToFile(String path, ArrayList<String> list) throws IOException
    {
        FileWriter write = new FileWriter(path, true);
        PrintWriter print_line = new PrintWriter(write);

        for(String text : list)
        {
            print_line.printf("%s" + "%n", text);   //saves text to file
        }

        print_line.close();     //closes print writer
    }

    //writes 2d char array to file, not appending to file
    private void writeToFile(String path, char[][] letters) throws IOException
    {
        FileWriter write = new FileWriter(path, false);
        PrintWriter print_line = new PrintWriter(write);

        for(int x = 0; x < tilesAcross; x++)
        {
            String line = "";
            for(int y = 0; y < tilesDown; y++)
            {
                line += letters[x][y];  //saves each line of characters as a string
            }
            print_line.printf("%s" + "%n", line);   //writes line of characters to file
        }
        print_line.close(); //close print writer
    }

    //writes string to file
    private static void writeToFile(String path, String textLine, boolean appendToFile) throws IOException
    {
        FileWriter write = new FileWriter(path, appendToFile);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s" + "%n", textLine);   //writes text to file
        print_line.close(); //closes print writer
    }

    /**
     * Checks if all the enemies have disappeared from the screen.
     * 
     * @return boolean  True if all the enemies are gone, otherwise false
     */
    private boolean enemiesGone()
    {
        List<Enemy> enemies = getObjects(Enemy.class);  //get all enemies in world
        if(enemies.size() <= 0)
            return true;    //if no enemies, return true
        return false;
    }

    /**
     * Changes the wave in the world
     */
    private void changeWave()
    {
        //reset variables
        enemySpawned = 0;

        //reset boolean variables for timer
        startTimer = false;
        waitAfter = false;

        wave++; //wave has increased

        if(wave % 10 == 0)
            enemyCount = bossSpawned;   //1 enemy spawned when boss level
        else
            enemyCount = wave % 10; //enemy count is 1s digit of wave

        if(wave > 10 && wave % 10 == 1) //if starting new "level" where grid changes
        {
            List<Shooter> shooters = getObjects(Shooter.class); //deletes all shooters currently in world
            for(Shooter a : shooters)
            {
                removeObject(a);    //remove shooter
            }

            loadGrid(wave); //load new grid

            money += 500;  //give them 1000 more points
            counter.setValue(money);
            waitBefore = false; //user has to wait
        }        

        //updates cursor
        if(cursorExists)    //if cursor exists
            cursor.update();    //update cursor
        else
        {
            addObject(cursor, 0, 0);    //add cursor
            cursor.update();    //update cursor
            removeObject(cursor);   //remove cursor
        }

        //remove current wave number
        removeObject(waveCount);
        //display current wave
        waveCount = new Text(wave, 35, new Color(0, 0, 0), new Color(0,0,0,0));
        addObject(waveCount, 470, 580);

        timer.mark();   //restarts timer
    }   

    //sets grid variables
    private void setGridVariables()
    {
        tileSize = grid.getTileWidth(); //size of tiles
        tilesAcross = grid.getTilesAcross();    //number of tiles across
        tilesDown = grid.getTilesDown();    //number of tiles down
    }

    //loads grid onto world
    private void loadGrid(Grid grid)
    {
        List<Grid> g = getObjects(Grid.class);  //deletes current grid in world
        for(Grid a : g)
        {
            removeObject(a);    //remove grids in world
        }

        List<Location> l = getObjects(Location.class);  //deletes all locations in world
        for(Location a : l)
        {
            removeObject(a);    //remove locations in world
        }

        this.grid = grid;
        //sets grid variables
        setGridVariables();

        addObject(grid, getWidth() / 2, getHeight() / 2);   //add grid to world
        grid.loadLocations();   //load all the locations into the grid
    }

    /**
     * Loads the new grid onto world
     * 
     * @param wave  Wave of the new grid
     */
    private void loadGrid(int wave)
    {
        //         loadGrid(grids[wave]);
        List<Grid> g = getObjects(Grid.class);  //deletes current grid in world
        for(Grid a : g)
        {
            removeObject(a);    //remove grids
        }

        List<Location> l = getObjects(Location.class);  //deletes all locations in world
        for(Location a : l)
        {
            removeObject(a);    //remove locations
        }

        if(wave > 30)
            grid = grids[4];    //set grid as 4th grid design
        else if(wave > 20)
            grid = grids[3];    //set grid as 3rd grid design
        else if(wave > 10)
            grid = grids[2];    //set grid as 2nd grid design
        else if(wave > 0)
            grid = grids[1];    //set grid as 1st grid design

        //sets grid variables
        setGridVariables();
        createWave(wave);   //create the wave on the grid

        addObject(grid, getWidth() / 2, getHeight() / 2);   //add grid to world
        grid.loadLocations();   //load all the locations into the grid
    }

    /**
     * Update the user's lives.
     * 
     * @param decreaseLives Number of lives lost
     * @return int          Return the current number of lives the user has
     */
    public int updateLives (int decreaseLives)
    {
        lives -= decreaseLives; //decrease user's number of lives
        heartBar.update(100, lives, 10);    //update user's life bar
        return lives;
    }

    //enemy can't go through a, b or 1 obstacles
    private void spawnEnemies()
    {
        char enemyType = '0';
        //load enemies based on scene of world and if it is a boss level
        if(scene == 'g')    //if grass world
        {
            if(wave % 10 == 0)
                enemyType = 'R';    //boss rayquaza
            else
                enemyType = 'C';    //regular caterpie
        }
        else if(scene == 'u')   //if underwater
        {
            if (wave % 10 == 0)
                enemyType = 'K';    //boss kyogre
            else
                enemyType = 'T';   //regular tentacool
        }
        else if(scene == 'f')   //if fire
        {
            if (wave % 10 == 0)
                enemyType = 'G';    //boss groundon
            else
                enemyType = 'V';    //regular vulpix
        }

        Enemy e = new Enemy(enemyType, wave);   //create new enemy

        int startX = 0; //starting coordinate
        //finds starting point on grid
        for(int x = 0; x < tilesAcross; x++)
        {
            if(grid.getState(x, 0) == 's')  //find starting coordinate
            {
                startX = x; //set starting coordinate
                break;
            }
        }

        addObject (e, 0, startX * grid.getTileWidth() + grid.getTileWidth() / 2);   //add enemy to world at starting value
        e.addedToWorld(this, wave);   //adds enemy's HP bar
    }

    /**
     * Creates world according to what wave it is in.
     * 
     * @param wave Which wave the world is in
     */
    private void createWave(int wave)
    {
        if(wave == 1)   //if wave 1, create wave 1 path
        {
            for(int y = 0; y < 5; y++)  //first horizontal segment
            {
                grid.setState(6, y, 'p');
            }

            for(int x = 6; x < 9; x++)  //first vertical segment
            {
                grid.setState(x, 5, 'p');
            }

            for(int y = 5; y < 10; y++) //second horizontal segment
            {
                grid.setState(9, y, 'p');
            }

            for(int x = 9; x > 3; x--)  //second vertical segment
            {
                grid.setState(x, 10, 'p');
            }

            for(int y = 10; y < tilesDown; y++)    //last horizontal segment
            {
                grid.setState(3, y, 'p');
            }

            //set obstacles
            addObstacles(20);

            //start and finish coordinates
            grid.setState(6,0,'s');
            grid.setState(3,15,'f');

        }
        else if(wave == 11)  //if wave 2, create wave 2 path
        {
            for(int y = 0; y < 4; y++)  //1st horizontal
            {
                grid.setState(9, y, 'p');
            }
            for(int x = 9; x > 3; x--)  //1st vertical
            {
                grid.setState(x, 4, 'p');
            }
            for(int y = 4; y < 8; y++)  //2nd horizontal
            {
                grid.setState(3, y, 'p');   
            }
            for(int x = 3; x > 1; x--)  //2nd vertical
            {
                grid.setState(x, 8, 'p');
            }
            for(int y = 8; y < 14; y++) //3rd horizontal
            {
                grid.setState(1, y, 'p');
            }
            for(int x = 1; x < 6; x++) //3rd vertical
            {
                grid.setState(x, 14, 'p');
            }
            for(int y = 14; y < tilesDown; y++)    //4th horizontal
            {
                grid.setState(6, y, 'p');
            }

            //add obstacles
            addObstacles(40);
            //start and finish coordinates
            grid.setState(9,0,'s');
            grid.setState(6,15,'f');
        }
        else if(wave == 21)  //if wave 3, create wave 3 path
        {
            for(int y = 0; y < 4; y++)  //1st horizontal
            {
                grid.setState(4, y, 'p');
            }
            for(int x = 4; x < 8; x++)  //1st vertical
            {
                grid.setState(x, 4, 'p');
            }
            for(int y = 4; y < 7; y++)  //2nd horizontal
            {
                grid.setState(8,y,'p');
            }
            for(int x = 8; x > 2; x--)  //2nd vertical
            {
                grid.setState(x,7,'p');
            }
            for(int y = 7; y < 13; y++) //3rd horizontal
            {
                grid.setState(2, y, 'p');
            }
            for(int x = 2; x < 13; x++) //3rd vertical
            {
                grid.setState(x, 13, 'p');
            }
            for(int y = 13; y < 18; y++)    //4th horizontal
            {
                grid.setState(13, y, 'p');
            }
            for(int x = 13; x > 7; x--) //4th vertical
            {
                grid.setState(x, 18, 'p');
            }
            for(int y = 18; y < tilesDown; y++) //5th horizontal
            {
                grid.setState(7, y, 'p');
            }

            addObstacles(80);   //adds 80 obstacles to world
            //sets start and finish
            grid.setState(4,0,'s');
            grid.setState(7, 19, 'f');
        }
        else if(wave == 31)  //if wave 4, create wave 4 path
        {
            for(int y = 0; y < 5; y++)  //1st horizontal 
            {
                grid.setState(10, y, 'p');
            }
            for(int x = 10; x > 2; x--) //1st vertical
            {
                grid.setState(x, 5, 'p');
            }
            for(int y = 5; y < 8; y++)  //2nd horizontal
            {
                grid.setState(2, y, 'p');
            }
            for(int x = 2; x < 13; x++) //2nd vertical
            {
                grid.setState(x, 8, 'p');
            }
            for(int y = 8; y < 15; y++) //3rd horizontal
            {
                grid.setState(13, y, 'p');
            }   
            for(int x = 13; x > 8; x--) //3rd vertical
            {
                grid.setState(x,15,'p');   
            }
            for(int y = 15; y > 11; y--)    //4th horizontal
            {
                grid.setState(8,y,'p');
            }
            for(int x = 8; x > 3; x--)  //4th vertical
            {
                grid.setState(x, 11, 'p');
            }
            for(int y = 11; y < 18; y++)    //5th horizontal
            {
                grid.setState(3, y, 'p');
            }
            for(int x = 3; x < 8; x++)  //5th vertical
            {
                grid.setState(x, 18, 'p');
            }
            for(int y = 18; y < tilesDown; y++) //6th horizontal
            {
                grid.setState(8,y,'p');
            }

            addObstacles(120);  //adds 120 obstacles
            //set start and finish coordinates
            grid.setState(10,0,'s');
            grid.setState(8, 19, 'f');
        }
    }

    /**
     * Adds obstacles to the grid randomly, as well as along the top and bottom.
     * 
     * @param num   Number of obstacles to add
     */
    private void addObstacles(int num)
    {
        for(int i = 0; i < tilesDown; i++)  //adds obstacles across top and bottom; these tiles are "blocked" off
        {
            grid.setState(tilesAcross - 1, i, '1'); //set bottom line as obstacles
            grid.setState(0, i, '1');   //set top row as obstacles
        }
        for(int i = 0; i < num; i++)    //for as many obstacles need to be added
        {
            int xRand;  //random x coordinate
            int yRand;  //random y coordinate
            do {
                xRand = Greenfoot.getRandomNumber(tilesAcross); //get random x coordinate
                yRand = Greenfoot.getRandomNumber(tilesDown);   //get random y coordinate
            } while(grid.getState(xRand,yRand) != 'a' && grid.getState(xRand, yRand) != 'b');    //find new coordinate if current coordinate is not empty
            grid.setState(xRand, yRand, '1');   //set coordinate as obstacle
        }
    }

    /**
     * Returns the cursor.
     * 
     * @return Cursor   The cursor that is in the world
     */
    public Cursor getCursor()
    {
        return cursor;
    }

    /**
     * Called by buttons to know when a new button has been selected.
     * Whatever is selected will be what is created when the cursor is clicked.
     * 
     * @param button    Button that has been selected
     */
    public void setCurrentButtonSelected(Button button)
    {
        if(currentButtonSelected != null) 
        {
            currentButtonSelected.deselected(); //let the previously selected one know it's been deselected
        }
        currentButtonSelected = button; //save the new button
    }

    //when mouse is clicked in world, see if shooter needs to be added where cursor is
    private void mouseClicked()
    {
        //when the mouse gets clicked, see if there's a shooter creation button currently selected and there's nothing in the way of the cursor and user has enough money
        if(currentButtonSelected != null && cursor.checkIfSpaceIsAvailable() && money >= currentButtonSelected.getPrice())
        {
            Shooter s = new Shooter(currentButtonSelected.getWhichPokemon());   //creates new shooter based on which button is selected
            addObject(s, cursor.getX(), cursor.getY()); //adds shooter to world

            addMoney(-currentButtonSelected.getPrice()); //subtract the amount shooter costs

            currentButtonSelected.deselected(); //deselect current button
            currentButtonSelected = null; //no current button has been clicked
        }
    }

    /**
     * Adds money to user's current amount and updates counter.
     * 
     * @param amount    Amount money increases by
     */
    private void addMoney(int amount)
    {
        money += amount;    //increase in money
        counter.setValue(money);    //update counter
    }

    /**
     * Sets money user has and updates counter.
     * 
     * @param money Amount of money the user has
     */
    private void setMoney(int money)
    {
        this.money = money; //amount of money user has
        counter.setValue(money);    //update counter
    }
}
