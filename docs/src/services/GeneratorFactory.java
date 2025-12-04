package services;

import generation.*;
import domain.*;

public class GeneratorFactory {
    public PlanGenerator getGenerator(GoalType g) {
        return switch (g) {
            case STRENGTH -> new StrengthPlanGenerator();
            case ENDURANCE -> new EndurancePlanGenerator();
            default -> new StrengthPlanGenerator();
        };
    }
}
