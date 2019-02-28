package Main.java.HashCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PhotoFileParser {

    public static void main(String[] args) {
        try {
            Set<Photo> photoSet = PhotoFileParser.parsePhotoFile("res/a_example.txt");
            for (Photo photo : photoSet) {
                System.out.println(photo.getId());
                for (String tag : photo.getTags()) {
                    System.out.println(tag);
                }
                System.out.println(photo.isVertical());
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Set<Photo> parsePhotoFile(String filepath) throws FileNotFoundException {
        Scanner sc = null;
        Set<Photo> photoSet = new HashSet<>();

        try {
            sc = new Scanner(new FileInputStream(filepath), "UTF-8");
            String line = sc.nextLine();
            int photoCount = Integer.parseInt(line.substring(0, 1));

            for (int id=0; id<photoCount; id++) {
                String[] photoDetails = sc.nextLine().split(" ");
                photoSet.add(new Photo(
                        photoDetails[0].charAt(0),
                        id,
                        Arrays.asList(photoDetails).subList(2, photoDetails.length)
                ));
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return photoSet;
    }
}
