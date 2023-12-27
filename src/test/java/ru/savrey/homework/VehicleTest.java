package ru.savrey.homework;

/**
 * Проект Vehicle.
 * В этом проекте, вы будете работать с проектом ""Vehicle"", который представляет собой иерархию классов,
 * включающую абстрактный базовый класс ""Vehicle"" и два его подкласса ""Car"" и ""Motorcycle"".
 * Базовый класс ""Vehicle"" содержит абстрактные методы ""testDrive()"" и ""park()"", а также поля
 * ""company"", ""model"", ""yearRelease"", ""numWheels"" и ""speed"".
 * Класс ""Car"" расширяет ""Vehicle"" и реализует его абстрактные методы.
 * При создании объекта ""Car"", число колес устанавливается в 4, а скорость в 0.
 * В методе ""testDrive()"" скорость устанавливается на 60, а в методе ""park()"" - обратно в 0.
 * Класс ""Motorcycle"" также расширяет ""Vehicle"" и реализует его абстрактные методы.
 * При создании объекта ""Motorcycle"", число колес устанавливается в 2, а скорость в 0.
 * В методе ""testDrive()"" скорость устанавливается на 75, а в методе ""park()"" - обратно в 0.
 */
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class VehicleTest {
    Car car;
    Motorcycle motorcycle;

    @BeforeEach
    public void setup() {
        car = new Car("Hyundai", "Sonata", 2023);
        motorcycle = new Motorcycle("Ducati", "Panigale", 2020);
    }

    /**
     * Проверить, что экземпляр объекта Car также является экземпляром транспортного средства
     *  * (используя оператор instanceof).
     */
    @Test
    void carInstanceOfVehicle() {
        assertThat(car).isInstanceOf(Vehicle.class);
    }

    /**
     * Проверить, что объект Car создается с 4-мя колесами.
     */
    @Test
    void carNumberOfWheels() {
        assertEquals(4, car.getNumWheels());
    }

    /**
     * Проверить, что объект Motorcycle создается с 2-мя колесами.
     */
    @Test
    void motorcycleNumberOfWheels() {
        assertEquals(2, motorcycle.getNumWheels());
    }

    /**
     * Проверить, что объект Car развивает скорость 60
     * в режиме тестового вождения (используя метод testDrive()).
     */
    @Test
    void carTestDriveSpeed() {
        car.testDrive();
        assertEquals(60, car.getSpeed());
    }

    /**
     * Проверить, что объект Motorcycle развивает скорость 75 в режиме тестового вождения
     *  * (используя метод testDrive()).
     */
    @Test
    void motorcycleTestDriveSpeed() {
        motorcycle.testDrive();
        assertEquals(75, motorcycle.getSpeed());
    }

    /**
     * Проверить, что в режиме парковки (сначала testDrive, потом park, т.е. эмуляция движения транспорта)
     * машина останавливается (speed = 0).
     */
    @Test
    void carParkSpeed() {
        car.testDrive();
        car.park();
        assertEquals(0, car.getSpeed());
    }

    /**
     * Проверить, что в режиме парковки (сначала testDrive, потом park, т.е. эмуляция движения транспорта)
     *  * мотоцикл останавливается (speed = 0).
     */
    @Test
    void motorcycleParkSpeed() {
        motorcycle.testDrive();
        motorcycle.park();
        assertEquals(0, motorcycle.getSpeed());
    }


}
