import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class CapitalizeClient {

	private BufferedReader in;
    private PrintWriter out;
    /*private static JFrame frame = new JFrame("Maze Game Client Score");
    private static JLabel msg1 = new JLabel("");
    private static JPanel boardPanel;*/
    
 /*   public CapitalizeClient(){
    	    msg1.setBackground(Color.GRAY);
	        frame.getContentPane().add(msg1, "Center");
	    	boardPanel = new JPanel();
	    	boardPanel.setBackground(Color.WHITE);
	    	msg1.setText("Client Score is 0");
	    	boardPanel.add(msg1);
	        frame.add(boardPanel);
	    	
    }
    */
    public void connectToServer() throws IOException {
    	Socket s =new Socket("localhost",2189);
    	Maze M2= new Maze(5,s);	            	            
    	StdDraw.show(0);
    	M2.draw();
    
    	
		new Capitalizer(s).start();
    	
    }
    
    private static class Capitalizer extends Thread {
 	   private Socket socket;
 	   	
 	   //Constructor
 	   public Capitalizer(Socket socket){	       

 		   this.socket = socket;
 		   
 	   }
 	   
 	   public void run(){
 		   try{
 			   BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
               PrintWriter out = new PrintWriter(socket.getOutputStream(), true);   	
 			    
                
 		  // out.println("Welcome Client");
 		   
 		   while (true) {
                String input = in.readLine();
                if (input == null || input.equals(".")) {
                    break;
                } // end if 
                else if(input.startsWith("Welcome")){
             	   System.out.println("The client-server connection established is connected");
                }
                
                else if(input.startsWith("Server")){
             	   System.out.println("Server winner");
             	   Maze.msg1.setText("The Winner is Server");
           	       Maze.boardPanel.add(Maze.msg1);
           	       Maze.frame.add(Maze.boardPanel);
           	       out.println("Server");
      			   
                }
                else if (input.startsWith("Client")){
             	   System.out.println("Client winner");
             	   Maze.msg1.setText("The Winner is Client");
           	       Maze.boardPanel.add(Maze.msg1);
              	   Maze.frame.add(Maze.boardPanel);
           	   
                }
                        
                             
                
                else  if(input.startsWith("Down")){
                	
                	String s ,s1, s2 =  "";
             	   s= input.substring(37 ,38 );
             	   s=s.trim();
             	   int posp = Integer.parseInt(s);
             	   s1= input.substring(38,39);
             	   s1=s1.trim();
             	   int posq = Integer.parseInt(s1);
             	   s2= input.substring(47);
             	   s2 = s2.trim();
             	   int countva =Integer.parseInt(s2);
             	  if((posp == Maze.positionp()) &&(posq-1==Maze.positionq())){
           		   if(countva > Maze.countvalue()){
           			   System.out.println("Game Over");
           			   System.out.println("The Winner is Server");
           			   StdDraw.setPenColor(StdDraw.WHITE);
                	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                	   StdDraw.show(10);
                	   Maze.msg1.setText("The Winner is Server");
                	   Maze.boardPanel.add(Maze.msg1);
                	   Maze.frame.add(Maze.boardPanel);
                	   out.println("Server");
           			   break;
           		   }
           		   else
           		   System.out.println("The Winner is Client");
           		   Maze.msg1.setText("The Winner is Client");
            	   Maze.boardPanel.add(Maze.msg1);
            	   Maze.frame.add(Maze.boardPanel);
            	   out.println("Client");
            	   break;
           		   
           	   }
           	   else{
             	   StdDraw.setPenColor(StdDraw.WHITE);
             	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
             	   StdDraw.show(10);
             	  // StdDraw.setPenColor(StdDraw.BLUE);
            	  // StdDraw.filledCircle(posp + 0.5, (posq-1) + 0.5, 0.25);
            	 //  StdDraw.show(10);
             	   Maze.vis[posp][posq] = false;
             	  // System.out.println("The server pos s1  "+s1);
             	   //System.out.println("The server pos  s2"+s2);
             	 /*  System.out.println("The server pos p "+posp);
              	   System.out.println("The server pos q " + posq);
              	   System.out.println("The server count "+ countva);
              	   System.out.println("The client pos p "+Maze.positionp());
           	       System.out.println("The client pos q " + Maze.positionq());
           	       System.out.println("The client   count "+ Maze.countvalue()); */
             	   System.out.println("The Client count is "+ Maze.countvalue()+ " The server count is " +countva);
             	 
             	   System.out.println("The server hit down key.Now it is client turn to play");
             	   Maze.msg1.setText("The Client count is "+ Maze.countvalue()+ " The server count is " +countva);
           	       Maze.boardPanel.add(Maze.msg1);
           	       Maze.frame.add(Maze.boardPanel);
           	  
             	   
             	   
                }}
                else if(input.startsWith("Right")){
              	  String s ,s1, s2 =  "";
              	   s= input.substring(38 ,39 );
              	   s=s.trim();
              	   int posp = Integer.parseInt(s);
              	   s1= input.substring(39,40);
              	   s1=s1.trim();
              	   int posq = Integer.parseInt(s1);
              	   s2= input.substring(48);
              	   s2 = s2.trim();
              	   int countva =Integer.parseInt(s2);
              	 if((posp+1 == Maze.positionp()) &&(posq==Maze.positionq())){
             		   if(countva > Maze.countvalue()){
             			   System.out.println("Game Over");
             			   System.out.println("The Winner is Server");
             			   StdDraw.setPenColor(StdDraw.WHITE);
                   	       StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                   	       StdDraw.show(10);
                   	   Maze.msg1.setText("The Winner is Server");
                 	   Maze.boardPanel.add(Maze.msg1);
                 	   Maze.frame.add(Maze.boardPanel); 
                 	  out.println("Server");
             			   break;
             		   }
             		  else
                 			System.out.println("The Winner is Client");
             	  Maze.msg1.setText("The Winner is Client");
               	   Maze.boardPanel.add(Maze.msg1);
               	   Maze.frame.add(Maze.boardPanel);
               	out.println("Client");
               	
               	   
             		   break;
                 	   
             	   }
             	   else{
              	   StdDraw.setPenColor(StdDraw.WHITE);
              	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
              	   StdDraw.show(10);
              	  // StdDraw.setPenColor(StdDraw.BLUE);
          	      // StdDraw.filledCircle((posp+1) + 0.5, posq + 0.5, 0.25);
          	      //  StdDraw.show(10);
              	   Maze.vis[posp][posq] = false;
              	  // System.out.println("The server pos s1  "+s1);
              	 //  System.out.println("The server pos  s2"+s2);
              	/* System.out.println("The server pos p "+posp);
            	   System.out.println("The server pos q " + posq);
            	   System.out.println("The server count "+ countva);
            	   System.out.println("The client pos p "+Maze.positionp());
         	       System.out.println("The client pos q " + Maze.positionq());
         	       System.out.println("The client   count "+ Maze.countvalue());*/
              	   System.out.println("The Client count is "+ Maze.countvalue()+ " The server count is " +countva);
              	   System.out.println("The server hit right key.Now it is client turn to play");
              	   Maze.msg1.setText("The Client count is "+ Maze.countvalue()+ " The server count is " +countva);
         	       Maze.boardPanel.add(Maze.msg1);
         	       Maze.frame.add(Maze.boardPanel);
         	  
              	   
             	   }
              	   
                 }
                 	else if(input.startsWith("Left")){
               	   
               	   String s ,s1, s2 =  "";
               	   s= input.substring(37 ,38 );
               	   s=s.trim();
               	   int posp = Integer.parseInt(s);
               	   s1= input.substring(38,39);
               	   s1=s1.trim();
               	   int posq = Integer.parseInt(s1);
               	   s2= input.substring(47);
               	   s2 = s2.trim();
               	   int countva =Integer.parseInt(s2);
               	if((posp-1 == Maze.positionp()) && (posq==Maze.positionq())){
            		   if(countva > Maze.countvalue()){
            			   System.out.println("Game Over");
            			   System.out.println("The Winner is Server");
            			   StdDraw.setPenColor(StdDraw.WHITE);
                   	       StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                   	       StdDraw.show(10);
                   	    Maze.msg1.setText("The Winner is Server");
                 	   Maze.boardPanel.add(Maze.msg1);
                 	   Maze.frame.add(Maze.boardPanel);   
                 	  out.println("Server");
            			   break;
            		   }
            		   else
                  			System.out.println("The Winner is Client");
            		   Maze.msg1.setText("The Winner is Client");
                	   Maze.boardPanel.add(Maze.msg1);
                	   Maze.frame.add(Maze.boardPanel);
                	   out.println("Client");
                	    
            		   break;
                  	   
            	   }
            	   else{
               	   StdDraw.setPenColor(StdDraw.WHITE);
               	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
               	   StdDraw.show(10);
               	 //  StdDraw.setPenColor(StdDraw.BLUE);
          	     //  StdDraw.filledCircle(( posp-1) + 0.5, posq + 0.5, 0.25);
          	    //   StdDraw.show(10);
               	   Maze.vis[posp][posq] = false;
               	  // System.out.println("The server pos s1  "+s1);
               	 //  System.out.println("The server pos  s2"+s2);
               	/*System.out.println("The server pos p "+posp);
           	   System.out.println("The server pos q " + posq);
           	   System.out.println("The server count "+ countva);
           	   System.out.println("The client pos p "+Maze.positionp());
        	       System.out.println("The client pos q " + Maze.positionq());
        	       System.out.println("The client   count "+ Maze.countvalue());*/
                 	System.out.println("The Client count is "+ Maze.countvalue()+ " The server count is " +countva);
               	   System.out.println("The server hit left key.Now it is client turn to play");
               	   Maze.msg1.setText("The Client count is "+ Maze.countvalue()+ " The server count is " +countva);
        	       Maze.boardPanel.add(Maze.msg1);
        	       Maze.frame.add(Maze.boardPanel);
        	  
               	   
            	   }
               	   
                  }
                 
                 	 else if(input.startsWith("Up")){
                	   
                	   String s ,s1, s2 =  "";
                	   s= input.substring(35 ,36 );
                	   s=s.trim();
                	   int posp = Integer.parseInt(s);
                	   s1= input.substring(36,37);
                	   s1=s1.trim();
                	   int posq = Integer.parseInt(s1);
                	   s2= input.substring(45);
                	   s2 = s2.trim();
                	   int countva =Integer.parseInt(s2);
                	   if((posp == Maze.positionp()) &&(posq+1==Maze.positionq())){
                   		   if(countva > Maze.countvalue()){
                   			   System.out.println("Game Over");
                   			   System.out.println("The Winner is Server");
                   			   StdDraw.setPenColor(StdDraw.WHITE);
                     	       StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                     	       StdDraw.show(10);
                     	      Maze.msg1.setText("The Winner is Server");
                       	   Maze.boardPanel.add(Maze.msg1);
                       	   Maze.frame.add(Maze.boardPanel); 
                         	out.println("Server");
                   			   break;
                   		   }
                   		   else
                   			   System.out.println("The Winner is Client");
                   		Maze.msg1.setText("The Winner is Client");
                 	   Maze.boardPanel.add(Maze.msg1);
                 	   Maze.frame.add(Maze.boardPanel);
                 	  out.println("Client");
                 	   
                   		        break;
                   	   }
                   	   else{
                	   StdDraw.setPenColor(StdDraw.WHITE);
                	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                	   StdDraw.show(10);
                	  // StdDraw.setPenColor(StdDraw.BLUE);
                	 //  StdDraw.filledCircle(posp + 0.5, (posq+1) + 0.5, 0.25);
                	 //  StdDraw.show(10);
                	   Maze.vis[posp][posq] = false;
                	//   System.out.println("The server pos s1  "+s1);
                	 //  System.out.println("The server pos  s2"+s2);
                	/*   System.out.println("The server pos p "+posp);
                  	   System.out.println("The server pos q " + posq);
                  	   System.out.println("The server count "+ countva);
                  	   System.out.println("The client pos p "+Maze.positionp());
               	       System.out.println("The client pos q " + Maze.positionq());
               	       System.out.println("The client   count "+ Maze.countvalue());*/
                	   System.out.println("The Client count is "+ Maze.countvalue() + " The server count is " +countva);
                	   System.out.println("The server hit up key.Now it is client turn to play");
                	   Maze.msg1.setText("The Client count is "+ Maze.countvalue()+ " The server count is " +countva);
               	       Maze.boardPanel.add(Maze.msg1);
               	       Maze.frame.add(Maze.boardPanel);
               	  
                   	   }
                	   
                   }
              
                
                  
                
                       } // end while
        } // end try
 		   
 		   
 		   catch (IOException e) {
            System.out.println("Error handling server " + e);
        } // end catch
 		   finally {
            try {
                socket.close();
                } catch (IOException e) {
                System.out.println("Couldn't close a socket, what's going on?");
                }
            System.out.println("Connection with Server closed");
        }
    
            
 	   }
    }// end Capitalizer

    
    
    
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		 CapitalizeClient client = new CapitalizeClient();
		 client.connectToServer();
		 
		 
	}
	
}
