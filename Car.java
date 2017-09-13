/*
 *      Thomas Pretto 
 *
 */
class Car
{
    private int carID;
    private int serviceTime;
    private int arrivalTime;

    Car()
    {
        this.carID=0;          //car "zero" is a placeholder
        this.serviceTime=0;
        this.arrivalTime=0;
//        System.out.println("Current number of cars="+carID);
	// add statements
    }

    Car(int carid, int servicetime, int arrivaltime)
    {
	this.carID = carid;
        this.serviceTime = servicetime;
        this.arrivalTime = arrivaltime;
    }

    int getServiceTime()
    {
	// 
	return this.serviceTime;
    }


    int getArrivalTime()
    {
	// 
	return this.arrivalTime;
    }

    int getCarID()
    {
  	return this.carID;
    }

    public String toString()
    {
    	return ""+this.carID+":"+this.serviceTime+":"+this.arrivalTime;
    }

    public static void main(String[] args) {
	// quick check!
	Car mycar = new Car(20,30,40);
	System.out.println("Car Info:"+mycar);

    }
}
