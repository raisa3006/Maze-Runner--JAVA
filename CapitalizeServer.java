import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class CapitalizeServer {
	public static int N= 5;
	
	

	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		
	        System.out.println("The capitalization server is running.");
	        ServerSocket listener = new ServerSocket(2189);
	       
	       
	        
	        try {
	            while (true) {
	            	
	            	Socket s = listener.accept();
	            	Maze1 M1 = new Maze1(5,s);	            	            
	            	StdDraw.show(0);
	        	    M1.draw();
	        		new Capitalizer(s).start();
	            } // end try
	        } finally {
	            listener.close();
	        } // end final
	        
	    
	} // end main
	
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
               } // endif 
               else if(input.startsWith("Connected")){
            	   System.out.println("The client is connected");
            	   
               }
               else if(input.startsWith("Server")){
            	   System.out.println("Server winner");
            	   Maze1.msg1.setText("The Winner is Server");
           	       Maze1.boardPanel.add(Maze1.msg1);
           	       Maze1.frame.add(Maze1.boardPanel);
           	       
               }
               else if (input.startsWith("Client")){
            	   System.out.println("Client winner");
            	   Maze1.msg1.setText("The Winner is Client");
           	       Maze1.boardPanel.add(Maze1.msg1);
              	   Maze1.frame.add(Maze1.boardPanel);
           	   
               }
               
               else if(input.startsWith("Down")){
            	   
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
            	   
            	   if((posp == Maze1.positionp()) &&(posq-1==Maze1.positionq())){
               		   if(countva > Maze1.countvalue()){
               			   System.out.println("Game Over");
               			   System.out.println("The Winner is Client");
               			   StdDraw.setPenColor(StdDraw.WHITE);
              	           StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
              	           StdDraw.show(10);
              	           Maze1.msg1.setText("The Winner is Client");
                  	       Maze1.boardPanel.add(Maze1.msg1);
                  	       Maze1.frame.add(Maze1.boardPanel);
                  	       out.println("Client");
             			   
               		 	   break;
               		   }
               		   else
               			System.out.println("The Winner is Server");
               		    Maze1.msg1.setText("The Winner is Server");
        	            Maze1.boardPanel.add(Maze1.msg1);
        	            Maze1.frame.add(Maze1.boardPanel);
        	            out.println("Server");
        	   
               		   break;
               	   }
            	   else{
            	   StdDraw.setPenColor(StdDraw.WHITE);
            	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
            	   StdDraw.show(10);
            	  // StdDraw.setPenColor(StdDraw.BLUE);
            	 //  StdDraw.filledCircle(posp + 0.5, (posq-1) + 0.5, 0.25);
            	//   StdDraw.show(10);
            	   Maze1.vis[posp][posq] = false;
            	   //System.out.println("The client pos s1  "+s1);
            	  // System.out.println("The client pos  s2"+s2);
            	  /* System.out.println("The client pos p "+posp);
               	   System.out.println("The client pos q " + posq);
               	   System.out.println("The client  count "+ countva);
               	   System.out.println("The server pos p "+Maze1.positionp());
            	   System.out.println("The server pos q " + Maze1.positionq());
            	   System.out.println("The server  count "+ Maze1.countvalue());*/
            	   System.out.println("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
            	   System.out.println("The client hit down key.Now it is Server turn to play");
            	   Maze1.msg1.setText("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
           	       Maze1.boardPanel.add(Maze1.msg1);
           	       Maze1.frame.add(Maze1.boardPanel);
           	  
            	   
            	 
            	   
               }
            	   }
               
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
            	   if((posp+1 == Maze1.positionp()) &&(posq==Maze1.positionq())){
             		   if(countva > Maze1.countvalue()){
             			   System.out.println("Game Over");
             			   System.out.println("The Winner is Client");
             			   StdDraw.setPenColor(StdDraw.WHITE);
                	       StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                	       StdDraw.show(10);
                	       Maze1.msg1.setText("The Winner is Client");
                  	       Maze1.boardPanel.add(Maze1.msg1);
                  	       Maze1.frame.add(Maze1.boardPanel);
                  	     out.println("Client");
             			   
             			   break;
             		   }
             		  else
                 			System.out.println("The Winner is Server");
             		  Maze1.msg1.setText("The Winner is Server");
          	           Maze1.boardPanel.add(Maze1.msg1);
          	            Maze1.frame.add(Maze1.boardPanel);
          	          out.println("Server");
          	   
             		        break;
                 	   
             	   }
            	   else{
            	   
            	   StdDraw.setPenColor(StdDraw.WHITE);
            	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
            	   StdDraw.show(10);   
            	  // StdDraw.setPenColor(StdDraw.BLUE);
            	  // StdDraw.filledCircle((posp+1) + 0.5, posq + 0.5, 0.25);
            	 //  StdDraw.show(10);
            	   Maze1.vis[posp][posq] = false;
            	  // System.out.println("The client pos s1  "+s1);
            	  // System.out.println("The client pos  s2"+s2);
            	 /*  System.out.println("The client  pos p "+posp);
               	   System.out.println("The client  pos q " + posq);
               	   System.out.println("The client count  "+ countva);
               	   System.out.println("The server pos p "+Maze1.positionp());
           	       System.out.println("The server pos q " + Maze1.positionq());
           	       System.out.println("The server  count "+ Maze1.countvalue());*/
            	   System.out.println("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
            	   System.out.println("The client hit right key.Now it is Server turn to play");
            	   Maze1.msg1.setText("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
           	       Maze1.boardPanel.add(Maze1.msg1);
           	       Maze1.frame.add(Maze1.boardPanel);
           	  
            	   
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
             	 	if((posp-1 == Maze1.positionp()) && (posq==Maze1.positionq())){
             		   if(countva > Maze1.countvalue()){
             			   System.out.println("Game Over");
             			   System.out.println("The Winner is Client");
             			   StdDraw.setPenColor(StdDraw.WHITE);
                	       StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                	       StdDraw.show(10);
                	       Maze1.msg1.setText("The Winner is Client");
                  	       Maze1.boardPanel.add(Maze1.msg1);
                  	       Maze1.frame.add(Maze1.boardPanel);
                  	     out.println("Client");
             			   
             			   break;
             		   }
             		   else
                   			System.out.println("The Winner is Server");
             		  Maze1.msg1.setText("The Winner is Server");
          	           Maze1.boardPanel.add(Maze1.msg1);
          	            Maze1.frame.add(Maze1.boardPanel);
          	          out.println("Server");
          	   
             		   break;
                   	   
             	   }
           	   else{
             	   StdDraw.setPenColor(StdDraw.WHITE);
             	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
             	   StdDraw.show(10);
             	 //  StdDraw.setPenColor(StdDraw.BLUE);
           	     //  StdDraw.filledCircle((posp-1) + 0.5, posq + 0.5, 0.25);
           	    //   StdDraw.show(10);
             	  Maze1.vis[posp][posq] = false;
             	   //System.out.println("The client pos s1  "+s1);
             	  // System.out.println("The client pos  s2"+s2);
             	/* System.out.println("The client  pos p "+posp);
             	   System.out.println("The client  pos q " + posq);
             	   System.out.println("The client  count "+ countva);
             	  System.out.println("The server pos p "+Maze1.positionp());
           	   System.out.println("The server pos q " + Maze1.positionq());
           	   System.out.println("The server  count "+ Maze1.countvalue());*/
               	 System.out.println("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
             	   System.out.println("The client hit left key.Now it is Server turn to play");
             	  Maze1.msg1.setText("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
          	       Maze1.boardPanel.add(Maze1.msg1);
          	       Maze1.frame.add(Maze1.boardPanel);
          	  
             	   
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
              	 if((posp == Maze1.positionp()) &&(posq+1==Maze1.positionq())){
             		   if(countva > Maze1.countvalue()){
             			   System.out.println("Game Over");
             			   System.out.println("The Winner is Client");
             			   StdDraw.setPenColor(StdDraw.WHITE);
                	       StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
                	       StdDraw.show(10);
                	       Maze1.msg1.setText("The Winner is Client");
                  	       Maze1.boardPanel.add(Maze1.msg1);
                  	       Maze1.frame.add(Maze1.boardPanel);
                  	     out.println("Client");
             			   
             			   break;
             		   }
             		   else
             			   System.out.println("The Winner is Server");
             		       Maze1.msg1.setText("The Winner is Server");
               	           Maze1.boardPanel.add(Maze1.msg1);
               	            Maze1.frame.add(Maze1.boardPanel);
               	         out.println("Server");
               	   
             		   break;
             	   }
          	   else{
              	   StdDraw.setPenColor(StdDraw.WHITE);
              	   StdDraw.filledCircle(posp + 0.5, posq + 0.5, 0.3);
              	   StdDraw.show(10);
              	  // StdDraw.setPenColor(StdDraw.BLUE);
          	      // StdDraw.filledCircle(posp + 0.5, (posq+1) + 0.5, 0.25);
          	     //  StdDraw.show(10);
              	   Maze1.vis[posp][posq] = false;
              	   //System.out.println("The client pos s1  "+s1);
              	  // System.out.println("The client pos  s2"+s2);
              /*	 System.out.println("The client pos p "+posp);
             	   System.out.println("The client pos q " + posq);
             	   System.out.println("The client count "+ countva);
             	  System.out.println("The server pos p "+Maze1.positionp());
           	     System.out.println("The server pos q " + Maze1.positionq());
           	    System.out.println("The server  count "+ Maze1.countvalue());*/
              	 System.out.println("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
              	   System.out.println("The client hit up key.Now it is Server turn to play");
              	 Maze1.msg1.setText("The Server count is "+ Maze1.countvalue()+ " The client count is " +countva);
         	       Maze1.boardPanel.add(Maze1.msg1);
         	       Maze1.frame.add(Maze1.boardPanel);
         	  
              	   
          	   }
              	   
                 }
                      } // end while
       } // end try
		   
		   catch (IOException e) {
           System.out.println("Error handling client " + e);
       } // end catch
		   finally {
           try {
        	   
               socket.close();
               } catch (IOException e) {
               System.out.println("Couldn't close a socket, what's going on?");
               }
           System.out.println("Connection with client closed");
       }
   
           
	   }
   }// end Capitalizer
   

} // end CapitalieServer
