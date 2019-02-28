package HashCode;

import java.io.*;
import java.util.*;
import java.text.*;

import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.NodeEvaluator2;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.FirstSolutionStrategy;
import com.google.ortools.constraintsolver.RoutingSearchParameters;

class Tsp {
    static {
        System.loadLibrary("jniortools");
    }

    static class SlidesEvaluator extends NodeEvaluator2 {

        private ArrayList<Slide> slides;

        public SlidesEvaluator(ArrayList<Slide> slides) {
            this.slides = slides;
        }

        @Override
        public long run(int firstIndex, int secondIndex) {
            return 0L;
        }
    }

    static class ConstantCallback extends NodeEvaluator2 {
        @Override
        public long run(int firstIndex, int secondIndex) {
            return 1;
        }
    }

    static void solve(ArrayList<Slide> slides) {
        RoutingModel routing = new RoutingModel(slides.size(), 1, 0);

        // Setting the cost function.
        // Put a permanent callback to the distance accessor here. The callback
        // has the following signature: ResultCallback2<int64, int64, int64>.
        // The two arguments are the from and to node inidices.
        NodeEvaluator2 distances = new SlidesEvaluator(slides);
        routing.setArcCostEvaluatorOfAllVehicles(distances);

        // Forbid node connections (randomly).
        Random randomizer = new Random();
        long forbidden_connections = 0;
        while (forbidden_connections < forbidden) {
            long from = randomizer.nextInt(size - 1);
            long to = randomizer.nextInt(size - 1) + 1;
            if (routing.nextVar(from).contains(to)) {
                System.out.println("Forbidding connection " + from + " -> " + to);
                routing.nextVar(from).removeValue(to);
                ++forbidden_connections;
            }
        }

        // Solve, returns a solution if any (owned by RoutingModel).
        RoutingSearchParameters search_parameters =
                RoutingSearchParameters.newBuilder()
                        .mergeFrom(RoutingModel.defaultSearchParameters())
                        .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                        .build();

        Assignment solution = routing.solveWithParameters(search_parameters);
        if (solution != null) {
            // Solution cost.
            System.out.println("Cost = " + solution.objectiveValue());
            // Inspect solution.
            // Only one route here; otherwise iterate from 0 to routing.vehicles() - 1
            int route_number = 0;
            for (long node = routing.start(route_number); !routing.isEnd(node);
                 node = solution.value(routing.nextVar(node))) {
                System.out.print("" + node + " -> ");
            }
            System.out.println("0");
        }
    }

    public static void main(String[] args) throws Exception {
        int size = 10;
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }

        int forbidden = 0;
        if (args.length > 1) {
            forbidden = Integer.parseInt(args[1]);
        }

        int seed = 0;
        if (args.length > 2) {
            seed = Integer.parseInt(args[2]);
        }

        solve(size, forbidden, seed);
    }
}
