package com.fdimensiontests.sync;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.game.bsn.SyncAsteroid;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.Asteroid;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.SpaceGame;
import com.fdimensions.model.SpaceGameMap;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/25/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class SyncAsteroidTest {

//    static {
//        BasicConfigurator.configure();
//    }

    private SyncAsteroid syncAsteroid;

    @Mock
    private SpaceGame sg;

    @Mock
    private PlayerInfo pi;

    @Mock
    DimensionServerExtension dse;

    @Mock
    SpaceGameMap spm;

    List<SyncAsteroidTestInfo> testInfos;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testInfos = new ArrayList<>();

        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 0.0, new Vector2(200, 200), -.5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI/8, new Vector2(235, 157), -.5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI/4, new Vector2(261, 108), -.5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI/2, new Vector2(282, 0), -.5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI, new Vector2(200, -200), -.5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 3*Math.PI/2, new Vector2(0, -282), -.5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 2*Math.PI, new Vector2(-200, -200), -.5f));

        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 0.0, new Vector2(200, 200), -1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI/8, new Vector2(261, 108), -1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI/4, new Vector2(282, 0), -1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI/2, new Vector2(200, -200), -1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI, new Vector2(-200, -200), -1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 3*Math.PI/2, new Vector2(-200, 200), -1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 2*Math.PI, new Vector2(200, 200), -1f));

        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 0.0, new Vector2(200, 200), .5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,282), Math.PI/8, new Vector2(-55, 276), .5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,282), Math.PI/4, new Vector2(-107, 260), .5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI/2, new Vector2(0, 282), .5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), Math.PI, new Vector2(-200, 200), .5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 3*Math.PI/2, new Vector2(-282, 0), .5f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,200), 2*Math.PI, new Vector2(-200, -200), .5f));

        testInfos.add(new SyncAsteroidTestInfo(new Vector2(-200,0), 0.0, new Vector2(-200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(-200,0), Math.PI/4, new Vector2(-141, -141), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(-200,0), Math.PI/2, new Vector2(0, -200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(-200,0), Math.PI, new Vector2(200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(-200,0), 3*Math.PI/2, new Vector2(0, 200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(-200,0), 2*Math.PI, new Vector2(-200, 0), 1f));

        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,-200), 0.0, new Vector2(0, -200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,-200), Math.PI/4, new Vector2(141, -141), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,-200), Math.PI/2, new Vector2(200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,-200), Math.PI, new Vector2(0, 200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,-200), 3*Math.PI/2, new Vector2(-200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,-200), 2*Math.PI, new Vector2(0, -200), 1f));

        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,200), 0.0, new Vector2(0, 200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,200), Math.PI/4, new Vector2(-141, 141), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,200), Math.PI/2, new Vector2(-200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,200), Math.PI, new Vector2(0, -200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,200), 3*Math.PI/2, new Vector2(200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(0,200), 2*Math.PI, new Vector2(0, 200), 1f));

        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,0), 0.0, new Vector2(200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,0), Math.PI/4, new Vector2(141, 141), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,0), Math.PI/2, new Vector2(0, 200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,0), Math.PI, new Vector2(-200, 0), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,0), 3*Math.PI/2, new Vector2(0, -200), 1f));
        testInfos.add(new SyncAsteroidTestInfo(new Vector2(200,0), 2*Math.PI, new Vector2(200, 0), 1f));

    }

    @Test
    public void testRun() throws Exception {
        ConcurrentHashMap<Integer, SpaceGame> systems = new ConcurrentHashMap<>();
        systems.put(1, sg);
        when(dse.getSystems()).thenReturn(systems);
        ConcurrentHashMap<Integer, PlayerInfo> playerInfos = new ConcurrentHashMap<>();
        playerInfos.put(1, pi);
        when(sg.getPlayers()).thenReturn(playerInfos);
        when(sg.getSpaceGameMap()).thenReturn(spm);
        when(pi.isReady()).thenReturn(true);

        syncAsteroid = new SyncAsteroid(dse);

        Asteroid a;
        for (SyncAsteroidTestInfo sati : testInfos) {
            a = new Asteroid(1, 10, sati.getStartPos(), sati.getVelMag(), 1, "cco");
            List<Asteroid> asteroids = new ArrayList<>();
            asteroids.add(a);
            when(spm.getAsteroids()).thenReturn(asteroids);
            syncAsteroid.run(sati.getTestTimePassed());
            Assert.assertTrue(sati.getExpectedEndPos().x == (int)(a.getCurPos().x));
            Assert.assertTrue(sati.getExpectedEndPos().y == (int)(a.getCurPos().y));
        }
    }
}
