package flight_booking;

public class Flight_Details {
    
    private String Origin,Destination,DateTime,Flight_Name;
    private double Original_Price;
    private int numberOfSeatsleft,Flight_Number,Capacity;

    public Flight_Details(String fname,int fn, String o, String ds, String dp, int c,int ns, double op) {
        if (ds.equals(o) == true) {
            throw new IllegalArgumentException("destination and origin are the same");
        }
        Flight_Name=fname;
        Flight_Number = fn;
        Origin = o;
        Destination = ds;
        DateTime = dp;
        Capacity = c;
        numberOfSeatsleft = ns;
        Original_Price = op;
    }
    public boolean bookASeat() {
        if (numberOfSeatsleft > 0) {
            numberOfSeatsleft = numberOfSeatsleft - 1;
            return true;

        }
        return false;
    }
    public double getPrice() {
        return Original_Price;
    }

    public String getOrigin() {
        return Origin;
    }

    public String getDestination() {
        return Destination;
    }

    public String getDepartureTime() {
        return DateTime;
    }

    public String getFlightName() {
        return Flight_Name;
    }
    public int getFlightNumber() {
        return Flight_Number;
    }

    public int getNumberOfSeatsleft() {
        return numberOfSeatsleft;
    }

    public int getCapacity() {
        return Capacity;
    }
    public String toCSVString() {
        return Flight_Name + "," + Flight_Number + "," + Origin + "," + Destination + "," + DateTime + "," + Capacity + "," + Original_Price + "," + numberOfSeatsleft;
    }

    public static Flight_Details fromCSVString(String csvData) {
        String[] parts = csvData.split(",");
        String flightName= parts[0];
        int flightNumber = Integer.parseInt(parts[1]);
        String origin = parts[2];
        String destination = parts[3];
        String departureDateTime = parts[4];
        int capacity = Integer.parseInt(parts[5]);
        double price = Double.parseDouble(parts[6]);
        int noseatleft = Integer.parseInt(parts[7]);
        return new Flight_Details(flightName,flightNumber, origin, destination, departureDateTime, capacity,noseatleft, price);
    }

    // public void setPrice(double p) {
    //     Original_Price = p;
    // }

    // public void setOrigin(String o) {
    //     Origin = o;
    // }

    // public void setDestination(String d) {
    //     Destination = d;
    // }

    // public void setDepartureTime(String d) {
    //     DateTime = d;
    // }

    // public void setFlightName(String fname) {
    //     Flight_Name = fname;
    // }
    // public void setFlightNumber(int fnumber) {
    //     Flight_Number = fnumber;
    // }

    // public void setNumberOfSeatsleft(int s) {
    //     numberOfSeatsleft = s;
    // }

    // public void setCapacity(int c) {
    //     Capacity = c;
    // }


    // Overridding to print flight details
    public String toString() {
        String one = "Flight " + Flight_Number + ", Flightname is : "+ Flight_Name;
        String two = "," + Origin + " to " + Destination;
        String three = "," + DateTime;
        String four = ", original price: " + Original_Price + "$";
        return one + two + three + four;
    }
}