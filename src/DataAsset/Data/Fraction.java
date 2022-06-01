package DataAsset.Data;

import DataAsset.Extended.ExtendedMath;

public class Fraction {
    private int numerator;
    private int denominator;
    private boolean autoReduce = true;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(String fracStr) {
        String[] num = fracStr.split("/");
        this.numerator = Integer.parseInt(num[0]);
        this.denominator = Integer.parseInt(num[1]);
    }

    public void setAutoReduce(boolean autoReduce) {
        this.autoReduce = autoReduce;
    }

    public double getDecimal() {
        return 1.0 * numerator / denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String toString() {
        if (numerator >= 0 && denominator >= 0) {
            return numerator + "/" + denominator;
        } else if (numerator < 0 && denominator < 0) {
            return numerator * -1 + "/" + denominator * -1;
        } else if (numerator < 0) {
            return numerator + "/" + denominator;
        } else {
            return "-" + numerator + "/" + denominator * -1;
        }
    }

    public void add(Fraction f) {
        int lcm = ExtendedMath.getLeastCommonMultiple(this.denominator, f.getDenominator());
        this.numerator = this.numerator
                * (lcm / this.denominator)
                + f.getNumerator() * (lcm / f.denominator);
        this.denominator = lcm;
        //automatically reduce the current fraction
        if (autoReduce) reduce();
    }

    public void subtract(Fraction f) {
        Fraction tmp = new Fraction(f.toString());
        tmp.numerator = -tmp.numerator;
        add(f);
    }

    public void multiplyBy(Fraction f) {
        this.numerator *= f.numerator;
        this.denominator *= f.denominator;
        if (autoReduce) reduce();
    }

    public void divideBy(Fraction f) {
        Fraction tmp = new Fraction(f.toString());
        int t = tmp.denominator;
        tmp.denominator = tmp.numerator;
        tmp.numerator = t;
        multiplyBy(tmp);
    }

    public void reduce() {
        int gcd = ExtendedMath.getGreatestCommonDivisor(Math.abs(numerator), Math.abs(denominator));
        this.numerator /= gcd;
        this.denominator /= gcd;
    }
}

class Test {
    public static void main(String[] args) {
        Fraction f = new Fraction("1/109");
        f.add(new Fraction("1/699"));
        f.multiplyBy(new Fraction("3/1"));
        System.out.println(f);
    }
}