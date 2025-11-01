import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Login extends JFrame {

    int boardWidth = 450;
    int boardHeight = 250;

    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameField;
    JPasswordField passwordField;


    JButton reset;
    JButton close;
    JButton submit;

    Login(String title){
        super(title);
        setSize(boardWidth, boardHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        usernameLabel.setText("Username: ");
        usernameLabel.setBounds(65, 20, 80,50);
        add(usernameLabel);

        passwordLabel = new JLabel();
        passwordLabel.setFont(new Font("Arial",Font.PLAIN,15));
        passwordLabel.setText("Password: ");
        passwordLabel.setBounds(65, 60, 80,50);
        add(passwordLabel);


        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial",Font.PLAIN,15));
        usernameField.setBounds(165,35, 200,20);
        add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial",Font.PLAIN,15));
        passwordField.setBounds(165,75, 200,20);
        add(passwordField);


        reset = new JButton();
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
        reset.setFont(new Font("Arial",Font.PLAIN,15));
        reset.setBounds(55, 150, 90, 30);
        reset.setText("Reset");
        reset.setBackground(Color.GRAY);
        reset.setForeground(Color.white);
        reset.setFocusable(false);
        add(reset);

        close = new JButton();
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        close.setFont(new Font("Arial",Font.PLAIN,15));
        close.setBounds(175, 150, 90, 30);
        close.setText("Close");
        close.setBackground(Color.GRAY);
        close.setForeground(Color.white);
        close.setFocusable(false);
        add(close);

        submit = new JButton();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conn con = null;
                PreparedStatement ps = null;
                ResultSet rs =null;
                try{

                    String userName = usernameField.getText().trim();
                    String passWord =new String(passwordField.getPassword()).trim();

                    if(userName.isEmpty() || passWord.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please enter required fields: UserName and Password");
                        return;
                    }
                     con = new Conn();
                      ps = con.conn.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
                     ps.setString(1, userName);
                     ps.setString(2, passWord);
                     rs = ps.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Hi Admin!");
                        new Home("Home");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Invalid usename and password!");
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                catch(Error er){
                    throw new Error(er);
                }
                finally {
                    try{
                   if(rs != null) rs.close();
                   if(ps != null) ps.close();

                    if(con != null && con.conn != null)con.conn.close();
                }
                catch(Exception closeEx){
                    closeEx.printStackTrace();
                }
                }
            }
        });
        submit.setFont(new Font("Arial",Font.PLAIN,15));
        submit.setBounds(295, 150, 90, 30);
        submit.setText("Submit");
        submit.setBackground(Color.decode("#2EA2D1"));
        submit.setForeground(Color.white);
        submit.setFocusable(false);
        add(submit);

        setVisible(true);
    }

    public static void main(String[] args){
        new Login("Login Page");
    }
}