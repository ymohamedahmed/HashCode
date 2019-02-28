package HashCode;

import java.util.HashSet;
import java.util.Set;

public class Slide {

    public final boolean isVerticalPair;

    public Photo p1;
    public Photo p2;

    public Set<String> tags = new HashSet<>();

    public Slide(Photo p) {
        this.p1 = p;
        isVerticalPair = false;

        tags.addAll(p.getTags());
    }

    public Slide(Photo p1, Photo p2) {
        isVerticalPair = true;
        this.p1 = p1;
        this.p2 = p2;

        tags.addAll(p1.getTags());
        tags.addAll(p2.getTags());
    }

    @Override
    public String toString() {
        if(isVerticalPair)
            return "" + p1.getId();
        else
            return p1.getId() + " " + p2.getId();
    }
}