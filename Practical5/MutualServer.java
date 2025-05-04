import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MutualServer implements Runnable {
    static ServerSocket ss;
    Socket socket;

    MutualServer(Socket newSocket) {
        this.socket = newSocket;
    }

    public static void main(String args[]) throws IOException {
        ss = new ServerSocket(7000);
        System.out.println("Server Started");
        while (true) {
            Socket s = ss.accept();
            MutualServer es = new MutualServer(s);
            Thread t = new Thread(es);
            t.start();
        }
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
