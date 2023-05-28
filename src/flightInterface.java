import java.time.LocalDateTime;

public interface flightInterface {

    /*
     * This interface is used to create flight objects.
     */

    public int getFlightNo();
    /**
     * @return This method returns the flight number
     */

    public void setFlightNo(int flightNo);
    /**
     * @param flightNo This parameter is used to set the flight number
     */

    public String getFlightDestination();
    /**
     * @return This method returns the flight destination
     */

    public void setFlightDestination(String flightDestination);
    /**
     * @param flightDestination This parameter is used to set the flight destination
     */

    public LocalDateTime getFlightDateTime();
    /**
     * @return This method returns the flight date and time
     */

    public void setFlightDateTime(LocalDateTime flightDateTime);
    /**
     * @param flightDateTime This parameter is used to set the flight date and time
     */

    public int getPrice();
    /**
     * @return This method returns the price of the flight
     */

    public void setPrice(int price);
    /**
     * @param price This parameter is used to set the price of the flight
     */

    public int getAvailableSeats();
    /**
     * @return This method returns the available seats of the flight
     */

    public void setAvailableSeats(int availableSeats);
    /**
     * @param availableSeats This parameter is used to set the available seats of the flight
     */

    public String[] getFlightSeats();
    /**
     * @return This method returns the seats of the flight
     */

    public void setFlightSeats(String[] flightSeats);
    /**
     * @param flightSeats This parameter is used to set the seats of the flight
     */
}
