package domain;

public class WorkoutExercise {
    private final Exercise exercise;
    private final int sets;
    private final int reps;
    private final int restSeconds;

    public WorkoutExercise(Exercise exercise, int sets, int reps, int restSeconds) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.restSeconds = restSeconds;
    }

    public Exercise getExercise() { return exercise; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public int getRestSeconds() { return restSeconds; }

    @Override
    public String toString() {
        return exercise.getName() + ": " + sets + "x" + reps + " (rest " + restSeconds + "s)";
    }
}
