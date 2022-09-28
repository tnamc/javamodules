import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong21 implements KeyListener
{
    public static void main (String[] args)
    {
        Pong21 pong = new Pong21();
        pong.rungame();
    }



    static int leftpaddlescore = 0;
    static int rightpaddlescore = 0;
    static boolean lefthit = false;
    static boolean righthit = false;
    static int newball = (int)(Math.random() * 2.0D); // = random number <2 => 0 is left, 1 is right
    static boolean newserve;
    static boolean startdone = false;
    static String balldirection;
    static boolean winnerfound = false;
    static String winner;

    int ballX;
    int ballY;
    int ballWidth = 40;
    int ballHeight = 40;
    int moveX = 2;
    int moveY = 2;

    // left paddle
    int leftPaddleX = 50;
    int leftPaddleY = 380;
    int leftPaddleWitdh = 20;
    int leftPaddleHeight = 40;
    // right paddle
    int rightPaddleX = 1100;
    int rightPaddleY = 380;
    int rightPaddleWidth = 20;
    int rightPaddleHeight = 40;



    public void rungame()
    {
        JFrame gameframe = new JFrame();
        PongDrawPanel drawPanel = new PongDrawPanel(); // class PongDrawPanel extends JPanel

        gameframe.getContentPane().add(drawPanel); // add drawPanel into gameframe
        gameframe.addKeyListener(this); // add KeyListener into gameframe

        gameframe.setSize(1200, 800); // set game windows size = horizontal rectangle
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameframe.setVisible(true);

//// Move the ball from left to right
        while (true)
        {
            if (rightpaddlescore==11){winnerfound=true;winner="Rechtes Paddel siegte!";drawPanel.repaint();break;}
            if (leftpaddlescore==11){winnerfound=true;winner="Linkes Paddel siegte!";drawPanel.repaint();break;}

            // Pause on hitting
            if (lefthit){try{Thread.sleep(1000);ballX=leftPaddleX+40;lefthit=false;}catch(Exception e){}}
            if (righthit){try{Thread.sleep(1000);ballX=rightPaddleX-40;righthit=false;}catch(Exception e){}}

            if (newball==0 && startdone==false)
            {ballX=leftPaddleX+40;
                ballY=leftPaddleY;
                startdone=true;
                balldirection="lefttoright";
            }
            if (newball==1 && startdone==false)
            {ballX=rightPaddleX-40;
                ballY=rightPaddleY;
                startdone=true;
                balldirection="righttoleft";}

            if (newserve){
                try{Thread.sleep(2000);newserve=false;}catch(Exception e){}
            }

// move the ball position
            if (balldirection=="lefttoright"){
                ballX = ballX + moveX;
                ballY = ballY + moveY;
            }
            if (balldirection=="righttoleft"){
                ballX = ballX - moveX;
                ballY = ballY - moveY;
            }

// draw the ball new, update the latest state of game display
            // drawPanel is an instance of PongDrawPanel, inheriting JPanel
            drawPanel.repaint();

// slow the ball speed
            try
            {
                Thread.sleep(11);
            }
            catch (Exception ex)
            { /* nope */
            }
        } // End of while loop
        //// Move the ball from right to left
    } // END OF RUNGAME


    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Implement KeyListener methods
    public void keyPressed(KeyEvent e){
        // check which char pressed
        // depend on this move paddle(s)
        if (balldirection=="righttoleft"){
            if (e.getKeyText(e.getKeyCode()) == "Up"){leftPaddleY -= 20;}
            if (e.getKeyText(e.getKeyCode()) == "Down"){leftPaddleY += 20;}
        }
        if (balldirection=="lefttoright"){
            if (e.getKeyText(e.getKeyCode()) == "Up"){rightPaddleY -= 20;}
            if (e.getKeyText(e.getKeyCode()) == "Down"){rightPaddleY += 20;}
        }

    }
    public void keyReleased(KeyEvent e){
        // check which char pressed
        // depend on this move paddle(s)
    }



    // inner class PongDrawPanel
    class PongDrawPanel extends JPanel
    {
        public void paintComponent(Graphics g)  // override JPanel's method paintComponent,
                                                // set own game display rules
        {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.blue); // color of ball
            g.fillOval(ballX, ballY, ballWidth, ballHeight); // draw the "ball"

            g.setColor(Color.red);
            g.fillRect(leftPaddleX, leftPaddleY, leftPaddleWitdh, leftPaddleHeight); // draw paddle left

            g.setColor(Color.green);
            g.fillRect(rightPaddleX, rightPaddleY, rightPaddleWidth, rightPaddleHeight); // draw paddle right

            g.setColor(Color.orange);
            g.drawString(String.valueOf(leftpaddlescore)+" - "+String.valueOf(rightpaddlescore), 600, 100);

            if (winnerfound){
                g.setColor(Color.black);
                g.drawString("Spiel beendet.",600,250);
                g.drawString(winner,600,300);
            }


            lefthit = false;
            righthit = false;
// CHECK the right wall => Left paddle scores => New serve/ball for left paddle
            if (ballX > this.getWidth() - ballWidth)
            {
                // New ball for Left paddle
                newball = 0;
                leftpaddlescore += 1;
                newserve = true;
                startdone = false;
                // Random ball direction serving
                while (true){
                    moveX = (int)(Math.random() * 3.0D);
                    moveY = (int)(Math.random() * 3.0D);
                    if (moveX==0){continue;}else{break;}
                }

            }
// CHECK the left wall => Right paddle scores => New serve/ball for right paddle
            if (ballX < 0)
            {
                // New ball for Right paddle
                newball = 1;
                rightpaddlescore += 1;
                newserve = true;
                startdone = false;
                // Random ball direction serving
                while (true){
                    moveX = (int)(Math.random() * 3.0D);
                    moveY = (int)(Math.random() * 3.0D);
                    if (moveX==0){continue;}else{break;}
                }
            }
/////////////////////////////
// CHECK the bottom wall
            if (ballY > this.getHeight() - ballHeight)
            {
                moveY = -moveY;
            }

// CHECK the top wall
            if (ballY < 0)
            {
                moveY = -moveY;
            }
            /////////////////////////////
            // Left paddle Hit
            if (ballX < leftPaddleX + leftPaddleWitdh
                    && ballY < leftPaddleY+leftPaddleHeight
                    && ballY > leftPaddleY-leftPaddleHeight)
            {moveX = -moveX;
            lefthit = true;
            righthit = false;
            balldirection = "lefttoright";
            //increase speed in random direction
                int randomchoice = (int)(Math.random() * 2.0D);
                int delta = (int)(Math.random() * 3.0D);
            if (moveX < 0 ) moveX = moveX-1; else moveX = moveX+1;
            if (randomchoice==0) moveY = moveX-delta; else moveY = delta-moveX;}

            // Right paddle Hit
            if (ballX > rightPaddleX - ballWidth
                && ballY < rightPaddleY+rightPaddleHeight
                && ballY > rightPaddleY-rightPaddleHeight)
            {moveX = -moveX;
                righthit = true;
                lefthit = false;
                balldirection = "righttoleft";
                //increase speed in random direction
                int randomchoice = (int)(Math.random() * 2.0D);
                int delta = (int)(Math.random() * 3.0D);
                if (moveX < 0 ) moveX = moveX-1; else moveX = moveX+1;
                if (randomchoice==0) moveY = moveX-delta; else moveY = delta-moveX;}
        }// End of Method paintComponent

    }// End of Class PongDrawPanel

}