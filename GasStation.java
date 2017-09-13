import java.util.*;
/*
 *      Thomas Pretto 
 *
 */
//--------------------------------------------------------------------------
//
// Define service queues in a gas station. Queues hold references to Car &
// GasPump objects
//
// Car queue is used to hold waiting cars. If the queue is too long
// (i.e. >  carQLimnit), car goes away without entering car queue
//
// There are several gas pumps in a gas station. Use PriorityQueue to
// hold BUSY gas pumps and FIFO queue to hold FREE gas pumps,
// i.e. a pump that is FREE for the longest time should start be used first.
//
// To handle gasPump in PriorityQueue, we need to define comparator
// for comparing 2 gasPump objects. Here is a constructor from Java API:
//
// 	PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
//
// For priority queue, the default compare function is "natural ordering"
// i.e. for numbers, minimum value is returned first
//
// User can define own comparator class for PriorityQueue.
// For gaspump objects, we like to have smallest end service time first.
//
// The following class define compare() for two gas pumps :

class CompareGasPump implements Comparator<GasPump>{

	// overide compare() method
 	public int compare(GasPump o1, GasPump o2) {
		return (o1.getEndServiceTime() - o2.getEndServiceTime());
	}
}


// DO NOT ADD NEW METHODS OR DATA FIELDS
class GasStation {


  // Private data fields:

  // define one priority queue
  private PriorityQueue <GasPump> busyGasPumpQ;

  // define two FIFO queues
  private Queue<Car> carQ;
  private Queue<GasPump> freeGasPumpQ;

  // define carPtr queue limit
  private int carQLimit;


  // Constructor
  public GasStation()
  {
	// Add statetments
      // use ArrayDeque to construct FIFO queue objects
      carQ=new ArrayDeque();
      freeGasPumpQ=new ArrayDeque();
	// construct PriorityQueue object
 	// overide compare() in Comparator to compare GasPump objects
	busyGasPumpQ= new PriorityQueue<GasPump>( 1, new CompareGasPump());
//System.out.println("initGasStation()"+busyGasPumpQ.element());
	// initialize carQlimit
        carQLimit=1;
  }

  // Constructor
  public GasStation(int numGasPumps, int carQlimit, int startGasPumpID)
  {
	// use ArrayDeque to construct FIFO queue objects
      carQ=new ArrayDeque(carQlimit);
      freeGasPumpQ=new ArrayDeque(numGasPumps);
	// construct PriorityQueue object
 	// overide compare() in Comparator to compare GasPump objects
	busyGasPumpQ= new PriorityQueue<GasPump>( numGasPumps, new CompareGasPump());

	// initialize carQlimit
        carQLimit=carQlimit;
        // Construct GasPump objects and insert into FreeGasPumpQ
        for (int i=1; i<=numGasPumps; i++)
        {
            this.insertFreeGasPumpQ(new GasPump(startGasPumpID+i-1));//initialize Q with pumps
        }
        System.out.print("Gas pump");
        if(numGasPumps>1)
            System.out.print("s");
        System.out.print(" #"+startGasPumpID);
        if (numGasPumps>1)
            System.out.print(" to #"+(startGasPumpID+numGasPumps-1));
        if(numGasPumps==1)
            System.out.println(" is ready.");
        if(numGasPumps>1)
            System.out.println(" are ready.\n");



//System.out.println("busyGasPumpQ="+busyGasPumpQ+"\ncarQ="+carQ+"\nfreeGasPumpQ="+freeGasPumpQ);// remove
/*
        GasPump freePump;
 for (int i=0; i<numGasPumps; i++)
        {
     freePump=freeGasPumpQ.poll();

            System.out.println("Pump<"+freeGasPumpQ.size()+">:");//initialize Q with pumps
        }
*/
  }

  public GasPump removeFreeGasPumpQ()
  {
	// remove and return a free gasPump
	// Add statetments
GasPump aPump=freeGasPumpQ.remove();
//System.out.println("busyGasPumpQ="+busyGasPumpQ+"\ncarQ="+carQ+"\nfreeGasPumpQ="+freeGasPumpQ);// remove
    return aPump;
  }

  public GasPump removeBusyGasPumpQ()
  {
    // System.out.println("\n:removeBusyGasPumpQ:");// remove

	// remove and return a busy gasPump
	// Add statetments
      GasPump aPump=new GasPump();
      try
      {
      aPump=this.busyGasPumpQ.remove();
	
      }
      catch (NoSuchElementException NSEE)
      {
        
          throw (new Exception ("NoMorePumpsException"));
      }
      finally
      {
//System.out.println("busyGasPumpQ="+busyGasPumpQ+"\ncarQ="+carQ+"\nfreeGasPumpQ="+freeGasPumpQ);// remove

          return aPump;
      }
  }

  public Car removeCarQ()
  {
  //      System.out.println("removeCarQ:");// remove

	// remove and return a car
	// Add statetments
      Car aCar=carQ.poll();
      //aCar=carQ.poll();
	return aCar;
  }

  public void insertFreeGasPumpQ(GasPump gasPump)
  {
   //    System.out.println("insertFreeGasPumpQ:");// remove

	// insert a free gasPump
	// Add statetments
//System.out.println("insertFreePump()");
if(gasPump!=null)
      freeGasPumpQ.offer(gasPump);
  }

  public void insertBusyGasPumpQ(GasPump gasPump)
  {
  //System.out.println("insertBusyGasPumpQ:");

	// insert a busy gasPump
	
      this.busyGasPumpQ.add(gasPump);
  }

  public void insertCarQ(Car car)
  {
         //      System.out.println("insertCarQ:");// remove
	// insert a car
      carQ.add(car);
  }

  public boolean emptyFreeGasPumpQ()
  {
    //                 System.out.println("\nemptyFreeGasPumpQ:");

	// is freeGasPumpQ empty?
	// Add statetments
	return this.freeGasPumpQ.isEmpty();
  }

  public boolean emptyBusyGasPumpQ()
  {
 //              System.out.println("emptyBusyGasPumpQ:");// remove

	// is busyGasPumpQ empty?
	// Add statetments
	return this.busyGasPumpQ.isEmpty();
  }

  public boolean emptyCarQ()
  {
  //                         System.out.println("emptyCarQ:");// remove

	// is carQ empty?
	// Add statetments
	return (this.carQ.isEmpty());
  }

  public int numFreeGasPumps()
  {
      //                     System.out.println("numFreeGasPumps:");// remove

	// get number of free gasPumps
	// Add statetments
        if(this.freeGasPumpQ.isEmpty())
            return 0;
        else
            return freeGasPumpQ.size();
  }

  public int numBusyGasPumps()
  {
  //                         System.out.println("numBusyGasPumps:"+busyGasPumpQ.size());

	// get number of busy gasPumps
	// Add statetments
      if(busyGasPumpQ.isEmpty())
          return 0;
      else
	return busyGasPumpQ.size();
  }

  public int numWaitingCars()
  {
  //                         System.out.println("numWaitingCars:");// remove

	// get number of cars
      if(this.carQ.isEmpty())
          return 0;
      else
      {
 //         System.out.println("CarQSize="+this.carQ.size());
   	return this.carQ.size();

      }
  }

  public GasPump getFrontBusyGasPumpQ()
  {
 //System.out.println("getFrontBusyGasPumpQ:"+busyGasPumpQ);// remove

	// get front of busy gasPumps
	// "retrieve" but not "remove"
      if(this.busyGasPumpQ.isEmpty())
      {
//          System.out.println("->BusyGasPumpQ.isEmpty!");//remove

          return new GasPump(0);
      }
          else
	return this.busyGasPumpQ.peek();
  }

  public int getFrontCarID()
    {
  //     System.out.println("getFrontCarID:");// remove
if (this.carQ.isEmpty())
    return 0;
else
  return this.carQ.peek().getCarID();
  }

  public boolean isCarQTooLong()
  {
  //     System.out.println("isCarQTooLong:");// remove

	// is carQ too long?
	// Add statetments
//      System.out.println("Queue is "+carQ.size());
      return(carQ.size()>=carQLimit);
  }

public void printStatistics()
  {
  	System.out.println("\t# waiting cars        : "+numWaitingCars());
  	System.out.println("\t# busy gas pumps      : "+numBusyGasPumps());
  	System.out.println("\t# free gas pumps      : "+numFreeGasPumps());
  }


  public static void main(String[] args) {
	// quick tests
        GasStation sc = new GasStation(4, 5, 1001);
        Car c1 = new Car(1,18,10);
        Car c2 = new Car(2,33,10);
        Car c3 = new Car(3,21,10);
        Car c4 = new Car(3,37,10);
  	sc.insertCarQ(c1);
  	sc.insertCarQ(c2);
  	sc.insertCarQ(c3);
System.out.println("CarQ:"+sc.carQ);
	System.out.println("Remove car:"+sc.removeCarQ());
	System.out.println("Remove car:"+sc.removeCarQ());
	System.out.println("Remove car:"+sc.removeCarQ());

System.out.println("FreeGasPumpQ"+sc.freeGasPumpQ);
	GasPump p1=sc.removeFreeGasPumpQ();
	GasPump p2=sc.removeFreeGasPumpQ();
	GasPump p3=sc.removeFreeGasPumpQ();
	GasPump p4=sc.removeFreeGasPumpQ();
	System.out.println("Remove free gas pump:"+p1);
	System.out.println("Remove free gas pump:"+p2);
	System.out.println("Remove free gas pump:"+p3);
	System.out.println("Remove free gas pump:"+p4);

        p1.freeToBusy (c1, 13);
        p2.freeToBusy (c2, 13);
        p3.freeToBusy (c3, 13);
        p4.freeToBusy (c4, 13);
	sc.insertBusyGasPumpQ(p1);
	sc.insertBusyGasPumpQ(p2);
	sc.insertBusyGasPumpQ(p3);
	sc.insertBusyGasPumpQ(p4);
	System.out.println("BusyGasPumps:"+sc.busyGasPumpQ);
	p1=sc.removeBusyGasPumpQ();
	p2=sc.removeBusyGasPumpQ();
	p3=sc.removeBusyGasPumpQ();
	p4=sc.removeBusyGasPumpQ();
	System.out.println("Remove busy gas pump:"+p1);
	System.out.println("Remove busy gas pump:"+p2);
	System.out.println("Remove busy gas pump:"+p3);
	System.out.println("Remove busy gas pump:"+p4);

   }


};

