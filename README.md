# JAVA-Gas-Station-Simulator
Simulates a gas station

This is a program to simulate the behavior of a self-serve gas station. 
The goal of this simulation is to collect statistical information of the gas station’s cars and gas pumps. 
A gas station consists of several pumps and a car waiting line (i.e. a car queue). 
In each time unit, at most one new car(s) arrives at the gas station. 
If the car waiting line is too long, the car leaves without getting gas; otherwise,
the car gets into the waiting line. 
If all pumps are busy, cars in the waiting lines must wait for a gas pump. 
If a pump is free and cars are waiting, the first car in the waiting line gets the pump and begins
pumping gas immediately. 
When a car finishes using a pump, the car departs and the pump becomes free. 
When we run the simulation through many units of time, at the end of each time unit, the program prints out a "snap-shot" 
of queues, cars and pumps. At the end of the program, the program prints out statistics and concludes simulation.

Assumptions and Requirements:
• Assumptions
• At most one car arrival per time unit
• All numbers are positive integer numbers (>=0), except average values should be displayed to
two decimal places
• No time lost in between events:
         a car arriving and entering waiting line
         a car arriving and leaving without pumping gas
         a car leaving the waiting line, advancing to a pump and beginning service
         a car completing service and departing
• The limits of simulations parameters:
         Maximum number of pumps 50
         Maximum simulation length 10000
         Maximum service time 500
         Maximum car queue limit 50
         Probability of a new car 1% - 100%
• Input parameters and customer (random/file) data

The following data are read at the beginning of the simulation :
        int numPumps; // number of pumps.
        int simulationTime; // time to run simulation
        int carQLimit; // car queue limit
        int chancesOfArrival; // probability of a new car (1 - 100)
        int maxServiceTime; // maximum service (or pumping gas) time per car


Sample input layout :
         *** Get Simulation Parameters ***
        Enter simulation time (positive integer) : 10
        Enter maximum service time of cars : 5
        Enter chances (0% < & <= 100%) of new car : 50
        Enter the number of service pumps : 2
        Enter car waiting queue limit : 1
        Enter 1/0 to get data from file/rand() : 1
        Enter a seed (+ integer) for random function : 2311

In each time unit of simulation, your program will need two positive integer numbers to compute
boolean anyNewArrival & int serviceTime. A user should have two options to specify the source of
those numbers:

• Numbers are read from a file. A filename should be provided at the beginning of simulation.
Each line in a datafile should contain two positive numbers (> 0). A datafile should contain
sufficient data for simulationTime upto 100 units, i.e. at least 100 lines. In each time unit,
anyNewArrival & serviceTime are generated as follows :
        read data1 and data2 from the file;
        anyNewArrival = (((data1%100)+1)<= chancesOfArrival);
        serviceTime = (data2%maxServiceTime)+1;

• Numbers are generated by random object. Random object, dataRandom, are
constructed at the beginning of simulation. In each time unit, anyNewArrival & serviceTime
are generated as follows :
        anyNewArrival = ((dataRandom.nextInt(100)+1) <= chancesOfArrival);
        serviceTime = dataRandom.nextInt(maxServiceTime)+1;

• Output Information
At the end of each time unit, the program prints out information as the following example :
      Time : 5
       car #4 arrives with service time 4 units
       car #4 waits in the car queue.
       car #2 is done
       pump #2 is free
       car #4 gets a pump
       pump #1 starts serving car #4 for 4 uni  ts 

At the end of the simulation, the program prints out information as the following example :
        End of simulation report :
         # total arrival cars : 4
         # cars gone-away : 0
         # cars served : 4
         Current Gas Pumps Info. :
         # waiting cars : 0
         # busy pumps : 0
         # free pumps : 2
         Total waiting time : 4
         Average waiting time : 0.11
         Free Pumps Info. :
         Pump ID : 2
         Total free time : 7
         Total busy time : 3
         Total # of cars : 1
         Average service time : 3
         Pump ID : 1
         Total free time : 3
         Total busy time : 7
         Total # of cars : 3
         Average service time : 2.3 
         
     
