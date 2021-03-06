import Logic.FrameCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameCalculatorTest {

    @Test
    void getAllFrames() {

        //Null check
        assertEquals(FrameCalculator.getAllFrames(null).length, 0);

        //Empty check
        assertEquals(FrameCalculator.getAllFrames(new int[0]).length, 0);

        //Base size checks
        assertEquals(FrameCalculator.getAllFrames(new int[]{1}).length, 0);
        assertEquals(FrameCalculator.getAllFrames(new int[]{1, 2}).length, 1);
        assertEquals(FrameCalculator.getAllFrames(new int[]{1, 2, 3}).length, 1);
        assertEquals(FrameCalculator.getAllFrames(new int[]{1, 2, 3, 4}).length, 2);
        assertEquals(FrameCalculator.getAllFrames(new int[]{1, 2, 3, 4, 5}).length, 2);

        //Complex size checks
        assertEquals(FrameCalculator.getAllFrames(new int[]{10}).length, 0);
        assertEquals(FrameCalculator.getAllFrames(new int[]{1, 9}).length, 0);
        assertEquals(FrameCalculator.getAllFrames(new int[]{1, 2, 10}).length, 1);

        //Valid check 1
        int[] input1 = new int[]{1,2};
        int[] expectedOutput1 = new int[]{3};
        assertArrayEquals(expectedOutput1, FrameCalculator.getAllFrames(input1));

        //Valid check 2
        int[] input2 = new int[]{1,2,3,4};
        int[] expectedOutput2 = new int[]{3,10};
        assertArrayEquals(expectedOutput2, FrameCalculator.getAllFrames(input2));

        //Valid check 3
        int[] input3 = new int[]{1,4,4,5,6,4,5,5,10,0,1,7,3,6,4,10,2,8,6};
        int[] expectedOutput3 = new int[]{5,14,29,49,60,61,77,97,117,133};
        assertArrayEquals(expectedOutput3, FrameCalculator.getAllFrames(input3));

        //Valid check 4
        int[] input4 = new int[]{10,10,10,10,10,10,10,10,10,10,10,10};
        int[] expectedOutput4 = new int[]{30,60,90,120,150,180,210,240,270,300};
        assertArrayEquals(expectedOutput4, FrameCalculator.getAllFrames(input4));

        //Valid check 5
        int[] input5 = new int[]{10,10,10,10,10,10,10,10,10,10,4,5};
        int[] expectedOutput5 = new int[]{30,60,90,120,150,180,210,240,264,283};
        assertArrayEquals(expectedOutput5, FrameCalculator.getAllFrames(input5));
    }
}