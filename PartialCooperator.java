public class PartialCooperator extends Organism {

    public PartialCooperator() {
        super();
    }

    public String getType() {
        return "PartialCooperator";
    }

    public Organism reproduce() {
        this.emptify();
        return new PartialCooperator();
    }

    public double getCooperationProbability() {
        return 0.5;
    }

    public boolean cooperates() {
        return java.util.concurrent.ThreadLocalRandom.current().nextBoolean();
    }

}
