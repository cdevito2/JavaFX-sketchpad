package shapes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;
public class SquareShape extends ShapeClass {
    public double x0, y0;
    public double x1, y1;


    Rectangle square = new Rectangle();

    private GraphicsContext graphicsContext;
    private ColorPicker cpLine;
    private ColorPicker cpFill;

    public void SquareShape(){
    }

    public ColorPicker getStroke(){ return cpLine; }
    public ColorPicker getFill(){ return cpFill; }
    public double getX(){
        return square.getX();
    }
    public double getY(){
        return square.getY();
    }
    public double getWidth(){ return square.getWidth(); }
    public double getHeight() {return square.getHeight(); }
    public void setColor(ColorPicker colorPicker){ cpLine = colorPicker; }
    public void setFill(ColorPicker colorPicker){ cpFill = colorPicker; }
    public void setGraphicsContext(GraphicsContext graphicsContext){ this.graphicsContext = graphicsContext; }
    public boolean IsPointContained(Point2D p){return square.contains(p); }

    public void setStartPoint(double x, double y){
        this.x0 = x;
        this.y0 = y;

        square.setX(x);
        square.setY(y);
    }

    public void setEndPoint(double x, double y){
        this.x1 = x;
        this.y1 = y;
    }



    public void draw(){
        square.setWidth(Math.abs((x1 - x0)));
        square.setHeight(Math.abs((y1 - y0)));
        if(getX() > x1) {
            square.setX(x1);
        }
        if(getY() > y1) {
            square.setY(y1);
        }
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.fillRect(getX(), getY(), square.getWidth(), square.getWidth());
        graphicsContext.strokeRect(getX(), getY(), square.getWidth(), square.getWidth());

    }

}
