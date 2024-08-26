package flight_booking;

public class Non_member extends Passenger_Details{

    public Non_member(int a, String n,int p_id) {
        super(a, n,p_id);
    }

    public double applyDiscount(double p) {
        if (age > 65) {
            p = (p *9)/10;
            return p;
        }
        return p;
    }
}