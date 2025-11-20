public class Exercise {
    public String name;
    public String muscleGroup;
    public int sets;
    public int reps;
    public int restSeconds;

    public Exercise(String name, String muscleGroup, int sets, int reps, int restSeconds) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.sets = sets;
        this.reps = reps;
        this.restSeconds = restSeconds;
    }
}
