//||/////////////////////////////////////////////////////////////////////////||
//||Program Name - Pong													     ||
//||Author - Kizr															 ||
//||Description - This is a program designed to implement techniques learned ||
//||throughout the week as well as advanced techniques. The purpose of this  ||
//||program is to serve as a final project for students.					 ||
//||/////////////////////////////////////////////////////////////////////////||

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

//The above list are all the things that you will need to import to
//get this program running. Please keep the spelling the exact same.


//Main method. You must implement ActionListener and KeyListener to allow
//you to use specific keys to set for movement
public class PongPanel extends JPanel implements ActionListener, KeyListener
{
	
	//These private boolean and ints will be used to declare your
	//initial conditions. You can choose to omit some like title screen,
	//but for the sake of the program, write this exactly as is
    private boolean showTitleScreen = true;
    private boolean playing = false;
    private boolean gameOver = false;
    //initial conditions for movements
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;
    //initial conditions for ball
    //X and Y coordinates
    private int ballX = 175;
    private int ballY = 175;
    //movement and size conditions
    private int diameter = 10;
    private int ballDeltaX = -1;
    private int ballDeltaY = 3;
    //initial conditions for player 1
    //X and Y coordinates
    private int playerOneX = 25;
    private int playerOneY = 250;
    //Size conditions
    private int playerOneWidth = 10;
    private int playerOneHeight = 50;
    //initial conditions for player 2
    //X and Y coordinates
    private int playerTwoX = 465;
    private int playerTwoY = 250;
    //Size conditions
    private int playerTwoWidth = 10;
    private int playerTwoHeight = 50;
    //set paddle speed
    private int paddleSpeed = 5;
    //Initial Score
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    
	public Random random = new Random();
	public boolean point = false;
	
	public Color gBall;
	public Color g1;
	public Color g2;



    //construct a PongPanel
    public PongPanel()
    {
        setBackground(Color.BLACK);

        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
    }


    public void actionPerformed(ActionEvent e)
    {
        step();
    }
    //Method labeled step. Notice above how it is called.
    public void step()
    {
    	//move player 1
        if(playing)
        {
            //If condition to set the buttons for movement p1
            if (wPressed) 
            {
                if (playerOneY-paddleSpeed > 0) 
                {
                	
                	playerOneY -= paddleSpeed;
                }
            }//If condition to set the buttons for movement p1
            if (sPressed) 
            {
                if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) 
                {
                	
                	playerOneY += paddleSpeed;
                }
            }

            //move player 2 
            if (upPressed) 
            {//If condition to set the buttons for movement p2
                if (playerTwoY-paddleSpeed > 0) 
                {
                	
                	playerTwoY -= paddleSpeed;
                }
            }
            //If condition to set the buttons for movement p2
            if (downPressed) 
            {
                if (playerTwoY + paddleSpeed + playerTwoHeight < getHeight()) 
                {
                
                	playerTwoY += paddleSpeed;
                }
            }



            //where will the ball be after it moves?
            int nextBallLeft = ballX + ballDeltaX;
            int nextBallRight = ballX + diameter + ballDeltaX;
            int nextBallTop = ballY + ballDeltaY;
            int nextBallBottom = ballY + diameter + ballDeltaY;

            int playerOneRight = playerOneX + playerOneWidth;
            int playerOneTop = playerOneY;
            int playerOneBottom = playerOneY + playerOneHeight;

            float playerTwoLeft = playerTwoX;
            float playerTwoTop = playerTwoY;
            float playerTwoBottom = playerTwoY + playerTwoHeight;


            //if statement for when a ball bounces off top and bottom of screen
            if (nextBallTop < 0 || nextBallBottom > getHeight()) 
            {
                ballDeltaY *= -1;//boolean operator for multiplication
                g.setColor(Color.BLUE);
            }

            //will the ball go off the left side?
            if (nextBallLeft < playerOneRight) 
            { 
                //is it going to miss the paddle?
                if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) 
                {
                	//add 1 to p2 score
                    playerTwoScore ++;

                    if (playerTwoScore == 10) //win conditions
                    {
                        playing = false;
                        gameOver = true;
                    }

                    ballX = 250;
                    ballY = 250;
                }
                else 
                {//boolean operator for multiplication
                    ballDeltaX *= -1;
                }
            }

            //will the ball go off the right side?
            if (nextBallRight > playerTwoLeft) {
                //is it going to miss the paddle?
                if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
                	//add 1 to p1 score
                    playerOneScore ++;

                    if (playerOneScore == 10) //win conditions
                    {
                        playing = false;
                        gameOver = true;
                    }

                    ballX = 250;
                    ballY = 250;
                }
                else 
                {//boolean operator for multiplication
                    ballDeltaX *= -1;
                }
            }

            //move the ball
            ballX += ballDeltaX;
            ballY += ballDeltaY;
        }

        //stuff has moved, tell this JPanel to repaint itself
        repaint();
    }

    //paint the game screen
    public void paintComponent(Graphics g)
    {
    

        super.paintComponent(g);
        g.setColor(Color.GREEN);
        
        //if statement for the title screen
        if (showTitleScreen) 
        {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
            g.drawString("Lets play a little game.....", 100, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

            g.drawString("Press 'P' to play", 175, 300);
        }//otherwise you are playing the game
        else if (playing) 
        {


            int playerOneRight = playerOneX + playerOneWidth;
            int playerTwoLeft =  playerTwoX;

            //draw dashed line down center
            for (int lineY = 0; lineY < getHeight(); lineY += 50)
            {
                g.drawLine(250, lineY, 250, lineY+25);
            }

            //draw "goal lines" on each side
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());

            //draw the scores
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
            //coordinates for the scores
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 400, 100);

            //draw the ball
            g.fillOval(ballX, ballY, diameter, diameter);
            

            //draw the paddles
            g.setColor(Color.ORANGE);
            g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
            g.setColor(Color.BLUE);
            g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
        }
        else if (gameOver) 
        {//otherwise the game is over

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 400, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            //If statement for when player1 wins
            if (playerOneScore > playerTwoScore) {
                g.drawString("Player 1 Wins!", 165, 200);
            }
            else {//otherwise... statement for when player2 wins
                g.drawString("Player 2 Wins!", 165, 200);
            }
            //Would you l
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press space to restart.", 150, 400);
        }
    }


    //The following statements will signify when a key specified is pressed,
    //so if a button is pressed, we must also account for if a button
    //is being lifted as well
    public void keyTyped(KeyEvent e) {}


    //statement for if 'P' is pressed. show the titlescreen = false,
    //while the playing screen = true
    public void keyPressed(KeyEvent e) 
    {
        if (showTitleScreen) 
        {
            if (e.getKeyCode() == KeyEvent.VK_P) 
            {
                showTitleScreen = false;
                playing = true;
            }
        }//if you are playing what buttons are pressed?
        else if(playing)
        {
            if (e.getKeyCode() == KeyEvent.VK_UP) 
            {//up
                upPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
            {//down
                downPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) 
            {//'W'
                wPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) 
            {//'S'
                sPressed = true;
            }
        }
        else if (gameOver) 
        {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) 
            {//'Spacebar'
                gameOver = false;//game will not be over
                showTitleScreen = true;//show the title screen again
                //reset players
                playerOneY = 250;
                playerTwoY = 250;
                //reset ball
                ballX = 250;
                ballY = 250;
                //reset score
                playerOneScore = 0;
                playerTwoScore = 0;
            }
        }
    }

    //when the specific keys used to play are released,
    //record the keyevent
    public void keyReleased(KeyEvent e) 
    {
        if (playing) 
        {
            if (e.getKeyCode() == KeyEvent.VK_UP)
            {//up
                upPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            {//down
                downPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) 
            {//'W'
                wPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) 
            {//'S'
                sPressed = false;
            }//closing else if
        }//closing keyrelease event if
        
    }//closing method
	public void randomColors() {
		gBall = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		g1 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		g2 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}
	


}//closing class