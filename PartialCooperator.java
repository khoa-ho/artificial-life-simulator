public class PartialCooperator extends Organism {


    public PartialCooperator(){
        super();
    }

    public String getType(){
        return "PartialCooperator";
    }

    public Organism reproduce(){
        //Energy Alteration? this.energy -= 10;
        return new PartialCooperator();
    }

    public double getCooperationProbability(){
        return 0.5;
    }

    public boolean cooperates(){
        return java.util.concurrent.ThreadLocalRandom.current().nextBoolean();
    }

}
