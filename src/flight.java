import java.time.LocalDateTime;

public class flight implements flightInterface{
    
    private int flightNo;
    private String flightDestination;
    private LocalDateTime flightDateTime;
    private int price;
    private int availableSeats;
    private String[] flightSeats;

    public flight(int flightNo, String flightDestination, String flightDateTime, int price, int availableSeats, String[] flightSeats) {
        this.flightNo = flightNo;
        this.flightDestination = flightDestination;

        String[] parts = flightDateTime.split("T");
        String[] date = parts[0].split("-");
        String[] time = parts[1].split(":");
        this.flightDateTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));

        this.price = price;
        this.availableSeats = availableSeats;
        this.flightSeats = flightSeats;
    }

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightDestination() {
        return flightDestination;
    }
    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String[] getFlightSeats() {
        return flightSeats;
    }

    public void setFlightSeats(String[] flightSeats) {
        this.flightSeats = flightSeats;
    }

    public LocalDateTime getFlightDateTime() {
        return flightDateTime;
    }

    public void setFlightDateTime(LocalDateTime flightDateTime) {
        this.flightDateTime = flightDateTime;
    }

    public String getFlightDateTimeString() {
        return flightDateTime.toString();
    }

}