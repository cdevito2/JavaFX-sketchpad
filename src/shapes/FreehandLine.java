package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

import java.util.ArrayList;

public class FreehandLine extends ShapeClass {

    private GraphicsContext gc;
    private ColorPicker cpLine;
    private double x0;
    private double x1;
    private double y0;
    private double y1;
    private ArrayList<Double>xValues = new ArrayList<>();
    private ArrayList<Double>yValues = new ArrayList<>();


    public FreehandLine(){}
    /*setters and getters*/
    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }
    public void setGraphicsContext(GraphicsContext graphicsContext){ this.gc = graphicsContext; }
    public  void setStartCoordinates(double x, double y)
    {
        this.x0 = x;
        this.y0 = y;
    }
    public  void setEndCoordinates(double x, double y)
    {
        this.x1 = x;
        this.y1 = y;
    }
    public void addCoordinates(double x, double y)
    {
        xValues.add(x);
        yValues.add(y);
    }
    public ColorPicker getColor(){
        return cpLine;
    }
    public double getStartX(){ return x0; }
    public double getStartY(){ return y0; }
    public double getEndX(){ return x1; }
    public double getEndY(){ return y1;}
    /*implemented draw function*/
    public void draw()
    {
        gc.setStroke(getColor().getValue());
        gc.beginPath();
        gc.lineTo(getStartX(), getStartY());
        int i=0;
        while(i<xValues.size())
        {
            gc.lineTo(xValues.get(i),yValues.get(i));
            gc.stroke();
            i++;
        }
        gc.lineTo(getEndX(), getEndY());
        gc.stroke();
        gc.closePath();
    }
    public boolean IsPointContained(Point2D p){ //used for the delete function
        for(int x = 0; x < xValues.size(); x++){
            if(xValues.get(x) == p.getX() && yValues.get(x) == p.getY()){ //check if the point matches a point on the freehand
                return true;
            }
        }

        return false;
    }
    public double[] getXVals(){/*return all x values in the freehand drawn line*/
        double[] xVals = new double[xValues.size()];
        int x=0;
        while(x<xValues.size())
        {
            xVals[x] = xValues.get(x);
            x++;
        }
        return xVals;
    }

    public double[] getYvals(){ /*return all y values in the freehand drawn line*/
        double[] yVals = new double[yValues.size()];
        int y=0;
        while(y<yValues.size())
        {
            yVals[y] = yValues.get(y);
            y++;
        }
        return yVals;
    }

}
