package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Workout {
    private final String id;
    private String name;
    private User owner;
    private final List<WorkoutExercise> items = new ArrayList<>();

    public Workout(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() { return owner; }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<WorkoutExercise> getExercises() {
        return items;
    }

    public void addItem(WorkoutExercise exercise) {
        items.add(exercise);
    }

    @Override
    public String toString() {
        return name + " (" + items.size() + " exercises)";
    }
}
