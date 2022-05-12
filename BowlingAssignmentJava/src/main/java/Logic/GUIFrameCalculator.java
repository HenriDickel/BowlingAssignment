package Logic;

import java.util.List;

public class GUIFrameCalculator {

    /**
     * Calculate all frames (including unfinished frames)
     * @param scores The number of pins knocked down by each ball
     * @return Returns all frames
     */
    public static Frame[] getAllFrames(List<Integer> scores){

        //Result
        Frame[] frames = new Frame[10];
        for(int i = 0; i < 10; i++) frames[i] = new Frame(i);

        //Error cases
        if(scores == null || scores.size() < 1) return frames;

        //Current frame
        int frameIndex = 0;

        //Check if index is at the start of a frame
        boolean startOfFrame = true;

        //Calculate individual score per frame
        for(int i = 0; i < scores.size(); i++){

            //Current frame
            Frame currFrame = frames[frameIndex];

            //Strike
            if(scores.get(i) == 10){
                //Set isStrike
                currFrame.setStrike(true);

                //Next two balls have already been thrown
                if(scores.size() > i + 2){
                    //Input scores
                    if(!currFrame.isLastFrame()) currFrame.setInputScores(new int[]{10});
                    else currFrame.setInputScores(new int[]{scores.get(i), scores.get(i + 1), scores.get(i + 2)});

                    //Frame score
                    currFrame.setFrameScore(10 + scores.get(i+1) + scores.get(i+2));
                    if(frameIndex > 0) currFrame.addFrameScore(frames[frameIndex - 1].getFrameScore());

                    //Finished
                    currFrame.setFinished(true);
                    if(currFrame.isLastFrame()) break;

                    //Go to next frame
                    frameIndex++;
                    continue;
                }
                //Only one of the next balls has been thrown
                else if(scores.size() > i + 1){
                    //Input score
                    if(!currFrame.isLastFrame()) currFrame.setInputScores(new int[]{10});
                    else currFrame.setInputScores(new int[]{scores.get(i), scores.get(i + 1)});

                    //Frame score
                    currFrame.setFrameScore(10 + scores.get(i+1));
                    if(frameIndex > 0) currFrame.addFrameScore(frames[frameIndex - 1].getFrameScore());

                    //Finished
                    currFrame.setFinished(false);
                    if(currFrame.isLastFrame()) break;

                    //Go to next frame
                    frameIndex++;
                    continue;
                }
                //None of the next balls has been thrown
                else {
                    //Input score
                    currFrame.setInputScores(new int[]{10});

                    //Frame score
                    currFrame.setFrameScore(10);
                    if(frameIndex > 0) currFrame.addFrameScore(frames[frameIndex - 1].getFrameScore());

                    //Finished
                    currFrame.setFinished(false);
                    if(currFrame.isLastFrame()) break;

                    //Go to next frame
                    frameIndex++;
                    continue;
                }

            }

            //Start of frame and no strike - we can't (completely) calculate frame from here
            if(startOfFrame){
                //Set values we already know
                currFrame.setInputScores(new int[]{scores.get(i)});
                currFrame.setFrameScore(scores.get(i));
                if(frameIndex > 0) currFrame.addFrameScore(frames[frameIndex - 1].getFrameScore());

                //Check next number
                startOfFrame = false;
                continue;
            }
            else startOfFrame = true; //Set to true for next number

            //Spare
            if(scores.get(i-1) + scores.get(i) == 10){
                //Set isSpare
                currFrame.setSpare(true);

                //Next ball has already been thrown
                if(scores.size() > i + 1){
                    //Input score
                    if(!currFrame.isLastFrame()) currFrame.setInputScores(new int[]{scores.get(i-1), scores.get(i)});
                    else currFrame.setInputScores(new int[]{scores.get(i-1), scores.get(i), scores.get(i + 1)});

                    //Frame score
                    currFrame.setFrameScore(10 + scores.get(i+1));
                    if(frameIndex > 0) currFrame.addFrameScore(frames[frameIndex - 1].getFrameScore());

                    //Finished
                    currFrame.setFinished(true);
                    if(currFrame.isLastFrame()) break;

                    //Go to next frame
                    frameIndex++;
                }
                else {
                    //Input score
                    currFrame.setInputScores(new int[]{scores.get(i-1), scores.get(i)});

                    //Frame score
                    currFrame.setFrameScore(10);
                    if(frameIndex > 0) currFrame.addFrameScore(frames[frameIndex - 1].getFrameScore());

                    //Finished
                    currFrame.setFinished(false);
                    if(currFrame.isLastFrame()) break;

                    //Go to next frame
                    frameIndex++;
                }
            }
            //No Spare
            else {
                //Input score
                currFrame.setInputScores(new int[]{scores.get(i-1), scores.get(i)});

                //Frame score
                currFrame.setFrameScore(scores.get(i-1) + scores.get(i));
                if(frameIndex > 0) currFrame.addFrameScore(frames[frameIndex - 1].getFrameScore());

                //Finished
                currFrame.setFinished(true);

                //Go to next frame
                frameIndex++;
            }
        }

        //Return result
        return frames;
    }
}
