public interface customerInterface {

    /*
     * This interface is for the customer class which is used to store the data
     */

    public String getName();
    /**
     * @return This method returns the name of the customer
     */

    public String getSurname();
    /**
     * @return This method returns the surname of the customer
     */


    public String getMail();
    /**
     * @return This method returns the mail of the customer
     */

    public String getPhoneNumber();
    /**
     * @return This method returns the phone number of the customer
     */

    public String getPassword();
    /**
     * @return This method returns the password of the customer
     */

    public int getUserType();
    /**
     * @return This method returns the user type of the customer
     */

    public void setName(String name);
    /**
     * @param name This method sets the name of the customer
     */

    public void setSurname(String surname);
    /**
     * @param surname This method sets the surname of the customer
     */

    public void setMail(String mail);
    /**
     * @param mail This method sets the mail of the customer
     */

    public void setPhoneNumber(String phoneNumber);
    /**
     * @param phoneNumber This method sets the phone number of the customer
     */
}