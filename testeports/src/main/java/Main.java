import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by pmatos9 on 24/05/17.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println("elasticsearch: " + portIsOpen("localhost",9300,20));
        System.out.println("logstash: " + portIsOpen("localhost",5000,20));
        System.out.println("kibana: " + portIsOpen("localhost",5601,20));
        System.out.println("db: " + portIsOpen("localhost",5432,20));
        System.out.println("rabbit: " + portIsOpen("localhost",5672,20));
        System.out.println("dashboard: " + portIsOpen("localhost",12215,20));
        System.out.println("tomcat: " + portIsOpen("localhost",12214,20));
        //System.out.println("jpa: " + portIsOpen("",,20));
        //System.out.println("p1: " + portIsOpen("",,20));

    }

    public static boolean portIsOpen(String ip, int port, int timeout) {
        boolean result = false;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            result = true;
        } catch (Exception ex) {
            //System.out.println(ex.getClass());  -> class java.net.ConnectException
            result = false;
        }

        return result;
    }


}
