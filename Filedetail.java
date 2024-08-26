package flight_booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
// import java.util.List;

public class Filedetail
 {
    public static void saveflight(ArrayList<Flight_Details> flights, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Flight_Details flight : flights) {
                writer.println(flight.toCSVString());
            }
            System.out.println("Flights have been saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving flights to file.");
        }
    }

    public static  ArrayList<Flight_Details> loadflight(String fileName) {
        ArrayList<Flight_Details> flights = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Flight_Details flight = Flight_Details.fromCSVString(line);
                flights.add(flight);
            }
            System.out.println("Flights have been loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading flights from file.");
        }

        return flights;
    }

     public static void savetickets(ArrayList<Ticket> tickets, String fileName) {
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Ticket t : tickets) {
                writer.println(t.toCSVString());
            }
            System.out.println("Tickets have been saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving tickets to file.");
        }
    }

    public static ArrayList<Ticket> loadtickets(String fileName,ArrayList<Flight_Details> F,ArrayList<Concrete> P) {
        ArrayList<Ticket> tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Ticket t = Ticket.fromCSVString(line,F ,P);
                tickets.add(t);
            }
            System.out.println("ticktes have been loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading tickets from file.");
        }

        return tickets;
    }

    public static void savepassenger(ArrayList<Concrete> Pass, String fileName) {
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Concrete p : Pass) {
                writer.println(p.toCSVString());
            }
            System.out.println("Passengers have been saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving Passengers to file.");
        }
    }

    public  static ArrayList<Concrete> loadpassenger(String fileName) {
        ArrayList<Concrete> P = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Concrete p = Concrete.fromCSVString(line);
                P.add(p);
            }
            System.out.println("Passengers have been loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Passengers from file.");
        }

        return P;
    }
}

