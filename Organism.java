public abstract class Organism {
    protected static final double MUTATION_CHANCE = 0.0;
    private int energy;

    public Organism() {
        this.energy = 0;
    }

    /**
     * Updates the Organism
     */
    public void update() {
        this.incrementEnergy();
    }

    /**
     * Returns the Energy level of this Organism
     *
     * @return : this.energy
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Increments the Energy level of this Organism
     */
    public void incrementEnergy() {
        this.energy++;
    }

    /**
     * Decrements the Energy level of this Organism
     */
    public void decrementEnergy() {
        this.energy -= (this.energy > 0) ? 1 : 0;
    }
    
    /**
     * Decreases the Energy level by an amount
     * @param amount
     */
    public void decreaseEnergy(int amount) {
        for (int i = 0; i < amount; i++) {
            this.decrementEnergy();
        }
    }

    /**
     * Sets the Energy level of this Organism to 0 used in reproduce()
     */
    protected void emptify() {
        this.energy = 0;
    }

    /**
     * Returns the type of this Organism
     *
     * @return : the type of this Organism as an identifying String
     */
    public abstract String getType();

    /**
     * Causes this Organism to reproduce
     *
     * @return : the new child organism
     * @post : this organism has had emptify() invoked on it
     */
    public abstract Organism reproduce();

    /**
     * Returns the Cooperation Probability of this Organism
     *
     * @return : the probability that this organism will cooperate
     */
    public abstract double getCooperationProbability();

    /**
     * Returns whether this Organism will cooperate
     *
     * @return : true if this organism will cooperate, false otherwise
     */
    public abstract boolean cooperates();

}
