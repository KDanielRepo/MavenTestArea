import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    Socket socket;
    ServerSocket serverSocket;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    String st;

    public void emulate(String keyCode){

    }

    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        try{
            socketServer.serverSocket = new ServerSocket(7800);
            while (true){
                socketServer.socket = socketServer.serverSocket.accept();
                socketServer.inputStreamReader = new InputStreamReader(socketServer.socket.getInputStream());
                socketServer.bufferedReader = new BufferedReader(socketServer.inputStreamReader);
                socketServer.st = socketServer.bufferedReader.readLine();

                System.out.println(socketServer.st);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

//krotki
//nie potrzebnie tyle komend