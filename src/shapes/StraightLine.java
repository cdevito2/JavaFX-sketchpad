package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

public class StraightLine extends ShapeClass {
    private GraphicsContext gc;
    private Line line = new Line();
    private ColorPicker cpLine;

    public StraightLine(){}
    /*setters and getters*/
    public void setGraphicsContext(GraphicsContext g){this.gc = g;}
    public void setStart(double x, double y){
        line.setStartX(x);
        line.setStartY(y);
    }
    public void setEnd(double x, double y){
        line.setEndX(x);
        line.setEndY(y);
    }
    public double getStartX(){return line.getStartX();}
    public double getStartY(){return line.getStartY();}
    public double getEndX(){return line.getEndX();}
    public double getEndY(){return line.getEndY();}
    public boolean IsPointContained(Point2D p){ return line.contains(p); }
    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }
    public ColorPicker getColor(){ return cpLine; }
    /*implemetned draw fcn*/
    public void draw()
    {
        gc.setStroke(getColor().getValue());
        gc.strokeLine(line.getStartX(),line.getStartY(),line.getEndX(),line.getEndY());
    }


}
