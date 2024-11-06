# Socket-Programming
Exploring socket programming and computer networking through Java

The code within Client.java and Server.java uses TCP (transmission control Protocol), where first the Connection bewteen Server and Client is established and the data transfer takes place after that.
# Some main points:

# Printwriter:
it is used to send data over network and uses output stream. SO WTF IS OUTPUT STREAM???
Stream is like a pathway for sending data from your program to some other external destination i.e to another file, or network console. Just like System.out is an output stream to direct data to console, OutputStream in Socket is stream to send data to other end of its connection (client to server, or vice versa)

# Flush:
force sends any output stream that is stuck/unsent in the buffer.
# AutoFlusg
it is a feature of PrintWriter class and if its enabled (set to True), the Flush happend automatically.

#BufferedReader:
Now this is an input Stream just like the printwriter is an output Stream. It receives the data which is sent thru PrintWriter
