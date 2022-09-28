import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoButtons {
    static JFrame frame;
    static JLabel label;
    MyDrawPanel drawPanel;

    public static void main(String[] args){
        TwoButtons gui = new TwoButtons();
        gui.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change Label");
        labelButton.addActionListener(new LabelListener());
        JButton colorButton = new JButton("Change Circle");
        colorButton.addActionListener(new ColorListener());

        label = new JLabel("0");
        drawPanel = new MyDrawPanel();

        frame.setSize(700,700);
        frame.setVisible(true);
        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);
    }

// Old position of two Listeners

} // END of "TwoButtons" class


class LabelListener extends TwoButtons implements ActionListener{
    public void actionPerformed(ActionEvent event){
        this.label = label;
        label.setText(String.valueOf((int)(Math.random() * 9.0D)));
    }
}
class ColorListener extends TwoButtons implements ActionListener{
    public void actionPerformed(ActionEvent event){
        this.frame = frame;
        frame.repaint();
    }
}

class MyDrawPanel extends JPanel{
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        int red = (int) (Math.random()*255);int green = (int) (Math.random()*255);int blue = (int) (Math.random()*255);
        Color startColor = new Color(red,green,blue);
        red = (int) (Math.random()*255);green = (int) (Math.random()*255);blue = (int) (Math.random()*255);
        Color endcolor = new Color(red,green,blue);

        GradientPaint gradient = new GradientPaint(70,70,startColor,150,150,endcolor);
        g2d.setPaint(gradient);
        g2d.fillOval(70,70,100,100);
    }
}
