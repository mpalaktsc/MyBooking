package LoginSystem;

import Misc.Storage;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * This is the main welcome and log-in screen of the application. Here the user can sign-in using their account
 * credentials or sign-up creating a new account.
 * @author Christos Balaktsis
 */
public class LoginScreen {
    private final JFrame loginForm;
    private final JLabel logo;
    private final JTextField usernameField;
    private final JLabel usernameLabel;
    private final JPasswordField passwordField;
    private final JLabel passwordLabel;
    private final JLabel forgotPassLabel;
    private final JLabel welcomeLabel;
    private final JButton signInButton;
    private final JButton signUpButton;
    private final JDialog accountType;
    private final JComboBox<String> reqRole;
    private final JLabel chooseRoleLabel;
    private final JButton signUpFormButton;
    private final Container loginFormContentPane;
    private final Container accountTypeContentPane;

    public LoginScreen() {
        loginForm = new JFrame();
        logo = new JLabel();
        usernameField = new JTextField();
        usernameLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordLabel = new JLabel();
        forgotPassLabel = new JLabel();
        welcomeLabel = new JLabel();
        signInButton = new JButton();
        signUpButton = new JButton();
        accountType = new JDialog();
        reqRole = new JComboBox<String>();
        chooseRoleLabel = new JLabel();
        signUpFormButton = new JButton();

        //======== loginForm ========

        loginForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginForm.setBackground(Color.white);
        loginForm.setTitle("MyBooking");
        loginForm.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))).getImage());
        loginForm.setResizable(false);
        loginForm.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    Storage.storeDataToFiles();
                    System.exit(0);
                }
            }
        });

        loginFormContentPane = loginForm.getContentPane();
        loginFormContentPane.setLayout(null);

        //---- logo ----
        logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        loginFormContentPane.add(logo);
        logo.setBounds(new Rectangle(new Point(115, 30), logo.getPreferredSize()));

        //---- usernameField ----
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameFieldFocusGained(e);
            }
        });
        loginFormContentPane.add(usernameField);
        usernameField.setBounds(65, 240, 225, 35);

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginFormContentPane.add(usernameLabel);
        usernameLabel.setBounds(70, 215, 70, 25);
        loginFormContentPane.add(passwordField);
        passwordField.setBounds(65, 300, 225, 35);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginFormContentPane.add(passwordLabel);
        passwordLabel.setBounds(70, 275, 70, 25);

        //---- forgotPassLabel ----
        forgotPassLabel.setText("Forgot Password?");
        forgotPassLabel.setFont(forgotPassLabel.getFont().deriveFont(forgotPassLabel.getFont().getSize() - 2f));
        forgotPassLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                forgotPassLabelMouseClicked(e);
            }
        });
        loginFormContentPane.add(forgotPassLabel);
        forgotPassLabel.setBounds(new Rectangle(new Point(210, 340), forgotPassLabel.getPreferredSize()));

        //---- welcomeLabel ----
        welcomeLabel.setText("Welcome to MyBooking!");
        welcomeLabel.setBackground(Color.white);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(16, 143, 233));
        loginFormContentPane.add(welcomeLabel);
        welcomeLabel.setBounds(new Rectangle(new Point(85, 170), welcomeLabel.getPreferredSize()));

        //---- signInButton ----
        signInButton.setText("Sign-In");
        signInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signInButtonMouseClicked(e);
            }
        });
        loginFormContentPane.add(signInButton);
        signInButton.setBounds(new Rectangle(new Point(220, 370), signInButton.getPreferredSize()));

        //---- signUpButton ----
        signUpButton.setText("Sign-Up");
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUpButtonMouseClicked(e);
            }
        });
        loginFormContentPane.add(signUpButton);
        signUpButton.setBounds(new Rectangle(new Point(65, 370), signUpButton.getPreferredSize()));


        // compute preferred size of loginForm frame
        Dimension loginFormPreferredSize = new Dimension();
        for(int i = 0; i < loginFormContentPane.getComponentCount(); i++) {
            Rectangle bounds = loginFormContentPane.getComponent(i).getBounds();
            loginFormPreferredSize.width = Math.max(bounds.x + bounds.width, loginFormPreferredSize.width);
            loginFormPreferredSize.height = Math.max(bounds.y + bounds.height, loginFormPreferredSize.height);
        }
        Insets insets = loginFormContentPane.getInsets();
        loginFormPreferredSize.width += insets.right;
        loginFormPreferredSize.height += insets.bottom;
        loginFormContentPane.setMinimumSize(loginFormPreferredSize);
        loginFormContentPane.setPreferredSize(loginFormPreferredSize);

        loginForm.setSize(355, 485);
        loginForm.setLocationRelativeTo(loginForm.getOwner());


        //======== accountType ========
        accountType.setTitle("Account type");
        accountType.setBackground(Color.white);
        accountType.setName("accountType");
        accountType.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        accountType.setAlwaysOnTop(true);
        accountTypeContentPane = accountType.getContentPane();
        accountTypeContentPane.setLayout(null);
        accountTypeContentPane.add(reqRole);
        accountType.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                loginForm.setVisible(true);
            }
        });
        reqRole.setBounds(35, 40, 124, reqRole.getPreferredSize().height);

        //---- chooseRoleLabel ----
        chooseRoleLabel.setText("Choose the type of account you want to create:");
        accountTypeContentPane.add(chooseRoleLabel);
        chooseRoleLabel.setBounds(new Rectangle(new Point(15, 15), chooseRoleLabel.getPreferredSize()));

        //---- signUpFormButton ----
        signUpFormButton.setText("Next");
        signUpFormButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUpFormButtonMouseClicked(e);
            }
        });
        accountTypeContentPane.add(signUpFormButton);
        signUpFormButton.setBounds(new Rectangle(new Point(165, 40), signUpFormButton.getPreferredSize()));


        // compute preferred size of accountType dialog
        Dimension accountTypeDialogPreferredSize = new Dimension();
        for(int i = 0; i < accountTypeContentPane.getComponentCount(); i++) {
            Rectangle bounds = accountTypeContentPane.getComponent(i).getBounds();
            accountTypeDialogPreferredSize.width = Math.max(bounds.x + bounds.width, accountTypeDialogPreferredSize.width);
            accountTypeDialogPreferredSize.height = Math.max(bounds.y + bounds.height, accountTypeDialogPreferredSize.height);
        }
        Insets accountTypeContentPaneInsets = accountTypeContentPane.getInsets();
        accountTypeDialogPreferredSize.width += accountTypeContentPaneInsets.right;
        accountTypeDialogPreferredSize.height += accountTypeContentPaneInsets.bottom;
        accountTypeContentPane.setMinimumSize(accountTypeDialogPreferredSize);
        accountTypeContentPane.setPreferredSize(accountTypeDialogPreferredSize);

        accountType.setSize(280, 120);
        accountType.setLocationRelativeTo(accountType.getOwner());

        loginForm.setVisible(true);
        loginForm.setTitle("MyBooking");
        usernameField.requestFocusInWindow();
        usernameField.addActionListener(e -> signInButtonMouseClicked(null));
        passwordField.addActionListener(e -> signInButtonMouseClicked(null));
        signInButton.addActionListener(e -> signInButtonMouseClicked(null));
        signUpButton.addActionListener(e -> signUpButtonMouseClicked(null));
        signUpFormButton.addActionListener(e -> signUpFormButtonMouseClicked(null));

        for(Roles role : Roles.values())
            reqRole.addItem(role.toString());
        accountType.setIconImage(loginForm.getIconImage());
        accountType.setVisible(false);
    }

    private void forgotPassLabelMouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(this.loginForm,"Relax. Focus and try to remember it!","Forgot password",JOptionPane.INFORMATION_MESSAGE );
    }

    private void resetLoginWindow() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocusInWindow();
    }

    private void signInButtonMouseClicked(MouseEvent e) {
        User user = LoginSystem.checkUser(usernameField.getText(), new String(passwordField.getPassword()));
        if(user == null) {
            JOptionPane.showMessageDialog(this.loginForm, "Username or password does not match to any" +
                    " existing account!\nPlease try again!","Wrong Credentials", JOptionPane.ERROR_MESSAGE);
            resetLoginWindow();
        }
        else {
            if (user.getApprovalStatus()) {
                loginForm.dispose();
                user.showInterface(true);
            }
            else
                JOptionPane.showMessageDialog(this.loginForm, "Your account has not been activated yet." +
                        "\nTry again later!","Unapproved Account", JOptionPane.INFORMATION_MESSAGE);
            resetLoginWindow();
        }
    }

    private void usernameFieldFocusGained(FocusEvent e) {
        passwordField.setText("");
    }

    private void signUpButtonMouseClicked(MouseEvent e) {
        accountType.setVisible(true);
        loginForm.setVisible(false);
    }

    private void signUpFormButtonMouseClicked(MouseEvent e) {
        String role = Objects.requireNonNull(reqRole.getSelectedItem()).toString();
        accountType.setVisible(false);
        switch (role) {
            case "ADMINISTRATOR" -> new AdminForm();
            case "CUSTOMER" -> new CustomerForm();
            case "LANDLORD" -> new LandlordForm();
        }
    }

}