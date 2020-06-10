
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import shapes.*;
import javax.imageio.ImageIO;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javafx.geometry.Point2D;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

public class Controller {
    private String mode, color;
    double x0, y0, x1, y1;
    public GraphicsContext gc;
    public ColorPicker cpLine = new ColorPicker(Color.BLACK);
    public ColorPicker cpFill = new ColorPicker(Color.BLACK);
    public Line line = new Line();
    public Rectangle rectangleObj = new Rectangle();
    public Circle circleObj = new Circle();
    public Ellipse ellipseObj = new Ellipse();

    @FXML
    private Canvas canvas;

    @FXML
    private MenuItem freeHand;
    @FXML
    private MenuItem straightLine;
    @FXML
    private MenuItem rectangle;
    @FXML
    private MenuItem square;
    @FXML
    private MenuItem circle;
    @FXML
    private MenuItem openPolygon;
    @FXML
    private MenuItem closedPolygon;
    @FXML
    private MenuItem red;
    @FXML
    private MenuItem yellow;
    @FXML
    private MenuItem orange;
    @FXML
    private MenuItem blue;
    @FXML
    private MenuItem green;
    @FXML
    private MenuItem black;
    @FXML
    private MenuItem clearTheCanvas;
    @FXML
    private MenuItem undoItem;
    @FXML
    private MenuItem redoItem;
    @FXML
    private MenuItem delete;
    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem loadItem;
    @FXML
    private MenuItem saveItem;

    public FreehandLine freehandLine;
    public StraightLine lineStraight;
    public RectangleClass rect;
    public EllipseShape ellipseShape;
    public SquareShape squareShape;
    public CircleShape circleShape;
    public OpenPolygon polygonOpen;
    public ClosedPolygon polygonClosed;
    Stack<ShapeClass>undo = new Stack<>();
    Stack<ShapeClass>redo = new Stack<>();
    public Point2D pointDelete;
    public Point2D pointCopy;
    public Point2D pointPaste;
    public Point2D movePt;
    @FXML
    void setModeEllipse(ActionEvent event) {
        mode = "ellipse";
    }

    @FXML
    void setModeOpenPolygon(ActionEvent event) {
        mode = "openPolygon";
    }

    @FXML
    void setModeClosedPolygon(ActionEvent event) {
        mode = "closedPolygon";
    }

    @FXML
    void setModeRectangle(ActionEvent event) {
        mode = "rectangle";
    }

    @FXML
    void setModeFreeHand(ActionEvent event) {
        mode = "freeHand";
    }

    @FXML
    void setModeSquare(ActionEvent event) {
        mode = "square";
    }

    @FXML
    void setModeStraighLine(ActionEvent event) {
        mode = "straightline";
    }

    @FXML
    void setModeCircle(ActionEvent event) {
        mode = "circle";
    }

    @FXML
    void setModeCopy(ActionEvent event) {
        mode = "copy";
    }

    @FXML
    void setModeDelete(ActionEvent event) {
        mode = "delete";
    }
    @FXML
    void setModeMove(ActionEvent event){
        mode = "move";
    }

    @FXML
    void clearCanvas(ActionEvent event) { //remove all shapes from drawing
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //undo.push(new EmptyShape());
        while ((!undo.isEmpty()) && (!redo.isEmpty()))
        {
            undo.pop();
            redo.pop();
        }
    }

    @FXML
    void setColorBlack(ActionEvent event) { //this and fcn below are for changing the shape colour
        color = "black";
        cpLine = new ColorPicker(Color.BLACK);
        cpFill = new ColorPicker(Color.BLACK);
    }

    @FXML
    void setColorBlue(ActionEvent event) {
        color = "blue";
        cpLine = new ColorPicker(Color.BLUE);
        cpFill = new ColorPicker(Color.BLUE);
    }

    @FXML
    void setColorRed(ActionEvent event) {
        color = "red";
        cpLine = new ColorPicker(Color.RED);
        cpFill = new ColorPicker(Color.RED);
    }

    @FXML
    void setColorOrange(ActionEvent event) {
        color = "orange";
        cpLine = new ColorPicker(Color.ORANGE);
        cpFill = new ColorPicker(Color.ORANGE);
    }

    @FXML
    void setColorYellow(ActionEvent event) {
        color = "yellow";
        cpLine = new ColorPicker(Color.YELLOW);
        cpFill = new ColorPicker(Color.YELLOW);
    }

    @FXML
    void setColorGreen(ActionEvent event) {
        color = "green";
        cpLine = new ColorPicker(Color.GREEN);
        cpFill = new ColorPicker(Color.GREEN);
    }

    @FXML
    void startDraw(MouseEvent event) { //this starts on mouse pressed
        gc = canvas.getGraphicsContext2D();
        x0 = event.getX();
        y0 = event.getY();
        switch (mode) {
            case "freeHand": {
                freeHandDraw(x0,y0,"start");
                break;
            }
            case "straightline": {
                straightLineDraw(x0,y0,"start");
                break;
            }
            case "rectangle": {
                rectangleDraw(x0,y0,"start");
                break;

            }
            case "circle": {
                circleDraw(x0,y0,"start");
                break;
            }

            case "square": {
                squareShape = new SquareShape();
                squareShape.setGraphicsContext(gc);
                squareShape.setColor(cpLine);
                squareShape.setFill(cpFill);
                squareShape.setStartPoint(x0, y0);
                gc.setStroke(cpLine.getValue());
                gc.setFill(cpFill.getValue());
                break;

            }
            case "ellipse": {
                ellipseDraw(event.getX(),event.getY(),"start");
                break;
            }
            case "openPolygon": {
                polygonOpenDraw(x0,y0,"start");
                break;

            }
            case "closedPolygon": {
                polygonCloseDraw(x0,y0,"start");
                break;

            }
            case "delete":{
                pointDelete = new Point2D(x0,y0);
                break;
            }
            case "copy":{
                pointCopy = new Point2D(x0,y0);
                break;
            }
            case "move":{
                movePt = new Point2D(x0,y0); //did not end up implementing
            }
        }


    }

    @FXML
    void drag(MouseEvent event) { //this is on mouse dragged
        switch (mode) {
            case "freeHand": {
                freeHandDraw(event.getX(),event.getY(),"drag");
                break;
            }

            case "openPolygon":{
                polygonOpenDraw(event.getX(),event.getY(),"drag");
                break;
            }
            case "closedPolygon":{
                polygonCloseDraw(event.getX(),event.getY(),"drag");
                break;
            }
        }

    }

    @FXML
    void endDraw(MouseEvent event) {

        x1 = event.getX();
        y1 = event.getY();
        switch (mode) {
            case "freeHand": {
                freeHandDraw(x1,y1,"end");
                break;
            }
            case "straightline": {
                straightLineDraw(x1,y1,"end");
                break;
            }
            case "rectangle": {
                rectangleDraw(x1,y1,"end");
                break;
            }
            case "circle": {
                circleDraw(event.getX(),event.getY(),"end");
                break;
            }

            case "square": {
                squareShape.setEndPoint(x1, y1);
                squareShape.draw();
                undo.push(squareShape);
                break;
            }
            case "ellipse": {
                ellipseDraw(x1,y1,"end");
                break;
            }
            case "delete":{
                delete(pointDelete);
                break;
            }
            case "copy":{
                pointPaste = new Point2D(x1,y1);
                paste(pointPaste,pointCopy);
                break;
            }

            case "openPolygon":{
                polygonOpenDraw(x1,y1,"end");
                break;
            }
            case "closedPolygon":{
                polygonCloseDraw(x1,y1,"end");
                break;
            }
            case "move":{
                //couldnt fingure out how to implement
            }


        }//endof switchcase




    }//end of end draw

    @FXML
    public void undo(ActionEvent event) //undo functionality
    {
        if(!(undo.isEmpty())) //shapes have been drawn
        {

            ShapeClass shapeRemoved = undo.pop();
            redo.push(shapeRemoved);
            //iterate through stack and redraw every shape except the one that has been popped
            //need to use a secondary stack
            gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
            Iterator iterator = undo.iterator();
            Stack<ShapeClass>temp = new Stack<>();

            while (iterator.hasNext()){
                temp.push((ShapeClass)iterator.next());
            }
            reDraw(temp);
        }
    }//end of undo

    @FXML
    public void redo(ActionEvent event){
        if(!redo.isEmpty()){ //same logic as undo but with a different stack

            ShapeClass x = redo.pop();
            undo.push(x);
            gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
            Iterator iterator = undo.iterator();
            Stack<ShapeClass>temp = new Stack<>();

            while (iterator.hasNext()){
                temp.push((ShapeClass)iterator.next());
            }
            reDraw(temp);


        }
    }

    public void delete(Point2D p){
        //find which shape encapsulates the point you clicked on and delete it
        //similar logic as undo and redo, we redraw all shapes on the canvas
        Stack<ShapeClass>stack = new Stack<>();

        Iterator<ShapeClass>iterator = undo.iterator();
        while(iterator.hasNext()){stack.push(iterator.next());}//add all drawn shapes to this stack
        //loop through and find which shape the point belongs to
        System.out.println("delete stack "+undo);
        while(!stack.isEmpty()){
            ShapeClass shape = stack.pop();
            System.out.println(shape.IsPointContained(p));
            if(shape.IsPointContained(p)){
                //delete the shape that has the point
                System.out.println(shape.getClass().getName());
                redo.push(shape);
                //delete it from the undo stack
                Iterator<ShapeClass>undoIterator = undo.iterator();
                while(undoIterator.hasNext()){
                    if(undoIterator.next() == redo.peek()){undoIterator.remove();break;}
                    else{continue;}
                }
                //now redraw all shapes except the one deleted
                gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                Stack<ShapeClass>shapeToDraw = new Stack<>();
                Iterator i = undo.iterator();
                while(i.hasNext())
                {
                    shapeToDraw.push((ShapeClass)i.next()); // add all items to temp stack to push
                }
                //loop through stack and redraw items
                reDraw(shapeToDraw);
                redo.pop();
                break;
            }
            else{continue;}
        }
    }



    public void reDraw(Stack<ShapeClass> temp) /*reusable function to redraw shapes that were not copied/deleted etc*/
    {
        while (!temp.isEmpty()){

            ShapeClass s = temp.pop();//redraw shape
            String className = s.getClass().getName();

            switch (className){ //switch case to draw the specific shape depending on the type of object
                case("shapes.FreehandLine"): {
                    FreehandLine newFreehand = (FreehandLine)s;
                    newFreehand.draw();
                    break;
                }
                case("shapes.StraightLine"): {
                    StraightLine newStraightLine = (StraightLine) s;
                    newStraightLine.draw();
                    break;
                }

                case("shapes.EllipseShape"): {
                    EllipseShape e  = (EllipseShape) s;
                    e.draw();
                    break;

                }
                case("shapes.RectangleClass"): {
                    RectangleClass r = (RectangleClass)s;
                    r.draw();
                    break;

                }
                case("shapes.SquareShape"): {
                    SquareShape sq = (SquareShape)s;
                    sq.draw();
                    break;
                }
                case("shapes.CircleShape"): {
                    CircleShape c = (CircleShape) s;
                    c.draw();
                    break;
                }

                case("shapes.ClosedPolygon"):{
                    ClosedPolygon c = (ClosedPolygon)s;
                    c.draw();
                    break;
                }
                case("shapes.OpenPolygon"):{
                    OpenPolygon o = (OpenPolygon)s;
                    o.draw();
                    break;
                }
            }

        }
    }//end redraw

    public void paste(Point2D paste,Point2D copy){

        Stack<ShapeClass> temp = new Stack<>();
        Iterator<ShapeClass>iterator = undo.iterator();
        while(iterator.hasNext()){temp.push(iterator.next());}//copy shapes to second stack
        //go through stack and find which point the shape belongs to

        while(!temp.isEmpty()){
            ShapeClass shape = temp.pop();
            if(shape.IsPointContained(copy))
            {
                String name = shape.getClass().getName();
                pasteFunction(name,shape,paste);//function to draw copied shape
            }
        }

    }

    @FXML
    public void SaveDrawing(ActionEvent event)
    {
        FileChooser save = new FileChooser();
        save.setTitle("savedFile");
        File fileSave = save.showSaveDialog(Main.stage);
        if (!(fileSave == null)) {
            try {
                WritableImage imgWritable = new WritableImage(1000, 1000);
                canvas.snapshot(null, imgWritable);
                RenderedImage imgRender = SwingFXUtils.fromFXImage(imgWritable, null);
                ImageIO.write(imgRender, "png", fileSave);
            } catch (IOException ex) { System.out.println(ex); }
        }
        else {//do nothing
        }
    }
    @FXML
    public void LoadDrawing(ActionEvent event)
    {
        FileChooser openFile = new FileChooser();
        openFile.setTitle("title");
        File fileOpen = openFile.showOpenDialog(Main.stage);
        if (!(fileOpen == null)) {
            try {
                InputStream stream = new FileInputStream(fileOpen);
                Image image = new Image(stream);
                canvas.getGraphicsContext2D().drawImage(image, 0, 0);
            } catch (IOException ex) { System.out.println(ex); }
        }
        else
        {
            //do nothing
        }

    }

    public void pasteFunction(String name, ShapeClass shape,Point2D paste)
    {
        switch (name){
            case("shapes.FreehandLine"):{

                FreehandLine oldFreeHand = (FreehandLine) shape;
                FreehandLine newFreeHand = new FreehandLine();
                gc.setStroke(oldFreeHand.getColor().getValue());

                newFreeHand.setGraphicsContext(gc);
                newFreeHand.setColor(oldFreeHand.getColor());
                newFreeHand.setStartCoordinates(paste.getX(),paste.getY());

                //get all the points in the original sketch and shift them then redraw
                double [] origX = oldFreeHand.getXVals();
                double [] origY = oldFreeHand.getYvals();
                int i=0;
                while (i < origX.length)
                {
                    newFreeHand.addCoordinates(origX[i] + (paste.getX() - oldFreeHand.getStartX()), origY[i] + (paste.getY() - oldFreeHand.getStartY()));
                    i++;
                }
                newFreeHand.setEndCoordinates(oldFreeHand.getEndX()+(paste.getX() - oldFreeHand.getStartX()),oldFreeHand.getEndY()+(paste.getY() - oldFreeHand.getStartY()));
                newFreeHand.draw();
                undo.push(newFreeHand);
                break;
            }
            case("shapes.StraightLine"):{
                StraightLine oldLine = (StraightLine)shape;
                StraightLine newLine = new StraightLine();
                newLine.setGraphicsContext(gc);
                newLine.setColor(oldLine.getColor());
                newLine.setStart(paste.getX(),paste.getY());
                newLine.setEnd(oldLine.getEndX()+(paste.getX() - oldLine.getStartX()),oldLine.getEndY()+(paste.getY()-oldLine.getStartY()));
                newLine.draw();
                undo.push(newLine);
                break;
            }
            case("shapes.EllipseShape"):{
                System.out.println("copying ellipse");
                EllipseShape oldEllipse = (EllipseShape)shape;
                EllipseShape newEllipse = new EllipseShape();
                newEllipse.setGraphicsContext(gc);
                newEllipse.setColor(oldEllipse.getStroke());
                newEllipse.setFill(oldEllipse.getFill());
                newEllipse.setCenterPoint(paste.getX(),paste.getY());
                newEllipse.setEndPoint(newEllipse.getCenterX()+oldEllipse.getRadiusX(),newEllipse.getCenterY()+oldEllipse.getRadiusY());
                newEllipse.draw();
                undo.push(newEllipse);
                break;

            }
            case("shapes.RectangleClass"):{
                System.out.println("copying rectangle");
                RectangleClass oldRect = (RectangleClass)shape;
                RectangleClass newRect = new RectangleClass();
                newRect.setGraphicsContext(gc);
                newRect.setFill(oldRect.getFill());
                newRect.setColor(oldRect.getStroke());
                newRect.setStartPoint(paste.getX(),paste.getY());
                newRect.setEndPoint(paste.getX() + oldRect.getWidth(),paste.getY()+oldRect.getHeight());
                newRect.draw();
                undo.push(newRect);
                break;

            }
            case("shapes.SquareShape"):{
                SquareShape oldSquare = (SquareShape)shape;
                SquareShape newSquare = new SquareShape();
                newSquare.setGraphicsContext(gc);
                newSquare.setFill(oldSquare.getFill());
                newSquare.setColor(oldSquare.getStroke());
                newSquare.setStartPoint(paste.getX(),paste.getY());
                newSquare.setEndPoint(paste.getX()+oldSquare.getWidth(),paste.getY()+oldSquare.getHeight());
                newSquare.draw();
                undo.push(newSquare);
                break;

            }
            case("shapes.CircleShape"):{
                System.out.println("copying circle");
                CircleShape oldCircle = (CircleShape)shape;
                CircleShape newCircle = new CircleShape();
                gc.setStroke(oldCircle.getStroke().getValue());
                gc.setFill(oldCircle.getFill().getValue());
                newCircle.setGraphicsContext(gc);
                newCircle.setColor(oldCircle.getStroke());
                newCircle.setFill(oldCircle.getFill());
                newCircle.setCenterPoint(paste.getX(),paste.getY());
                newCircle.setRadius(oldCircle.getRadius());
                newCircle.setEndPoint(newCircle.getCenterX()+oldCircle.getRadius(),newCircle.getCenterY()+oldCircle.getRadius());
                newCircle.draw();
                undo.push(newCircle);
                break;
            }
            case("shapes.OpenPolygon"):{
                OpenPolygon oldPolygon = (OpenPolygon)shape;
                OpenPolygon newPolygon = new OpenPolygon();
                gc.setStroke(oldPolygon.getCpLine().getValue());
                newPolygon.setGc(gc);
                newPolygon.setCpFill(oldPolygon.getCpFill());
                newPolygon.setCpLine(oldPolygon.getCpLine());
                ArrayList<Double> polygonX = oldPolygon.getXValues();
                ArrayList<Double> polygonY = oldPolygon.getYValues();
                int i=0;
                while (i < oldPolygon.getXValues().size()){
                    newPolygon.addPoint(polygonX.get(i)+(paste.getX()-polygonX.get(0)),polygonY.get(i)+(paste.getY()-polygonY.get(0)));
                    i++;
                }
                newPolygon.draw();
                undo.push(newPolygon);
                break;
            }
            case("shapes.ClosedPolygon"):{
                ClosedPolygon oldPolygon = (ClosedPolygon) shape;
                ClosedPolygon newPolygon = new ClosedPolygon();
                gc.setStroke(oldPolygon.getCpLine().getValue());
                newPolygon.setGc(gc);
                newPolygon.setCpFill(oldPolygon.getCpFill());
                newPolygon.setCpLine(oldPolygon.getCpLine());
                ArrayList<Double> polygonX = oldPolygon.getxValues();
                ArrayList<Double> polygonY = oldPolygon.getyValues();
                int i=0;
                while (i < oldPolygon.getxValues().size()){
                    newPolygon.addPoint(polygonX.get(i)+(paste.getX()-polygonX.get(0)),polygonY.get(i)+(paste.getY()-polygonY.get(0)));
                    i++;
                }
                newPolygon.draw();
                undo.push(newPolygon);
                break;
            }
        }
    }




    public void freeHandDraw(double x, double y, String state)
    {

        if (state == "start")
        {
            freehandLine = new FreehandLine();
            gc.setStroke(cpLine.getValue());
            gc.beginPath();
            gc.lineTo(x, y);
            freehandLine.setStartCoordinates(x, y);
            freehandLine.setGraphicsContext(gc);
            freehandLine.setColor(cpLine);
        }
        else if (state == "drag")
        {
            gc.lineTo(x, y);
            gc.stroke();
            freehandLine.addCoordinates(x, y);
        }
        else//end draw state
        {
            freehandLine.setEndCoordinates(x1, y1);
            gc.lineTo(x1, y1);
            gc.stroke();
            gc.closePath();
            undo.push(freehandLine);
        }
    }
    public void straightLineDraw(double x, double y, String state)
    {
        if (state == "start")
        {
            gc.setStroke(cpLine.getValue());
            lineStraight = new StraightLine();
            lineStraight.setStart(x, y);
            lineStraight.setGraphicsContext(gc);
            lineStraight.setColor(cpLine);
        }
        else{//end draw
            lineStraight.setEnd(x1, y1);
            lineStraight.draw();
            undo.push(lineStraight);
        }
    }
    public void rectangleDraw(double x, double y, String state)
    {
        if(state == "start")
        {
            rect = new RectangleClass();
            gc.setStroke(cpLine.getValue());
            gc.setFill(cpFill.getValue());
            rect.setStartPoint(x, y);
            rect.setGraphicsContext(gc);
            rect.setFill(cpFill);
            rect.setColor(cpLine);
        }
        else{//end draw
            rect.setEndPoint(x1, y1);
            rect.draw();
            undo.push(rect);
        }
    }
    public void circleDraw(double x, double y, String state)
    {
        if(state == "start") {
            gc.setStroke(cpLine.getValue());
            gc.setFill(cpFill.getValue());
            circleShape = new CircleShape();
            circleShape.setGraphicsContext(gc);
            circleShape.setColor(cpLine);
            circleShape.setFill(cpFill);
            circleShape.setCenterPoint(x, y);
        }
        else{//end draw
            circleShape.setEndPoint(x, y);
            circleShape.draw();
            undo.push(circleShape);
        }
    }
    public void ellipseDraw(double x, double y, String state)
    {
        if(state == "start")
        {
            gc.setStroke(cpLine.getValue());
            gc.setFill(cpFill.getValue());
            ellipseShape = new EllipseShape();
            ellipseShape.setGraphicsContext(gc);
            ellipseShape.setColor(cpLine);
            ellipseShape.setFill(cpFill);
            ellipseShape.setCenterPoint(x, y);
        }
        else
        {
            ellipseShape.setEndPoint(x, y);
            ellipseShape.draw();
            undo.push(ellipseShape);
        }
    }
    public void polygonOpenDraw(double x, double y, String state)
    {
        if (state == "start")
        {
            gc.setStroke(cpLine.getValue());
            gc.setFill(cpFill.getValue());
            polygonOpen = new OpenPolygon();
            polygonOpen.setGc(gc);
            polygonOpen.setCpFill(cpFill);
            polygonOpen.setCpLine(cpLine);
            polygonOpen.addPoint(x,y);
        }
        else if (state == "drag")
        {
            polygonOpen.addPoint(x,y);
            try{
                Thread.sleep(250);
            }
            catch(Exception e){}
        }
        else
        {
            polygonOpen.addPoint(x,y);
            polygonOpen.draw();
            undo.push(polygonOpen);
        }
    }
    public void polygonCloseDraw(double x, double y, String state)
    {
        if (state == "start")
        {
            gc.setStroke(cpLine.getValue());
            gc.setFill(cpFill.getValue());
            polygonClosed = new ClosedPolygon();
            polygonClosed.setGc(gc);
            polygonClosed.setCpFill(cpFill);
            polygonClosed.setCpLine(cpLine);
            polygonClosed.addPoint(x,y);
        }
        else if (state == "drag")
        {
            polygonClosed.addPoint(x,y);
            try{
                Thread.sleep(250);
            }
            catch(Exception e){System.out.println(e);}
        }
        else
        {
            polygonClosed.addPoint(x,y);
            polygonClosed.draw();
            undo.push(polygonClosed);
        }
    }
}


