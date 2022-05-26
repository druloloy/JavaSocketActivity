import java.net.*;
import java.io.*;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;

    public Server(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("Server is running on port " + port);
            socket = server.accept();
            System.out.println("Client connected");
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String message = "";
            while(!message.equals("exit()")){
                try{
                    message = input.readUTF();
                    System.out.println(message);
                } catch (Exception e){
                    System.out.println(e.getStackTrace());
                    break;
                }
            }

            socket.close();
            input.close();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    public static void main (String[] args){
        // driver code
        Server server = new Server(5000);
    }
}
