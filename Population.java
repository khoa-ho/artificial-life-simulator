import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    private ArrayList<Organism> population;

    public Population(Map<String, Integer> counts) {
        // Pre-calculate the size of the ArrayList
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

    public void update() {

        LinkedList<Organism> birthList = new LinkedList<>();

        for (Organism o : population) {
            // Update
            o.update();

            // Cooperation
            if (o.cooperates()) {
                if (population.size() > 1) {
                    o.decrementEnergy();
                    for (int i = 0; i < 8; i++) {
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

    public double calculateCooperationMean() {
        double ret = 0;
        for (Organism o : population) {
            ret += o.getCooperationProbability();
        }
        return ret / population.size();
    }

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
