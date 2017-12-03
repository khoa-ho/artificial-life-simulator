import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    private static final int COOP_BENEFIT = 8;
    private static final int COOP_COST = 1;

    private ArrayList<Organism> population;

    /**
     * Constructs a population of organisms from the given counts
     *
     * @param counts
     *            : a map of organism names to respective quantities
     */
    public Population(Map<String, Integer> counts) {
        // Pre-calculate the size of the ArrayList to save on space copy costs
        int size = 0;
        for (Entry<String, Integer> e : counts.entrySet()) {
            size += e.getValue();
        }
        population = new ArrayList<Organism>(size);

        // Populate the population
        for (int i = 0; i < counts.get("Cooperator").intValue(); i++) {
            population.add(new Cooperator());
        }
        for (int i = 0; i < counts.get("Defector").intValue(); i++) {
            population.add(new Defector());
        }
        for (int i = 0; i < counts.get("PartialCooperator").intValue(); i++) {
            population.add(new PartialCooperator());
        }
    }

    /**
     * Updates this population
     *
     * Invokes "update()" on all population members, calculates cooperations, and
     * causes reproduction
     */
    public void update() {

        LinkedList<Organism> birthList = new LinkedList<>();

        for (Organism o : population) {
            // Update
            o.update();

            // Cooperation
            if (o.cooperates() && o.getEnergy() >= COOP_COST) {
                if (population.size() > 1) {
                    o.decreaseEnergy(COOP_COST);
                    for (int i = 0; i < COOP_BENEFIT; i++) {
                        Organism z = population
                                .get(ThreadLocalRandom.current().nextInt(population.size()));
                        while (z == o) {
                            z = population
                                    .get(ThreadLocalRandom.current().nextInt(population.size()));
                        }
                        z.incrementEnergy();
                    }
                }
            }

            // Reproduction
            if (o.getEnergy() >= 10) {
                birthList.addFirst(o.reproduce());
            }
        }

        // Newborns overwrite the dead
        while (birthList.size() > 0) {
            Organism n = birthList.poll();
            population.set(ThreadLocalRandom.current().nextInt(population.size()), n);
        }

    }

    /**
     * Calculates the mean cooperation probability of all organisms
     *
     * @return : the mean cooperation probability of the population
     */
    public double calculateCooperationMean() {
        double ret = 0;
        for (Organism o : population) {
            ret += o.getCooperationProbability();
        }
        return ret / population.size();
    }

    /**
     * Returns the counts of each type of Organism in the population
     *
     * @return: the population counts, in the same format as the constructor's
     *          counts argument
     */
    public Map<String, Integer> getPopulationCounts() {
        Map<String, Integer> map = new HashMap<>();
        int coopCount = 0, defCount = 0, partCount = 0;
        for (Organism o : population) {
            switch (o.getType()) {
            case "Cooperator":
                coopCount++;
                break;
            case "Defector":
                defCount++;
                break;
            case "PartialCooperator":
                partCount++;
            }
        }
        map.put("Cooperator", new Integer(coopCount));
        map.put("Defector", new Integer(defCount));
        map.put("PartialCooperator", new Integer(partCount));
        return map;
    }

}
