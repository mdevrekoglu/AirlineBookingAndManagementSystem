public class customer {
    
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phoneNumber;
    private int userType;

    public customer(String name, String surname, String mail, String password, String phoneNumber, int userType) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
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
}
