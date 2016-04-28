package fitnessapp.jimsgym;

/**
 * Created by james_000 on 21/04/2016.
 */
public class ProgressTracker {

    int totalCalories;

    public void setTotalCalories(int totalCalories) {
        this.totalCalories += totalCalories;
    }

    public int getTotalCalories() {
        return totalCalories;
    }
}
