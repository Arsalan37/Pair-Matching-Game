import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;


public class Window extends JFrame {

    public Window(String name) throws HeadlessException {
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(name + " : Choose Difficulty Level");
        setBackground(Color.lightGray);
        setLocation(500, 300);
        setSize(500, 280);
        //setResizable(false);
        JPanel levelsPanel = new JPanel(new FlowLayout());
        JButton btnEasyLevel = new JButton("LEVEL-EASY 4x4");
        btnEasyLevel.addActionListener(a -> {
            this.dispose();
             new Game(4 , name);
        });
        JButton btnHardLevel = new JButton("LEVEL-HARD 6x6");
        btnHardLevel.addActionListener(a -> {
            this.dispose();
            new Game(6,name);
        });
        btnEasyLevel.setPreferredSize(new Dimension(200,50));
        btnHardLevel.setPreferredSize(new Dimension(200,50));
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("diff.jpg"));
        Image scaledImg = img.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel image = new JLabel(scaledIcon);
        levelsPanel.add(image, BorderLayout.NORTH);
        levelsPanel.add(btnEasyLevel);
        levelsPanel.add(btnHardLevel);
        levelsPanel.setBackground(Color.BLACK);
        levelsPanel.setOpaque(false);
        levelsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        levelsPanel.setBorder(BorderFactory.createEtchedBorder());
        levelsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        levelsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Choose Level"));
        add(levelsPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        
    }
}