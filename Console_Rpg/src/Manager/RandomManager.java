package Manager;

import java.util.Random;

public class RandomManager {

    static Random generator = new Random();

    // includes both lower and upper
    public static int Random(int lower , int upper){
        return generator.nextInt(upper + 1 - lower) + lower;
    }

    public static double nextGaussian(){
        return generator.nextGaussian();
    }
}

