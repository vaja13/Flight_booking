package flight_booking;

public class Concrete extends Passenger_Details {
    public Concrete(int a, String n, int p_id) {
        super(a, n, p_id);
    }

    @Override
    public double applyDiscount(double p) {
        // Implement the discount logic here
        return p;
    }
    public String toCSVString() {
        return passenger_id + "," + name + "," + age ;
    }

    public static Concrete fromCSVString(String csvData) {
        String[] parts = csvData.split(",");
        int passenger_id = Integer.parseInt(parts[0]);
        int age = Integer.parseInt(parts[2]);
        String name = parts[1];
        return new Concrete(age,name,passenger_id);
    }
}
