// Pranav Joseph paj220001
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <math.h>

using namespace std;

const int MAX_DRIVERS = 20;
const int MAX_COORDINATES = 16;

const int THIRD_DIMENSION = 2;

void getCoordinates(int, double[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION], string[MAX_DRIVERS], int [MAX_DRIVERS]);
double area (double[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION], int, int [MAX_DRIVERS]);
void readFile(string[MAX_DRIVERS], double[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION], ifstream&, int&, int [MAX_DRIVERS]);

int main()
{
    ifstream inputFile;
    string inputFileName, outputFileName, drivers[MAX_DRIVERS];
    double coordinateArray[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION];
    double areaOFP[MAX_DRIVERS];
    int counter, points[MAX_DRIVERS];

    cout << "Please enter the filename of driver data: ";
    cin >> inputFileName;

    inputFile.open(inputFileName);

    if(inputFile)
    {
        readFile(drivers, coordinateArray, inputFile, counter, points);
        
        for(int x = 0; x < counter; x++)
        {
            areaOFP[x] = area(coordinateArray, x, points);
            cout << fixed << setprecision(2);
            cout << drivers[x] << "\t" << areaOFP[x] << endl;
        }

    }
    else
    {
        cout << inputFileName << " could not be opened.\n";
    }
}


void getCoordinates(int counter, double coordinateArray[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION], string line[MAX_DRIVERS], int point[MAX_DRIVERS])
{
    int index;
    string term;
    
    for(int count = 0; count <= counter; count++)
    {
        int i = 0;
        point[count] = 0;
        while(line[count].length() > 0)
        {
            index = line[count].find(' ');
            if(index != -1)
            {
                term = line[count].substr(0, index);
                line[count] = line[count].substr(index + 1);
            }
            else 
            {
                term = line[count];
                line[count] = "";

            }

            index = term.find(',');

            coordinateArray[count][i][0] = stod(term.substr(0, index));
            coordinateArray[count][i][1] = stod(term.substr(index + 1));

            i++;
            point[count]++;
        }
        
    }
}

void readFile(string drivers[MAX_DRIVERS], double coordinateArray[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION], ifstream& inputFile, int& counter, int points[MAX_DRIVERS])
{
    string line[MAX_DRIVERS]; 
    counter = 0;
    while(getline(inputFile, line[counter]))
    {
        if(line[counter] == "")
            break;

        int index = line[counter].find(' ');

        drivers[counter] = line[counter].substr(0, index); 
        line[counter] = line[counter].substr(index + 1);
        counter++;
       
    }

     getCoordinates(counter, coordinateArray, line, points);
}

double area (double coordinateArray[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION], int counter, int points[MAX_DRIVERS])
{
    double sum;
    sum = (coordinateArray[counter][1][0] + coordinateArray[counter][0][0]) * (coordinateArray[counter][1][1] - coordinateArray[counter][0][1]);
    for(int i = 1; i < points[counter] - 1; i++)
    {
        sum += (coordinateArray[counter][i + 1][0] + coordinateArray[counter][i][0]) * (coordinateArray[counter][i + 1][1] - coordinateArray[counter][i][1]);
    }

    return .5 * abs(sum);
}