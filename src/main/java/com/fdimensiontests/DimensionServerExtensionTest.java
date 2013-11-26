package com.fdimensiontests;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.model.SpaceGame;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/20/13
 * Time: 9:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class DimensionServerExtensionTest {

    private DimensionServerExtension dse;

    @Mock
    private SpaceGame sg;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dse = new DimensionServerExtension();
    }

    @Test
    public void testGenerateSystems() throws Exception {

    }

    @Test
    public void testCreateSolSystemMap() throws Exception {

    }

    @Test
    public void testCreateTestNpcs() throws Exception {

    }

    @Test
    public void testGetSystems() throws Exception {

    }

    @Test
    public void testUpdatePlayersForPlanets() throws Exception {

    }

    @Test
    public void testUpdatePlayerForNPC() throws Exception {

    }
}
