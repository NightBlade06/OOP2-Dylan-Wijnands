import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    public int EmptySpace;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }
    
    public void dolhofSolver() {
        while(onNest() == false) {
            move();
            if (getDirection() == EAST) {
                turnRight();
                if (fenceAhead() == false) {
                    move();
                }
                else if (fenceAhead() == true && getDirection() == SOUTH) {
                    turn180();
                }
            }
                        if (getDirection() == SOUTH) {
                turnRight();
                if (fenceAhead() == false) {
                    move();
                }
                else if (fenceAhead() == true && getDirection() == WEST) {
                    turn180();
                }
            }
                        if (getDirection() == WEST) {
                turnRight();
                if (fenceAhead() == false) {
                    move();
                }
                else if (fenceAhead() == true && getDirection() == NORTH) {
                    turn180();
                }
            }
                        if (getDirection() == NORTH) {
                turnRight();
                if (fenceAhead() == false) {
                    move();
                }
                else if (fenceAhead() == true && getDirection() == EAST) {
                    turn180();
                }
            }
        }
    }
    
        public boolean GrainAhead() {
        move();
        if ( onGrain() == true ){
            stepOneCellBackwards();
            return true;
        } else {
            stepOneCellBackwards();
            return false;
        }
    }
    
    public void climbOverFence() {
        if (fenceAhead() == true && getDirection() == EAST){
            turnLeft();
            move();
            turnRight();
            move();
            move();
            turnRight();
            move();
            turnLeft();
        } else if (fenceAhead() == false && getDirection() == EAST) {
            turn180();
            turnRight();
            move();
            turnLeft();
            move();
            move();
            turnLeft();
            move();
            turnRight();
            turn180();
        }
        else {
            System.out.println("There is no fence nearby to climb");
        }
        
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            System.out.println("Moved");
            nrStepsTaken++;                 // increment the counter
        }
    }

    
    
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        while( ! borderAhead() ){
            move();
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
        }else{
            return true;
      }
    } 
    
    public void turn180() {
        turnRight();
        turnRight();
    }
    public void stepOneCellBackwards() {
        turn180();
        move();
        turn180();
    }
}
