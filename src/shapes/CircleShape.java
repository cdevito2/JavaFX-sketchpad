package shapes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;

public class CircleShape extends ShapeClass {

    Circle circle = new Circle();
    private double centerX, centerY,x1, y1;
    private GraphicsContext graphicsContext;
    ColorPicker cpLine;
    ColorPicker cpFill;
    public ColorPicker getStroke(){
        return cpLine;
    }
    public ColorPicker getFill(){
        return cpFill;
    }
    public void setRadius(double radius){ circle.setRadius(radius); }
    public double getRadius(){ return circle.getRadius(); }
    public void setColor(ColorPicker colorPicker){ cpLine = colorPicker; }
    public void setFill(ColorPicker colorPicker){ cpFill = colorPicker; }
    public void setGraphicsContext(GraphicsContext graphicsContext){ this.graphicsContext = graphicsContext; }
    public boolean IsPointContained(Point2D p){ return circle.contains(p); }
    public double getCenterX(){
        return circle.getCenterX();
    }
    public double getCenterY(){
        return circle.getCenterY();
    }

    public void setCenterPoint(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;

        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
    }
    public void setEndPoint(double endX, double endY){
        this.x1 = endX;
        this.y1 = endY;

    }




/*implemented draw function*/
    public void draw(){
        circle.setRadius((Math.abs(x1 - centerX) + Math.abs(y1 - centerY)) / 2);
        if(centerX > x1) {
            circle.setCenterX(x1);
        }
        if(centerY > y1) {
            circle.setCenterY(y1);
        }
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
        graphicsContext.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());

    }
}
