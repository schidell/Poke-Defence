import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Creates the grid using a 2D array to make the basis of the game. The grids are modular and can be
 * used in any future games as they can create a grid of any desired size. Should call the loadLocations() 
 * method to add the locations to the world to create the tiles.
 * 
 * @author Maggie, Catherine
 * @version June 2018
 */
public class Grid extends Actor
{
    //varaibles to store grid dimensions
    private int xDim;
    private int yDim;

    private int width;
    private int length;

    //2d array to store grid
    private Location[][] grid;

    //stores the scene used
    private char scene;

    /**
     * Creates a grid of varying dimensions and tile size using a 2d array.
     * 
     * @param tilesAcross Amount of tiles wanted horizontally. Must be greater than 0.
     * @param tilesDown Amount of tiles wanted vertically. Must be greater than 0.
     * @param width Width of the tile. Must be greater than 0. The tile width multiplied by the amount of tiles across must be equal to or less than the world size.
     * @param length Length of the tile. Must be greater than 0. The tile length multiplied by the amount of tiles down must be greater or equal to the world size.
     * @param scene Character used to differentiate the different scenes. u: underwater, f: fire, g: grass
     */
    public Grid(int tilesAcross, int tilesDown, int width, int length, char scene)
    {
        xDim = tilesAcross;
        yDim = tilesDown;

        this.width = width;
        this.length = length;

        grid = new Location[xDim][yDim];

        this.scene = scene;

        for(int y = 0; y < yDim; y++)
        {
            for(int x = 0; x < xDim; x++)
            {
                if((x % 2 == 0 && y % 2 == 0) || x % 2 == 1 && y % 2 == 1)
                {
                    grid[x][y] = new Location(width, length, 'a', scene);
                }
                else
                {
                    grid[x][y] = new Location(width, length, 'b', scene);
                }
            }
        }

        GreenfootImage empty = null;
        setImage(empty);
    }

    /**
     * Creates a grid of varying dimensions and tile size using a 2d array based off of a 2d character array.
     * 
     * @param array A 2d array of characters used to create the grid.
     * @param width Width of the tile. Must be greater than 0. The tile width multiplied by the amount of tiles across must be equal to or less than the world size.
     * @param length Length of the tile. Must be greater than 0. The tile length multiplied by the amount of tiles down must be greater or equal to the world size.
     * @param scene Character used to differentiate the different scenes. u: underwater, f: fire, g: grass
     */
    public Grid(char[][] array, int width, int length, char scene)
    {
        this(array.length, array[0].length, width, length, scene);

        for(int y = 0; y < yDim; y++)
        {
            for(int x = 0; x < xDim; x++)
            {
                if(array[x][y] == 's' || array[x][y] == 'f')
                {
                    grid[x][y].setState('p');
                    grid[x][y].setState(array[x][y]);
                }
                if(array[x][y] != 'e')
                {
                    grid[x][y].setState(array[x][y]);
                }
            }
        }
    }

    /**
     * Getter method for the amount of tiles across
     * 
     * @return int Amount of tiles across
     */
    public int getTilesAcross()
    {
        return xDim;
    }

    /**
     * Getter method for the amount of tiles adown
     * 
     * @return int Amount of tiles down
     */
    public int getTilesDown()
    {
        return yDim;
    }

    /**
     * Getter method for the width of the tiles
     * 
     * @return int Width of a tile
     */
    public int getTileWidth()
    {
        return width;
    }

    /**
     * Getter method for the length of the tiles
     * 
     * @return int Length of a tile
     */
    public int getTileLength()
    {
        return length;
    }

    /**
     * Getter method for the state of a tile at a specified coordinate
     * 
     * @param xCoord x-coordinate of the location you want the state of
     * @param yCoord y-coordinate of the location you want the state of
     * @return int Character that tells the state of the tile (1 --> obstacle, p --> path, a --> empty1, b --> empty2, s--> start, f --> finish)
     */
    public char getState(int xCoord, int yCoord)
    {
        return grid[xCoord][yCoord].getState(); //get state of location
    }

    /**
     * Loads the locations onto the grid
     */
    public void loadLocations()
    {
        for(int y = 0; y < yDim; y++)
        {
            for(int x = 0; x < xDim; x++)
            {
                getWorld().addObject(grid[x][y], length * y + length / 2, width * x + width / 2);   //add each location to world
            }
        }
    }

    /**
     * Sets the state of a tile at a specified location.
     * 
     * @param xCoord x-coordinate of the location you want the set the state of
     * @param yCoord y-coordinate of the location you want to set the state of
     * @param state The state you want to set to (1 --> obstacle, p --> path, a --> empty1, b --> empty2, s--> start, f --> finish)
     */
    public void setState(int xCoord, int yCoord, char state)
    {
        grid[xCoord][yCoord].setState(state);   //set state of location
    }
}
