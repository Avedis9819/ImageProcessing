import ij.*;
import ij.process.*;
import ij.plugin.*;
import ij.io.*;
import java.io.*;

public class ImageJPlugin implements PlugIn {

    public void run(String arg) {
        String crsFilePath = "car-f-92.stu";
        int N = readNumberOfCourses(crsFilePath);

        ImageProcessor ip = new ByteProcessor(N, N);
        ip.setValue(255);
        ip.fill();

        String stuFilePath = "/home/yura/Desktop/Image_Processing/HW01/pur-s-93.stu";
        processStuFile(ip, stuFilePath);


        ImagePlus imp = new ImagePlus("UETP Visualization", ip);
        imp.show();


        String outputPath = "/home/yura/Desktop/Image_Processing/HW01";
        FileSaver fileSaver = new FileSaver(imp);
        fileSaver.saveAsPng(outputPath);
    }

      private int readNumberOfCourses(String filePath) {
        int numberOfCourses = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
      
                numberOfCourses = Integer.parseInt(line.split(" ")[1]);
            }
            reader.close();
        } catch (IOException e) {
            IJ.showMessage("Error reading thing.crs file: " + e.getMessage());
        }
        return numberOfCourses;
    }


    private void processStuFile(ImageProcessor ip, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split(" ");
                if (tokens.length >= 2) {
                    int x = Integer.parseInt(tokens[0]) - 1; // Adjust for 0-based indexing
                    int y = Integer.parseInt(tokens[1]) - 1; // Adjust for 0-based indexing
                    ip.putPixel(x, y, 0); // Set pixel to black
                }
            }
            reader.close();
        } catch (IOException e) {
            IJ.showMessage("Error reading thing.stu file: " + e.getMessage());
        }
    }
}
