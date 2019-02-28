package main.java.HashCode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Photo {

    private boolean isVertical;
    private Set<String> tags;
    private int id;

    public Photo(char direction, int id, Collection<String> tags) {
        this.tags = new HashSet<>(tags);
        isVertical = (direction == 'V');
        this.id = id;
    }

    public Set<String> getTags() { return tags; }

    public int getId() { return id;}

    public boolean isVertical() { return isVertical; }


}
