public class Number implements Comparable<Number>
{
    //set private members
    private double num;

    //set overloaded constructor 
    public Number(double number)
    {
        //set num
        this.num = number;
    }

    //method to getnum
    public double getNum()
    {
        //retunr num
        return num;
    }

    //method to change num
    public void setNum(double num)
    {
        //set num to given value
        this.num = num;
    }

    //real number add function takes in number function
    public String add(Number o)
    {
        //set variables
        String answer = "";
        double value;
        if(o instanceof Complex)//if the second number is complex
        {
            Complex c = (Complex) o;//create complex object
            c.setNum(this.num + c.getNum());  // Add real parts
            answer = c.toString();  // Real number + imaginary part
        }
        else//if second num is real
        {
            //add the 2 number and store it into num
            num += ((Number)o).getNum();

            //store answer
            answer = toString();
        }

        //return the answer 
        return answer;
    }

    //subtraction method takes in number object
    public String sub(Number o)
    {
        //set variables 
        String answer = "";
        double value;

        if(o instanceof Complex)//if second number is complex
        {
            Complex c = (Complex) o;//create complex object
            c.setNum(this.num - c.getNum());  // subtract real parts
            answer = c.toString();  // Real number + imaginary part
            
        }
        else//if number is real
        {
            //subtract both numbers and store it
            num -= ((Number)o).getNum();
            //store answer
            answer = toString();
        }

        //return answer 
        return answer;
    }

    //multiplaction method takes in number
    public String mult(Number o)
    {
        //set variables 
        String answer = "";
        double value;

        if(o instanceof Complex)//if second number is complex
        {
            Complex c = (Complex) o; //create complex object
            c.setNum(this.num * c.getNum());  // mutiply real parts
            c.setImaginary(this.num * c.getImaginary());//multiply imaginary parts
            answer = c.toString();  // Real number + imaginary part
        }
        else//if its a real number
        {
            ((Number)o).setNum(this.num * ((Number)o).getNum());//multiply both numbers
            //store answer
            answer = o.toString();
        }

        //return answer 
        return answer;
    }

    //division method takes in number object 
    public String div(Number o)
    {
        if(o instanceof Complex)// if second number is complex 
        {
            Complex c = (Complex) o;//create complex object
            double denominator = c.getNum() * c.getNum() + c.getImaginary() * c.getImaginary();//get the denominator
            double real = (this.num * c.getNum()) / denominator;//calculate real number
            double imag = (-this.num * c.getImaginary()) / denominator;//calculate imaginary number

            //set the imaginary and real numbers
            c.setNum(real);
            c.setImaginary(imag);

            //return string of complex
            return c.toString();
        }
        else//divide real numbers
        {
            //divide and change value
            this.num = this.num / o.getNum();

            //return string 
            return toString();
        }
    }

    //overridden compare to method from comparable interface
    @Override
    public int compareTo(Number o)
    {
        if(o instanceof Complex)// if the second number is complex 
        {
            //calculate the magnitude
            double magnitudeCompare = Math.sqrt((o.getNum() * o.getNum()) + ((Complex) o).getImaginary() * ((Complex) o).getImaginary());

            //if the first number is greater than the second 
            if(Math.abs(this.num) > magnitudeCompare)
            {
                //return positive number
                return 1;
            }
            else if(Math.abs(this.num) < magnitudeCompare)//if the second number is greater than first
            {
                //return negative number
                return -1;
            }
            else//they are equal
            {
                //return 0
                return 0;
            }
        }
        else//if second number is real 
        {
            //if the first num is greater 
            if(this.num > o.getNum())
            {
                //return positve
                return 1;
            }
            else if(this.num < o.getNum())//if second number is greater
            {
                //return negative number
                return -1;
            }
            else// they are equal
            {
                //return 0
                return 0;
            }
        }
    }

    //overridden equals method takes in object 
    @Override
    public boolean equals(Object o)
    {
        //set inital truth value 
        boolean value = false;
        if(o instanceof Complex)//if it is complex 
        {
            //make sure there is no imaginary number
            if(((Complex) o).getImaginary() == 0)
            {
                if(Math.abs(this.num) == Math.abs(((Number) o).getNum()))//if the real number elements are the same
                {
                    //means it is equal
                    value = true;
                }
            }
        }
        else//if its a real number
        {
            if(this.num == ((Number) o).getNum())//if they are equal
            {
                //it is true 
                value = true;
            }
        }

        //return truth value
        return value;
    }

    //overridden toString 
    @Override
    public String toString()
    {
        //return formated string of real number 
        return String.format("%.2f", num);
    }
}
