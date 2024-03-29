import java.util.ArrayList;

public class customer implements customerInterface{
    
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phoneNumber;
    private int userType;
    private ArrayList<Integer> flights;

    public customer(String name, String surname, String mail, String password, String phoneNumber, int userType, ArrayList<Integer> flights) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.flights = flights;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getMail(){
        return mail;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getPassword(){
        return password;
    }

    public int getUserType(){
        return userType;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUserType(int userType){
        this.userType = userType;
    }

    public ArrayList<Integer> getFlights(){
        return flights;
    }

    public void setFlights(ArrayList<Integer> flights){
        this.flights = flights;
    }

    public void addFlight(int flightID){
        flights.add(flightID);
    }

    public void removeFlight(int flightID){
        flights.remove(flightID);
    }
}
