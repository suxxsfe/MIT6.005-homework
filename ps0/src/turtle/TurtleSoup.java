/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;
import java.math.*;

public class TurtleSoup {

    private static final double RIGHT_ANGLE_DEGREE = 90;
    private static final double ROUND_ANGLE_DEGREE = 360;
    private static final double STRAIGHT_ANGLE_DEGREE = 180;
    
    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        for(int i=0; i < 4; i++){
            turtle.forward(sideLength);
            turtle.turn(RIGHT_ANGLE_DEGREE);
        }
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        double angle=(sides-2)*STRAIGHT_ANGLE_DEGREE;
        return angle/sides;
        
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        return (int)Math.round(2*STRAIGHT_ANGLE_DEGREE/(STRAIGHT_ANGLE_DEGREE-angle));
        
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle=TurtleSoup.calculateRegularPolygonAngle(sides);
        for(int i=0; i < sides; i++){
            turtle.forward(sideLength);
            turtle.turn(STRAIGHT_ANGLE_DEGREE-angle);
        }
        
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double turnDegree;
        if(currentX == targetX){
            turnDegree=0;
        }
        else{
            double atan=Math.toDegrees(Math.atan((double)(targetY-currentY)/(targetX-currentX)));
            turnDegree=RIGHT_ANGLE_DEGREE-atan;
            if(targetX < currentX){
                turnDegree+=STRAIGHT_ANGLE_DEGREE;
            }
        }
        turnDegree-=currentHeading;
        
        if(turnDegree < 0){
            turnDegree+=ROUND_ANGLE_DEGREE;
        }
        if(turnDegree > ROUND_ANGLE_DEGREE){
            turnDegree-=ROUND_ANGLE_DEGREE;
        }
        return turnDegree;
        
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        double currentHeading=0;
        List<Double> headings=new ArrayList<Double>();
        
        for(int i=0; i < xCoords.size()-1; i++){
            double turnDegree=TurtleSoup.calculateHeadingToPoint(currentHeading, xCoords.get(i), yCoords.get(i),
                                                            xCoords.get(i+1), yCoords.get(i+1));
            headings.add(turnDegree);
            currentHeading+=turnDegree;
        }
        
        return headings;
        
//        throw new RuntimeException("implement me!");
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

//        drawSquare(turtle, 40);
        drawRegularPolygon(turtle, 10, 50);

        // draw the window
        turtle.draw();
    }

}
