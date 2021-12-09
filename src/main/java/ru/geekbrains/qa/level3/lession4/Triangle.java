package ru.geekbrains.qa.level3.lession4;

public class Triangle {
    int a;
    int b;
    int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }


    public double triangleSquare() throws Exception {
        if (!isTriangleExist()) {
            throw new TriangleNotExistException();
        }
        double p = (a+b+c)/2.0;
        if ( p/2 == a | p/2 == b | p/2 == c ) return 0;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public boolean isTriangleExist() {
        if((a+b) < c) {
            return false;
        } else if((b+c) < a) {
            return false;
        } else return (c + a) >= b;
    }

}
