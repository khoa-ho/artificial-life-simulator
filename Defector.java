import java.util.concurrent.ThreadLocalRandom;

public class Defector extends Organism {

    public Defector() {
        super();
    }

    public String getType() {
        return "Defector";
    }

    public Organism reproduce() {
        this.emptify();
        if (ThreadLocalRandom.current().nextDouble() < MUTATION_CHANCE) {
            return ThreadLocalRandom.current().nextBoolean() ? new Cooperator()
                    : new PartialCooperator();
        } else {
            return new Defector();
        }
    }

    public double getCooperationProbability() {
        return 0.0;
    }

    public boolean cooperates() {
        return false;
    }

}
