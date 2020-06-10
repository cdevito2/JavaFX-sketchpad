package shapes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Ellipse;
public class EllipseShape extends ShapeClass {
    Ellipse ellipse = new Ellipse();
    private double centerX, centerY;
    private double endX, endY;
    private GraphicsContext graphicsContext;
    ColorPicker cpLine;
    ColorPicker cpFill;

    public boolean IsPointContained(Point2D p){ return ellipse.contains(p); }



    public void setCenterPoint(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;

        ellipse.setCenterX(centerX);
        ellipse.setCenterY(centerY);
    }
    /*setters and getters*/
    public double getCenterX(){
        return ellipse.getCenterX();
    }
    public double getCenterY(){
        return  ellipse.getCenterY();
    }
    public double getRadiusX(){ return ellipse.getRadiusX(); }
    public double getRadiusY(){ return ellipse.getRadiusY(); }
    public ColorPicker getStroke(){
        return cpLine;
    }
    public ColorPicker getFill(){
        return cpFill;
    }
    public void setColor(ColorPicker colorPicker){ cpLine = colorPicker; }
    public void setFill(ColorPicker colorPicker){ cpFill = colorPicker; }
    public void setGraphicsContext(GraphicsContext graphicsContext){ this.graphicsContext = graphicsContext; }
    public void setEndPoint(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }
    /*implemented draw function*/
    public void draw(){

        ellipse.setRadiusX(Math.abs((endX - centerX)));
        ellipse.setRadiusY(Math.abs((endY - centerY)));
        if(centerX > endX) {
            ellipse.setCenterX(endX);
        }
        if(centerY > endY) {
            ellipse.setCenterY(endY);
        }
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.strokeOval(getCenterX(), getCenterY(), getRadiusX(), getRadiusY());
        graphicsContext.fillOval(getCenterX(), getCenterY(), getRadiusX(), getRadiusY());

    }
}
