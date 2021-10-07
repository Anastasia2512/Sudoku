package com.example.sudokuapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.util.concurrent.Callable;

public class SudokuClient {
    public static int numberFromClient;
    public static ObjectOutputStream toServer;
    public static ObjectInputStream fromServer;
    public static Socket clientSocket;



    public SudokuClient(int num) {
        numberFromClient = num;
    }
    boolean res = false;
    public Callable<Boolean> action = () -> {
        try {
            clientSocket = new Socket("10.0.2.2", 8010);
            System.out.println("Client: Socket created");


            toServer = new ObjectOutputStream(clientSocket.getOutputStream());
            fromServer = new ObjectInputStream(clientSocket.getInputStream());



            toServer.writeObject("matrix");
            toServer.writeObject(SudokuInitilazer.board);


            toServer.writeObject("checkNumber");
            toServer.writeObject(numberFromClient);
            toServer.writeObject(SudokuInitilazer.selected_row-1);
            toServer.writeObject(SudokuInitilazer.selected_column-1);

             res = (boolean)fromServer.readObject();

            closeClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    };

    public static void closeClient() throws IOException {
        if (clientSocket != null) {
            System.out.println("client: Close all streams");
            toServer.writeObject("stop");
            fromServer.close();
            toServer.close();
            clientSocket.close();
        }
    }
}
