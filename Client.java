import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localHost",4999);
        PrintWriter out = new PrintWriter(s.getOutputStream()); // to send data over network

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a messaga to send over the server: ");
        String mesg =input.next();


        out.println(mesg);
        out.flush();

    }
}