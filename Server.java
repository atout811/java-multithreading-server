import java.net.*;
import java.util.concurrent.TimeUnit;

import java.io.*; 

public class Server extends Thread{
    public synchronized static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(8080);
        System.out.println("Server starts");
        System.out.println("Waiting for a client to connect ... ");  
        while(true){
            try{
               
                Socket client = ss.accept();
                // InputStream inputStream = s.getInputStream();
                // DataInputStream in = new DataInputStream(inputStream);
                // OutputStream outputStream = s.getOutputStream();
                // DataOutputStream out = new DataOutputStream(outputStream);
        
                System.out.println("Client Connected !!");

                ClientHandler clientSock
                = new ClientHandler(client);

            
                new Thread(clientSock).start();
                
                // TimeUnit.SECONDS.sleep(1);
                // line = in.readUTF();
              
                
           

            
        }catch(IOException e) {
            System.out.println("got interrupted!");
        }

            
        }
    }
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
  
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                    
                  // get the outputstream of client
                out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
  
                  // get the inputstream of client
                in = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream()));
  
                String line;
                while ((line = in.readLine()) != null) {
  
                    // writing the line message from
                    // client
                    if (line.equals("Connect to server")){
                        System.out.println("Client: "+ line);
                       out.println("Welcome");
                        
                    }
                    else if (line.equals("I need recommendation")){
                        System.out.println("Client: "+ line);
                       out.println("Here is The recommendation");
                        
                    }
                    else if (line.equals("Thanks !")){
                        System.out.println("Client: "+ line);
                       out.println("You're Welcome :) , Bye");
                        
                    }
                    
                    // System.out.printf(
                    //     " Sent from the client: %s\n",
                    //     line);
                 
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
} 



