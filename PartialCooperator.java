import java.util.concurrent.ThreadLocalRandom;

public class PartialCooperator extends Organism {

    public PartialCooperator() {
        super();
    }

    public String getType() {
        return "PartialCooperator";
    }

    public Organism reproduce() {
        this.emptify();
        if (ThreadLocalRandom.current().nextDouble() < MUTATION_CHANCE) {
            return ThreadLocalRandom.current().nextBoolean() ? new Cooperator() : new Defector();
        } else {
            return new PartialCooperator();
        }
    }

    public double getCooperationProbability() {
        return 0.5;
    }

    public boolean cooperates() {
        return ThreadLocalRandom.current().nextBoolean();
    }

}
