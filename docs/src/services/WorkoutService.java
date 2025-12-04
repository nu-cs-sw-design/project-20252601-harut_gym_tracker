package services;

import persistence.WorkoutRepository;
import generation.*;
import domain.*;
import java.util.List;

public class WorkoutService {

    private final WorkoutRepository repo;
    private final GeneratorFactory factory;

    public WorkoutService(WorkoutRepository repo, GeneratorFactory factory) {
        this.repo = repo;
        this.factory = factory;
    }

    public WorkoutBuilder createCustomWorkout() {
        return new WorkoutBuilder();
    }

    public void saveWorkout(Workout workout, User user) {
        workout.setOwner(user);
        repo.save(workout);
    }

    public List<Workout> listWorkouts(User user) {
        return repo.findByUser(user);
    }

    public Workout generateWorkout(User user) {
        PlanGenerator gen = factory.getGenerator(user.getGoal());
        Workout workout = gen.generate(user);
        workout.setOwner(user);
        repo.save(workout);
        return workout;
    }
}
