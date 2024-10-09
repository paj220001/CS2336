public class Complex extends Number
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
            o.add(this); 
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
            o.sub(this); 
        }

        return toString();
    }

    @Override 
    public String mult(Number o)
    {
        if(o instanceof Complex)
        {
            double ogReal = this.getNum();
            double ogImag = this.imaginaryNumber;
            this.setNum(ogReal * o.getNum() - ogImag * ((Complex) o).getImaginary());
            this.imaginaryNumber = ogReal * ((Complex) o).imaginaryNumber + ogImag * o.getNum();
        }
        else
        {
            o.mult(this);
        }

        return toString();
    }

    @Override
    public String div(Number o)
    {
        if(o instanceof Complex)
        {
            Complex c = (Complex) o;

            double denominator = c.getNum() * c.getNum() + c.getImaginary() * c.getImaginary();
            double real = (this.getNum() * c.getNum() + this.getImaginary() * c.getImaginary()) / denominator;
            double imag = (this.getImaginary() * c.getNum() - this.getNum() * c.getImaginary()) / denominator;

            this.setNum(real);
            this.imaginaryNumber = imag;

            return toString();

        }
        else
        {
            this.setNum(this.getNum() / o.getNum());
            this.imaginaryNumber = this.imaginaryNumber / o.getNum();
            return toString();
        }
    }

    /*@Override
    public int compareTo(Number o)
    {
        
        double magnitudeOrg, magnitudeCompare;
        magnitudeOrg = Math.sqrt((this.getNum() * this.getNum()) + (this.imaginaryNumber * this.imaginaryNumber));
        magnitudeCompare = Math.sqrt((o.getNum() * o.getNum()) + (o.getImaginary() * o.getImaginary()));

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
