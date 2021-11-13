// Вариант 7.
//  Описать класс, представляющий круг.
//  Предусмотреть методы:
//      1. создания объектов(см. к-тор Circle)
//      2. вычисления площади круга(см. Circle::getArea),
//      3. вычисления длины окружности(см. Circle::getPerimeter),
//      4. проверки попадания заданной точки внутрь (Circle::checkIsInside).
//          При непопадания точку внутрь круга выбрасывается исключение.

class MyPoint2D {
    private int _x;
    private int _y;

    public MyPoint2D(int x, int y) {
        _x = x;
        _y = y;
    }

    public int GetX() { return _x; }
    public int GetY() { return _y; }

    @Override
    public String toString() {
        return "MyPoint2D{ _x: " + _x +", _y:" + _y + " }";
    }
}

interface Figure {
    double getArea();
    double getPerimeter();
    void checkIsInside(MyPoint2D point) throws RuntimeException;
}

class Circle implements Figure {
    private MyPoint2D _center;
    private int _radius;

    public Circle(MyPoint2D center, int radius) {
        setRadius(radius);
        _center = center;
    }

    public void setRadius(int radius) {
        if(radius <= 0)
            throw new IllegalArgumentException("Illegal circle radius: " + radius);
        _radius = radius;
    }
    public int getRadius() {
        return _radius;
    }

    public double getArea() {
        return Math.PI * _radius * _radius;
    }
    public double getPerimeter() {
        return 2 * Math.PI * _radius;
    }

    public void checkIsInside(MyPoint2D point) {
        var dist = Math.sqrt(
            Math.pow((_center.GetX() - point.GetX()), 2)
            + Math.pow((_center.GetY() - point.GetY()), 2)
        );
        if(dist > _radius)
            throw new RuntimeException("Point (" + point + ") isn't inside circle.");
    }

    @Override
    public String toString() {
        return "Circle{"
                    + "\n\t_center: " +_center + ","
                    + "\n\t_radius: " + _radius
                + "\n}";
    }
}

public class Main {
    public static void printIsPointInside(Figure figure, MyPoint2D point) {
        boolean isPointInsideCircle = false;
        try {
            figure.checkIsInside(point);
            isPointInsideCircle = true;
        } catch (Exception e) { }
        System.out.println("Figure::checkIsInside(" + point + ") " + isPointInsideCircle);
    }

    public static void main(String[] args) {
        Figure circle = new Circle(new MyPoint2D(0, 0), 3);

        System.out.println(circle);
        System.out.println("Figure::getArea " + circle.getArea());
        System.out.println("Figure::getPerimeter " + circle.getPerimeter());

        printIsPointInside(circle, new MyPoint2D(1, 1));
        printIsPointInside(circle, new MyPoint2D(7, 2));

        System.out.println("\nTrying to create circle with negative radius.");
        try {
            new Circle(new MyPoint2D(0, 0), -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
