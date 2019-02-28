package HashCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SlideOutput {

    public static void writeSlides(List<Slide> slides, File f) {

        StringBuilder builder = new StringBuilder();

        builder.append(slides.size() + "\n");

        for(Slide slide : slides) {
            builder.append(slide.toString() + "\n");
        }


        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(f));
            writer.write(builder.toString());
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
