package flight_booking;

import java.util.ArrayList;
import java.util.Random;

public class Ticket {
    private Flight_Details flight;
    private Passenger_Details passenger;
    private double price;
    private int number,ticket_id;
    private static int count = 0;

    public Ticket(Flight_Details f, Passenger_Details pa, double pr){
        Random rand=new Random();
        ticket_id=rand.nextInt(100000);
        count++;
        flight = f;
        passenger = pa;
        price = pr;
        number = count;
    }

    public String toCSVString() {
        return ticket_id + ","  +passenger.getpid()  +  ","+ passenger.getName() + "," +passenger.getAge() + "," + flight.getFlightNumber() + "," + flight.getFlightName() + "," +flight.getOrigin()+ "," +flight.getDestination() + "," + flight.getDepartureTime() + ","+ price;
    }

    public static Ticket fromCSVString(String csvData,ArrayList<Flight_Details> flights,ArrayList<Concrete> pass) {
        String[] parts = csvData.split(",");
        // String tid= parts[0];
        // int fnum = Integer.parseInt(parts[1]);
        // String fname=parts[2];
        // String o = parts[3];
        // String d = parts[4];
        // String dep_t = parts[5];
        double price = Double.parseDouble(parts[9]);

        Flight_Details f1=flights.get(0);
        for (Flight_Details f : flights){
           if(f.getFlightNumber()==Integer.parseInt(parts[4])){
                f1=f;
                break;
           }
        }

        Concrete p1=pass.get(0);
        for(Concrete p: pass){
            if(p.getpid()==Integer.parseInt(parts[1])){
                p1=p;
                break;
            }
        }

        return new Ticket(f1,p1,price);



    }

    public String toString() {
        String one = passenger.getName() + ", Flight " + flight.getFlightNumber();
        String two = ", " + flight.getOrigin() + " to " + flight.getDestination();
        String three = ", " + flight.getDepartureTime();
        String four = ", original price: " + flight.getPrice() + "$";
        String five = ", ticket price: " + price + "$";
        String six =", Ticket ID : " + ticket_id ; 
        return one + two + three + four + five+ six;
    }

    public Flight_Details getFlight() {
        return flight;
    }
    public void setFlight(Flight_Details F) {
        flight=F;
    }
     public Passenger_Details getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger_Details P) {
        passenger=P;
    }
     public double getPrice() {
        return price;
    }
    public void setPrice(double P) {
        price=P;
    }
     public int getNumber() {
        return number;
    }
    public void setNumber(int n) {
        number=n;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int n) {
        count=n;
    }    
}