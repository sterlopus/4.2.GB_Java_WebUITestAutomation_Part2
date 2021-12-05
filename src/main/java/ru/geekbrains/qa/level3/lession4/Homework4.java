package ru.geekbrains.qa.level3.lession4;

public class Homework4 {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(1,2,-2);

        try {
            System.out.println("Triangle's square is: " + triangle.triangleSquare());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * 1. Напишите функцию, вычисляющую площадь треугольника по трём сторонам (int a, int b, int c).
 *      Разместите класс с функцией в src/main/java.
 * 2. Разместите тесты на эту функцию в классе src/test/java/.../TriangleTest.java.
 * 3. Настройте генерацию отчёта и по желанию — логирование.
 */