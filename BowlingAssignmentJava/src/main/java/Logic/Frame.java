package Logic;

/**
 * Represents one frame of the bowling game
 */
public class Frame {

    //Index
    private final int frameIndex;
    private final boolean isLastFrame;

    //Values
    private int[] inputScores;
    private int frameScore;

    //Status
    private boolean isFinished;
    private boolean isStrike;
    private boolean isSpare;


    public Frame(int index) {
        frameIndex = index;
        isLastFrame = frameIndex == 9;
    }

    public boolean isEmpty(){
        return inputScores == null;
    }

    public int[] getInputScores() {
        return inputScores;
    }

    public void setInputScores(int[] inputScores) {
        this.inputScores = inputScores;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    public void addFrameScore(int frameScore) {
        this.frameScore += frameScore;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public boolean isLastFrame() {
        return isLastFrame;
    }
}
