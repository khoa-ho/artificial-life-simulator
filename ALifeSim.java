import java.util.HashMap;
import java.util.Map;

public class ALifeSim {

    public static void main(String[] args) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Cooperator", Integer.parseInt(args[1]));
        counts.put("Defector", Integer.parseInt(args[2]));
        counts.put("PartialCooperator", Integer.parseInt(args[3]));

        Population pop = new Population(counts);
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            pop.update();
        }

        System.out.println("After " + Integer.parseInt(args[0]) + " ticks:");
        counts = pop.getPopulationCounts();
        System.out.println("Cooperators = " + counts.get("Cooperator"));
        System.out.println("Defectors = " + counts.get("Defector"));
        System.out.println("PartialCooperators = " + counts.get("PartialCooperator"));
        System.out.println("\nMean Cooperation Probability = " + pop.calculateCooperationMean());
    }
}
