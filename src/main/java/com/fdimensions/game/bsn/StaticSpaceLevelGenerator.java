package com.fdimensions.game.bsn;

import com.fdimensions.math.Vector2;
import com.fdimensions.model.AsteroidArea;
import com.fdimensions.model.SpaceGameMap;
import com.fdimensions.model.StationaryBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class StaticSpaceLevelGenerator {

    private static final int MAX_SYSTEM_RADIUS = 10000;
    private static final int MIN_SYSTEM_RADIUS = 1000;
    private static final int BELT_AREA_SQ_UNITS = 1000;
    private static final int MIN_ASTEROIDS_PER_BELTAREA = 10;
    private static final int MAX_ASTEROIDS_PER_BELTAREA = 100;
    private static final int MAX_ASTEROID_WIDTH = 200;
    private static final int MIN_ASTEROID_WIDTH = 100;
    private static final int PLANETS_PER_MIN_SYS_RAD = 1;
    private static final int MAX_STARS = 3;
    private static final int MAX_PLANET_RADIUS = 30;
    private static final int MIN_PLANET_RADIUS = 10;
    private static final int MAX_STAR_RADIUS = 50;
    private static final int MIN_STAR_RADIUS = 20;

    public static SpaceGameMap generateRandomLevel() {
        Random r = new Random();
        int systemRadius = r.nextInt(MAX_SYSTEM_RADIUS-MIN_SYSTEM_RADIUS) + MIN_SYSTEM_RADIUS;
        float minRadii = systemRadius/MIN_SYSTEM_RADIUS;
        int beltCount = r.nextInt((int)minRadii)+1;
        int starCount = r.nextInt(MAX_STARS) + 1;
        int planetCount = r.nextInt((int)(PLANETS_PER_MIN_SYS_RAD * minRadii))+1;
        List<AsteroidArea> asteroidAreas = new ArrayList<AsteroidArea>();
        for (int i = 0; i < beltCount; i++) {
            int innerRingRadius = 300;//(i+1)*MIN_SYSTEM_RADIUS;
            int outerRingRadius = 300;//innerRingRadius + r.nextInt(MAX_ASTEROID_WIDTH-MIN_ASTEROID_WIDTH)+MIN_ASTEROID_WIDTH;

            //TODO ****asteroid per minRadii calculation*****
            int asteroidCount = 100*(i+1);
            //***********************************************

            AsteroidArea ab = new AsteroidArea(new Vector2(innerRingRadius, -outerRingRadius), new Vector2(outerRingRadius, outerRingRadius), asteroidCount);
            asteroidAreas.add(ab);
        }
        List<StationaryBody> stars = new ArrayList<StationaryBody>();
        //TODO figure out distance from system center and distance from each other algorithm, for now only one star in the middle
        stars.add(new StationaryBody(new Vector2(0,0), 1, r.nextInt(MAX_STAR_RADIUS-MIN_STAR_RADIUS)+MIN_STAR_RADIUS));

        List<StationaryBody> planets = new ArrayList<StationaryBody>();

        return new SpaceGameMap(stars, asteroidAreas, systemRadius);
    }
}
