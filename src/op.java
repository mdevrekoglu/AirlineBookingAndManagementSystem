import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class op  {

    // create a string array list
    private MySingleton mySingleton = MySingleton.getInstance();
    private static int loggedInUserIndex = -1;

    // Main Frame
    private static JFrame frame = new JFrame("APP");

    // Main Menu Buttons
    private static JButton logInButton = new JButton("Log In");
    
    private static JButton signUpButton = new JButton("Sign Up");
    private static JButton logOutButton = new JButton("Log Out");

    // User Label and Buttons
    private static JLabel userInfoLabel = new JLabel();
    private static JButton scheduleFlight = new JButton("Schedule Flight");


    JLabel clockLabel = new JLabel();
    public void clock(){
        Thread clock = new Thread(){
            public void run(){
                try{
                    for(;;){
                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get((Calendar.MONTH)+1)%12;
                        int year = cal.get(Calendar.YEAR);

                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = (cal.get(Calendar.HOUR) + 12) % 24;

                        String clock = String.format("%02d:%02d:%02d", hour, minute, second);
                        String date = String.format("%02d/%02d/%02d", day, month, year);
                        clockLabel.setText(clock + "   " + date);
                        sleep(250);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    public op() {

        clockLabel.setBounds(200, 400, 200, 50);
        frame.getContentPane().add(clockLabel);
        clockLabel.setVisible(true);

        clock();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(800, 200);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        logInButton.setBounds(200, 100, 100, 50);
        frame.getContentPane().add(logInButton);
        logInButton.setVisible(false);

        signUpButton.setBounds(200, 200, 100, 50);
        frame.getContentPane().add(signUpButton);
        signUpButton.setVisible(false);

        // place it left up corner
        logOutButton.setBounds(0, 0, 90, 30);
        frame.getContentPane().add(logOutButton);
        logOutButton.setVisible(false);

        // place userInfoLabel next to logOutButton
        userInfoLabel.setBounds(120, 0, 400, 30);
        frame.getContentPane().add(userInfoLabel);
        userInfoLabel.setVisible(false);

        // place scheduleFlight button unter to userInfoLabel
        scheduleFlight.setBounds(0, 60, 150, 30);
        frame.getContentPane().add(scheduleFlight);
        scheduleFlight.setVisible(false);

        mainMenu();

        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setEnabled(false);

                JFrame logInFrame = new JFrame("Log In");
                logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                logInFrame.setSize(500, 500);
                logInFrame.setLocation(800, 200);
                logInFrame.getContentPane().setLayout(null);
                logInFrame.setResizable(false);
                logInFrame.setVisible(true);

                JLabel idLabel = new JLabel("Number or Email");
                idLabel.setBounds(55, 91, 188, 20);
                logInFrame.getContentPane().add(idLabel);

                JTextField id = new JTextField();
                id.setBounds(192, 88, 146, 26);
                logInFrame.getContentPane().add(id);
                id.setColumns(10);

                JLabel passwordLabel = new JLabel("Password");
                passwordLabel.setBounds(55, 151, 69, 20);
                logInFrame.getContentPane().add(passwordLabel);
                logInFrame.setVisible(true);

                JTextField passport = new JPasswordField();
                passport.setBounds(192, 148, 146, 26);
                logInFrame.getContentPane().add(passport);
                passport.setColumns(10);

                JButton logIn = new JButton("Log In");
                logIn.setBounds(192, 200, 115, 29);
                logInFrame.getContentPane().add(logIn);

                logIn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        int index = mySingleton.isCustomer(id.getText());

                        // Check if user exists
                        if(index != -1){
                            if(mySingleton.getCustomers().get(index).getPassword().equals(passport.getText())){
                                loggedInUserIndex = index;
                                frame.setEnabled(true);
                                logInFrame.dispose();
                                
                                // Show welcome message
                                JOptionPane.showMessageDialog(null, "Welcome " + mySingleton.getCustomers().get(index).getName() + " " + mySingleton.getCustomers().get(index).getSurname(), "Welcome",
                                    JOptionPane.INFORMATION_MESSAGE);

                                // Show user info
                                userInfoLabel.setText("Welcome " + mySingleton.getCustomers().get(index).getName() + " " + mySingleton.getCustomers().get(index).getSurname());

                                // Show menu depending on user type
                                if(mySingleton.getCustomers().get(index).getUserType() == 1){
                                    adminMenu();
                                }else if(mySingleton.getCustomers().get(index).getUserType() == 0){
                                    userMenu();
                                }else{
                                    userMenu();
                                    mySingleton.getCustomers().get(index).setUserType(0);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Wrong ID or Password!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Wrong ID or Password!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // when the logInFrame is closed, the main frame will be enabled
                logInFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setEnabled(false);

                JFrame signUpFrame = new JFrame("Sign Up");
                signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                signUpFrame.setSize(500, 600);
                signUpFrame.setLocation(800, 200);
                signUpFrame.getContentPane().setLayout(null);
                signUpFrame.setResizable(false);
                signUpFrame.setVisible(true);

                JLabel nameLabel = new JLabel("Name");
                nameLabel.setBounds(55, 91, 188, 20);
                signUpFrame.getContentPane().add(nameLabel);

                JTextField name = new JTextField();
                name.setBounds(192, 88, 146, 26);
                signUpFrame.getContentPane().add(name);
                name.setColumns(10);

                JLabel surnameLabel = new JLabel("Surname");
                surnameLabel.setBounds(55, 151, 69, 20);
                signUpFrame.getContentPane().add(surnameLabel);
                signUpFrame.setVisible(true);

                JTextField surname = new JTextField();
                surname.setBounds(192, 148, 146, 26);
                signUpFrame.getContentPane().add(surname);
                surname.setColumns(10);

                JLabel mailLabel = new JLabel("Mail");
                mailLabel.setBounds(55, 211, 69, 20);
                signUpFrame.getContentPane().add(mailLabel);
                signUpFrame.setVisible(true);

                JTextField mail = new JTextField();
                mail.setBounds(192, 208, 146, 26);
                signUpFrame.getContentPane().add(mail);
                mail.setColumns(10);

                JLabel passwordLabel = new JLabel("Password");
                passwordLabel.setBounds(55, 271, 69, 20);
                signUpFrame.getContentPane().add(passwordLabel);
                signUpFrame.setVisible(true);

                JTextField password = new JPasswordField();
                password.setBounds(192, 268, 146, 26);
                signUpFrame.getContentPane().add(password);
                password.setColumns(10);

                JLabel phoneNumberLabel = new JLabel("Phone Number");
                phoneNumberLabel.setBounds(55, 331, 85, 20);
                signUpFrame.getContentPane().add(phoneNumberLabel);
                signUpFrame.setVisible(true);

                JTextField phoneNumber = new JTextField();
                phoneNumber.setBounds(192, 328, 146, 26);
                signUpFrame.getContentPane().add(phoneNumber);
                phoneNumber.setColumns(10);

                JLabel adminLabel = new JLabel("Admin Password");
                adminLabel.setBounds(55, 391, 100, 20);
                signUpFrame.getContentPane().add(adminLabel);
                signUpFrame.setVisible(true);

                JTextField admin = new JPasswordField();
                admin.setBounds(192, 388, 146, 26);
                signUpFrame.getContentPane().add(admin);
                admin.setColumns(10);

                JButton signUp = new JButton("Sign Up");
                signUp.setBounds(192, 450, 115, 29);
                signUpFrame.getContentPane().add(signUp);

                // When the sign up button is clicked
                signUp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                 
                        Boolean flag = false;
                        String total_error = "";

                        // Check if all the fields are filled
                        if (name.getText().equals("") || surname.getText().equals("")
                                || mail.getText().equals("") || password.getText().equals("")
                                || phoneNumber.getText().equals("")) {
                            flag = true;
                            total_error += "Please fill all the fields!\n";
                        }

                        // Check if name and surname contains only letters
                        if (!name.getText().matches("[a-zA-Z]+") || !surname.getText().matches("[a-zA-Z]+")) {
                            total_error += "Name and surname must contain only letters!\n";
                            flag = true;
                        }

                        // Checking if the mail and phone number is valid
                        mail.setText(mail.getText().replaceAll(" ", ""));
                        if (!mail.getText().endsWith("@gmail.com") && !mail.getText().endsWith("@hotmail.com")
                                && !mail.getText().endsWith("@outlook.com") && !mail.getText().endsWith("@yahoo.com")) {
                            total_error += "Please enter a valid mail!\n";
                            flag = true;
                        }

                        // Checking if the phone number is valid
                        if (phoneNumber.getText().length() != 11 || !phoneNumber.getText().startsWith("0")
                                || !phoneNumber.getText().matches("[0-9]+")) {
                            total_error += "Please enter a valid phone number!\n";
                            flag = true;
                        }

                        // Checking password length
                        if (password.getText().length() < 8) {
                            total_error += "Password must be at least 8 characters!\n";
                            flag = true;
                        }

                        // Checking if mail unique
                        for(int i = 0; i < mySingleton.getCustomerSize(); i++){
                            if(mySingleton.getCustomerByIndex(i).getMail().equals(mail.getText())){
                                total_error += "This mail is already used!\n";
                                flag = true;
                                break;
                            }
                        }

                        // Checking if phone number unique
                        for(int i = 0; i < mySingleton.getCustomerSize(); i++){
                            if(mySingleton.getCustomerByIndex(i).getPhoneNumber().equals(phoneNumber.getText())){
                                total_error += "This phone number is already used!\n";
                                flag = true;
                                break;
                            }
                        }

                        // Checking if admin password is correct
                        if(flag){
                            JOptionPane.showMessageDialog(null, total_error, "Error", JOptionPane.ERROR_MESSAGE);
                        }else{

                            // Creating a new customer
                            mySingleton.addCustomer(new customer(name.getText(), surname.getText(), mail.getText(), password.getText(), phoneNumber.getText(), 
                            admin.getText().equals("admin") ? 1 : 0, new ArrayList<Integer>()));

                            signUpFrame.dispose();
                            frame.setEnabled(true);

                            JOptionPane.showMessageDialog(null, "You have successfully signed up!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

                // When the signUpFrame is closed, the main frame will be enabled
                signUpFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                loggedInUserIndex = -1;
                mainMenu();
                userInfoLabel.setText("");
            }
        });

        scheduleFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setEnabled(false);

                JFrame scheduleFlightFrame = new JFrame("Schedule Flight");
                scheduleFlightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                scheduleFlightFrame.setSize(500, 600);
                scheduleFlightFrame.setLocation(800, 200);
                scheduleFlightFrame.getContentPane().setLayout(null);
                scheduleFlightFrame.setResizable(false);
                scheduleFlightFrame.setVisible(true);

                JLabel flightNumberLabel = new JLabel("Flight Number");
                flightNumberLabel.setBounds(55, 91, 188, 20);
                scheduleFlightFrame.getContentPane().add(flightNumberLabel);

                JTextField flightNumber = new JTextField();
                flightNumber.setBounds(192, 88, 146, 26);
                scheduleFlightFrame.getContentPane().add(flightNumber);
                flightNumber.setColumns(10);

                JLabel destinationLabel = new JLabel("Destination");
                destinationLabel.setBounds(55, 151, 69, 20);
                scheduleFlightFrame.getContentPane().add(destinationLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField destination = new JTextField();
                destination.setBounds(192, 148, 146, 26);
                scheduleFlightFrame.getContentPane().add(destination);
                destination.setColumns(10);

                JLabel dateLabel = new JLabel("Date");
                dateLabel.setBounds(55, 271, 69, 20);
                scheduleFlightFrame.getContentPane().add(dateLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField date = new JTextField();
                date.setBounds(192, 268, 146, 26);
                scheduleFlightFrame.getContentPane().add(date);
                date.setColumns(10);

                JLabel timeLabel = new JLabel("Time");
                timeLabel.setBounds(55, 331, 85, 20);
                scheduleFlightFrame.getContentPane().add(timeLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField time = new JTextField();
                time.setBounds(192, 328, 146, 26);

                scheduleFlightFrame.getContentPane().add(time);
                time.setColumns(10);

                JLabel priceLabel = new JLabel("Price");
                priceLabel.setBounds(55, 391, 85, 20);
                scheduleFlightFrame.getContentPane().add(priceLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField price = new JTextField();
                price.setBounds(192, 388, 146, 26);
                scheduleFlightFrame.getContentPane().add(price);
                price.setColumns(10);

                JButton scheduleFlight = new JButton("Schedule Flight");
                scheduleFlight.setBounds(192, 450, 130, 29);
                scheduleFlightFrame.getContentPane().add(scheduleFlight);

                // 10047/Paris-Istanbul/01.04.2023/10:10-12:10/100/120/-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1

                scheduleFlightFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

            }
        });

    }

    // Menus
    // ********************************************************************************************************************
    public static void mainMenu() {
        logInButton.setVisible(true);
        signUpButton.setVisible(true);
        logOutButton.setVisible(false);
        userInfoLabel.setVisible(false);
        scheduleFlight.setVisible(false);
    }

    public static void adminMenu() {
        logInButton.setVisible(false);
        signUpButton.setVisible(false);
        logOutButton.setVisible(true);
        userInfoLabel.setVisible(true);
        scheduleFlight.setVisible(true);
    }

    public static void userMenu() {
        logInButton.setVisible(false);
        signUpButton.setVisible(false);
        logOutButton.setVisible(true);
        userInfoLabel.setVisible(true);
        scheduleFlight.setVisible(false);
    }
   
    // ***************************************************************************************************************************
}
