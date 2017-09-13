// Gas pump
/*
 *      Thomas Pretto
 *
 */
class GasPump {


   // define constants for representing intervals
   static int BUSY = 1;
   static int FREE = 0;

   // gas pump id and current car which is using the pump
   private int gasPumpID;
   private Car currentCar;

   // start time and end time of current interval
   private int startTime;
   private int endTime;

   // for keeping statistical data
   private int totalFreeTime;
   private int totalServiceTime;
   private int totalCars;

   // Constructor
   GasPump()
   {
       currentCar=new Car(0,0,0);

       this.gasPumpID=0;
	// add statements
       startTime=0;
       endTime=0;
       totalFreeTime=0;
       totalServiceTime=0;
       totalCars=0;

	// add statements
   }


   // Constructor with gas pump id
   GasPump(int gasPumpId)
   {
       currentCar=new Car(0,0,0);

       this.gasPumpID=gasPumpId;
	// add statements
       startTime=0;
       endTime=0;
       totalFreeTime=0;
       totalServiceTime=0;
       totalCars=0;

   }

   // get data member
   int getGasPumpID ()
   {
	return this.gasPumpID;
   }

   Car getCar()
   {

	//Car aCar =new Car();

       // add statements
	return this.currentCar;
   }

   int getEndServiceTime()
   {
	// return end time of busy interval
	// add statements
       if (this.endTime==0)
           return 0;
       else
           return this.endTime;
   }

   // functions for state transition
   // FREE -> BUSY :
   void freeToBusy (Car currentCar, int currentTime)
   {
// System.out.println("(->) freeToBusy:");


  //     System.out.println(currentCar/*.getServiceTime()*/);

  	// Main goal  : switch from free interval to busy interval
  	//
  	// end free interval, start busy interval
  	// steps	: update totalFreeTime
//System.out.print("Totalfreetime:["+totalFreeTime+"-->");
       totalFreeTime=totalFreeTime+(currentTime-endTime);//endTime was last time pump"used"
//System.out.println(totalFreeTime+"]");

  	// 		  set startTime, endTime, currentCar,
            this.currentCar=currentCar;
//System.out.println(this.currentCar/*.getServiceTime()*/);
            this.endTime=currentTime+currentCar.getServiceTime();
            this.startTime=currentTime;
//            System.out.println("CurentTime("+currentTime+"): Start("+startTime+"):end("+endTime+")");
//System.out.println(this);
            // 		  update totalCars
            totalCars++;

	// add statements
   }

   // BUSY -> FREE :
   Car busyToFree (int currentTime)
   {
    //    System.out.println("(<-) BusyToFree");
   	// Main goal : switch from busy interval to free interval
   	//
  	// steps     : update totalServiceTime
  	// 	       set startTime
  	//             return currentCar
        Car aCar = this.currentCar;
        this.totalServiceTime+=aCar.getServiceTime();
        this.startTime=0;
	return aCar;
   }

   // need this at the end of simulation
   void setEndServiceTime (int endsimulationtime, int intervalType)
   {
  	// for end of simulation
  	// set endTime,
//  this.endTime=endsimulationtime;
  	// for FREE interval, update totalFreeTime
       if(intervalType==FREE)//"free"pump was sitting idle at the rapture.
       {
            this.totalFreeTime+=(endsimulationtime-this.endTime);
       }
  	// for BUSY interval, update totalServiceTime
    if(intervalType==BUSY)//"Busy" pump was in the middle of service when the end came.
    {
       this.totalServiceTime+=(endsimulationtime-this.startTime);
    }//end if
    }//end setEndServiceTime

   // functions for printing statistics :
   void printStatistics ()
   {
  	// print gasPump statistics, see project statement

  	System.out.println("\t\tGasPump ID           : "+gasPumpID);
  	System.out.println("\t\tTotal free time      : "+totalFreeTime);
  	System.out.println("\t\tTotal service time   : "+totalServiceTime);
  	System.out.println("\t\tTotal # of cars      : "+totalCars);
  	if (totalCars > 0)
  	    System.out.format("\t\tAverage service time : %.2f%n\n",(totalServiceTime*1.0)/totalCars);
   }

   public String toString()
   {
//	return "GasPump:"+gasPumpID+":"+startTime+"-"+endTime+":Car:"+currentCar;
        	return "GasPump#:"+gasPumpID+":Start="+startTime+":end="+endTime+":Car="+currentCar;

   }

   public static void main(String[] args) {
	// quick check
        Car mycar = new Car(20,30,40);
	GasPump mypump = new GasPump(5);
        mypump.freeToBusy (mycar, 13);
        System.out.println(mypump);
   }

};

