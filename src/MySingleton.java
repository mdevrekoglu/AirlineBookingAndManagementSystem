import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MySingleton {

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

    public void addCustomer(customer customer){
        customers.add(customer);
        customerWriter();
    }

    public void removeCustomer(customer customer){
        customers.remove(customer);
        customerWriter();
    }

    public void addFlight(flight flight){
        flights.add(flight);
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
                customers.add(new customer(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5])));
                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.exit(0);
        }
    }

    public void customerWriter() {
        // name/surname/mail/password/phone number/usertype(0-> user, 1-> admin)

        try {          
            FileWriter myWriter = new FileWriter(customersFilePath);
            for (int i = 0; i < customers.size(); i++) {
                myWriter.write(customers.get(i).getName() + "/" + customers.get(i).getSurname() + "/"
                        + customers.get(i).getMail() + "/" + customers.get(i).getPassword() + "/"
                        + customers.get(i).getPhoneNumber() + "/" + customers.get(i).getUserType() + "\n");
            }

            myWriter.close();
        } catch (Exception e2) {
            e2.printStackTrace();
            System.exit(0);
        }
    }

    public void flightReader() {
        // flightNo/flightDestination/flightDate/flightTime/price/availableSeats/flightSeats
        try {

            BufferedReader reader = new BufferedReader(new FileReader(flightsFilePath));

            // Read all file and assign to users array list
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("/");
                String[] seats = parts[6].split(";");

                int[] seatsInt = new int[120];
                for (int i = 0; i < seats.length; i++) {
                    seatsInt[i] = Integer.parseInt(seats[i]);
                }

                flight newFlight = new flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3],
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), seatsInt);
                flights.add(newFlight);

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.exit(0);
        }
    }

    public void flightWriter() {
        // flightNo/flightDestination/flightDate/flightTime/price/availableSeats/flightSeats
        try {
            FileWriter myWriter = new FileWriter(flightsFilePath);
            for (int i = 0; i < flights.size(); i++) {
                myWriter.write(flights.get(i).getFlightNo() + "/" + flights.get(i).getFlightDestination() + "/"
                        + flights.get(i).getFlightDate() + "/" + flights.get(i).getFlightTime() + "/"
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
