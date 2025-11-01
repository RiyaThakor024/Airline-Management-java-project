import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class TicketCancellation extends JFrame {

    JLabel text, ticket;
    JTextField ticketField;
    JButton cancelButton;

    TicketCancellation(String name){
        super(name);
        setSize(900,240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        text = new JLabel("Enter Your Ticket To Cancel");
        text.setFont(new Font("Arial",Font.BOLD,20));
        text.setBounds(300,10,300,100);
        add(text);

        ticket = new JLabel("Ticket:");
        ticket.setFont(new Font("Arial",Font.PLAIN,15));
        ticket.setBounds(250,108,80,16);
        add(ticket);

        ticketField = new JTextField();
        ticketField.setFont(new Font("Arial",Font.PLAIN,15));
        ticketField.setBounds(330,108,200,17);
        add(ticketField);

        cancelButton = new JButton();
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticket = ticketField.getText();
                     if (ticket.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a ticket number!");
                    return;
                }
                Conn c=null;
                PreparedStatement ps=null;

                try{
                     c = new Conn();
                    ps = c.conn.prepareStatement("delete from reservations WHERE ticket = ?");
                    ps.setString(1, ticket);
                    int rows = ps.executeUpdate();
                    if(rows != 0){
                        JOptionPane.showMessageDialog(null, "Ticket Cancelled Successfully!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Ticket Not Found!");
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                finally{
                    try{
                        if(ps != null)ps.close();
                        if(c != null && c.conn != null)c.conn.close();
                    }
                    catch(Exception closeEx){
                             closeEx.printStackTrace();
                    }
                    
                }
            }
        });
        cancelButton.setBackground(Color.black);
        cancelButton.setForeground(Color.white);
        cancelButton.setText("Cancel Ticket");
        cancelButton.setFocusable(false);
        cancelButton.setFont(new Font("Arial",Font.PLAIN,12));
        cancelButton.setBounds(560,108,120,16);
        add(cancelButton);

        setVisible(true);
    }
}