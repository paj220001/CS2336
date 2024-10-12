public class Complex extends Number
{
    //set private members
    private double imaginaryNumber;

    //set overloaded constructor 
    public Complex(double real, double complex)
    {
        //call parent constructor to store real number
        super(real);

        //set imaginary number
        imaginaryNumber = complex;
    }

    //method to get the imaginary number
    public double getImaginary()
    {
        //return the imaginary number
        return imaginaryNumber;
    }

    //set imaginary number
    public void setImaginary(double value)
    {
        //set imaginary number to given value 
        imaginaryNumber = value;
    }

    //overridden comple number add function takes in number object
    @Override
    public String add(Number o)
    {
        //get the numbers 
        double realSum = this.getNum();
        double imagSum = this.imaginaryNumber;

        if (o instanceof Complex) //if number 2 is complex
        {
            //add both elements of the complex
            this.setNum(realSum += o.getNum());
            this.setImaginary(imagSum += ((Complex)o).getImaginary());

        } else if (o instanceof Number) //if the second number is real
        {
            //call real number add function
            o.add(this); 
        }

        //return the answer 
        return toString();
    }

    //overridden subtraction method takes in number object
    @Override
    public String sub(Number o)
    {
        //get the numbers 
        double realSum = this.getNum();
        double imagSum = this.imaginaryNumber;

        if (o instanceof Complex) //if number 2 is complex
        {
            //subtract both elements of the complex
            this.setNum(realSum -= o.getNum());
            this.setImaginary(imagSum -= ((Complex)o).getImaginary());
        } else if (o instanceof Number) //if the second number is real
        {
            //call real number sub function
            o.sub(this); 
        }

        //return answer 
        return toString();
    }

    //overridden multiplaction method takes in number object 
    @Override 
    public String mult(Number o)
    {
        if(o instanceof Complex)//if second number is complex
        {
            //get the numbers 
            double ogReal = this.getNum();
            double ogImag = this.imaginaryNumber;


            //use foil method
            this.setNum(ogReal * o.getNum() - ogImag * ((Complex) o).getImaginary());//calculate real number
            this.imaginaryNumber = ogReal * ((Complex) o).imaginaryNumber + ogImag * o.getNum();//calculate imaginary number
        }
        else//if second number is real 
        {
            //call the real number multiplication
            o.mult(this);
        }

        //return answer 
        return toString();
    }

    //overridden division method takes in number object 
    @Override
    public String div(Number o)
    {
        if(o instanceof Complex)//if second number is complex 
        {
            Complex c = (Complex) o;//create complex object 

            double denominator = c.getNum() * c.getNum() + c.getImaginary() * c.getImaginary();//find the denominator
            double real = (this.getNum() * c.getNum() + this.getImaginary() * c.getImaginary()) / denominator;//calculate the real number
            double imag = (this.getImaginary() * c.getNum() - this.getNum() * c.getImaginary()) / denominator;//calculate the imaginary number 

            this.setNum(real);//set the real number
            this.imaginaryNumber = imag;//set the imaginary number

            //return answer 
            return toString();

        }
        else//if its a real number
        {
            this.setNum(this.getNum() / o.getNum());//calculate the real number by dividing 
            this.imaginaryNumber = this.imaginaryNumber / o.getNum();//calculate imaginary number by dividing 
            
            //return answer
            return toString();
        }
    }

    //overridden compareTo method that takes in number 
    @Override
    public int compareTo(Number o)
    {
        if(o instanceof Complex)// if complex
        {
            //calculate magnitudes
            double magnitudeOrg, magnitudeCompare;
            magnitudeOrg = Math.sqrt((this.getNum() * this.getNum()) + (this.imaginaryNumber * this.imaginaryNumber));
            magnitudeCompare = Math.sqrt((o.getNum() * o.getNum()) + (((Complex) o).getImaginary() * ((Complex) o).getImaginary()));

            if(magnitudeOrg > magnitudeCompare)//if the first magnitude is bigger
            {
                //return positive number
                return 1;
            }
            else if(magnitudeOrg < magnitudeCompare)//if second magnitude is bigger
            {
                //return negative number 
                return -1;
            }
            else
            {
                //if magnitudes are equal
                return 0;
            }
        }
        else//if real
        {
            //call real compareTo method and return inverse
            return -o.compareTo(this);
        }
    }

    //overridden equal method 
    @Override 
    public boolean equals(Object o)
    {
        //create base boolean value 
        boolean value = false;
        if(o instanceof Complex)//if second number is complex
        {
            if(this.getNum() == ((Number)o).getNum())//make sure the real numbers are the same 
            {
                if(this.imaginaryNumber == ((Complex) o).getImaginary())// make sure imaginary numbers are the same too
                {
                    //if so thery are equal
                    value = true;
                }
            }
            else//if not they are not equal
            {
                //so false
                value = false;
            }
        }
        else // if real 
        {
            //call real equal method 
            value = ((Number)o).equals(this);
        }

        //return boolean value 
        return value;
    }

    //overridden complex toString 
    @Override
    public String toString()
    {
        //create variables 
        String line, imagine;

        //store and format imaginary
        imagine = String.format("%.2f", imaginaryNumber);

        //store real number 
        double real = Double.parseDouble(super.toString());

        if(real != 0.00)//if there is a real number 
            if(imaginaryNumber > 0)//if imaginary number is positive
            {
                //store complex number with +
                line = super.toString() + "+" + imagine + "i";
            }
            else//if imaginary is negative
            {
                //store complex number with -
                line = super.toString() + imagine + "i";
            }
        else //if there is no real number
        {
            //just get imaginary number
            line = imagine + "i";
        }

        //return line 
        return line;
    }

    
}
