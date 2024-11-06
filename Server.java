    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.net.ServerSocket;
    import java.net.Socket;

    public class Server {
        public static void main(String[] args) throws IOException {
            System.out.println("Waiting for Client to Join");
            ServerSocket ss= new ServerSocket(4999);

            Socket s =ss.accept(); // waits for the connection, ss.accept actually returns a Socket type object

            System.out.println("Client connected");

            InputStreamReader isr = new InputStreamReader(s.getInputStream()); // gives a reader object
            BufferedReader in = new BufferedReader(isr);  // BufferedReader need a Reader object not an inputStream object
            // so basically Buffereader need a reader i.e. InputStreamReader, which then reads the data(the InputStream i.e. data coming over network) recieved over network
            /*
            InputStreamReader converts the bytes from InputStream into characters, making it compatible with BufferedReader.
            Then BufferedReader adds efficient reading and buffering capabilities, which is ideal for reading lines of text.
            Each class has a different requirement on the type of stream it can wrap:

            PrintWriter can wrap an OutputStream directly.
            BufferedReader requires a Reader, which we create by wrapping the InputStream in an InputStreamReader.
             */

            String str = in.readLine();
            System.out.println("Client: " + str);

        }
    }
