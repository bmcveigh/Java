package snake;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.FileSystem;
import main.Location;
import main.Main;
import main.MessageDialog;
import main.Size;

public class SnakeGame extends JDialog {

	private static final long serialVersionUID = 1L;
	
	static Container pane;
	static JLabel ScoreLabel;
	static JButton StartButton;
	static JButton PauseButton;
	static Background bg;
	static JDialog dialog;
	
	static boolean pause;
	static int score;
	static int highscore;
	
	static Snake snake;
	public static Timer time;
	public static int NextInput;
	
	public static Powerup power;	
	
	public SnakeGame(int hs) {
		SnakeGame.highscore = hs;
	}
	
	
	
	public void OpenWindow() {	
		dialog = this;
		pane = getContentPane();
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		int x = Main.main.getLocationOnScreen().x + ((FileSystem.width - 700) / 2);
		int y = Main.main.getLocationOnScreen().y + ((FileSystem.height - 400) / 2);
		setTitle("Snake");
		setModal(true);
		setLocation(x,y);
		setSize(700, 400);
		setLayout(null);
		
		getRootPane().registerKeyboardAction(KeyW, KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);	   
		getRootPane().registerKeyboardAction(KeyA, KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		getRootPane().registerKeyboardAction(KeyS, KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		getRootPane().registerKeyboardAction(KeyD, KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		addWindowListener(new WindowAdapter() 
		{
		  public void windowClosing(WindowEvent e)
		  {
		    ResetGame();
		    if(time != null)
		    	time.cancel();
		    dispose();
		  }
		});
		
		InitImages();
		Draw();	
		ResetGame();
		Timer timer = new Timer();
		timer.schedule( new Tick(), 0, 400 );
		setVisible(true);			
	}
		
	public static void InitImages() {
		try {
			URL url = SnakeGame.class.getResource("snake_body.png");
			Snake.body = ImageIO.read(url);
			url = SnakeGame.class.getResource("snake_head.png");
			Snake.head = ImageIO.read(url);
			url = SnakeGame.class.getResource("snake_coin.png");
			Powerup.img = ImageIO.read(url);
			url = SnakeGame.class.getResource("snake_bg.png");
			Background.img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void ResetGame() {
		pause = true;
		NextInput = -1;
		if(power != null) {
			power.remove();
			power = null;
		}
		SetScore(0);	
		Spawn();	
	}
	
	public void Spawn() {
		if(snake != null) {
			snake.remove();
			snake = null;
		}
		Random r = new Random();
		int x = r.nextInt((bg.GetSize().getWidth() / bg.GetGridSize().getWidth()) - 4) + 2;
		int y = r.nextInt((bg.GetSize().getHeight() / bg.GetGridSize().getHeight()) - 4) + 2;
		int Dir = r.nextInt(5) * 90;
		Location loc = new Location(x, y);
		snake = new Snake(Dir, loc);
	}
		
	public void Draw() {
		pane.setBackground(Color.WHITE);
		Size s = new Size(150, 25);
		Location loc = new Location(20, 20);
		
		StartButton = new JButton("Start");
		StartButton.setBounds(loc.getX(), loc.getY(), s.getWidth(), s.getHeight());
		StartButton.addActionListener(ButtonAction);
		add(StartButton);
		
		loc.setY(65);
		PauseButton = new JButton("Pause");
		PauseButton.setBounds(loc.getX(), loc.getY(), s.getWidth(), s.getHeight());
		PauseButton.addActionListener(ButtonAction);
		PauseButton.setEnabled(false);
		add(PauseButton);
		repaint();	
		
		loc.setY(110);
		AddButton(s, loc, "Neues Spiel", ButtonAction);
		
		loc.setY(225);
		JLabel instructions = new JLabel("<html>" +
										 " W = Nach oben" + "<br>" +
										 " A = Nach links" + "<br>" +
										 " S = Nach unten" + "<br>" +
										 " D = Nach rechts" + "<br>" + "</html>");
		instructions.setBounds(loc.getX(), loc.getY(), 150, 65);
		instructions.setVerticalAlignment(SwingConstants.TOP);
		instructions.setHorizontalAlignment(SwingConstants.LEFT);
		instructions.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(100, 100, 100)), new EmptyBorder(0, 2, 0, 0))); 
		instructions.setBounds(loc.getX(), loc.getY(), 150, 65);
		instructions.setFont(new Font(instructions.getName(), Font.PLAIN, 12));
		add(instructions);
		loc.setY(295);
		AddButton(s, loc, "Schließen", ButtonAction);
				
		Location bgloc = new Location(190, 20);
		Size bgsize = new Size(460 , 300); //310 pixel high; 470 pixel wide
		bg = new Background(bgloc, bgsize);
		bg.draw();		
		repaint();
	}

	
	public static void SetScore(int s) {
		if(ScoreLabel == null) {
			ScoreLabel = new JLabel();
			Location loc = new Location(20, 150);
			ScoreLabel.setVerticalAlignment(SwingConstants.TOP);
			ScoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
			ScoreLabel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(100, 100, 100)), new EmptyBorder(2, 2, 0, 0)));
			ScoreLabel.setBounds(loc.getX(), loc.getY(), 150, 65);
			ScoreLabel.setFont(new Font(ScoreLabel.getName(), Font.PLAIN, 15));
			dialog.add(ScoreLabel);
		}
		score = s;
		if(score > highscore) {
			highscore = score;
			FileSystem.Snake_Highscore = score;
		}
		
		int slength = 2;
		if(snake != null)
			slength = snake.GetSnakeLength();
		
		ScoreLabel.setText("<html>" + 
				"Dein Score: " + score + "<br>" +
			    "Bester Score: " + highscore + "<br>" +
			    "Snake Länge: " + slength + "</html>"
				);
	}

	public void AddButton(Size s, Location loc, String text, ActionListener al) {
		JButton b = new JButton(text);
		b.setBounds(loc.getX(), loc.getY(), s.getWidth(), s.getHeight());
		add(b);
		repaint();
		b.addActionListener(al);
	}
		

	ActionListener ButtonAction = new ActionListener() { 
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				if(b.getText().equals("Schließen")) {
					dispose();
				} else if(b.getText().equals("Start")) {
					pause = false;
					StartButton.setEnabled(false);
					PauseButton.setEnabled(true);
				} else if(b.getText().equals("Pause")) {
					pause = true;
					StartButton.setEnabled(true);
					PauseButton.setEnabled(false);
				} else if(b.getText().equals("Neues Spiel")) {
					pause = true;
					StartButton.setEnabled(true);
					PauseButton.setEnabled(false);
					ResetGame();
				}
			}
		}
	};
	
	public static void Loose(String reason) {
		pause = true;
		StartButton.setEnabled(false);
		PauseButton.setEnabled(false);
		
		String hs;
		if(score > highscore)
			hs = "Du hast einen neuen Highscore!";
		else
			hs = "Du hast deinen Highscore von " + highscore + " Punkten nicht übertroffen!";
		String msg = reason + "\n" + "Dein Punktestand: " + score + "\n" + hs;
		
		MessageDialog MD = new MessageDialog("Verloren", msg, JOptionPane.PLAIN_MESSAGE, SnakeGame.dialog);
		MD.show();
	}
	
	
	ActionListener KeyA = new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
			if(NextInput == -1 && snake != null && !pause) {
				if(snake.GetDirection() == 0 || snake.GetDirection() == 180) {
					NextInput = 90;
				}
			}
		}
	};
	ActionListener KeyW = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(NextInput == -1 && snake != null && !pause) {
				if(snake.GetDirection() == 90 || snake.GetDirection() == 270) {
					NextInput = 180;
				}
			}
		}
	};
	ActionListener KeyS = new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
			if(NextInput == -1 && snake != null && !pause) {
				if(snake.GetDirection() == 90 || snake.GetDirection() == 270) {
					NextInput = 0;
				}
			}
		}
	};
	ActionListener KeyD = new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
			if(NextInput == -1 && snake != null && !pause) {
				if(snake.GetDirection() == 0 || snake.GetDirection() == 180) {
					NextInput = 270;
				}
			}
		}
	};
	
}

class Tick extends TimerTask
{
  @Override 
  public void run()
  {
	  if(!SnakeGame.pause) {
		  if(SnakeGame.snake != null) {
			  if(SnakeGame.NextInput % 90 == 0) {
				  SnakeGame.snake.SetDirection(SnakeGame.NextInput);
			  }
			  SnakeGame.snake.Move(SnakeGame.snake.GetDirection());
			  SnakeGame.NextInput = -1;
			  
			  if(SnakeGame.power == null) {
				  SnakeGame.power = new Powerup();
			  }
		  }
	  }
  }
}


