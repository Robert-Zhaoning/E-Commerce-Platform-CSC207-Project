package view;

import javax.swing.*;

public class SignUpView {
    private final String title = "Sign Up";
    private final int x_dimension = 500;
    private final int y_dimension = 500;
    public SignUpView(){
        JFrame frame = new JFrame(title);
        frame.setMinimumSize(new java.awt.Dimension(x_dimension, y_dimension));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameTextField = new JTextField();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Username:");
        JTextField passwordTextField = new JTextField();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);

    }
}
