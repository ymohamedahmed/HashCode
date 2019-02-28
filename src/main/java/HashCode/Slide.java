package main.java.HashCode;

public class Slide {

    public final boolean isVerticalPair;

    public Photo p1;
    public Photo p2;

    public Slide(Photo p) {
        this.p1 = p;
        isVerticalPair = false;
    }

    public Slide(Photo p1, Photo p2) {
        isVerticalPair = true;
        this.p1 = p1;
        this.p2 = p2;
    }

}