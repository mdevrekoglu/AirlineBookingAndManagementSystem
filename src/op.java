
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class op {

    // Singleton
    private MySingleton mySingleton = MySingleton.getInstance();
    private static customer currentCustomer = null;

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
    private static JButton removeUser = new JButton("Remove User");
    private static JButton removeFlight = new JButton("Remove Flight");

    private static JButton AccountSettings = new JButton("Account Settings");
    private static JButton buyTicket = new JButton("Buy Ticket");
    private static JButton MySchedule = new JButton("My Schedule");

    // Clock
    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    for (;;) {
                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get((Calendar.MONTH) + 1) % 12;
                        int year = cal.get(Calendar.YEAR);

                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);

                        String clock = String.format("%02d:%02d:%02d", hour, minute, second);
                        String date = String.format("%02d/%02d/%02d", day, month, year);
                        clockLabel.setText(clock + "   " + date);
                        sleep(250);
                    }
                } catch (Exception e) {
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

        scheduleFlight.setBounds(160, 150, 150, 30);
        frame.getContentPane().add(scheduleFlight);
        scheduleFlight.setVisible(false);

        buyTicket.setBounds(160, 150, 150, 30);
        frame.getContentPane().add(buyTicket);
        buyTicket.setVisible(false);

        MySchedule.setBounds(160, 200, 150, 30);
        frame.getContentPane().add(MySchedule);
        MySchedule.setVisible(false);

        removeUser.setBounds(160, 200, 150, 30);
        frame.getContentPane().add(removeUser);
        removeUser.setVisible(false);

        removeFlight.setBounds(160, 250, 150, 30);
        frame.getContentPane().add(removeFlight);
        removeFlight.setVisible(false);

        AccountSettings.setBounds(160, 250, 150, 30);
        frame.getContentPane().add(AccountSettings);
        AccountSettings.setVisible(false);

        // Main Menu Operations
        mainMenu();

        // If logged in button is clicked
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Set main menu unavailable
                frame.setEnabled(false);

                // Log In Frame
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
                        if (index != -1) {
                            if (mySingleton.getCustomerByIndex(index).getPassword().equals(passport.getText())) {
                                frame.setEnabled(true);
                                currentCustomer = mySingleton.getCustomerByIndex(index);
                                logInFrame.dispose();

                                // Show welcome message
                                JOptionPane.showMessageDialog(null,
                                        "Welcome " + mySingleton.getCustomers().get(index).getName() + " "
                                                + mySingleton.getCustomers().get(index).getSurname(),
                                        "Welcome",
                                        JOptionPane.INFORMATION_MESSAGE);

                                // Show user info
                                userInfoLabel.setText("Welcome " + mySingleton.getCustomers().get(index).getName() + " "
                                        + mySingleton.getCustomers().get(index).getSurname());

                                // Show menu depending on user type
                                if (mySingleton.getCustomers().get(index).getUserType() == 1) {
                                    adminMenu();
                                } else if (mySingleton.getCustomers().get(index).getUserType() == 0) {
                                    userMenu();
                                } else {
                                    userMenu();
                                    mySingleton.getCustomers().get(index).setUserType(0);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong ID or Password!", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
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

                // Sign Up Frame
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
                        for (int i = 0; i < mySingleton.getCustomerSize(); i++) {
                            if (mySingleton.getCustomerByIndex(i).getMail().equals(mail.getText())) {
                                total_error += "This mail is already used!\n";
                                flag = true;
                                break;
                            }
                            if (mySingleton.getCustomerByIndex(i).getPhoneNumber().equals(phoneNumber.getText())) {
                                total_error += "This phone number is already used!\n";
                                flag = true;
                                break;
                            }
                        }

                        // Checking if admin password is correct
                        if (flag) {
                            JOptionPane.showMessageDialog(null, total_error, "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            // Creating a new customer
                            mySingleton.addCustomer(new customer(name.getText(), surname.getText(), mail.getText(),
                                    password.getText(), phoneNumber.getText(),
                                    admin.getText().equals("admin") ? 1 : 0, new ArrayList<Integer>()));

                            signUpFrame.dispose();
                            frame.setEnabled(true);

                            JOptionPane.showMessageDialog(null, "You have successfully signed up!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
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

                currentCustomer = null;
                mainMenu();
                userInfoLabel.setText("");
            }
        });

        // When the schedule flight button is clicked
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
                destinationLabel.setBounds(55, 135, 69, 20);
                scheduleFlightFrame.getContentPane().add(destinationLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField destination = new JTextField();
                destination.setBounds(192, 132, 146, 26);
                scheduleFlightFrame.getContentPane().add(destination);
                destination.setColumns(10);

                JLabel dateLabel = new JLabel("Date");
                dateLabel.setBounds(55, 182, 69, 20);
                scheduleFlightFrame.getContentPane().add(dateLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField date = new JTextField();
                date.setBounds(192, 179, 146, 26);
                scheduleFlightFrame.getContentPane().add(date);
                date.setColumns(10);

                JLabel timeLabel = new JLabel("Time");
                timeLabel.setBounds(55, 229, 85, 20);
                scheduleFlightFrame.getContentPane().add(timeLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField time = new JTextField();
                time.setBounds(192, 226, 146, 26);
                scheduleFlightFrame.getContentPane().add(time);
                time.setColumns(10);

                JLabel priceLabel = new JLabel("Price");
                priceLabel.setBounds(55, 273, 85, 20);
                scheduleFlightFrame.getContentPane().add(priceLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField price = new JTextField();
                price.setBounds(192, 270, 146, 26);
                scheduleFlightFrame.getContentPane().add(price);
                price.setColumns(10);

                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                JLabel capacityLabel = new JLabel("Capacity");
                capacityLabel.setBounds(55, 317, 85, 20);
                scheduleFlightFrame.getContentPane().add(capacityLabel);
                scheduleFlightFrame.setVisible(true);

                JTextField capacity = new JTextField();
                capacity.setBounds(192, 314, 146, 26);
                scheduleFlightFrame.getContentPane().add(capacity);
                capacity.setColumns(10);

                JButton addFlight = new JButton("Add Flight");
                addFlight.setBounds(192, 364, 130, 29);
                scheduleFlightFrame.getContentPane().add(addFlight);
                addFlight.setVisible(true);

                // When the scheduleFlight button is clicked
                addFlight.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        boolean flag = false;
                        String total_error = "";

                        if (flightNumber.getText().equals("") || destination.getText().equals("")
                                || date.getText().equals("") || time.getText().equals("")
                                || price.getText().equals("") || capacity.getText().equals("")) {
                            flag = true;
                            total_error += "Please fill all the fields!\n";
                        }

                        if (!flightNumber.getText().matches("[0-9]+")) {
                            total_error += "Flight number must be contains only integer!\n";
                            flag = true;
                        }

                        if (!destination.getText().contains("-")) {
                            total_error += "Destination must be filled From-To !\n";
                            flag = true;
                        }
                        if (!date.getText().matches("\\d{4}-\\d{2}-\\d{2}")
                                && !date.getText().startsWith("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                            total_error += "Date must be filled YYYY-MM-DD !\n";
                            flag = true;
                        }
                        if (!time.getText().equals("\\d{2}:\\d{2}")
                                || !time.getText().contains(":")) {
                            total_error += "Time must be filled HH:MM !\n";
                            flag = true;
                        }
                        if (!price.getText().matches("[0-9]+")) {
                            total_error += "Price must be contains only positive integer!\n";
                            flag = true;
                        }
                        if (!capacity.getText().matches("[0-9]+")) {
                            total_error += "Capacity must be contains only positive integer!\n";
                            flag = true;
                        }

                        try {
                            if (!flag && (mySingleton.isFlight(Integer.parseInt(flightNumber.getText()))) != -1) {
                                JOptionPane.showMessageDialog(null, "Flight number already exists!", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                flag = true;
                            }
                        } catch (Exception e1) {
                            // TODO: handle exception
                            flag = true;

                            System.out.println(e1);
                        }

                        // Checking if the flight number already exists
                        if (flag) {
                            JOptionPane.showMessageDialog(null, total_error, "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {

                            String parts = date.getText() + "T" + time.getText();
                            int availableSeats = capacity.getText().length();
                            String flightSeats[] = new String[capacity.getText().length()];
                            mySingleton.addFlight(
                                    new flight(Integer.parseInt(flightNumber.getText()), destination.getText(), parts,
                                            Integer.parseInt(price.getText()), availableSeats, flightSeats));
                            JOptionPane.showMessageDialog(null, "Flight scheduled successfully!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            scheduleFlightFrame.dispose();
                            frame.setEnabled(true);
                        }
                    }
                });

                // When the flight adding frame is closed, the main frame is enabled

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
                buyTicketFrame.setSize(1920, 1080);

                buyTicketFrame.setLocation(-10, 0);
                buyTicketFrame.getContentPane().setLayout(null);
                buyTicketFrame.setResizable(false);
                buyTicketFrame.setVisible(true);

                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(10, 11, 850, 940);
                buyTicketFrame.getContentPane().add(scrollPane);

                MySingleton mySingleton = MySingleton.getInstance();
                ArrayList<flight> currenFlights = mySingleton.getFlights("", "", 0, 0);
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
                        }) {
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
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table.getTableHeader().setReorderingAllowed(false);

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

                // Min Price
                JLabel minPriceLabel = new JLabel("Min Price");
                minPriceLabel.setBounds(900, 190, 85, 20);
                buyTicketFrame.getContentPane().add(minPriceLabel);
                buyTicketFrame.setVisible(true);

                JTextField minPrice = new JTextField();
                minPrice.setBounds(900, 220, 146, 26);
                buyTicketFrame.getContentPane().add(minPrice);
                minPrice.setColumns(20);

                // Max Price
                JLabel maxPriceLabel = new JLabel("Max Price");
                maxPriceLabel.setBounds(900, 260, 85, 20);
                buyTicketFrame.getContentPane().add(maxPriceLabel);
                buyTicketFrame.setVisible(true);

                JTextField maxPrice = new JTextField();
                maxPrice.setBounds(900, 290, 146, 26);
                buyTicketFrame.getContentPane().add(maxPrice);
                maxPrice.setColumns(20);

                // Update button
                JButton update = new JButton("Update");
                update.setBounds(900, 340, 115, 29);
                buyTicketFrame.getContentPane().add(update);
                buyTicketFrame.setVisible(true);

                // Buy button
                JButton buy = new JButton("Buy");
                buy.setBounds(900, 380, 115, 29);
                buyTicketFrame.getContentPane().add(buy);
                buyTicketFrame.setVisible(true);

                // Back button
                JButton back = new JButton("Back");
                back.setBounds(900, 420, 115, 29);
                buyTicketFrame.getContentPane().add(back);
                buyTicketFrame.setVisible(true);

                // Clock
                clockLabel.setBounds(900, 10, 200, 20);
                buyTicketFrame.getContentPane().add(clockLabel);
                clockLabel.setVisible(true);
                clock();

                // When update button is clicked
                update.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String start = startPoint.getText();
                        String end = endPoint.getText();
                        int min = (minPrice.getText().equals("")) ? 0 : Integer.parseInt(minPrice.getText());
                        int max = (maxPrice.getText().equals("")) ? 0 : Integer.parseInt(maxPrice.getText());

                        ArrayList<flight> currentFlights = mySingleton.getFlights(start, end, min, max);
                        Object[][] data = new Object[currentFlights.size()][5];

                        for (int i = 0; i < currentFlights.size(); i++) {
                            data[i][0] = currentFlights.get(i).getFlightNo();
                            data[i][1] = currentFlights.get(i).getFlightDestination();
                            data[i][2] = currentFlights.get(i).getFlightDateTime();
                            data[i][3] = currentFlights.get(i).getPrice();
                            data[i][4] = currentFlights.get(i).getAvailableSeats();
                        }

                        JTable table = new JTable();
                        table.setModel(new DefaultTableModel(
                                data,
                                new String[] {
                                        "Flight No", "Start - End", "Flight Date and Hour", "Price", "Available Seats"
                                }) {
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
                        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        table.getTableHeader().setReorderingAllowed(false);
                    }
                });

                // When buy button is clicked
                buy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = table.getSelectedRow();
                        int flightNo = (int) table.getModel().getValueAt(row, 0);
                        int price = (int) table.getModel().getValueAt(row, 3);
                        int availableSeats = (int) table.getModel().getValueAt(row, 4);
                        JFrame seatSelectionFrame = new JFrame("Seat Selection");
                        seatSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        seatSelectionFrame.setSize(1920, 1080);
                        seatSelectionFrame.setLocation(-10, 0);
                        seatSelectionFrame.getContentPane().setLayout(null);
                        seatSelectionFrame.setResizable(false);
                        seatSelectionFrame.setVisible(true);
                        int index = 1;

                        // Back button
                        JButton back = new JButton("Back");
                        back.setBounds(900, 300, 115, 29);
                        seatSelectionFrame.getContentPane().add(back);
                        seatSelectionFrame.setVisible(true);

                        // When back button is clicked
                        back.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                seatSelectionFrame.dispose();

                            }
                        });

                        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        flight currentFlight = mySingleton.getFlightByFlightNo(flightNo);
                        String[] seatArray = currentFlight.getFlightSeats();

                        /*
                         * for (int j = 1; j < allFlights.size(); j++) {
                         * if(allFlights.get(j).getFlightNo()==flightNo) {
                         * flight currentFlight=allFlights.get(j);
                         * System.out.println(allFlights.get(j).getFlightSeats().length);
                         * seatArray=allFlights.get(j).getFlightSeats();
                         * break;
                         * }
                         * }
                         */
                        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! BACK BUTTON!!!!!!!!!!!!!!!!!!!

                        JButton[] buttons = new JButton[121];
                        int x = 100;
                        int y = 20;
                        int tempx = x;
                        int tempy = y;
                        for (int i = 0; i < 20; i++) {
                            y = tempy;
                            x = tempx;
                            for (int j = 1; j <= 6; j++) {
                                JButton temp = new JButton(String.valueOf(index));

                                if (j == 4) {
                                    x += 100;
                                }
                                temp.setBounds(x, y, 70, 25);
                                seatSelectionFrame.getContentPane().add(temp);
                                temp.setVisible(true);
                                if (seatArray[index - 1].equals("-1")) {
                                    temp.setEnabled(true);
                                } else {
                                    temp.setEnabled(false);
                                }
                                x += 70;
                                buttons[index] = temp;
                                index++;
                            }
                            tempy += 30;
                        }

                        for (int i = 1; i < 121; i++) {
                            final int buttonSelection = i;
                            buttons[i].addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {

                                    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    JOptionPane.showMessageDialog(null, "Seat " + buttonSelection + " taken");
                                    ArrayList<Integer> flg = currentCustomer.getFlights();
                                    flg.add(flightNo);
                                    currentCustomer.setFlights(flg);

                                    seatArray[buttonSelection - 1] = currentCustomer.getPhoneNumber();
                                    currentFlight.setFlightSeats(seatArray);
                                    currentFlight.setAvailableSeats(currentFlight.getAvailableSeats() - 1);
                                    mySingleton.flightWriter();
                                    mySingleton.customerWriter();

                                    seatSelectionFrame.dispose();
                                    buyTicketFrame.dispose();
                                    frame.setEnabled(true);
                                }
                            });
                        }

                    }
                });

                // If buyticket frame is closed, enable the main frame
                buyTicketFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

                // When back button is clicked
                back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buyTicketFrame.dispose();
                        frame.setEnabled(true);
                    }
                });

            }
        });
        // When My Schedule button is clicked
        MySchedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setEnabled(false);

                JFrame MyScheduleFrame = new JFrame("My Schedule");
                MyScheduleFrame.setSize(500, 600);
                MyScheduleFrame.setLocation(800, 200);
                MyScheduleFrame.getContentPane().setLayout(null);
                MyScheduleFrame.setResizable(false);
                MyScheduleFrame.setVisible(true);

                JLabel MyScheduleLabel = new JLabel("My Schedule");
                MyScheduleLabel.setBounds(200, 91, 188, 20);
                MyScheduleFrame.getContentPane().add(MyScheduleLabel);
                MyScheduleFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

                JTable MyScheduleTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(MyScheduleTable);
                scrollPane.setBounds(50, 150, 400, 300);
                MyScheduleFrame.getContentPane().add(scrollPane);
                MyScheduleTable.setBackground(Color.LIGHT_GRAY);

                scrollPane.setViewportView(MyScheduleTable);

                // MySingleton mySingleton = MySingleton.getInstance();
                // ArrayList<flight> customersFlights = mySingleton.getCustomersFlights();
                // mySingleton.get
                // loggedInUserIndex

                String[] columnNames = { "Flight No", "From-To", "Date" };
                String[][] data = new String[100][3];
                ArrayList<Integer> customerFlights = currentCustomer.getFlights();
                MySingleton mySingleton = MySingleton.getInstance();
                int i = 0;
                String[] temp = new String[customerFlights.size()];
                String[] fromto = new String[customerFlights.size()];
                for (Integer flightNo : customerFlights) {
                    if (mySingleton.getFlightByFlightNo(flightNo) != null) {
                        fromto[i] = mySingleton.getFlightByFlightNo(flightNo).getFlightDestination();
                        temp[i] = mySingleton.getFlightByFlightNo(flightNo).getFlightDateTimeString();
                        System.out.println(temp);
                        i++;
                    }
                }

                for (int j = 0; j < customerFlights.size(); j++) {
                    data[j][0] = String.valueOf(customerFlights.get(j));
                    data[j][1] = fromto[j];
                    data[j][2] = temp[j];
                }

                MyScheduleTable.setModel(new DefaultTableModel(data, columnNames));
                MyScheduleTable.setDefaultEditor(getClass(), null);
                MyScheduleTable.setEnabled(false); // to disable table editing
                MyScheduleTable.getColumnModel().getColumn(0).setResizable(false);
                MyScheduleTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                MyScheduleTable.getColumnModel().getColumn(1).setPreferredWidth(200);
                MyScheduleTable.getColumnModel().getColumn(2).setResizable(false);
                MyScheduleTable.getColumnModel().getColumn(2).setPreferredWidth(100);

                MyScheduleFrame.setVisible(true);

                DefaultTableModel modeloo = new DefaultTableModel(data, columnNames);

                MyScheduleTable = new JTable(modeloo);

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
                        customer tempCustomer = currentCustomer;

                        // Check if name and surname contains only letters
                        if (!name.getText().equals("")) {
                            if (!name.getText().matches("[a-zA-Z]+")) {
                                total_error += "Name must contain only letters!\n";
                                flag = true;
                            } else if (name.getText().equals(currentCustomer.getName())) {
                                total_error += "Name shouldn't be the same as last  name!\n";
                                flag = true;
                            } else {
                                tempCustomer.setName(name.getText());
                            }
                        }

                        if (!surname.getText().equals("")) {
                            if (!surname.getText().matches("[a-zA-Z]+")) {
                                total_error += "Surname must contain only letters!\n";
                                flag = true;
                            } else if (surname.getText().equals(currentCustomer.getName())) {
                                total_error += "Surname shouldn't be the same as last surname!\n";
                                flag = true;
                            } else {
                                tempCustomer.setSurname(surname.getText());
                            }
                        }
                        // Checking if the mail and phone number is valid
                        if (!mail.getText().equals("")) {
                            mail.setText(mail.getText().replaceAll(" ", ""));
                            if (!mail.getText().endsWith("@gmail.com")
                                    && !mail.getText().endsWith("@hotmail.com")
                                    && !mail.getText().endsWith("@outlook.com")
                                    && !mail.getText().endsWith("@yahoo.com")) {

                                total_error += "Please enter a valid mail!\n";
                                flag = true;
                            } else if (mail.equals(currentCustomer.getMail())) {

                                total_error += "This mail can not be the same as your last mail!\n";
                                flag = true;
                            }
                        }

                        // Checking if the phone number is valid
                        if (!phoneNumber.getText().equals("")) {
                            if ((phoneNumber.getText().length() != 11
                                    || !phoneNumber.getText().startsWith("0")
                                    || !phoneNumber.getText().matches("[0-9]+"))) {
                                total_error += "Please enter a valid phone number!\n";
                                flag = true;
                            } else if (phoneNumber.equals(currentCustomer.getPhoneNumber())) {
                                total_error += "This phone number can not be the same as your last phone Number!\n";
                                flag = true;
                            }
                        }

                        // Checking password length
                        if (!password.getText().equals("")) {
                            if (password.getText().length() < 8) {
                                total_error += "Password must be at least 8 characters!\n";
                                flag = true;
                            } else if (password.equals(currentCustomer.getPassword())) {
                                total_error += "This password can not be the same as your last password!\n";
                                flag = true;
                            } else {
                                tempCustomer.setPassword(password.getText());
                            }
                        }
                        Boolean checkMail = true, checkPhoneNumber = true;
                        // Checking if mail and phone number unique
                        if (mail.getText().equals("")) {
                            checkMail = false;
                        }
                        if (phoneNumber.getText().equals("")) {
                            checkPhoneNumber = false;
                        }
                        for (int i = 0; i < mySingleton.getCustomerSize(); i++) {
                            if (checkMail && mySingleton.getCustomerByIndex(i).getMail().equals(mail.getText())) {
                                total_error += "This mail is already used!\n";
                                flag = true;
                                break;
                            }
                            if (checkPhoneNumber && mySingleton.getCustomerByIndex(i).getPhoneNumber()
                                    .equals(phoneNumber.getText())) {
                                total_error += "This phone number is already used!\n";
                                flag = true;
                                break;
                            }
                        }
                        if (checkMail) { // if mail has changed
                            tempCustomer.setMail(mail.getText());
                        }
                        if (checkPhoneNumber) {// if phone number has changed
                            ArrayList<Integer> customerFlights = currentCustomer.getFlights();
                            for (int i = 0; i < customerFlights.size(); i++) {
                                flight temp = mySingleton.getFlightByFlightNo(customerFlights.get(i));
                                String[] seats = temp.getFlightSeats();
                                for (int j = 0; j < seats.length; j++) {
                                    if (seats[j].equals(tempCustomer.getPhoneNumber())) {
                                        seats[j] = phoneNumber.getText();
                                    }
                                }
                            }
                            mySingleton.flightWriter();
                            tempCustomer.setPhoneNumber(phoneNumber.getText());
                        }
                        if (flag) {
                            JOptionPane.showMessageDialog(null, total_error, "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Checking which informations has been changed
                            mySingleton.removeCustomer(currentCustomer);
                            mySingleton.addCustomer(tempCustomer);
                            currentCustomer = tempCustomer;
                            AccountSettingsFrame.dispose();
                            frame.setEnabled(true);
                            mySingleton.customerWriter();
                            JOptionPane.showMessageDialog(null, tempCustomer.getName() + " deleted", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                // 1/Semarang-Kochi/21.12.2023/11:16-13:44/295/120/
                // -1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1
                AccountSettingsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

            }
        });
        removeUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setEnabled(false);

                JFrame removeUserFrame = new JFrame("Remove User");
                removeUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeUserFrame.setSize(500, 600);
                removeUserFrame.setLocation(800, 200);
                removeUserFrame.getContentPane().setLayout(null);
                removeUserFrame.setResizable(false);
                removeUserFrame.setVisible(true);

                JLabel flightNumberLabel = new JLabel("Enter User Data");
                flightNumberLabel.setBounds(55, 91, 188, 20);
                removeUserFrame.getContentPane().add(flightNumberLabel);

                JTextField flightNumber = new JTextField();
                flightNumber.setBounds(192, 88, 146, 26);
                removeUserFrame.getContentPane().add(flightNumber);
                flightNumber.setColumns(10);

                JButton removeUserBut = new JButton("Remove User");
                removeUserBut.setBounds(192, 364, 130, 29);
                removeUserFrame.getContentPane().add(removeUserBut);
                removeUserBut.setVisible(true);

                // When the removeUser button is clicked
                removeUserBut.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame removeFrame = new JFrame("Remove");
                        removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        removeFrame.setSize(500, 600);
                        removeFrame.setLocation(800, 200);
                        removeFrame.getContentPane().setLayout(null);
                        removeFrame.setResizable(false);
                        removeFrame.setVisible(true);
                        int idx = mySingleton.isCustomer(flightNumber.getText());
                        if (idx == -1) {
                            JOptionPane.showMessageDialog(null, flightNumber.getText() + " Couldn't found", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            customer temp = mySingleton.getCustomerByIndex(idx);
                            ArrayList<Integer> userFlight = temp.getFlights();
                            for (int i = 0; i < userFlight.size(); i++) {
                                flight flg = mySingleton.getFlightByFlightNo(userFlight.get(i));
                                String[] seat = flg.getFlightSeats();
                                for (int j = 0; j < seat.length; j++) {
                                    if (seat[j].equals(temp.getPhoneNumber())) {
                                        flg.setAvailableSeats(flg.getAvailableSeats() + 1);
                                        seat[j] = "-1";
                                        flg.setFlightSeats(seat);

                                        break;
                                    }
                                }
                            }
                            mySingleton.removeCustomer(temp);
                            mySingleton.flightWriter();
                            mySingleton.customerWriter();
                            JOptionPane.showMessageDialog(null, temp.getName() + " was deleted", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        removeFrame.dispose();
                        removeUserFrame.dispose();
                        frame.setEnabled(true);
                    }
                });

                // When the flight adding frame is closed, the main frame is enabled

                // 10047/Paris-Istanbul/01.04.2023/10:10-12:10/100/120/-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1

                removeUserFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        frame.setEnabled(true);
                    }
                });

            }
        });
        removeFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setEnabled(false);

                JFrame removeFlightFrame = new JFrame("Remove Flight");
                removeFlightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeFlightFrame.setSize(500, 600);
                removeFlightFrame.setLocation(800, 200);
                removeFlightFrame.getContentPane().setLayout(null);
                removeFlightFrame.setResizable(false);
                removeFlightFrame.setVisible(true);

                JLabel flightNumberLabel = new JLabel("Enter Flight Number");
                flightNumberLabel.setBounds(55, 91, 188, 20);
                removeFlightFrame.getContentPane().add(flightNumberLabel);

                JTextField flightNumber = new JTextField();
                flightNumber.setBounds(192, 88, 146, 26);
                removeFlightFrame.getContentPane().add(flightNumber);
                flightNumber.setColumns(10);

                JButton removeFlightButton = new JButton("Remove Flight");
                removeFlightButton.setBounds(192, 364, 130, 29);
                removeFlightFrame.getContentPane().add(removeFlightButton);

                // When the removeFlight button is clicked

                // removeFlightButton
                removeFlightButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        int flightNo = Integer.parseInt(flightNumber.getText());

                        if (mySingleton.getFlightByFlightNo(flightNo) != null) {

                            
                            //removing flight from customers
                            ArrayList<customer> customers = mySingleton.getCustomers();
                            ArrayList<flight> flights = mySingleton.getFlights();
                            //customer seat dolaş -1 ise pas geç 0 
                            //numarası eşitse gez 
                            my

                            for (flight fli : customers) {
                                    
                                ArrayList<Integer> flights = customer.getFlights();
                                for (int i = 0; i < flights.size(); i++) {
                                    if (flights.get(i) == flightNo) {
                                        flights.remove(i);
                                        break;
                                    }
                                }
                                customer.setFlights(flights);
                            }
                            mySingleton.removeFlight(mySingleton.getFlightByFlightNo(flightNo));
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Flight couldn't found", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        removeFlightFrame.dispose();
                        frame.setEnabled(true);

                        removeFlightFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                frame.setEnabled(true);
                            }
                        });
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
        MySchedule.setVisible(false);
        AccountSettings.setVisible(false);
        removeUser.setVisible(false);
        removeFlight.setVisible(false);
    }

    public static void adminMenu() {
        logInButton.setVisible(false);
        signUpButton.setVisible(false);
        logOutButton.setVisible(true);
        userInfoLabel.setVisible(true);
        scheduleFlight.setVisible(true);
        MySchedule.setVisible(false);
        removeUser.setVisible(true);
        removeFlight.setVisible(true);
        buyTicket.setVisible(false);
    }

    public static void userMenu() {
        logInButton.setVisible(false);
        signUpButton.setVisible(false);
        logOutButton.setVisible(true);
        userInfoLabel.setVisible(true);
        scheduleFlight.setVisible(false);
        buyTicket.setVisible(true);
        MySchedule.setVisible(true);
        AccountSettings.setVisible(true);
        removeUser.setVisible(false);
        removeFlight.setVisible(false);
    }

    // ***************************************************************************************************************************
}
