import java.time.LocalDateTime;

public interface flightInterface {

    public int getFlightNo();

    public void setFlightNo(int flightNo);

    public String getFlightDestination();

    public void setFlightDestination(String flightDestination);

    public LocalDateTime getFlightDateTime();

    public void setFlightDateTime(LocalDateTime flightDateTime);

    public int getPrice();

    public void setPrice(int price);

    public int getAvailableSeats();

    public void setAvailableSeats(int availableSeats);

    public String[] getFlightSeats();

    public void setFlightSeats(String[] flightSeats);

}
