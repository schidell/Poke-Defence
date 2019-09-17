import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Creates a shooter that shoots projectiles at a specified frequency depending on the pokemon type.
 * They find the object closest to them to shoot and rotate to face that object.
 * 
 * @author Amber, Maggie
 * @version June 2018
 */
public class Shooter extends Actor
{

    private int shooterFrequency = 125;  //initial alien weapon frequency

    int timer = 0;

    //variables for the images
    GreenfootImage right;
    GreenfootImage left;
    GreenfootImage up;
    GreenfootImage down;
    char whichPokemon;  //differentiates between pokemon

    /**
     * Constructor for the Shooters. Sets all the images depending on the type of pokemon.
     * 
     * @param whichPokemon Character used to differentiate between pokemon. b:bulbasaur i:ivysaur v:venusaur s:squirtle w:wartortle B:blastoise r:charmander n:charmelion d:charizard
     */

    public Shooter(char whichPokemon)
    {
        setRotation(180);//flips the image

        this.whichPokemon = whichPokemon;
        //sets the corresponding images to the directions the pokemon are facing
        right = new GreenfootImage("s" + whichPokemon + "3.png");
        left = new GreenfootImage("s" + whichPokemon + "2.png");
        left.rotate(180);
        up = new GreenfootImage("s" + whichPokemon + "0.png");
        up.rotate(90);
        down = new GreenfootImage("s" + whichPokemon + "1.png");
        down.rotate(270);

        setImage(right);

        //sets the shooting frequency based on the pokemon
        if(whichPokemon == 'b' || whichPokemon == 'r' || whichPokemon == 's')
        {
            shooterFrequency = 125;
        }
        else if(whichPokemon == 'i' || whichPokemon == 'n' || whichPokemon == 'w')
        {
            shooterFrequency = 70;
        }
        else if(whichPokemon == 'v' || whichPokemon == 'd' || whichPokemon == 'l')
        {
            shooterFrequency = 40;
        }

        //         shooterTotal++;
    }

    /**
     * Decreases the shoot timer and shoots when the timer reaches 0, then resets.
     */
    public void healthActorAct() 
    {
        if (timer == 0){ //shoots if timer is 0
            shoot(); //shoot projectiles
            timer = shooterFrequency; //resets timer after shot
        } else { //timer counts down if it isn't 0
            timer --;
        }
    }   

    /**
     * Shoots at the closest enemy in the direction of the enemy.
     */
    public void shoot()
    {
        //get the closest enemy
        Enemy closestEnemy = getClosestEnemy(500);
        if(closestEnemy!=null)
        {
            int direction = aimAtEnemy(closestEnemy);
            turnTowards(closestEnemy.getX(), closestEnemy.getY());
            getWorld().addObject(new Projectile(direction), getX(),getY()); //add projectile
        }
    }

    /**
     * Getter method that returns the pokemon.
     * 
     * @return char Character of the pokemon shooter. b:bulbasaur i:ivysaur v:venusaur s:squirtle w:wartortle B:blastoise r:charmander n:charmelion d:charizard
     */
    public char getWhichPokemon()
    {
        return whichPokemon;
    }

    /**
     * Run every time act or run is pressed. Will rotate shooter to the nearest enemy so they shoot, and adjusts the image accordingly.
     */
    public void act()
    {
        healthActorAct();
        int rotation = getRotation();
        //changes the image depending on the rotation
        if(rotation > 45 && rotation <= 135)
        {
            setImage(down);
        }
        else if(rotation > 135 && rotation <= 225)
        {
            setImage(left);
        }
        else if(rotation > 225 && rotation <= 315)
        {
            setImage(up);
        }
        else if(rotation > 315 || rotation < 45)
        {
            setImage(right);
        }

    }

    /**
     * Finds the closest enemy in the world.
     * 
     * @param range Range to search for the closest enemy
     * @return Enemy The closest enemy to the shooter
     */
    private Enemy getClosestEnemy(int range)
    {
        //gets the closest enemy
        List<Enemy> enemies = getWorld().getObjects(Enemy.class); //get all the enemies

        Enemy closestEnemy = null; //the closest enemy at the moment is null
        double closestDistance = range; //and the closest distance at the moment is whatever range they passed us.

        for(int i=0; i<enemies.size(); i++)
        {
            Enemy enemy = enemies.get(i); //loop through all the enemies
            double distance = Math.sqrt((double)(Math.pow(enemy.getX()-getX(),2)+Math.pow(enemy.getY()-getY(),2)));
            //distance formula -- check if their distance is lower that the closest distance so far
            if(distance < closestDistance)
            {
                closestEnemy = enemy; //if it is, this is the closest enemy so far
                closestDistance = distance; //store the distance so that it can be compared against later.
            }
        }

        return closestEnemy; //return the closest enemy. if there were none, we'll get null.
    }

    /**
     * Calculates the direction to shoot at to hit the closest enemy.
     * 
     * @param enemy Enemy to shoot at
     * @return int Direction from 0 to 359 to shoot at to hit the enemy
     */
    private int aimAtEnemy(Enemy enemy)
    {
        //returns a direction -- the direction to shoot to hit the closest enemy as it moves.

        int[] enemyVelocity = enemy.getVelocity();

        int x=enemy.getX()-getX();
        int y=enemy.getY()-getY(); //get their velocity, distance, etc
        int h=enemyVelocity[0];
        int v=enemyVelocity[1];
        int s=4;

        double bulletHSpeed=(x*Math.sqrt(Math.abs(Math.pow(s,2)*(Math.pow(x,2)+Math.pow(y,2))-Math.pow(v,2)*Math.pow(x,2)+2*h*v*x*y-Math.pow(h,2)*Math.pow(y,2)))+y*(h*y-v*x))/(Math.pow(x,2)+Math.pow(y,2));
        double bulletVSpeed=(y*Math.sqrt(Math.abs(Math.pow(s,2)*(Math.pow(x,2)+Math.pow(y,2))-Math.pow(v,2)*Math.pow(x,2)+2*h*v*x*y-Math.pow(h,2)*Math.pow(y,2)))+x*(v*x-h*y))/(Math.pow(x,2)+Math.pow(y,2));
        int direction=(int) Math.toDegrees(Math.atan2(bulletVSpeed,bulletHSpeed));
        //a formula i came up with for finding the direction to hit the moving target. maybe it could be simplified, i dunno.

        return direction;
    }
}
