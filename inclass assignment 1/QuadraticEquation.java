//Pranav Joseph paj220001
//Ryan Ho RXH200021

public class QuadraticEquation {

    private int a, b, c;
    public QuadraticEquation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticEquation() {
        this(0, 0, 0);
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

    public double getDiscriminant() {
        return (b * b) - (4 * a * c);
    }

    public double getRoot1() {
        double discriminant = getDiscriminant();
        if (discriminant >= 0) {
            return (-b + Math.sqrt(discriminant)) / (2 * a);
        } else {
            return 0; // Discriminant is negative, so no real root
        }
    }

    public double getRoot2() {
        double discriminant = getDiscriminant();
        if (discriminant >= 0) {
            return (-b - Math.sqrt(discriminant)) / (2 * a);
        } else {
            return 0; // Discriminant is negative, so no real root
        }
    }
}

