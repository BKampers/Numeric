/*
** © Bart Kampers
*/

package bka.numeric;


public final class Euclidean {
    
    
    public static int squareDistance(java.awt.Point c1, java.awt.Point c2) {
        int deltaX = c1.x - c2.x;
        int deltaY = c1.y - c2.y;
        return deltaX * deltaX + deltaY * deltaY;
    }
    
    
    public static double distance(java.awt.Point c1, java.awt.Point c2) {
        return Math.sqrt(squareDistance(c1, c2));
    }
    

    public static int squareDistance(int[] c1, int[] c2) {
        requireEqualLengths(c1.length, c2.length);
        int squareDistance = 0;
        for (int i = 0; i < c1.length; ++i) {
            int delta = c1[i] - c2[i];
            squareDistance += delta * delta;
        }
        return squareDistance;
    }
    
    
    public static double distance(int[] c1, int[] c2) {
        return Math.sqrt(squareDistance(c1, c2));
    }
    

    public static double squareDistance(double[] c1, double[] c2) {
        requireEqualLengths(c1.length, c2.length);
        double squareDistance = 0.0;
        for (int i = 0; i < c1.length; ++i) {
            double delta = c1[i] - c2[i];
            squareDistance += delta * delta;
        }
        return squareDistance;
    }
    
    
    public static double distance(double[] c1, double[] c2) {
        return Math.sqrt(squareDistance(c1, c2));
    }
    
    
    private static void requireEqualLengths(int l1, int l2) {
        if (l1 != l2) {
            throw new IllegalArgumentException("Incompatible coordinates of " + l1 + " and " + l2 + " dimensions.");
        }
    }
    
    
    private Euclidean() {
    }
    

}
