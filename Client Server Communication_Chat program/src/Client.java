
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    private static final SMS[] messageHistory = new SMS[100];
    private static int messageCount = 0;
    private static int messageIdCounter = 1;
    static Scanner consoleInput = new Scanner(System.in);
    public void RunClient () throws IOException {
        Socket s = new Socket("localhost", 4999);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true); // to send messages
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream())); // to receive messages

        // Thread to listen to server messages
      /* Thread serverListener = new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    //messageIdCountr++;

                    SMS message = new SMS(messageIdCountr, timestamp, serverMessage);
                    System.out.println(message);
                    addMessage(message);
                }
            } catch (IOException e) {
                System.out.println("Server disconnected.");
            }
        });*/
        Thread serverListener = new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    // Display serverMessage as-is without adding extra ID or timestamp

                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    SMS message = new SMS(messageIdCounter, timestamp, "Server: " + serverMessage);
                    System.out.println(message);
                    addMessage(message);
                    messageIdCounter++;
                }
            } catch (IOException e) {
                System.out.println("Server disconnected.");
            }
        });
















        serverListener.start();

        Scanner input = new Scanner(System.in);


        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1: Send a message");
            System.out.println("2: View message history");
            System.out.println("3: Search message by ID");
            System.out.println("4: Delete message by ID");
            System.out.println("5: Delete all messages");
            System.out.println("6: Sort Messages");
            System.out.println("7: close server");

            String option = input.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Message to the Server: ");
                    String clientMessage = input.nextLine();
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    SMS message = new SMS(messageIdCounter++, timestamp, "Client: " + clientMessage);
                    out.println(clientMessage);
                    addMessage(message);
                    break;
                case "2":
                    displayMessageHistory();
                    break;
                case "3":
                    System.out.print("Enter the message ID to search: ");
                    searchMessageById();
                    break;
                case "4":
                    System.out.print("Enter the message ID to delete: ");
                    deleteMessageById();
                    break;
                case "5":
                    deleteAllMessagess();
                    break;

                case "6":
                    displaySortedMessageHistory();
                    break;
                case "7":
                    System.out.println("Exiting client...");
                    s.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addMessage(SMS message) {
        if (messageCount < messageHistory.length) {
            messageHistory[messageCount] = message;
            messageCount++;
        } else {
            System.out.println("Cant add more messages.");
        }
    }

    private void displayMessageHistory() {
        System.out.println("Message History:");
        int Khaali=1;

        for (int i = 0; i < messageHistory.length; i++) {
            if(messageHistory[i]!=null)
            {
                System.out.println(messageHistory[i]);
                Khaali=0;
            }
        }
        if(Khaali==1)
        {
            System.out.println("No Meessages in the History");
        }
    }

    private void searchMessageById() {
        System.out.print("Enter the message ID to search: ");
        int messageId;
        messageId= consoleInput.nextInt();
        int found=0;
        for (int i = 0; i < messageHistory.length; i++) {
            if (messageHistory[i]!=null && messageHistory[i].id == messageId) {
                System.out.println("Message found: " + messageHistory[i]);
                found =1;

            }
        }
        if(found==0)
        {
            System.out.println("Message with ID " + messageId + " not found.");
        }

    }

    private void deleteMessageById() {
        int deleted=0;
        System.out.print("Enter the messageID to delete : ");
        int messageId=consoleInput.nextInt();

        for (int i = 0; i < messageHistory.length; i++) {
            if (messageHistory[i]!= null && messageHistory[i].id == messageId) {
                System.out.println("Deleting message: " + messageHistory[i]);
                // masla nai hoga cz in DisplayHistory we are doing k agr index is NULL, then dont print it
                messageHistory[i] = null;
                messageCount--;
                System.out.println("Message deleted.");
                deleted =1;
                return;

            }
        }
        if(deleted==0)
        {
            System.out.println("Message with ID " + messageId + " not found.");

        }
    }
    private void deleteAllMessagess()
    {
        for (int i = 0; i < messageHistory.length; i++) {
            if(messageHistory[i]!=null)
            {
                messageHistory[i]=null;
                messageCount--;
            }
        }
        System.out.println("Cleared the message history (wink)");
    }


    public void displaySortedMessageHistory() {
      /*  Arrays.sort(messageHistory);
        System.out.println("Sorted Message History:");
        if (messageHistory.length == 0) {
            System.out.println("No messages to display.");
        } else {
            for (int i = 0; i < messageHistory.length; i++) {
                if (messageHistory[i] != null) {
                    System.out.println(messageHistory[i]);
                }
            }
        }

       */

        SMS[] Sortarrayy = new SMS[messageCount];
        int idx = 0;
        for (int i = 0; i < messageHistory.length; i++) {
            if (messageHistory[i] != null) {
                Sortarrayy[idx++] = messageHistory[i];
            }
        }
        Arrays.sort(Sortarrayy);

        System.out.println("Sorted Message History:");
        if (Sortarrayy.length == 0) {
            System.out.println("No messages to display.");
        } else {
            for (int i = 0; i < Sortarrayy.length; i++) {
                if (Sortarrayy[i] != null) {
                    System.out.println(Sortarrayy[i]);
                }
            }
        }
    }

}

