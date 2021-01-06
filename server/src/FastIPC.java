import java.util.Date;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class FastIPC{
    public PrintWriter out;
    BufferedReader in;
    Socket socket = null;
    ServerSocket serverSocket = null;

    public FastIPC(int port) throws Exception{
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String msg){
        out.println(msg); // send price update to socket
    }

    public void flush(){
        out.flush();
    }

    public String recv() throws Exception{
        return in.readLine();
    }

    public static void main(String[] args){
        int port = 32000;
        try{
            FastIPC fip = new FastIPC(port);
            long start = new Date().getTime();
            System.out.println("Connected.");
            fip.send("+");
            fip.send(".");
            fip.flush();
            String msg = fip.recv();
            System.out.println(msg);
            long stop = new Date().getTime();
            System.out.println((double)(stop - start)/1000.);
        }catch(Exception e){
            System.exit(1);
        }
    }
}
