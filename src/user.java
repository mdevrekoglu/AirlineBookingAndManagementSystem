public class user {
    // name,surname,mail,password,phone number,usertype(0-> user, 1-> admin)

    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phoneNumber;
    private int userType;

    public user(String name, String surname, String mail, String password, String phoneNumber, int userType) {
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
}
