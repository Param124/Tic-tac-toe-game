package com.mygame;

//import java.awt.BorderLayout;
//import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;

//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;

public class MyGame extends JFrame implements ActionListener

{
	JLabel heading,clockLable;
	Font font=new Font("",Font.BOLD,40); // font present in awt and in this we give 3 things i.e family,style and size
	JPanel mainpanel;
	JButton btns[]=new JButton[9];
	
	// game instance variable
	
	int gameChances[]= {2,2,2,2,2,2,2,2,2};
	int activePlayer=0;
	int wps[][]= {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}
	      };
	int winner=2;
	boolean gameOver=false;
	
	
	MyGame()
	{
		System.out.println("create instance of game");
		setTitle("TIK TAC TOE");
		setSize(1000,1000);
		ImageIcon icon=new ImageIcon("src/img/four.png");
		setIconImage(icon.getImage());
		
		createGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	private void createGUI() 
	{
		this.getContentPane().setBackground(Color.yellow);               // change in background color
		BorderLayout bl=new BorderLayout();
		this.setLayout(bl);
		
		heading=new JLabel("Tic Tac Toe");
		
		heading.setFont(font);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.white);
		
		
		
		this.add(heading,BorderLayout.NORTH);
		
		
		//creating object of JLable for clock
		
		clockLable = new JLabel("Clock");
		clockLable.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(clockLable,BorderLayout.SOUTH);
		
		clockLable.setForeground(Color.green);
		
		
		//now we can use thread for time (<<<in this palce we can also use Timer class>>>)
		
		Thread t=new Thread()
				{
			public void run()
			{
			   try
			   {
				while(true)
				{
					
					LocalDateTime datetime=LocalDateTime.now();               // a bit of difference
					String k=datetime.toString();
					clockLable.setText(k);
					Thread.sleep(1000);
					
				}
			   }
			   catch(Exception e)

			{
				  System.out.println(e);
				 
				
			}
				   }
				};
			
				
		t.start();
		
		
		// panel area
		
		mainpanel=new JPanel();
		mainpanel.setLayout(new GridLayout(3,3));
		
		for(int i=1;i<=9;i++)
		{
			//JButton btn=new JButton(i+"");
			JButton btn=new JButton();
			             //btn.setIcon(new ImageIcon("src/img/one.png"));
			btn.setBackground(Color.white);
			
			btn.setFont(font);
			mainpanel.add(btn);
			//btns[i-1]=btn;
			btn.addActionListener((ActionListener) this);
			btn.setName(String.valueOf(i-1));
			
		}
		this.add(mainpanel,BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton currentButton=(JButton)e.getSource();
		String nameStr=currentButton.getName();
		
		int name=Integer.parseInt(nameStr.trim());
		
		// to see the draw match
		if(gameOver==true)
		{
			JOptionPane.showMessageDialog(this,"Game Already over");
			return;
			
		}
		System.out.println(name);
	                                              																
		if(gameChances[name]==2)
		{
			if(activePlayer==1)
			{
				currentButton.setIcon( new ImageIcon("src/img/1.gif"));
				gameChances[name]=activePlayer;
				activePlayer=0;
			}
			else
			{
				currentButton.setIcon( new ImageIcon("src/img/0.png"));
				gameChances[name]=activePlayer;
				activePlayer=1;
			}
			
			//find the winner
			
			for(int[] temp:wps)
			{
				if((gameChances[temp[0]]==gameChances[temp[1]]) && (gameChances[temp[1]]== gameChances[temp[2]]) &&  gameChances[temp[2] ]!=2)
				{
					winner = gameChances[temp[0]];
					
					gameOver=true;
					
					
					JOptionPane.showMessageDialog(null,"Player "+winner+" has won the game");
					int i=JOptionPane.showConfirmDialog(this," Do You Want To Play More");
					if(i==0)
					{																								// 0 pr yes , 1 pr No, 2 pr cancel
						this.setVisible(false);                      													
						new MyGame();
					}
					else if(i==1)
					{
						System.exit(0);
					}
					else
					{
						
					}
					
					System.out.println(i);
					break;
				}
			}
			
			// draw logic
			
			int c=0;
			for(int x:gameChances)
			{
				if(x==2)
				{
					c++;
					break;
				}
			}
			
			if(c==0 && gameOver==false)
			{
				JOptionPane.showMessageDialog(null, "match is draw");
			
				int i=JOptionPane.showConfirmDialog(this,"Play more ? ");
				if(i==0)
				{
					this.setVisible(false);
					new MyGame();
					
				}
				else if(i==1)
				{
					System.exit(0);
					
				}
				else
				{
					
				}
				gameOver=true;
			}
			
	
		 }
		else
		{
			JOptionPane.showMessageDialog(this,"position already occupied");
			System.out.println(this);
		}
		 																					// winning position --->>> {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
		
	}
	
		
		
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
