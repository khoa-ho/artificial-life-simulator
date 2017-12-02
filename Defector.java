public class Defector extends Organism {


    public Defector(){
        super();
    }

    public String getType(){
        return "Defector";
    }

    public Organism reproduce(){
        // Energy Alteration? this.energy -= 10;
        return new Defector();
    }

    public double getCooperationProbability(){
        return 0.0;
    }

    public boolean cooperates(){
        return false;
    }

}
