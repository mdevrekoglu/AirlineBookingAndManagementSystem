import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
//import java.nio.Buffer;
import java.util.ArrayList;
import javax.swing.JButton;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class MAIN {

    // create a string array list
    private static ArrayList<user> users = new ArrayList<user>();
    private static String usersFilePath = "users.txt";

    public MAIN() {

        userReader();



        JFrame frame = new JFrame("APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(800, 200);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        JButton logIn = new JButton("Log In");
        logIn.setBounds(200, 100, 100, 50);
        frame.getContentPane().add(logIn);

        logIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setEnabled(false);

                JFrame logInFrame = new JFrame("Log In");
                logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                logInFrame.setSize(500, 75);
                logInFrame.setLocation(800, 200);
                logInFrame.getContentPane().setLayout(new FlowLayout());
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

                JButton logInButton = new JButton("Log In");
                logInButton.setBounds(192, 200, 115, 29);
                logInFrame.getContentPane().add(logInButton);

                logInButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        Boolean flag = false;

                        for (int i = 0; i < users.size(); i++) {
                            if ((id.getText().equals(users.get(i).getMail())
                                    || id.getText().equals(users.get(i).getPhoneNumber()))
                                    && passport.getText().equals(users.get(i).getPassword())) {
                                frame.setEnabled(true);
                                flag = true;
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

        JButton signUp = new JButton("Sign Up");
        signUp.setBounds(200, 200, 100, 50);
        frame.getContentPane().add(signUp);

        signUp.addActionListener(new ActionListener() {
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

                JButton signUpButton = new JButton("Sign Up");
                signUpButton.setBounds(192, 450, 115, 29);
                signUpFrame.getContentPane().add(signUpButton);

                // add the new user to the users array list and close the signUpFrame
                signUpButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        Boolean flag = false;

                        if(name.getText().equals("") || surname.getText().equals("") 
                            || mail.getText().equals("") || password.getText().equals("") || phoneNumber.getText().equals("")){
                            flag = true;
                            JOptionPane.showMessageDialog(null, "Please fill all the blanks!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        if(!flag){
                            for (int i = 0; i < users.size(); i++) {
                            if (mail.getText().equals(users.get(i).getMail())
                                    || phoneNumber.getText().equals(users.get(i).getPhoneNumber())) {
                                JOptionPane.showMessageDialog(null, "This mail or phone number is already used!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                flag = true;
                                break;
                            }
                        }
                        }

                        if (!flag) {
                            int type = 0;
                            if(admin.getText().equals("admin")){
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

    }

    public static void userReader(){
        // name/surname/mail/password/phone number/usertype(0-> user, 1-> admin)
        try {

            BufferedReader reader = new BufferedReader(new FileReader(usersFilePath));

            // Read all file and assign to users array list
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("/");

                if (parts.length != 6)
                    throw new Exception("File is corrupted! (users.txt)");

                users.add(
                        new user(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5])));
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
}
