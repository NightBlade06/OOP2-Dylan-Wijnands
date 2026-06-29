import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MyDodo extends Dodo
{
    public int stepsTaken = 0;
    public int myScore = 0;

    public MyDodo() {
        super( EAST );
        int myNrOfStepsTaken = 0;
    }

    public void move() {
        if ( canMove() ) {
            step();
            stepsTaken++;
            System.out.println(stepsTaken);
        } else {
            showError( "I'm stuck!" );
        }
    }

    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    public void MimiGoGoGo() {
        GoldenEgg goldenEgg = findGoldenEgg();
        moveToLocation(goldenEgg.getX(), goldenEgg.getY());
        addScore(goldenEgg.getValue());
        getWorld().removeObject(goldenEgg);

        List<BlueEgg> eggList = getListOfBlueEggs();

        while (stepsTaken < 40) {
            BlueEgg nearest = findNearestEgg(eggList);
            if (nearest == null) break;
            moveToLocation(nearest.getX(), nearest.getY());
            addScore(nearest.getValue());
            removeCollectedEgg(eggList, nearest);
            getWorld().removeObject(nearest);
        }

        System.out.println("Final score: " + myScore);
        Greenfoot.stop();
    }

    public void moveToLocation(int inputX, int inputY) {
        int dodoX = getX();
        int dodoY = getY();
        int moveX = dodoX - inputX;
        int moveY = dodoY - inputY;
        if (moveX > 0) {
            int i = 0;
            setDirection(WEST);
            while (i < moveX && stepsTaken < 40) {
                move();
                i = i + 1;
            }
            setDirection(EAST);
        }
        else if (moveX < 0) {
            int i = 0;
            setDirection(EAST);
            while (i > moveX && stepsTaken < 40) {
                move();
                i = i - 1;
            }
            setDirection(EAST);
        }
        if (moveY > 0) {
            int i = 0;
            setDirection(NORTH);
            while (i < moveY && stepsTaken < 40) {
                move();
                i = i + 1;
            }
            setDirection(EAST);
        }
        else if (moveY < 0) {
            int i = 0;
            setDirection(SOUTH);
            while (i > moveY && stepsTaken < 40) {
                move();
                i = i - 1;
            }
            setDirection(EAST);
        }

    }

    public GoldenEgg findGoldenEgg() {
        List<GoldenEgg> goldenEggs = getWorld().getObjects(GoldenEgg.class);
        return goldenEggs.get(0);
    }

    public List<BlueEgg> getListOfBlueEggs() {
        return getWorld().getObjects(BlueEgg.class);
    }

    public void removeCollectedEgg(List<BlueEgg> eggList, BlueEgg egg) {
        eggList.remove(egg);
    }

    public void addScore(int value) {
        myScore = myScore + value;
    }

    public BlueEgg findNearestEgg(List<BlueEgg> eggList) {
        BlueEgg nearestEgg = null;
        int shortestDistance = Integer.MAX_VALUE;

        int myX = getX();
        int myY = getY();

        for (BlueEgg egg : eggList) {
            int distanceX = Math.abs(egg.getX() - myX);
            int distanceY = Math.abs(egg.getY() - myY);
            int totalDistance = distanceX + distanceY;

            if (totalDistance < shortestDistance) {
                shortestDistance = totalDistance;
                nearestEgg = egg;
            }
        }

        return nearestEgg;
    }
}
