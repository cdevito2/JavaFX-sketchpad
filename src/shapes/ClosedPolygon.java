package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ClosedPolygon extends ShapeClass {
    ArrayList<Double> xValues = new ArrayList<Double>();
    ArrayList<Double>yValues = new ArrayList<Double>();
    private GraphicsContext gc;
    ColorPicker cpLine;
    ColorPicker cpFill;
    /*auto generated setters and getters */
    public ArrayList<Double> getxValues() {
        return xValues;
    }


    public ArrayList<Double> getyValues() {
        return yValues;
    }



    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public ColorPicker getCpLine() {
        return cpLine;
    }

    public void setCpLine(ColorPicker cpLine) {
        this.cpLine = cpLine;
    }

    public ColorPicker getCpFill() {
        return cpFill;
    }

    public void setCpFill(ColorPicker cpFill) {
        this.cpFill = cpFill;
    }
    public boolean IsPointContained(Point2D p) {
        /*for(int i = 0; i < getxValues().size(); i++){
            if(xValues.get(i) == p.getX() && yValues.get(i) == p.getY()){
                return true;
            }
        }*/

        for (int i=0;i<getxValues().size();i++)
        {
            System.out.println("detecting...");
            if(xValues.get(i) == p.getX() && yValues.get(i) == p.getY()){
                return true;
            }

            else if (p.getX() >= Collections.min(xValues) && p.getX() <= Collections.max(xValues) && p.getY() >= Collections.min(yValues) && p.getY() <= Collections.max(xValues))
            { //attempt to implement point detection if use clicks in the middle of the shape
                return true;
            }

        }

        return false;
    }

    public ArrayList<Double> getXYCombined() {
        return XYCombined;
    }



    public ArrayList<Double>XYCombined;


    public void addPoint(double x, double y)
    {
        xValues.add(x);
        yValues.add(y);

    }
    /*implemented draw function*/
    public void draw()
    {
        double []x = new double[xValues.size()];
        double []y = new double[yValues.size()];
        int i =0;
        while (i < xValues.size())
        {
            x[i] = xValues.get(i);
            y[i] = yValues.get(i);
            i++;
        }
        gc.setFill(cpFill.getValue());
        gc.setStroke(cpLine.getValue());
        gc.strokePolyline(x,y,x.length);
        gc.fillPolygon(x,y,x.length);


    }


}
