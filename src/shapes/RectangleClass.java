package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;


public class RectangleClass extends ShapeClass {
    public double x0, y0,x1, y1,width, height;
    /*setters and getters*/
    Rectangle rectangle = new Rectangle();
    private GraphicsContext graphicsContext;
    private ColorPicker cpLine;
    private ColorPicker cpFill;
    public void RectangleClass(){ }
    public ColorPicker getStroke(){
        return cpLine;
    }
    public ColorPicker getFill(){
        return cpFill;
    }
    public double getX(){
        return rectangle.getX();
    }
    public double getY(){
        return rectangle.getY();
    }
    public double getWidth(){ return rectangle.getWidth(); }
    public double getHeight() { return rectangle.getHeight(); }
    public void setColor(ColorPicker colorPicker){ this.cpLine = colorPicker; }
    public void setFill(ColorPicker colorPicker){ this.cpFill = colorPicker; }
    public void setGraphicsContext(GraphicsContext graphicsContext){ this.graphicsContext = graphicsContext; }
    public boolean IsPointContained(Point2D p){ return rectangle.contains(p); }


    public void setStartPoint(double x, double y){
        this.x0 = x;
        this.y0 = y;

        rectangle.setX(x);
        rectangle.setY(y);
    }

    public void setEndPoint(double endX, double endY){
        this.x1 = endX;
        this.y1 = endY;
    }

    /*implemented draw function*/
    public void draw(){
        this.width = Math.abs((x1 - x0));
        rectangle.setWidth(Math.abs((x1 - x0)));
        this.height = Math.abs((y1 - y0));
        rectangle.setHeight(Math.abs((y1 - y0)));
        if(getX() > x1) {
            rectangle.setX(x1);
        }
        if(getY() > y1) {
            rectangle.setY(y1);
        }
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.fillRect(getX(), getY(), getWidth(), getHeight());
        graphicsContext.strokeRect(getX(), getY(), getWidth(), getHeight());

    }
}

