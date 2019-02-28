package HashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class PairMaker {

    public HashSet<Slide> makePairs(List<Photo> photos) {
        List<Photo> photosCopy = new ArrayList<>(photos);

        // Greedy O(N^2)

        HashSet<Slide> slides = new HashSet<>();

        while(photosCopy.size() > 0) {
            Photo p = photosCopy.get(0);
            photosCopy.remove(0);

            if(p.isVertical()) {

                Photo bestPair = null;
                int leastSharedTags = 99999;

                for(Photo p2 : photosCopy) {
                    if(p2.isVertical()) {
                        int st = Photo.tagsInCommon(p, p2);
                        if (st < leastSharedTags) {
                            bestPair = p2;
                            leastSharedTags = st;
                        }

                        if (leastSharedTags == 0)
                            break;
                    }
                }

                photosCopy.remove(bestPair);

                slides.add(new Slide(p, bestPair));
            }
            else {
                slides.add(new Slide(p));
            }
        }

        return slides;
    }

}