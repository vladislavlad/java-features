package software.darkmatter.java17;

import java.util.random.RandomGenerator;

public class Java17 {

    public void randomGeneratorInterface() {
        var randomGenerator = RandomGenerator.getDefault();
        System.out.println("Random Int: " + randomGenerator.nextInt());
    }
}
