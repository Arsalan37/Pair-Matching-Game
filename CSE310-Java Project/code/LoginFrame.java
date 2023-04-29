import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
class LoginFrame extends JFrame implements ActionListener {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    public LoginFrame() {
        setTitle("LOGIN PAGE");
        setResizable(false);
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel userLabel = new JLabel("USERNAME:");
        userLabel.setForeground(Color.BLACK);
        userLabel.setFont(new Font("Serif",Font.BOLD,19));
        JLabel passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Serif",Font.BOLD,19));
        userField = new JTextField(20);
        userField.setForeground(Color.BLACK);
        userField.setFont(new Font("Serif",Font.BOLD,19));
        passwordField = new JPasswordField(20);
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Serif",Font.BOLD,19));
        loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(200,50));
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setFont(new Font("Serif", Font.BOLD, 25));
        loginButton.addActionListener(this);
        FocusListener highlighter = new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                e.getComponent().setBackground(Color.cyan);
            }

            @Override
            public void focusLost(FocusEvent e) {
                e.getComponent().setBackground(UIManager.getColor("TextField.background"));
            }
        };

        userField.addFocusListener(highlighter);
        passwordField.addFocusListener(highlighter);
        loginButton.addFocusListener(highlighter);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Login Page"));

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        
        add(panel);
        setVisible(true);
    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String username = userField.getText();
        String password = new String(passwordField.getPassword());
        boolean matched=false;
        try{

                FileReader f=new FileReader("account.txt");
                int c;
                String line="";
                while ((c = f.read()) != -1) 
                {
                if (c == '\n') 
                {
                    String line2=username+password;
                    line = line.replaceAll("\\s+","");
                    if(line.equals(line2))
                    {
                        matched=true;
                        break;
                    }
                    line = "";
                }
                else {
                    line += (char)c;
                    }
                }
                f.close();
                if(matched){
                    new Window(username);
                    dispose();        
                }
                else if(username.equals("") && password.equals(""))
                {
                    JOptionPane.showMessageDialog(this, "USERNAME AND PASSWORD NOT ENTERED");
                }
                else if(username.equals(""))
                {
                    JOptionPane.showMessageDialog(this, "USERNAME NOT ENTERED");
                }
                else if(password.equals(""))
                {
                    JOptionPane.showMessageDialog(this, "PASSWORD NOT ENTERED");
                }
                else{
                    JOptionPane.showMessageDialog(this, "INVALID USERNAME OR PASSWORD");
                }
            }catch(Exception e){}
        }
    
    public static void main(String[] args) {
    }
}
