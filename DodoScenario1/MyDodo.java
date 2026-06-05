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

    public Boolean isFenceRight() {
        turnRight();
        if (fenceAhead()){
            turnLeft();
            return true;
        } 
        else {
            turnLeft();
            return false;
        }

    }

    public void doolhofSolver() {
        while(!onNest()) {
            if (isFenceRight() == false) {
                turnRight();
                move();
            }
            if (!fenceAhead() || nestAhead()){
                move();
            } 
            else {
                turnLeft();
            }
        }
    }

    public void moveToLocation(int inputX, int inputY) {
        int dodoX = getX();
        int dodoY = getY();
        int moveX = dodoX - inputX;
        int moveY = dodoY - inputY;
        if (moveX > 0) {
            int i=0;
            setDirection(WEST);
            while (i < moveX) {
                move();
                i = i + 1;
            }
        }
        else if (moveX < 0) {
            int i=0;
            setDirection(EAST);
            while (i > moveX) {
                move();
                i = i -1;
            }
            setDirection(EAST);
        }
        if (moveY > 0) {
            int i=0;
            setDirection(NORTH);
            while (i < moveY) {
                move();
                i = i + 1;
            }
            setDirection(EAST);
        }
        else if (moveX < 0) {
            int i=0;
            setDirection(SOUTH);
            while (i > moveY) {
                move();
                i = i -1;
            }
            setDirection(EAST);
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
        } else if (fenceAhead() == true && getDirection() == WEST) {
            turnRight();
            move();
            turnLeft();
            move();
            move();
            turnLeft();
            move();
            turnRight();
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

    public void walkToWorldEdge(){
        while( ! borderAhead() ){
            move();
        }
    }

    public void goToEgg() {
        while( !onEgg() ){
            move();
        }
    }

    public void goBackToStartOfRowAndFaceBack() {
        walkToWorldEdge();
        turn180();
        walkToWorldEdge();
        turn180();
    }

    public void walkToWorldEdgeClimbingOverFences() {
        while( ! borderAhead() ){
            move();
            if (fenceAhead() == true) {
                climbOverFence();
            }
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

    public void pickUpGrainsAndPrintCoordinates() {
        while(!borderAhead()) {
            if(onGrain()) {
                pickUpGrain();
                System.out.println("X = " + getX() + ". Y = " + getY() + ", ");
                move();
            } else{move();}
        }
        if(onGrain()) {
            pickUpGrain();
            System.out.println("X = " + getX() + ". Y = " + getY() + ", ");}
    }

    public void layEggWhileWalkingToWolrdBorder() {
        if(onNest() && canLayEgg()) {
            layEgg();
        }
        while(!borderAhead()) {
            move();
            if(onNest() && canLayEgg()) {
                layEgg();
            }
        }

    }

    public void AroundFenceAndLayEgg() {
        while(!onEgg()) {
            if(!fenceAhead()) {
                turnRight();}
            if(fenceAhead() == true){
                turnLeft();
                move();
            } else {move();}
        }
    }

    public void eggTrailToNest(){
        while(!onNest()) {
            if(eggAhead() == true) {
                move();
                pickUpEgg();
            } else{turnRight();}
            if(nestAhead() == true) {
                move();
            } 
        }
    }

    public void faceEast() {
        while(getDirection() != EAST) {
            turnLeft();
        }
    }
}
