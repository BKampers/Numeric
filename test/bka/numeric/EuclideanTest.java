/*
 * © Bart Kampers
 */
package bka.numeric;

import org.junit.*;
import static org.junit.Assert.*;


public class EuclideanTest {
    
    
    @Test
    public void distanceTestInt() {
        int[] c1 = new int[] { 0, 0 };
        int[] c2 = new int[] { 1, 1 };
        assertEquals(0, Euclidean.squareDistance(c1, c1));
        assertEquals(0.0, Euclidean.distance(c1, c1), 0.0);
        assertEquals(2, Euclidean.squareDistance(c1, c2));
        assertEquals(Math.sqrt(2), Euclidean.distance(c1, c2), 0.0);
        c2 = new int[] { 2, -2 };
        assertEquals(8, Euclidean.squareDistance(c1, c2));
        c1 = new int[] { 0, 0, 0 };
        c2 = new int[] { 1, 1, 1 };
        assertEquals(3, Euclidean.squareDistance(c1, c2));
    }


    @Test
    public void distanceTestDouble() {
        double[] c1 = new double[] { 0.0, 0.0 };
        double[] c2 = new double[] { 0.5, 0.5 };
        assertEquals(0.0, Euclidean.squareDistance(c1, c1), 0.0);
        assertEquals(0.0, Euclidean.distance(c1, c1), 0.0);
        assertEquals(0.5, Euclidean.squareDistance(c1, c2), 0.0);
        assertEquals(Math.sqrt(0.5), Euclidean.distance(c1, c2), 0.0);
        c2 = new double[] { -0.25, 0.25 };
        assertEquals(0.125, Euclidean.squareDistance(c1, c2), 0.0);
        c1 = new double[] { 0.0, 0.0, 0.0 };
        c2 = new double[] { 1, 1, 1 };
        assertEquals(3.0, Euclidean.squareDistance(c1, c2), 0.0);
    }

}
