import java.util.concurrent.ThreadLocalRandom;

public class Cooperator extends Organism {

    public Cooperator() {
        super();
    }

    public String getType() {
        return "Cooperator";
    }

    public Organism reproduce() {
        this.emptify();
        if (ThreadLocalRandom.current().nextDouble() < MUTATION_CHANCE) {
            return ThreadLocalRandom.current().nextBoolean() ? new Defector()
                    : new PartialCooperator();
        } else {
            return new Cooperator();
        }
    }

    public double getCooperationProbability() {
        return 1.0;
    }

    public boolean cooperates() {
        return true;
    }

}
