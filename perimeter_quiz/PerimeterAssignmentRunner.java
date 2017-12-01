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
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int totalPoints = getNumPoints(s);
        System.out.println("There are " + totalPoints + " Points");
        double avgL = getAverageLength(s);
        System.out.println("The average length of line segments is " + avgL);
        double largestSide = getLargestSide(s);
        System.out.println("The largest side is " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("The largest X is " + largestX);
    }
    
    public int getNumPoints (Shape s) {
        // Start with 0 points
        int num_points = 0;
        for (Point pt : s.getPoints()) {
            // Need to count these points, increase iterator by 1 for each point
            num_points = num_points + 1;
        }
        return num_points;
    }

    public double getAverageLength(Shape s) {
        //Use getPerim divided by total points which equals the number of lines
        double avgLength = 0.0;
        double len = getPerimeter(s);
        int points =  getNumPoints(s);
        avgLength = len / points;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        //Get the longest side by doing a side by side comparison
        //probably need to iterate through each side and store the current longest in a var
        // and make comparisons to the the curr largest
        double currLargest = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            //Compare current to Largest
            if(currDist > currLargest){
                // Update current to 
                currLargest = currDist;
            }   
        }
        return currLargest;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            
            if(currPt.getX() > prevPt.getX()){
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles(){
        DirectoryResource dr = new DirectoryResource();
        double largestLength = 0.0;
        for (File f : dr.selectedFiles()) {
                FileResource fr = new FileResource(f);
                Shape s = new Shape(fr);
                double currLength = getPerimeter(s);
                //Compare the lengths of each file, so it must be saved curr length, largest too
                if(currLength > largestLength){
                    largestLength = currLength;
                }
        }
        return largestLength;
    }
        
    
    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File largestFile = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPerim){
             largestPerim = getPerimeter(s);
             largestFile = f;
            }
     
        }
        return largestFile.getName();
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("The largest Perim is " + largestPerim);
        
    }

    public void testFileWithLargestPerimeter() {
        String largestF = getFileWithLargestPerimeter();
        System.out.println("The name of the file with the largest perim is " + largestF);// Put code here
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
        pr.testPerimeter();
    }
}
