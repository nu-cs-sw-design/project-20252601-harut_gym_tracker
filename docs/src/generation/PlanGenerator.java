package generation;

import domain.*;

public interface PlanGenerator {
    Workout generate(User user);
}
