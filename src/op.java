import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class op  {

    // create a string array list
    private MySingleton mySingleton = MySingleton.getInstance();
    private static int loggedInUserIndex = -1;
    private static customer newCustomer;
    // Main Frame
    private static JFrame frame = new JFrame("Airline Booking and Management System");

    // Main Menu Buttons
    private static JButton logInButton = new JButton("Log In");
    private static JButton signUpButton = new JButton("Sign Up");
    private static JButton logOutButton = new JButton("Log Out");


    // User Label and Buttons
    private static JLabel userInfoLabel = new JLabel();
    private static JLabel clockLabel = new JLabel();
    private static JButton scheduleFlight = new JButton("Schedule Flight");
    private static JButton AccountSettings = new JButton("Account Settings");
    private static JButton buyTicket = new JButton("Buy Ticket");

    // Clock
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
                        int hour = cal.get(Calendar.HOUR_OF_DAY);

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

        // Clock Label and Operations
        clockLabel.setBounds(200, 400, 200, 50);
        frame.getContentPane().add(clockLabel);
        clockLabel.setVisible(true);
        clock();

        // Button and Label Operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(800, 200);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        logInButton.setBounds(200, 100, 100, 50);
        frame.getContentPane().add(logInButton);
        logInButton.setVisible(false);

        signUpButton.setBounds(200, 200, 100, 50);
        frame.getContentPane().add(signUpButton);
        signUpButton.setVisible(false);

        logOutButton.setBounds(0, 0, 90, 30);
        frame.getContentPane().add(logOutButton);
        logOutButton.setVisible(false);

        userInfoLabel.setBounds(120, 0, 400, 30);
        frame.getContentPane().add(userInfoLabel);
        userInfoLabel.setVisible(false);

        scheduleFlight.setBounds(0, 60, 150, 30);
        frame.getContentPane().add(scheduleFlight);
        scheduleFlight.setVisible(false);

        buyTicket.setBounds(160, 100, 150, 30);
        frame.getContentPane().add(buyTicket);
        buyTicket.setVisible(false);
        
        AccountSettings.setBounds(160, 200, 150, 30);
        frame.getContentPane().add(AccountSettings);
        AccountSettings.setVisible(false);

        // Main Menu Operations
        mainMenu();

        // If logged in button is clicked
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Set main menu unavailable
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

                // If log in button is clicked
                logIn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        // Search for user in database
                        int index = mySingleton.isCustomer(id.getText());
                        
                        // Check if user exists
                        if(index != -1){
                            if(mySingleton.getCustomerByIndex(index).getPassword().equals(passport.getText())){
                                loggedInUserIndex = index;
                                frame.setEnabled(true);
                                newCustomer=mySingleton.getCustomerByIndex(index);
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

        // If sign up button is clicked
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Set main menu unavailable
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

                // If sign up button is clicked
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

                        // Checking if mail and phone number unique
                        for(int i = 0; i < mySingleton.getCustomerSize(); i++){
                            if(mySingleton.getCustomerByIndex(i).getMail().equals(mail.getText())){
                                total_error += "This mail is already used!\n";
                                flag = true;
                                break;
                            }
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

        // When the login button is clicked
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                loggedInUserIndex = -1;
                mainMenu();
                userInfoLabel.setText("");
            }
        });

        // When the login button is clicked
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

        // When buy ticket button is clicked
        buyTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setEnabled(false);

                // Create a new frame
                JFrame buyTicketFrame = new JFrame("Buy Ticket");
                buyTicketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                buyTicketFrame.setSize(1400, 1000);

                buyTicketFrame.setLocation(200, 0);
                buyTicketFrame.getContentPane().setLayout(null);
                buyTicketFrame.setResizable(false);
                buyTicketFrame.setVisible(true);
        
                
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(10, 11, 850, 940);
                buyTicketFrame.getContentPane().add(scrollPane);
                
                MySingleton mySingleton = MySingleton.getInstance();
                ArrayList<flight> currenFlights = mySingleton.getFlights("", "");
                Object[][] data = new Object[currenFlights.size()][5];
        
                for (int i = 0; i < currenFlights.size(); i++) {                  
                    data[i][0] = currenFlights.get(i).getFlightNo();
                    data[i][1] = currenFlights.get(i).getFlightDestination();
                    data[i][2] = currenFlights.get(i).getFlightDateTime();
                    data[i][3] = currenFlights.get(i).getPrice();
                    data[i][4] = currenFlights.get(i).getAvailableSeats();
                }
                
                JTable table = new JTable();
                table.setModel(new DefaultTableModel(
                    data,
                    new String[] {
                        "Flight No", "Start - End", "Flight Date and Hour", "Price", "Available Seats"
                    }
                ) {
                    Class[] columnTypes = new Class[] {
                        Integer.class, String.class, String.class, Integer.class, Integer.class
                    };
                    public Class getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                    boolean[] columnEditables = new boolean[] {
                        false, false, false, false, false
                    };
                    public boolean isCellEditable(int row, int column) {
                        return columnEditables[column];
                    }
                });
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(1).setPreferredWidth(261);
                table.getColumnModel().getColumn(2).setResizable(false);
                table.getColumnModel().getColumn(2).setPreferredWidth(114);
                table.getColumnModel().getColumn(3).setResizable(false);
                table.getColumnModel().getColumn(3).setPreferredWidth(51);
                table.getColumnModel().getColumn(4).setResizable(false);
                table.getColumnModel().getColumn(4).setPreferredWidth(90);
                scrollPane.setViewportView(table);

                // Start Point
                JLabel startPointLabel = new JLabel("Start Point");
                startPointLabel.setBounds(900, 50, 85, 20);
                buyTicketFrame.getContentPane().add(startPointLabel);
                buyTicketFrame.setVisible(true);

                JTextField startPoint = new JTextField();
                startPoint.setBounds(900, 80, 146, 26);
                buyTicketFrame.getContentPane().add(startPoint);
                startPoint.setColumns(20);

                // End Point
                JLabel endPointLabel = new JLabel("End Point");
                endPointLabel.setBounds(900, 120, 85, 20);
                buyTicketFrame.getContentPane().add(endPointLabel);
                buyTicketFrame.setVisible(true);

                JTextField endPoint = new JTextField();
                endPoint.setBounds(900, 150, 146, 26);
                buyTicketFrame.getContentPane().add(endPoint);
                endPoint.setColumns(20);

                // Update button
                JButton update = new JButton("Update");
                update.setBounds(900, 200, 115, 29);
                buyTicketFrame.getContentPane().add(update);
                buyTicketFrame.setVisible(true);

                // Buy button
                JButton buy = new JButton("Buy");
                buy.setBounds(900, 250, 115, 29);
                buyTicketFrame.getContentPane().add(buy);
                buyTicketFrame.setVisible(true);

                // When update button is clicked
                update.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String start = startPoint.getText();
                        String end = endPoint.getText();
                        ArrayList<flight> currenFlights = mySingleton.getFlights(start, end);
                        Object[][] data = new Object[currenFlights.size()][5];
                
                        for (int i = 0; i < currenFlights.size(); i++) {                  
                            data[i][0] = currenFlights.get(i).getFlightNo();
                            data[i][1] = currenFlights.get(i).getFlightDestination();
                            data[i][2] = currenFlights.get(i).getFlightDateTime();
                            data[i][3] = currenFlights.get(i).getPrice();
                            data[i][4] = currenFlights.get(i).getAvailableSeats();
                        }
                        
                        JTable table = new JTable();
                        table.setModel(new DefaultTableModel(
                            data,
                            new String[] {
                                "Flight No", "Start - End", "Flight Date and Hour", "Price", "Available Seats"
                            }
                        ) {
                            Class[] columnTypes = new Class[] {
                                Integer.class, String.class, String.class, Integer.class, Integer.class
                            };
                            public Class getColumnClass(int columnIndex) {
                                return columnTypes[columnIndex];
                            }
                            boolean[] columnEditables = new boolean[] {
                                false, false, false, false, false
                            };
                            public boolean isCellEditable(int row, int column) {
                                return columnEditables[column];
                            }
                        });
                        table.getColumnModel().getColumn(0).setResizable(false);
                        table.getColumnModel().getColumn(1).setPreferredWidth(261);
                        table.getColumnModel().getColumn(2).setResizable(false);
                        table.getColumnModel().getColumn(2).setPreferredWidth(114);
                        table.getColumnModel().getColumn(3).setResizable(false);
                        table.getColumnModel().getColumn(3).setPreferredWidth(51);
                        table.getColumnModel().getColumn(4).setResizable(false);
                        table.getColumnModel().getColumn(4).setPreferredWidth(90);
                        scrollPane.setViewportView(table);
                    }
                });

                // When buy button is clicked
                buy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = table.getSelectedRow();
                        int flightNo = (int) table.getModel().getValueAt(row, 0);
                        int price = (int) table.getModel().getValueAt(row, 3);
                        int availableSeats = (int) table.getModel().getValueAt(row, 4);
                        
                        System.out.println("Flight No: " + flightNo);
                        System.out.println("Price: " + price);
                        System.out.println("Available Seats: " + availableSeats);



                    }
                });

                // If buyticket frame is closed, enable the main frame
                buyTicketFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

            }
        });
        
        AccountSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	frame.setEnabled(false);

                JFrame AccountSettingsFrame = new JFrame("Sign Up");
                AccountSettingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                AccountSettingsFrame.setSize(500, 600);
                AccountSettingsFrame.setLocation(800, 200);
                AccountSettingsFrame.getContentPane().setLayout(null);
                AccountSettingsFrame.setResizable(false);
                AccountSettingsFrame.setVisible(true);

                JLabel nameLabel = new JLabel("Name");
                nameLabel.setBounds(55, 91, 188, 20);
                AccountSettingsFrame.getContentPane().add(nameLabel);

                JTextField name = new JTextField();
                name.setBounds(192, 88, 146, 26);
                AccountSettingsFrame.getContentPane().add(name);
                name.setColumns(10);

                JLabel surnameLabel = new JLabel("Surname");
                surnameLabel.setBounds(55, 151, 69, 20);
                AccountSettingsFrame.getContentPane().add(surnameLabel);
                AccountSettingsFrame.setVisible(true);

                JTextField surname = new JTextField();
                surname.setBounds(192, 148, 146, 26);
                AccountSettingsFrame.getContentPane().add(surname);
                surname.setColumns(10);

                JLabel mailLabel = new JLabel("Mail");
                mailLabel.setBounds(55, 211, 69, 20);
                AccountSettingsFrame.getContentPane().add(mailLabel);
                AccountSettingsFrame.setVisible(true);

                JTextField mail = new JTextField();
                mail.setBounds(192, 208, 146, 26);
                AccountSettingsFrame.getContentPane().add(mail);
                mail.setColumns(10);

                JLabel passwordLabel = new JLabel("Password");
                passwordLabel.setBounds(55, 271, 69, 20);
                AccountSettingsFrame.getContentPane().add(passwordLabel);
                AccountSettingsFrame.setVisible(true);

                JTextField password = new JPasswordField();
                password.setBounds(192, 268, 146, 26);
                AccountSettingsFrame.getContentPane().add(password);
                password.setColumns(10);

                JLabel phoneNumberLabel = new JLabel("Phone Number");
                phoneNumberLabel.setBounds(55, 331, 85, 20);
                AccountSettingsFrame.getContentPane().add(phoneNumberLabel);
                AccountSettingsFrame.setVisible(true);

                JTextField phoneNumber = new JTextField();
                phoneNumber.setBounds(192, 328, 146, 26);
                AccountSettingsFrame.getContentPane().add(phoneNumber);
                phoneNumber.setColumns(10);

                JButton AccountSettings = new JButton("Save");
                AccountSettings.setBounds(192, 450, 115, 29);
                AccountSettingsFrame.getContentPane().add(AccountSettings);
                // If save button is clicked
                AccountSettings.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                 
                        Boolean flag = false;
                        String total_error = "";
                        customer tempCustomer=newCustomer;

                        // Check if name and surname contains only letters
                        if(!name.getText().equals("")) {
	                        if (!name.getText().matches("[a-zA-Z]+")) {
	                            total_error += "Name must contain only letters!\n";
	                            flag = true;
	                        }
	                        else if (name.getText().equals(newCustomer.getName())) {
	                            total_error += "Name shouldn't be the same as last  name!\n";
	                            flag = true;
	                        }
	                        else{
	                        	tempCustomer.setName(name.getText());
	                        }
                        }
                        
                        if(!surname.getText().equals("")) {
	                        if(!surname.getText().matches("[a-zA-Z]+")) {
	                        	total_error += "Surname must contain only letters!\n";
	                            flag = true;
	                        }
	                        else if (surname.getText().equals(newCustomer.getName())) {
	                            total_error += "Surname shouldn't be the same as last surname!\n";
	                            flag = true;
	                        }
	                        else{
	                        	tempCustomer.setSurname(surname.getText());
	                        }
                        }
                        // Checking if the mail and phone number is valid
                        if(!mail.getText().equals("")) {
	                        mail.setText(mail.getText().replaceAll(" ", ""));
	                        if (!mail.getText().endsWith("@gmail.com") 
	                        		&& !mail.getText().endsWith("@hotmail.com")
	                                && !mail.getText().endsWith("@outlook.com") && !mail.getText().endsWith("@yahoo.com")) {
	                        	
	                            total_error += "Please enter a valid mail!\n";
	                            flag = true;
	                        }
	                        else if(mail.equals(newCustomer.getMail())){
	                        	
	                            total_error += "This mail can not be the same as your last mail!\n";
	                            flag = true;
	                        }
                        }
                        
                        
                        // Checking if the phone number is valid
                        if(!phoneNumber.getText().equals("")) {
	                        if ((phoneNumber.getText().length() != 11 
	                        		|| !phoneNumber.getText().startsWith("0")
	                                || !phoneNumber.getText().matches("[0-9]+"))) {
	                            total_error += "Please enter a valid phone number!\n";
	                            flag = true;
	                        }
	                        else if(phoneNumber.equals(newCustomer.getPhoneNumber())){
	                            total_error += "This phone number can not be the same as your last phone Number!\n";
	                            flag = true;
	                        }
                        }
                       
                        
                        // Checking password length
                        if(!password.getText().equals("")) {
	                        if (password.getText().length() < 8) {
	                            total_error += "Password must be at least 8 characters!\n";
	                            flag = true;
	                        }
	                        else if(password.equals(newCustomer.getPassword())){
	                            total_error += "This password can not be the same as your last password!\n";
	                            flag = true;
	                        }
	                        else{
	                        	tempCustomer.setPassword(password.getText());
	                        }
                        }
                        Boolean checkMail=true,checkPhoneNumber=true;
                        // Checking if mail and phone number unique
                        if(mail.getText().equals("")){
                        	checkMail=false;
                        }
                    	if(phoneNumber.getText().equals("")){
                    		checkPhoneNumber=false;
                        }
                    	for(int i = 0; i < mySingleton.getCustomerSize(); i++){
                            if(checkMail&&mySingleton.getCustomerByIndex(i).getMail().equals(mail.getText())){
                                total_error += "This mail is already used!\n";
                                flag = true;
                                break;
                            }
                            if(checkPhoneNumber&&mySingleton.getCustomerByIndex(i).getPhoneNumber().equals(phoneNumber.getText())){
                                total_error += "This phone number is already used!\n";
                                flag = true;
                                break;
                            }
                       }
                       if(checkMail) { //if mail has changed
                    	   tempCustomer.setMail(mail.getText());
                       }
                       if(checkPhoneNumber) {//if phone number has changed
                    	   tempCustomer.setPhoneNumber(phoneNumber.getText());
                       }
                        if(flag){
                            JOptionPane.showMessageDialog(null, total_error, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                        	//Checking which informations has been changed
                        	mySingleton.removeCustomer(newCustomer);
                        	mySingleton.addCustomer(tempCustomer);
                        	newCustomer=tempCustomer;
                            AccountSettingsFrame.dispose();
                            frame.setEnabled(true);
                            JOptionPane.showMessageDialog(null, tempCustomer.getName() , "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                //1/Semarang-Kochi/21.12.2023/11:16-13:44/295/120/
                //-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1
                AccountSettingsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
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
        buyTicket.setVisible(false);
        AccountSettings.setVisible(false);
    }

    public static void adminMenu() {
        logInButton.setVisible(false);
        signUpButton.setVisible(false);
        logOutButton.setVisible(true);
        userInfoLabel.setVisible(true);
        scheduleFlight.setVisible(true);
        buyTicket.setVisible(false);
    }
    
    public static void userMenu() {
        logInButton.setVisible(false);
        signUpButton.setVisible(false);
        logOutButton.setVisible(true);
        userInfoLabel.setVisible(true);
        scheduleFlight.setVisible(false);
        buyTicket.setVisible(true);
        AccountSettings.setVisible(true);
    }
   
    // ***************************************************************************************************************************
}
