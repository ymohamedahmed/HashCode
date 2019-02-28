package main.java.HashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class PairMaker {

    public HashSet<Slide> makePairs(List<Photo> photos) {
        Collection<Photo> photosCopy = new ArrayList<>(photos);

        // Greedy O(N^2)

        HashSet<Slide> slides = new HashSet<>();

        while(photosCopy.size() > 0) {
            Photo p = photos.get(0);
            photos.remove(0);

            if(p.isVertical()) {

                Photo bestPair = null;


            }
            else {
                slides.add(new Slide(p));
            }
        }

        return slides;
    }

}