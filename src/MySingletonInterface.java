public interface MySingletonInterface {

    public void setFlightNo(int flightNo);
    
    public int getFlightNo();

    public void setFlightDestination(String flightDestination);

    public String getFlightDestination();

    public void setFlightDateTime(String flightDateTime);

    public String getFlightDateTime();
    
    public int getPrice();

    public void setAvailableSeats(int availableSeats);

    public int getAvailableSeats();

    public void setFlightSeats(String[] flightSeats);

    public String[] getFlightSeats();
    
    public void setName(String name);

    public String getName();

    public void setSurname(String surname);

    public String getSurname();

    public void setMail(String mail);

    public String getMail();

    public void setPhoneNumber(String phoneNumber);

    public String getPhoneNumber();
    
    public void setPassword(String password);

    public String getPassword();

    public void setUserType(int userType);
    
    public int getUserType();
    
    public void setFlights(String[] flights);

    public String[] getFlights();  
    
}