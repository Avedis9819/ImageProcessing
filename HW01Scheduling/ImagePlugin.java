import ij.*;
import ij.process.*;
import ij.plugin.*;
import java.io.*;

public class ImagePlugin implements PlugIn {

    public void run(String arg) {
        int N = 543;

        ByteProcessor binaryProcessor = new ByteProcessor(N, N);
        binaryProcessor.setValue(255);
        binaryProcessor.fill();

        try {
            String stuFilePath = "car-f-92.stu"; // Replace with the path to your .stu file
            BufferedReader stuReader = new BufferedReader(new FileReader(stuFilePath));
            String line;

            while ((line = stuReader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");

                if (tokens.length >= 2) {
                    int x = Integer.parseInt(tokens[0]);
                    int y = Integer.parseInt(tokens[1]);


                    if (x >= 0 && x < N && y >= 0 && y < N) {
                        binaryProcessor.putPixel(x, y, 0); 
                    }
                }
            }

            stuReader.close();
        } catch (IOException e) {
            IJ.error("Error reading the .stu file: " + e.getMessage());
        }

        ImagePlus imagePlus = new ImagePlus("Course Clash Image", binaryProcessor);
        imagePlus.show();
    }

    public static void main(String[] args) {
        new ImagePlugin.run(null);
    }
}
