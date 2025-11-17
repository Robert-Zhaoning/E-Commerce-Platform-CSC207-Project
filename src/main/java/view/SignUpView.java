package view;

import entity.User;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignUpView extends JPanel implements PropertyChangeListener {
    private final String VIEW_NAME = "Sign Up";
    private SignUpViewModel signUpViewModel;
    private SignUpController signUpController;
    private String error = "";
    public SignUpView(){
        this.signUpViewModel = new SignUpViewModel();
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameTextField = new JTextField(10);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordTextField = new JTextField(10);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);

        JPanel password2Panel = new JPanel();
        JLabel password2Label = new JLabel("Confirm Password:");
        JTextField password2TextField = new JTextField(10);
        password2Panel.add(password2Label);
        password2Panel.add(password2TextField);

        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField(10);
        emailPanel.add(emailLabel);
        emailPanel.add(emailTextField);

        JPanel billingAddressPanel = new JPanel();
        JLabel billingAddressLabel = new JLabel("Billing Address:");
        JTextField billingAddressTextField = new JTextField(10);
        billingAddressPanel.add(billingAddressLabel);
        billingAddressPanel.add(billingAddressTextField);

        JPanel buttonsPanel = new JPanel();
        JButton createButton = new JButton("Create");
        JButton loginButton = new JButton("Login");
        buttonsPanel.add(createButton);
        buttonsPanel.add(loginButton);

        JPanel errorPanel = new JPanel();
        JLabel errorLabel = new JLabel(this.error);
        errorLabel.setForeground(new Color(255,0,0));
        errorPanel.add(errorLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(password2Panel);
        mainPanel.add(emailPanel);
        mainPanel.add(billingAddressPanel);
        mainPanel.add(errorPanel);
        mainPanel.add(buttonsPanel);

        createButton.addActionListener(event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String password2 = password2TextField.getText();
            String email = emailTextField.getText();
            String billingAddress = billingAddressTextField.getText();
            if (password.equals(password2)){
                this.signUpController = new SignUpController();
                this.signUpController.execute(username, password, email, billingAddress);
            }
        });

        loginButton.addActionListener(event -> {new LoginView();});
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("SignUpSuccess")){
            User loggedInUser = this.signUpViewModel.getState().getUser();
            new LoggedInView(loggedInUser);
        } else if (evt.getPropertyName().equals("SgnUpFailure")){
            String error = this.signUpViewModel.getState().getUser();
            this.error = error;
        }
    }
}
