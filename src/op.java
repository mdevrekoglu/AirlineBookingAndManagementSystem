import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
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
    private static ArrayList<user> users = new ArrayList<user>();
    private static ArrayList<flight> flights = new ArrayList<flight>();
    private static String usersFilePath = "users.txt";
    private static String flightsFilePath = "flights.txt";
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
                        int hour = cal.get(Calendar.HOUR);

                        clockLabel.setText(hour + ":" + minute + ":" + second + "         " + day + "/" + month + "/" + year);
                        clockLabel.setBounds(200, 400, 200, 50);
                        frame.getContentPane().add(clockLabel);
                        clockLabel.setVisible(true);
                        sleep(1000);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }
    public op() {
        clock();
        userReader();

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

        // place it left down corner
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

                        Boolean flag = false;

                        for (int i = 0; i < users.size(); i++) {
                            if ((id.getText().equals(users.get(i).getMail())
                                    || id.getText().equals(users.get(i).getPhoneNumber()))
                                    && passport.getText().equals(users.get(i).getPassword())) {
                                frame.setEnabled(true);
                                flag = true;
                                loggedInUserIndex = i;

                                if (users.get(i).getUserType() == 1) {
                                    adminMenu();
                                } else if (users.get(i).getUserType() == 0) {
                                    userMenu();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Wrong User Type! Your account is not admin!",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);

                                    users.get(i).setUserType(0);
                                    userWriter();
                                    userMenu();
                                }

                                JOptionPane.showMessageDialog(null,
                                        "Welcome " + users.get(i).getName() + " " + users.get(i).getSurname() + "!",
                                        "Welcome", JOptionPane.INFORMATION_MESSAGE);

                                userInfoLabel.setText(
                                        "Welcome " + users.get(i).getName() + " " + users.get(i).getSurname() + "!");

                                logInFrame.dispose();
                            }
                        }

                        if (!flag) {
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

                // add the new user to the users array list and close the signUpFrame
                signUp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        Boolean flag = false;
                        String total_error = "";
                        if (name.getText().equals("") || surname.getText().equals("")
                                || mail.getText().equals("") || password.getText().equals("")
                                || phoneNumber.getText().equals("")) {
                            flag = true;
                            total_error += "Please fill all the fields!\n";

                        }

                        // checking if the mail is valid

                        if (!mail.getText().contains("@gmail.com") && !mail.getText().contains("@hotmail.com")
                                && !mail.getText().contains("@outlook.com")) {
                            total_error += "Please enter a valid mail!\n";
                            flag = true;
                        }

                        if (phoneNumber.getText().length() != 11 || !phoneNumber.getText().startsWith("0")
                                || !phoneNumber.getText().matches("[0-9]+")) {
                            total_error += "Please enter a valid phone number!\n";
                            flag = true;
                        }

                        // checking password length

                        if (password.getText().length() < 8) {
                            total_error += "Password must be at least 8 characters!\n";
                            flag = true;
                        }

                        for (int i = 0; i < users.size(); i++) {
                            if (mail.getText().equals(users.get(i).getMail())
                                    || phoneNumber.getText().equals(users.get(i).getPhoneNumber())) {
                                total_error += "This mail or phone number is already used!\n";
                                flag = true;
                                break;
                            }
                        }
                        if (flag){
                            
                           JOptionPane.showMessageDialog(null, total_error, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                            
                        
                        if (!flag) {
                            int type = 0;
                            if (admin.getText().equals("admin")) {
                                type = 1;
                            }

                            users.add(new user(name.getText(), surname.getText(), mail.getText(), password.getText(),
                                    phoneNumber.getText(), type));
                            signUpFrame.dispose();
                            frame.setEnabled(true);
                            userWriter();
                        }
                    }
                });

                // when the signUpFrame is closed, the main frame will be enabled
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

    // Readers and Writers
    // ********************************************************************************************************************
    public static void userReader() {
        // name/surname/mail/password/phone number/usertype(0-> user, 1-> admin)

        try {

            BufferedReader reader = new BufferedReader(new FileReader(usersFilePath));

            // Read all file and assign to users array list
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("/");

                if (parts.length != 6) {
                    throw new Exception("File is corrupted! (users.txt)");
                }

                // if same mail is used

                /*
                 * for (int i = 0; i < users.size(); i++) {
                 * if (users.get(i).getMail().equals(parts[2])) {
                 * throw new Exception("Mail is already used!");
                 * }
                 * }
                 */

                user newUser = new user(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]));
                users.add(newUser);

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.exit(0);
        }
    }

    public static void userWriter() {
        try {
            // name/surname/mail/password/phone number/usertype(0-> user, 1-> admin)

            FileWriter myWriter = new FileWriter("users.txt");
            for (int i = 0; i < users.size(); i++) {
                myWriter.write(users.get(i).getName() + "/" + users.get(i).getSurname() + "/"
                        + users.get(i).getMail() + "/" + users.get(i).getPassword() + "/"
                        + users.get(i).getPhoneNumber() + "/" + users.get(i).getUserType() + "\n");
            }
            myWriter.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void flightWriter() {
        // flightNo/flightDestination/flightDate/flightTime/price/availableSeats/flightSeats
        try {
            FileWriter myWriter = new FileWriter("flights.txt");
            for (int i = 0; i < flights.size(); i++) {
                myWriter.write(flights.get(i).getFlightNo() + "/" + flights.get(i).getFlightDestination() + "/"
                        + flights.get(i).getFlightDate() + "/" + flights.get(i).getFlightTime() + "/"
                        + flights.get(i).getPrice() + "/" + flights.get(i).getAvailableSeats() + "/");

                for (int j = 0; j < 120; j++) {
                    if (j == 119)
                        myWriter.write(flights.get(i).getFlightSeats()[j] + "\n");
                    else
                        myWriter.write(flights.get(i).getFlightSeats()[j] + ";");
                }
            }
            myWriter.close();
        } catch (Exception e1) {
            e1.printStackTrace();
            System.exit(0);
        }

    }

    public static void flightReader() {
        // flightNo/flightDestination/flightDate/flightTime/price/availableSeats/flightSeats
        try {

            BufferedReader reader = new BufferedReader(new FileReader(flightsFilePath));

            // Read all file and assign to users array list
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("/");

                if (parts.length != 7)
                    throw new Exception("File is corrupted! (flights.txt)");

                String[] seats = parts[6].split(";");
                int[] seatsInt = new int[120];
                for (int i = 0; i < seats.length; i++) {
                    seatsInt[i] = Integer.parseInt(seats[i]);
                }

                flight newFlight = new flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3],
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), seatsInt);
                flights.add(newFlight);

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.exit(0);
        }
    }
    // ****************************************************************************************************************************************
}
