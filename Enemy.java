import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This code creates an object for the Enemy class that will be
 * reusuable by the other projects. The enemy will have their own
 * specific health (the amount of damage each object is able to
 * take) and a general speed for all enemies. To add the health bar, addedToWorld() method must be called.
 * 
 * @author Shivani, Kyle, Maggie
 * @version 0.2.0
 */
public class Enemy extends Actor
{
    //varaibles used for the enemy
    int health;
    int myWidth;
    int actCounter;
    boolean removeMe;
    boolean isAtEnd;    

    private HealthBar hpBar;

    //images used for the enemy
    private GreenfootImage up;
    private GreenfootImage down;
    private GreenfootImage right;
    private GreenfootImage left;

    private char whichPokemon;

    private int speed;
    private int level;

    //grid for the enemy
    private Grid theGrid;
    private char direction = 'r';
    private int tileSize;
    private int currentGridX;
    private int currentGridY;
    private int tilesAcross;
    private int tilesDown;

    /**
     * Getter method for the speed of the enemy
     * 
     * @param level Level of the enemy
     * @return Speed of the enemy
     */
    public static int getSpeed(int level)
    {
        if(level < 20)
            return 1;
        else if(level < 30)
            return 2;
        else if(level < 40)
            return 3;
        else if(level == 40)
            return 3;
        return 1;
    }

    /**
     * Creates an enemy pokemon and sets their speed and health depending on their level.
     * 
     * @param whichPokemon Character that corresponds to the enemy pokemon type
     * @param level Level of the pokemon
     */
    public Enemy(char whichPokemon, int level)
    {
        //locationSteps = 0;
        actCounter = 0;
        isAtEnd = false;
        removeMe = false;
        this.level = level;

        saveMyWidth();
        this.whichPokemon = whichPokemon;

        //sets the images for the enemy pokemon
        left = new GreenfootImage("e" + whichPokemon + "_Left0.png");
        right = new GreenfootImage("e" + whichPokemon + "_Right0.png");
        up = new GreenfootImage("e" + whichPokemon + "_Up0.png");
        down = new GreenfootImage("e" + whichPokemon + "_Down0.png");

        //rotates the images of the specified pokemon
        if(whichPokemon == 'G' || whichPokemon == 'K' || whichPokemon == 'R')
        {
            left.rotate(180);
            up.rotate(90);
            down.rotate(270);
        }

        //sets the health and the speed of the pokemon based on their level
        if(level >= 1 && level < 10)
        {
            health = 5;
            speed = 1;
        }
        else if (level == 10)
        {
            health = 50;
            speed = 1;
        }
        else if(level >= 11 && level < 20)
        {
            health = 10;
            speed = 1;
        }
        else if (level == 20)
        {
            health = 100;
            speed = 2;
        }
        else if(level >= 21 && level < 30)
        {
            health = 15;
            speed = 2;
        }
        else if (level == 30)
        {
            health = 150;
            speed = 3;
        }
        else if (level>=31 && level < 40)
        {
            health = 20;
            speed = 3;
        }
        else if (level == 40)
        {
            left.scale(left.getWidth() * 3, left.getHeight() * 3);      //images are different in level 40
            right.scale(right.getWidth() * 3, right.getHeight() * 3);
            up.scale(up.getWidth() * 3, up.getHeight() * 3);
            down.scale(down.getWidth() * 3, down.getHeight() * 3);
            health = 500;
            speed = 3;
        }

        hpBar = new HealthBar (health, this); //creates a health bar

        setImage(right);
    }

    /**
     * Creates a caterpie enemy for level 1
     */
    public Enemy (){
        this('C', 1);
    }

    /**
     * Adds an enemy pokemon and their health bar to world
     * 
     * @param w World to add the enemy in
     */
    public void addedToWorld (World w, int wave)
    {
        w.addObject(hpBar, getX(),getY());
        if(wave >= 40)
            hpBar.setOffset(60);
        hpBar.update(health);
    }

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //gets the grid dimensions
        if(actCounter == 0)
        {
            GameWorld w = (GameWorld)getWorld();
            theGrid = w.getGrid();
            tileSize = theGrid.getTileWidth();
            tilesDown = theGrid.getTilesDown();
            tilesAcross = theGrid.getTilesAcross();
        }
        movePath();
        //updates health
        if (health > 0)
        {
            hpBar.update(health);
        }

        actCounter++;
        if(actCounter >= 1000)
            actCounter = 2;
        //when an enemy dies, update scores and remove the enemy
        if (health <= 0)
        {
            GameWorld w = (GameWorld)getWorld();
            w.increaseEnemiesKilled(1);
            w.getCoin(10);  //earn 10 coins

            getWorld().removeObject(this);
        }
        //when it reaches the end of the world, remove the pokemon and update the lives
        else if (health > 0 && (isAtEnd() || removeMe))
        {
            GameWorld w = (GameWorld)getWorld(); 
            if (level == 10 || level == 20 || level == 30 || level == 40)
            {
                w.updateLives(5);
            }
            else
                w.updateLives(1);
            getWorld().removeObject(this);
        }
    }    

    //the enemy follows the path
    private void movePath()
    {

        move(speed);//moves at specified speed

        char inFront = '0';
        //gets grid dimensions
        currentGridX = getX() / tileSize;
        currentGridY = getY() / tileSize;

        if(currentGridY < tilesAcross - 1 && currentGridX < tilesDown - 1)  //if not at edge
        {
            if(direction == 'r')    //if going right
            {
                inFront = theGrid.getState(currentGridY, currentGridX + 1);
            }
            else if(direction == 'u')   //if going up
            {
                inFront = theGrid.getState(currentGridY - 1, currentGridX);
            }
            else if(direction == 'd')   //if going down
            {
                inFront = theGrid.getState(currentGridY + 1, currentGridX);
            }
            else if(direction == 'l')   //if going left
            {
                inFront = theGrid.getState(currentGridY, currentGridX - 1);
            }

            if(inFront != 'p' && inFront != 'f')    //if tile in front is not a path or finish, change direction
            {
                if(direction == 'r' && getX() >= tileSize * currentGridX + tileSize / 2)
                    changeDirection(direction);
                else if(direction == 'u' && getY() <= tileSize * currentGridY + tileSize / 2)
                    changeDirection(direction);
                else if(direction == 'd' && getY() >= tileSize * currentGridY + tileSize / 2)
                    changeDirection(direction);
                else if(direction == 'l' && getX() <= tileSize * currentGridX + tileSize / 2)
                    changeDirection(direction);
            }
        }

        //if it reaches the end of the world, remove it
        if (this.getX() > getWorld().getWidth())
        {
            removeMe = true;
        }
    }

    //changes the direction the enemy is going
    private void changeDirection(char currentDir)
    {
        if(currentDir == 'r' || currentDir == 'l')   //if current direction is right
        {
            //if should go up
            if(theGrid.getState(currentGridY - 1, currentGridX) == 'p' || theGrid.getState(currentGridY - 1, currentGridX) == 'f')
            {
                setRotation(270);
                direction = 'u';
                setImage(up);
            }
            //if should go down
            else if(theGrid.getState(currentGridY + 1, currentGridX) == 'p' || theGrid.getState(currentGridY + 1, currentGridX) == 'f')
            {
                setRotation(90);
                direction = 'd';
                setImage(down);
            }
        }
        else if(currentDir == 'u' || currentDir == 'd')  //if current direction is up
        {
            //should go right
            if(theGrid.getState(currentGridY, currentGridX + 1) == 'p' || theGrid.getState(currentGridY, currentGridX + 1) == 'f')
            {
                setRotation(0);
                direction = 'r';
                setImage(right);
            }
            //should go left
            else if(theGrid.getState(currentGridY, currentGridX - 1) == 'p' || theGrid.getState(currentGridY, currentGridX - 1) == 'f')
            {
                setRotation(180);
                direction = 'l';
                setImage(left);
            }
        }
    }

    /**
     * When hit by another Fighter, take the value of damage and subtract it from the Fighter's health.
     * If Fighter's health reaches zero, remove the Fighter from the world.
     * 
     * @param healthDecrease    takes in the value of the damage done by the weapon
     * 
     */
    public void takeDamage(int healthDecrease)
    {
        // Takes the damage and subtracts it from the current health
        health -= healthDecrease;   //health is decreased

        if (health <= 0)    //if health is below 0, should remove from world
        {
            removeMe = true;
        }
    }

    /**
     * Gets the velocity of the enemy pokemon.
     * 
     * @return int Velocity of pokemon
     */
    public int[] getVelocity()
    {
        int[] velocity = getDirection();
        velocity[0] = velocity[0] * speed;
        velocity[1] = velocity[1] * speed;
        return velocity;
    }

    /**
     * Initializes value of myWidth.
     */
    public void saveMyWidth()
    {
        myWidth = getImage().getWidth();
    }

    /**
     * Handy method that checks if this object is at the edge
     * of the World
     * 
     * @return boolean  true if at or past the edge of the World, otherwise false
     */
    public boolean isAtEnd()
    {
        if (getX() < -(myWidth / 2) || getX() > getWorld().getWidth() + (myWidth / 2))
            return true;
        else if (getY() < -(myWidth / 2) || getY () > getWorld().getHeight() + (myWidth / 2))
            return true;
        return false;
    }
    
    //gets direction of enemy
    private int[] getDirection()
    {
        int[] direction = new int[2];

        return direction;
    }
}
