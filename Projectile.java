import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A projectile is fired regularly by a shooter to hit enemies and kill them. If it hits an enemy, the enemy will take damage.
 * 
 * @author Amber Chen
 * @version June 2018
 */
public class Projectile extends Actor
{
    private int myWidth;        //saves width of the weapon image
    private boolean removeMe = false;   //state to indicate whether or not weapon needs to be present in the world

    /**
     * The projectile moves. If it hits an enemy, the enemy will take damage.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(7); //moves projectile

        Enemy e = (Enemy)getOneIntersectingObject(Enemy.class);    //get enemy touching weapon
        if(e != null)   //if intersecting fighter exists, is on oppoosite team, and not on heal planet
        {
            e.takeDamage(2); //one hit results in a decrease in health by 1
            removeMe = true; //weapon disappears once it hits a Fighter
        }

        if (removeMe || atWorldEdge())   //if object at edge or should not exist, remove from world
        {
            getWorld().removeObject(this);
        }
    }   

    /**
     * Creates a projectile with a specified direction.
     * 
     * @param direction Rotation of the projectile; specifies which way projectile will move
     */
    public Projectile (int direction)
    {
        setRotation(direction); //constructor. used to set which direction the projectile is firing
    }

    /**
     * Determines if the Weapon is at the edge of the world. If so,
     * remove the weapon.
     */
    public boolean atWorldEdge()
    {
        if (getX() < - (myWidth / 2) || getX() > getWorld().getWidth() + (myWidth / 2))
            return true;
        else if (getY() < - (myWidth / 2) || getY() > getWorld().getHeight() + (myWidth / 2))
            return true;
        else
            return false;
    }
}
