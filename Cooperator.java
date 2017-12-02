public class Cooperator extends Organism {


    public Cooperator(){
        super();
    }

    public String getType(){
        return "Cooperator";
    }

    public Organism reproduce(){
        // Energy alteration???
        return new Cooperator();
    }

    public double getCooperationProbability(){
        return 1.0;
    }

    public boolean cooperates(){
        return true;
    }

}
