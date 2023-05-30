import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MySingleton implements MySingletonInterface{

    private static MySingleton instance = null;
    private ArrayList<customer> customers;
    private ArrayList<flight> flights;
    private String customersFilePath = "customers.txt";
    private String flightsFilePath = "flights.txt";

    private MySingleton() {
        customers = new ArrayList<customer>();
        flights = new ArrayList<flight>();

        customerReader();
        flightReader();
    }

    public static MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    public ArrayList<customer> getCustomers(){
        return customers;
    }

    public ArrayList<flight> getFlights(){
        return flights;
    }

    public ArrayList<flight> getFlights(String start, String end, int minPrice, int maxPrice){
        ArrayList <flight> temp = new ArrayList<flight>();

        Boolean cond1 = start.equals("") ? true : false;
        Boolean cond2 = end.equals("") ? true : false;
        Boolean cond3 = minPrice == 0 ? true : false;
        Boolean cond4 = maxPrice == 0 ? true : false;


        // Get flights with given start and end points and available seats and flight date is after now
        for (flight flight : flights) {
            if((cond1 || flight.getFlightDestination().split("-")[0].equalsIgnoreCase(start)) 
                && (cond2 || flight.getFlightDestination().split("-")[1].equalsIgnoreCase(end))
                && flight.getAvailableSeats() > 0 && flight.getFlightDateTime().isAfter(LocalDateTime.now())
                && (cond3 || flight.getPrice() >= minPrice) && (cond4 || flight.getPrice() <= maxPrice)){
                temp.add(flight);
            }
        }

        return temp;
    }

    public void addCustomer(customer customer){
        customers.add(customer);
        customerWriter();
    }

    public void removeCustomer(customer customer){
        customers.remove(customer);
        customerWriter();
    }
    
    public void updateCustomer(customer customer){
        customerWriter();
    }

    public void addFlight(flight flight){
        flights.add(flight);
        
        // Sort flights by date
        Collections.sort(flights, new Comparator<flight>() {
            @Override
            public int compare(flight f1, flight f2) {
                return f1.getFlightDateTime().compareTo(f2.getFlightDateTime());
            }
        });

        flightWriter();
    }

    public void removeFlight(flight flight){
        flights.remove(flight);
        flightWriter();
    }

    public customer getCustomerByIndex(int index){
        return customers.get(index);
    }

    public flight getFlightByIndex(int index){
        return flights.get(index);
    }

    public flight getFlightByFlightNo(int flightNo){
        for (flight flight : flights) {
            if(flight.getFlightNo() == flightNo){
                return flight;
            }
        }

        return null;
    }

    public int getCustomerSize(){
        return customers.size();
    }

    public int getFlightSize(){
        return flights.size();
    }

    public int isCustomer(String data){
        if(data.contains("@")){
            for(int i = 0; i < customers.size(); i++){
                if(customers.get(i).getMail().equals(data)){
                    return i;
                }
            }
        }
        else{
            for(int i = 0; i < customers.size(); i++){
                if(customers.get(i).getPhoneNumber().equals(data)){
                    return i;
                }
            }
        }

        return -1;
    }

    public int isFlight(int flightNo){
        for(int i = 0; i < flights.size(); i++){
            if(flights.get(i).getFlightNo() == flightNo){
                return i;
            }
        }

        return -1;
    }

    public void customerReader() {
        // name/surname/mail/password/phone number/usertype(0-> user, 1-> admin)

        try {
            BufferedReader reader = new BufferedReader(new FileReader(customersFilePath));
            
            // Read all file and assign to users array list
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("/");
                String[] str = parts[6].split(";");

                ArrayList<Integer> flights = new ArrayList<Integer>();
                for(int i = 0; i < str.length; i++){
                    flights.add(Integer.parseInt(str[i]));
                }

                customers.add(new customer(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), flights));
                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e1) {

            e1.printStackTrace();
            System.exit(0);
        }
    }

    public void customerWriter() {
        // name/surname/mail/password/phone number/usertype(0-> user, 1-> admin)

        try {          
            FileWriter myWriter = new FileWriter(customersFilePath);
            for (int i = 0; i < customers.size(); i++) {
                ArrayList<Integer> flights = customers.get(i).getFlights();

                String str = "";
                for(int j = 0; j < flights.size(); j++){
                    str += flights.get(j) + ((j == flights.size() - 1) ? "" : ";");
                }

                myWriter.write(customers.get(i).getName() + "/" + customers.get(i).getSurname() + "/"
                        + customers.get(i).getMail() + "/" + customers.get(i).getPassword() + "/"
                        + customers.get(i).getPhoneNumber() + "/" + customers.get(i).getUserType() + "/" 
                        + (str.equals("") ? ";" : str) + "\n");
            }

            myWriter.close();
        } catch (Exception e2) {
            e2.printStackTrace();
            System.exit(0);
        }
    }

    public void flightReader() {
        // flightNo/flightDestination/flightDate/price/availableSeats/flightSeats
        try {

            BufferedReader reader = new BufferedReader(new FileReader(flightsFilePath));

            // Read all file and assign to users array list
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("/");
                String[] seats = parts[5].split(";");

                flight newFlight = new flight(Integer.parseInt(parts[0]), parts[1], parts[2],
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), seats);
                flights.add(newFlight);

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e1) {

            e1.printStackTrace();
            System.exit(0);
        }finally{
            
            // Sort flights by date earliest to latest by date and time
            Collections.sort(flights, new Comparator<flight>() {
                @Override
                public int compare(flight o1, flight o2) {
                    return o1.getFlightDateTime().compareTo(o2.getFlightDateTime());
                }
            });

            flightWriter();
        }
    }

    public void flightWriter() {
        // flightNo/flightDestination/flightDate/flightTime/price/availableSeats/flightSeats
        try {
            FileWriter myWriter = new FileWriter(flightsFilePath);
            for (int i = 0; i < flights.size(); i++) {
                myWriter.write(flights.get(i).getFlightNo() + "/" + flights.get(i).getFlightDestination() + "/"
                        + flights.get(i).getFlightDateTimeString() + "/"
                        + flights.get(i).getPrice() + "/" + flights.get(i).getAvailableSeats() + "/");

                for (int j = 0; j < 120; j++) {
                    if (j == 119)
                        myWriter.write(flights.get(i).getFlightSeats()[j] + "\n");
                    else
                        myWriter.write(flights.get(i).getFlightSeats()[j] + ";");
                }
            }
            myWriter.close();
        } catch (Exception e2) {
            e2.printStackTrace();
            System.exit(0);
        }
    }

}
