public interface MySingletonInterface {

    /*
     * This interface is for the MySingleton class which is used to store the data
     * of the program
     * And also it is used to get the data from the file and write the data to the
     * file
     * 
     */

    public void customerReader();
    /**
     * This method is used to read the data from the file and store it in the
     * program
     * It does not take any parameters because name of the file is already defined
     * in the MySingleton class
     */

    public void flightReader();
    /**
     * This method is used to read the data from the file and store it in the
     * program
     * It does not take any parameters because name of the file is already defined
     * in the MySingleton class
     */

    public void customerWriter();
    /**
     * This method is used to write the data to the file
     * It does not take any parameters because name of the file is already defined
     * in the MySingleton class
     */

    public void flightWriter();
    /**
     * This method is used to write the data to the file
     * It does not take any parameters because name of the file is already defined
     * in the MySingleton class
     */

    public customer getCustomerByIndex(int index);
    /**
     * @param index This parameter is used to get the customer from the arraylist
     * 
     * @return This method returns the customer object
     * 
     * This method is used to get the customer from the arraylist
     */

    public flight getFlightByIndex(int index);
    /**
     * @param index This parameter is used to get the flight from the arraylist
     * 
     * @return This method returns the flight object
     * 
     * This method is used to get the flight from the arraylist
     */

    public flight getFlightByFlightNo(int flightNo);
    /**
     * @param flightNo This parameter is used to get the flight from the arraylist
     * 
     * @return This method returns the flight object
     * 
     * This method is used to get the flight from the arraylist
     */

    public int getCustomerSize();
    /**
     * @return This method returns the size of the customer arraylist
     * 
     * This method is used to get the size of the customer arraylist
     */

    public int getFlightSize();
    /**
     * @return This method returns the size of the flight arraylist
     * 
     * This method is used to get the size of the flight arraylist
     */

    public int isCustomer(String data);
    /**
     * @param data This parameter is used to get the customer from the arraylist
     * 
     * @return This method returns the index of the customer
     * 
     * This method is used to get the customer from the arraylist
     */

    public int isFlight(int flightNo);
    /**
     * @param flightNo This parameter is used to get the flight from the arraylist
     * 
     * @return This method returns the index of the flight
     * 
     * This method is used to get the flight from the arraylist
     */

    public void addCustomer(customer customer);
    /**
     * @param customer This parameter is used to add the customer to the arraylist
     * 
     * This method is used to add the customer to the arraylist
     */

    public void addFlight(flight flight);
    /**
     * @param flight This parameter is used to add the flight to the arraylist
     * 
     * This method is used to add the flight to the arraylist
     */

    public void removeCustomer(customer customer);
    /**
     * @param customer This parameter is used to remove the customer from the
     *                 arraylist
     * 
     * This method is used to remove the customer from the arraylist
     */

    public void removeFlight(flight flight);
    /**
     * @param flight This parameter is used to remove the flight from the arraylist
     * 
     * This method is used to remove the flight from the arraylist
     */

}