import java.util.ArrayList;
import java.util.List;

/**
 * Main logic unit
 */
public class FrameCalculator {

        /**
         * Calculate all frame scores
         * @param scores The number of pins knocked down by each ball
         * @return Gives the score for each frame as output
         */
        public static int[] getAllFrames(int[] scores){

            //Error cases
            if(scores == null || scores.length <= 1) return new int[0];

            //Result
            List<Integer> frames = new ArrayList<>();

            //Check if index is at the start of a frame
            boolean startOfFrame = true;

            //Calculate individual score per frame
            for(int i = 0; i < scores.length; i++){

                //Strike
                if(scores[i] == 10){
                    //Next two balls have already been thrown
                    if(scores.length > i + 2){
                        frames.add(10 + scores[i+1] + scores[i+2]);
                        if(frames.size() > 1) frames.set(frames.size() - 1, frames.get(frames.size() - 1) + frames.get(frames.size() - 2));
                        continue;
                    }
                    else break;
                }

                //Start of frame and no strike, we can't calculate frame from here
                if(startOfFrame){
                    //Now not at the start anymore
                    startOfFrame = false;

                    //Check next number
                    continue;
                }
                else startOfFrame = true; //Set to true for next number

                //Spare
                if(scores[i-1] + scores[i] == 10){
                    //Next ball has already been thrown
                    if(scores.length > i + 1){
                        frames.add(10 + scores[i+1]);
                        if(frames.size() > 1) frames.set(frames.size() - 1, frames.get(frames.size() - 1) + frames.get(frames.size() - 2));
                        continue;
                    }
                    else break;
                }
                //No Spare
                else {
                    frames.add(scores[i] + scores[i-1]);
                    if(frames.size() > 1) frames.set(frames.size() - 1, frames.get(frames.size() - 1) + frames.get(frames.size() - 2));
                }

            }

            //Return result
            return frames.stream().mapToInt(Integer::intValue).toArray();
        }

}
