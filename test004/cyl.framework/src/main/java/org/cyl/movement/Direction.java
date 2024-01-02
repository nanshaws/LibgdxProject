package org.cyl.movement;

import org.cyl.utils.FastMath;

public class Direction {
    // Directions
    public final static float NO_DIRECTION = -1f;
    public final static float EAST = 0.0f;
    public final static float EAST_NORTH_EAST = 22.5f;
    public final static float NORTH_EAST = 45.0f;
    public final static float NORTH_NORTH_EAST = 67.5f;
    public final static float NORTH = 90.0f;
    public final static float NORTH_NORTH_WEST = 112.5f;
    public final static float NORTH_WEST = 135.0f;
    public final static float WEST_NORTH_WEST = 157.5f;
    public final static float WEST = 180.0f;
    public final static float WEST_SOUTH_WEST = 202.5f;
    public final static float SOUTH_WEST = 225.0f;
    public final static float SOUTH_SOUTH_WEST = 247.5f;
    public final static float SOUTH = 270.0f;
    public final static float SOUTH_SOUTH_EAST = 292.5f;
    public final static float SOUTH_EAST = 315.0f;
    public final static float EAST_SOUTH_EAST = 337.5f;
    private final static float FRACTION = 11.25f;

    /**
     * Takes two points and finds the direction from the original to the destination.
     * @param originX
     * @param originY
     * @param destinX
     * @param destinY
     * @return
     */
    public static float getDirection(double originX, double originY, double destinX, double destinY) {
        if (originX == destinX && originY == destinY) {
            return NO_DIRECTION;
        }
        float xAdj = (float) (destinX - originX);
        float yOpp = (float) (destinY - originY);
        float angle = FastMath.atan2DegLookup(yOpp, xAdj);
        if (yOpp < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * Converts any degree into one of the four Cardinal directions
     * @param degree
     * @return degree
     */
    public static float nearest4thDirection(float degree) {
        degree = convertTo360Degrees(degree);
        if (degree >= NORTH_EAST && degree < NORTH_WEST) {
            return NORTH;
        }
        if (degree >= NORTH_WEST && degree < SOUTH_WEST) {
            return WEST;
        }
        if (degree >= SOUTH_WEST && degree < SOUTH_EAST) {
            return SOUTH;
        }
        return EAST;
    }

    /**
     * Converts any degree into one of the eight Cardinal directions
     * @param degree
     * @return degree
     */
    public static float nearest8thDirection(float degree) {
        degree = convertTo360Degrees(degree);
        if (degree >= EAST_NORTH_EAST && degree < NORTH_NORTH_EAST) {
            return NORTH_EAST;
        }
        if (degree >= NORTH_NORTH_EAST && degree < NORTH_NORTH_WEST) {
            return NORTH;
        }
        if (degree >= NORTH_NORTH_WEST && degree < WEST_NORTH_WEST) {
            return NORTH_WEST;
        }
        if (degree >= WEST_NORTH_WEST && degree < WEST_SOUTH_WEST) {
            return WEST;
        }
        if (degree >= WEST_SOUTH_WEST && degree < SOUTH_SOUTH_WEST) {
            return SOUTH_WEST;
        }
        if (degree >= SOUTH_SOUTH_WEST && degree < SOUTH_SOUTH_EAST) {
            return SOUTH;
        }
        if (degree >= SOUTH_SOUTH_EAST && degree < EAST_SOUTH_EAST) {
            return SOUTH_EAST;
        }
        return EAST;
    }

    /**
     * Take a degree and ensures that it is between zero and 360.
     * @param degree
     * @return
     */
    public static float convertTo360Degrees(float degree) {
        while (degree < 0) {
            degree += 360;
        }
        while (degree > 360) {
            degree -= 360;
        }
        return degree;
    }
}