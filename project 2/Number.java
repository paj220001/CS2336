public class Number implements Comparable<Number>
{
    private double num;

    public Number(double number)
    {
        this.num = number;
    }

    public double getNum()
    {
        return num;
    }

    public void setNum(double num)
    {
        this.num = num;
    }

    public String add(Number o)
    {
        String answer = "";
        double value;
        if(o instanceof Complex)
        {
            Complex c = (Complex) o;
            c.setNum(this.num + c.getNum());  // Add real parts
            answer = c.toString();  // Real number + imaginary part
        }
        else
        {
            num += ((Number)o).getNum();
            answer = toString();
        }

        return answer;
    }

    public String sub(Number o)
    {
        String answer = "";
        double value;
        if(o instanceof Complex)
        {
            Complex c = (Complex) o;
            c.setNum(this.num - c.getNum());  // Add real parts
            answer = c.toString();  // Real number + imaginary part
            
        }
        else
        {
            num -= ((Number)o).getNum();
            answer = toString();
        }

        return answer;
    }

    public String mult(Number o)
    {
        String answer = "";
        double value;
        if(o instanceof Complex)
        {
            Complex c = (Complex) o;
            c.setNum(this.num * c.getNum());  // Add real parts
            c.setImaginary(this.num * c.getImaginary());
            answer = c.toString();  // Real number + imaginary part
        }
        else
        {
             answer = Double.toString(this.num * ((Number)o).getNum());
        }

        return answer;
    }

    public String div(Number o)
    {
        if(o instanceof Complex)
        {
            Complex c = (Complex) o;
            double denominator = c.getNum() * c.getNum() + c.getImaginary() * c.getImaginary();
            double real = (this.num * c.getNum()) / denominator;
            double imag = (-this.num * c.getImaginary()) / denominator;

            c.setNum(real);
            c.setImaginary(imag);
            return c.toString();
        }
        else
        {
            this.num = this.num / o.getNum();
            return toString();
        }
    }

    @Override
    public int compareTo(Number o)
    {
        if(o instanceof Complex)
        {
            double magnitudeCompare = Math.sqrt((o.getNum() * o.getNum()) + ((Complex) o).getImaginary() * ((Complex) o).getImaginary());
            if(Math.abs(this.num) > magnitudeCompare)
            {
                return 1;
            }
            else if(Math.abs(this.num) < magnitudeCompare)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if(this.num > o.getNum())
            {
                return 1;
            }
            else if(this.num < o.getNum())
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object o)
    {
        boolean value = false;
        if(o instanceof Complex)
        {
            if(((Complex) o).getImaginary() == 0)
            {
                double magnitudeCompare = Math.sqrt(((Number) o).getNum() * ((Number) o).getNum());
                if(Math.abs(this.num) == magnitudeCompare)
                {
                    value = true;
                }
            }
        }
        else
        {
            if(this.num == ((Number) o).getNum())
            {
                value = true;
            }
        }

        return value;
    }
    
    @Override
    public String toString()
    {
       return String.format("%.2f", num);
    }
}
