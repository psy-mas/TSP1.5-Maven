package edu.scut.emos.tsp.tools;

import edu.scut.emos.tsp.model.Position;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DistanceTimeMatrixTest {
    @Test
    public void computeDistanceTimeTable() throws Exception {
        DistanceTimeMatrix.computeDistanceTimeTable(new Position[]{DataGenerate.generateStochasticPosition(),DataGenerate.generateStochasticPosition()});
    }

}