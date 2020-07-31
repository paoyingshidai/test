package com.michael.test.junit5.extention;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Michael
 */
@ExtendWith(RandomParametersExtension.class)
public class RandomParametersExtensionTest {

    @Test
    @RepeatedTest(10)
    public void testRandom(@RandomParametersExtension.Random int random){
        System.out.println(random);
    }

}
