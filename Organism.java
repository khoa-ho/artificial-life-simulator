public abstract class Organism  {

    private int energy;

    public Organism(){
        this.energy = 0;
    }

    public void update(){
        this.energy++;
    }

    public int getEnergy(){
        return this.energy;
    }

    public void incrementEnergy(){
        this.energy++;
    }

    public void decrementEnergy(){
        this.energy -= (this.energy > 0) ? 1 : 0;
    }

    protected void emptify(){
        this.energy = 0;
    }

    public abstract String getType();
    
    public abstract Organism reproduce();

    public abstract double getCooperationProbability();

    public abstract boolean cooperates();


}
