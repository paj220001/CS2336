import java.util.*;
public class Main
{
    static final double LOAD_FACTOR = 0.5;
    public static void main(String [] args)
    {
        int [] array = new int[11];
        Scanner scnr = new Scanner(System.in);
        System.out.print("How many values will be entered: ");
        int size = scnr.nextInt();
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            System.out.print("Number: ");
            int key = scnr.nextInt();
            hash(array, key);
            count++;
            if((double)count / array.length >= LOAD_FACTOR)
            {
                System.out.println("Resizing");
                array = hashResize(array, array.length);
            }
        }

        print(array);
        scnr.close();
    }

    public static void hash(int [] array, int data)
    {
        int size = array.length, i = 0, bucketsProbed = 0;
        int hash = data % size;
        
        while(bucketsProbed < size)
        {
            if(array[hash] < 100 || array[hash] > 999)
            {
                array[hash] = data;
                return;
            }

            i = i + 1;
            hash = (data + i + i * i) % size;
            bucketsProbed++;
        }

    }

    public static int[] hashResize(int [] array, int currentSize)
    {
        int newSize = nextPrime(currentSize * 2);
        int[] newArray = new int[newSize];

        int bucket = 0;
        while(bucket < currentSize)
        {
            if(array[bucket] >= 100 && array[bucket] <= 999)
            {
                int key = array[bucket];
                hash(newArray, key);

            }

            bucket++;
        }

        return newArray;
        
    }

    public static int nextPrime(int size)
    {
        boolean prime = true;
        int i;
        for(i = size; i < 2 * size; ++i)
        {
            for(int j = 2; j < i; j++)
            {
                if(i % j == 0)
                {
                    prime = false;
                    break;
                }
            }

            if(prime == true)
            {
                break;
            }
            else
            {
                prime = true;
            }
        }

        return i;
    }

    public static void print(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] >= 100 && array[i] <= 999)
            {
                System.out.print(array[i] + " ");
            }
            else
            {
                System.out.print("_ ");
            }
        }
    }
}
