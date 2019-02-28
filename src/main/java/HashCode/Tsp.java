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

        private Slide[] slides;

        public SlidesEvaluator(Slide[] slides) {
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

    static ArrayList<Slide> solve(Set<Slide> slides) {
        RoutingModel routing = new RoutingModel(slides.size(), 1, 0);

        // Setting the cost function.
        // Put a permanent callback to the distance accessor here. The callback
        // has the following signature: ResultCallback2<int64, int64, int64>.
        // The two arguments are the from and to node inidices.
        NodeEvaluator2 distances = new SlidesEvaluator(slides.toArray(new Slide[0]));
        routing.setArcCostEvaluatorOfAllVehicles(distances);

        // Solve, returns a solution if any (owned by RoutingModel).
        RoutingSearchParameters search_parameters =
                RoutingSearchParameters.newBuilder()
                        .mergeFrom(RoutingModel.defaultSearchParameters())
                        .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                        .build();

        Assignment solution = routing.solveWithParameters(search_parameters);
        ArrayList<Slide>

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

}
