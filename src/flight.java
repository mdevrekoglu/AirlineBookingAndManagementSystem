public class flight {
    
    private int flightNo;
    private String flightDestination;
    private String flightDate;
    private String flightTime;
    private int price;
    private int availableSeats;
    private int[] flightSeats;

    public flight(int flightNo, String flightDestination, String flightDate, String flightTime, int price, int availableSeats, int[] flightSeats) {
        this.flightNo = flightNo;
        this.flightDestination = flightDestination;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
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

    public int[] getFlightSeats() {
        return flightSeats;
    }

    public void setFlightSeats(int[] flightSeats) {
        this.flightSeats = flightSeats;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }
}