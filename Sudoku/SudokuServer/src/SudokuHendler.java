import java.io.*;

public class SudokuHendler implements IHendler{
    private Sudoku sudoku;



    @Override
    public void handle(InputStream fromClient, OutputStream toClient) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(fromClient);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(toClient);

        boolean work = true;
        while (work) {
            switch (objectInputStream.readObject().toString()) {
                case "matrix": {
                    int[][] tempBoard = (int[][])objectInputStream.readObject();
                    this.sudoku = new Sudoku(tempBoard);
                    break;
                }
                case "checkNumber": {
                    int num = (int)objectInputStream.readObject();
                    int row = (int)objectInputStream.readObject();
                    int col = (int)objectInputStream.readObject();
                    if(this.sudoku != null) {
                        boolean valid = this.sudoku.checkNumber(row, col, num);
                        objectOutputStream.writeObject(valid);

                    }
                    break;
                }
                case "stop":{
                    work = false;
                    break;
                }

            }
        }
    }


}
