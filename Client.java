import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {

    // initialize socket i/o streams
    private Socket socket = null;
    private static Scanner scanner = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Client(String address, int port, String username) {
        try {
            socket = new Socket(address, port);
            System.out.println(String.format("Welcome %s, you are connected to the server.", username));

            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        try {
            String message = "";

            while(!message.equals("exit()")){
                try {
                    message = input.readLine();
                    output.writeUTF(message);
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        try {
            input.close();
            output.close();
            socket.close();
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
    public static void main (String[] args){
        // driver code

        // get username
        String username = getUsername();

        // connect to server
        Client client = new Client("localhost", 5000, username);

    }

    public static String getUsername(){
        try{
            scanner = new Scanner(System.in);

            System.out.println("Please enter your username: ");
            String uname = scanner.next();
            return uname;
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
