package domain;

public class Exercise {
    private final String name;
    private final String muscleGroup;
    private final int difficulty;

    public Exercise(String name, String muscleGroup, int difficulty) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.difficulty = difficulty;
    }

    public String getName() { return name; }
    public String getMuscleGroup() { return muscleGroup; }
    public int getDifficulty() { return difficulty; }

    @Override
    public String toString() {
        return name + " (" + muscleGroup + ")";
    }
}
