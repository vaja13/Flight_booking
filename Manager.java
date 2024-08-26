package flight_booking;

import java.io.File;
import java.util.*;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.lang.Throwable;
// import java.nio.file.Path;
// import java.nio.file.Paths;

public class Manager{

    ArrayList<Flight_Details>flights;
    ArrayList<Ticket>tickets;
    static ArrayList<Concrete> concrete_pass;
    
    // private Filedetail Filedetail ;
    
    public Manager() {
        flights = new ArrayList<Flight_Details>();
        tickets = new ArrayList<Ticket>();
        concrete_pass = new ArrayList<Concrete>();

        // Filedetail = new Filedetail();
        flights = Filedetail.loadflight("flightdetail.csv");
        concrete_pass = Filedetail.loadpassenger("passengerdetail.csv");
        tickets = Filedetail.loadtickets("ticketsdetail.csv",flights,concrete_pass);

    }
    


    public void createFlights() {
        Scanner s1=new Scanner(System.in);
        Scanner s2=new Scanner(System.in);
        Scanner s3=new Scanner(System.in);

        int flight_NO, cap,no_seats_left;
        double org_price;
        String origin, destination, date_time, flight_name;

        System.out.println("Enter flight Name");
        flight_name = s1.nextLine();
        System.out.println("Enter flight Number");
        flight_NO = s2.nextInt();
        System.out.println("Enter flight origin");
        origin = s1.nextLine();
        System.out.println("Enter flight destination");
        destination = s1.nextLine();
        System.out.println("Enter flight departure time and date");
        date_time = s1.nextLine();
        System.out.println("Enter flight capacity : ");
        cap = s2.nextInt();
        no_seats_left=cap;
        System.out.println("Enter original price : ");
        org_price = s3.nextDouble();
        Flight_Details F = new Flight_Details(flight_name, flight_NO, origin, destination, date_time, cap,no_seats_left, org_price);
        
        flights.add(F);
        Filedetail.saveflight(flights,"flightdetail.csv");

        System.out.println("The following flight has been created:");
        System.out.println(F);
        
    }

    public void displayAvailableFlights(String origin, String destination) {
        int size, seats;
        boolean flag = true;
        String o, d;
        size = flights.size();

        if (destination.equals(origin) == true) {
            throw new IllegalArgumentException("destination and origin are same");
        } 
        else {
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                o = flights.get(i).getOrigin();
                d = flights.get(i).getDestination();
                seats = flights.get(i).getNumberOfSeatsleft();
                System.out.println(o);

                if ((o.equals(origin) == true) && (d.equals(destination) == true) && seats > 0) {
                    if (flag == true) {
                        System.out.println("List of available flights:");
                        flag = false;
                    }
                    System.out.println(flights.get(i));
                }

            }
            if (flag == true) {
                System.out.println("No available flights");
            }
        }
    }

    public Flight_Details getFlight(int flightNumber) {
        int size = flights.size();
        for (int i = 0; i < size; i++) {
            if (flights.get(i).getFlightNumber() == flightNumber) {
                return flights.get(i);
            }

        }
        return null;
    }

    public void bookSeat(int flightNumber, Passenger_Details p){
        int size = flights.size();
        boolean flag = false;
        int index = 0, seats;
        for (int i = 0; i < size; i++) {
            if (flights.get(i).getFlightNumber()==flightNumber) {
                flag = true;
                index = i;
                break;
            }
        }
        double price;
        seats = flights.get(index).getNumberOfSeatsleft();
        if (flag == true && (seats > 0)) {
            flights.get(index).bookASeat();
            price = p.applyDiscount(flights.get(index).getPrice());
            Ticket a = new Ticket(flights.get(index), p, price);
            tickets.add(a);
            Filedetail.savetickets(tickets, "ticketsdetail.csv");

            System.out.println("you have succesfully booked a seat for flight " + flightNumber);
            System.out.println("ticket: " + a );

        }else {
            if (flag == true && seats == 0) {
                System.out.println("The Flight " + flightNumber + " is full you cannot book a ticket for this flight");

            } else if (flag == false) {
                System.out.println("The Flight " + flightNumber + " cdoes not exist");
            }
        }
    }

    public static void main(String[] args) {
        String input, destination, origin;
        Scanner sc1=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
        int diff;
        boolean iter=false;
        
        Manager M = new Manager();
        while(iter!=true){

        System.out.println("Enter 1 if you are admin or 2 if you are user and 3 if you want to exit the program: ");
        diff = sc2.nextInt();

        String username = "Admin", password = "Admin123";
        switch (diff) {
            case 1:
                int k = 3;
                boolean inneriter=false;
                while (k > 0 && inneriter!=true){
                    String user,pswd;
                    System.out.print("Enter username:");
                    user = sc1.nextLine();
                    System.out.print("Enter password:");
                    pswd = sc1.nextLine();
                    if (user.equals(username) && pswd.equals(password)) {
                        System.out.println("Welcome to admin panel.");

                        Flight_Details F;

                        int FN;
                        boolean flag = false;

                        while (flag != true) {
                            System.out.println("Enter c if you would like to create a flight : ");
                            System.out.println("Enter g if you would like to get information on a flight : ");
                            System.out.println("Enter t if you would like to terminate the program : ");
                            input = sc1.nextLine();
                            switch (input) {
                                case "c":
                                    M.createFlights();
                                    break;
                                case "g":
                                    System.out.println("Enter Flight number");
                                    FN = sc2.nextInt();
                                    F = M.getFlight(FN);
                                    if (F == null) {
                                        System.out.println("Flight " + FN + " does not exist");
                                    } else {
                                        System.out.println("Info for Flight " + FN + ":");
                                        System.out.println(F);
                                    }
                                    break;
                                case "t":
                                    flag = true;
                                    inneriter=true;
                                    System.out.println("Program is terminated");

                                    break;
                                default:
                                    System.out.println("You entered an invalid org_pricetion");

                                    break;
                            }

                        }
                    } else {
                        k--;
                        System.out.println("Wrong detail. Tries left : " + k);
                    }
                }
                break;
            case 2:
                boolean flag1 = false;
                int FN1,age,years,Pass_id;
                Flight_Details F1;
                Passenger_Details P1;
                String name,sub;
                while (flag1 != true ) {

                    System.out.println("Enter d if you would like to display all flights ");
                    System.out.println("Enter g if you would like to get information on a flight ");
                    System.out.println("Enter b if you would like to book a seat");
                    System.out.println("Enter t if you would like to terminate the program");
                    input = sc1.nextLine();
                    switch (input) {
                        case "d":
                            System.out.println("Enter flight origin");
                            origin = sc1.nextLine();
                            System.out.println("Enter flight destination");
                            destination = sc1.nextLine();
                            M.displayAvailableFlights(origin, destination);
                            break;
                        case "g":
                            System.out.println("Enter Flight number");
                            FN1 = sc2.nextInt();
                            F1 = M.getFlight(FN1);
                            if (F1 == null) {
                                System.out.println("Flight " + FN1 + " does not exist");
                            } else {
                                System.out.println("Info for Flight " + FN1+ ":");
                                System.out.println(F1);
                            }
                            break;
                        case "b":
                            System.out.println("If passenger is non memeber enter n, if passenger is a member enter m");
                            sub = sc1.nextLine();
                            System.out.println("Enter passengers age");
                            age = sc2.nextInt();
                            System.out.println("Enter passengers name");
                            name = sc1.nextLine();
                            System.out.println("Enter flight number");
                            FN1 = sc2.nextInt();
                            Random rand=new Random();
                            Pass_id=rand.nextInt(1000);
                            System.out.println("Passenger id is : "+ Pass_id);

                            Concrete c1=new Concrete(age, name, Pass_id);
                            concrete_pass.add(c1);
                            Filedetail.savepassenger(concrete_pass,"passengerdetail.csv");

                            if (sub.equals("n") == true) {
                                P1 = new Non_member(age, name,Pass_id);
                                M.bookSeat(FN1, P1);
                            } 
                            else if (sub.equals("m") == true) {
                                System.out.println("For how many years has the passenger been a member");
                                years = sc2.nextInt();
                                P1 = new Member(years, age, name,Pass_id);
                                M.bookSeat(FN1, P1);

                            }
                            break;

                        case "t":
                            flag1 = true;
                            System.out.println("Program is terminated");
                            break;

                        default:
                            System.out.println("You entered an invalid option");
                            break;

                    }

                }
                break;
            case 3:
                iter= true;
                break;
            default:
                System.out.println("Invalid input:");
                break;
        }
    
    }
    }
}