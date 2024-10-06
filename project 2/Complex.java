public class Complex extends Number// implements Comparable<Complex>
{
    private double imaginaryNumber;

    public Complex(double real, double complex)
    {
        super(real);
        imaginaryNumber = complex;
    }

    public double getImaginary()
    {
        return imaginaryNumber;
    }

    public void setImaginary(double value)
    {
        imaginaryNumber = value;
    }


    @Override
    public String add(Number o)
    {
        double realSum = this.getNum();
        double imagSum = this.imaginaryNumber;

        if (o instanceof Complex) {
            
            this.setNum(realSum += o.getNum());
            this.setImaginary(imagSum += ((Complex)o).getImaginary());
        } else if (o instanceof Number) {
            this.setNum(realSum += o.getNum());  
        }

        return toString();
    }

    @Override
    public String sub(Number o)
    {
        double realSum = this.getNum();
        double imagSum = this.imaginaryNumber;

        if (o instanceof Complex) {
            
            this.setNum(realSum -= o.getNum());
            this.setImaginary(imagSum -= ((Complex)o).getImaginary());
        } else if (o instanceof Number) {
            this.setNum(realSum -= o.getNum());  
        }

        return toString();
    }

    /*@Override
    public int compareTo(Complex o)
    {
        double magnitude;
    }*/

    @Override
    public String toString()
    {
        String line, imagine;

        imagine = String.format("%.2f", imaginaryNumber);
        double real = Double.parseDouble(super.toString());
        if(real > 0.00)
            if(imaginaryNumber > 0)
            {
                line = super.toString() + "+" + imagine + "i";
            }
            else
            {
                line = super.toString() + imagine + "i";
            }
        else
        {
            line = imagine + "i";
        }

        return line;
    }

    
}
