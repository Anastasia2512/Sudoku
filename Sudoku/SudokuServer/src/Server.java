import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {

    private final int port;
    private volatile boolean stopServer;
    private ThreadPoolExecutor threadPool;
    private IHendler requestHendler;

    public Server(int port){
        this.port = port;
        stopServer = false;
        threadPool = null;

    }

    public void run(IHendler hendler) {
        this.requestHendler = hendler;
        Runnable mainServerLogic = () -> {
            this.threadPool = new ThreadPoolExecutor(5, 7, 10, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>());
            try {
                ServerSocket serverSocket = new ServerSocket(this.port);
                while (!stopServer) {
                    Socket serverToSpacificClient = serverSocket.accept();
                    Runnable clientHandling = () -> {
                        try {
                            requestHendler.handle(serverToSpacificClient.getInputStream(),
                                    serverToSpacificClient.getOutputStream());
                        } catch (IOException ioException) {
                            System.out.println(ioException.getMessage());
                        }catch (ClassNotFoundException ignored){
                        }
                        try {
                            serverToSpacificClient.getInputStream().close();
                            serverToSpacificClient.getOutputStream().close();
                            serverToSpacificClient.close();
                        }catch (IOException ioException){
                            ioException.printStackTrace();
                        }
                    };
                    threadPool.execute(clientHandling);
                }
                serverSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        new Thread(mainServerLogic).start();
    }


    public void stop(){
        if(!stopServer){
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopServer = true;
            threadPool.shutdown();
        }

    }

    public static void main(String[] args) {
        Server openServer = new Server(8010);
        openServer.run(new SudokuHendler());
       // openServer.stop();
    }

}
