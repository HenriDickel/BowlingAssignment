import java.util.ArrayList;
import java.util.List;

/**
 * Main logic unit
 */
public class FrameCalculator {

        /**
         * Calculate all frame scores
         * @param balls The number of pins knocked down by each ball
         * @return Gives the score for each frame as output
         */
        public static int[] getAllFrames(int[] balls){

            //Error cases
            if(balls == null || balls.length <= 1) return new int[0];

            //Result
            List<Integer> frames = new ArrayList<>();

            //TODO

            //Return result
            return frames.stream().mapToInt(Integer::intValue).toArray();
        }

}
