package flight_booking;

// import java.util.ArrayList;

public abstract class Passenger_Details{

    String name;
    int age,passenger_id;
    // ArrayList<Passenger_Details> pArrayList=new ArrayList<>();
     
    public Passenger_Details(int a, String n,int p_id){
        name = n;
        age = a;
        passenger_id=p_id;
        // pArrayList.add(this);
    }

    // public String toCSVString() {
    //     return passenger_id + "," + name + "," + age ;
    // }

    // public static Concrete fromCSVString(String csvData) {
    //     String[] parts = csvData.split(",");
    //     int passenger_id = Integer.parseInt(parts[0]);
    //     int age = Integer.parseInt(parts[2]);
    //     String name = parts[1];
    //     return new Concrete(age,name,passenger_id);
    // }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getpid(){
        return passenger_id;
    }
    abstract public double applyDiscount(double p);
}