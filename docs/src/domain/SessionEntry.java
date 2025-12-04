package domain;

public class SessionEntry {
    private final WorkoutExercise exercise;
    private final int actualReps;
    private final double actualWeight;

    public SessionEntry(WorkoutExercise exercise, int actualReps, double actualWeight) {
        this.exercise = exercise;
        this.actualReps = actualReps;
        this.actualWeight = actualWeight;
    }

    public WorkoutExercise getExercise() { return exercise; }
    public int getActualReps() { return actualReps; }
    public double getActualWeight() { return actualWeight; }
}
