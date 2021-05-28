import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int n = 0;
        for (Point currPt : s.getPoints()) {
            n++;
        }
        return n;
    }

    public double getAverageLength(Shape s) {
        double avgLenght = 0;
        avgLenght = getPerimeter(s) / getNumPoints(s);
        return avgLenght;
    }

    public double getLargestSide(Shape s) {
        double lrgSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > lrgSide) {
                lrgSide = currDist;
            }
            prevPt = currPt;
        }
        return lrgSide;
    }

    public double getLargestX(Shape s) {
        double lrgX = 0;
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (currX > lrgX) {
                lrgX = currX;
            }
        }
        return lrgX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double lrgPerimeter = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > lrgPerimeter) {
                lrgPerimeter = perimeter;
            }
        }
        return lrgPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        Shape lrgShape = new Shape();
        Point pt = new Point(0,0);
        lrgShape.addPoint(pt);
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > getPerimeter(lrgShape)){
                lrgShape = s;
                temp = f;
            }
            
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPt = getNumPoints(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of point = " + numPt);
        System.out.println("Average lenght = " + getAverageLength(s));
        System.out.println("Largest side = " + getLargestSide(s));
        System.out.println("Largest X point = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Lergest perimeter = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("Name of file with largest perimeter = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
