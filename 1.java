import java.util.Arrays;

// Вариант 7.
//   Создать классы колесо, велосипед и автомобиль. Составить из них иерархию или композицию.
//   В случае недопустимых значений полей выбрасываются исключения.
//   Написать программу, демонстрирующую все разработанные элементы класса.

class Wheel {
    private int _radius;

    public Wheel(int radius) {
        if(radius <= 0)
            throw new IllegalArgumentException("Illegal wheel radius: " + radius);
        _radius = radius;
    }

    public int getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Wheel{ _radius: " + getRadius() +" }";
    }
}

interface Vehicle {
    double getSpeed();
}

abstract class WheelBasedVehicle implements Vehicle {
    private Wheel[] _wheels;
    private double _speed;
    public WheelBasedVehicle(double speedMultiplier, Wheel[] wheels) {
        if(wheels == null)
            throw new IllegalArgumentException("Illegal wheels array value: " + wheels);
        _wheels = wheels;
        _speed = wheels.length * speedMultiplier;
    }

    public Wheel[] getWheels() {
        return _wheels;
    }

    @Override
    public double getSpeed() {
        return _speed;
    }

    @Override
    public String toString() {
        return new String(
            "WheelBasedVehicle{ "
                    + "_speed: " + _speed
                    +", _wheels: " + Arrays.toString(_wheels)
                    + " }"
        );
    }
}

class Bike extends WheelBasedVehicle {
    public Bike(int wheelRadius) {
        super(7.5,
            new Wheel[]{
                new Wheel(wheelRadius),
                new Wheel(wheelRadius)
            }
        );
    }

    @Override
    public String toString() {
        return new String(
                "Bike{\n\t"
                        + super.toString()
                + "\n}"
        );
    }
}
class Car extends WheelBasedVehicle {
    private String _modelName;

    public Car(String modelName, int wheelRadius) {
        super(20,
            new Wheel[]{
                new Wheel(wheelRadius),
                new Wheel(wheelRadius),
                new Wheel(wheelRadius),
                new Wheel(wheelRadius)
            }
        );

        if(modelName == null || modelName.isEmpty())
            throw new IllegalArgumentException("Illegal car model name: " + modelName);
        _modelName = modelName;
    }

    public String getModelName()
    {
        return _modelName;
    }

    @Override
    public String toString() {
        return new String(
            "Car{\n\t"
                + super.toString()
                + ",\n\t_modelName: \"" + _modelName + "\""
            + "\n}"
        );
    }
}

public class Main {
    public static void main(String[] args) {
        var wheel = new Wheel(1);
        WheelBasedVehicle bike = new Bike(6);
        WheelBasedVehicle car = new Car("Audi", 3);

        System.out.println(wheel);
        System.out.println(bike);
        System.out.println(car);

        try {
            // wrong wheel radius
            new Bike(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // wrong car model name
            new Car(null, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
