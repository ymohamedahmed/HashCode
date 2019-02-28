package HashCode;

import java.util.*;
import com.google.ortools.constraintsolver.*;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.RoutingSearchParameters;

public class TSP {

    public static double costEvaluator() {
        return 0.0;
    }

    public TSP(Collection<Main.java.HashCode.Photo> slides) {
        RoutingModel routingModel = new RoutingModel(slides.size(), 1, 0);
        RoutingSearchParameters searchParameters = routingModel.defaultSearchParameters();
        routingModel.setArcCostEvaluatorOfAllVehicles();
    }
}
