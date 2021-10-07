package com.example.sudokuapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoard extends View{
    private final int boardColor;
    private final int cellFillColor;
    private final int cellsHighlightColor;

    private final int letterColor;
    private final int letterColorSolve;
    private final int numberInput;
    private final int wrongNumber;


    private final Paint boardColorPaint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellsHighlightColorPaint = new Paint();

    private final Paint letterPaint = new Paint();
    private final Rect letterPaintBounds = new Rect();
    private final Paint numberInputPaint = new Paint();
    private final Paint wrongNumberPaint = new Paint();

    private int cellSize;

    private final SudokuInitilazer sInitilazer = new SudokuInitilazer();

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                0,0);

        try {
            boardColor =arr.getInteger(R.styleable.SudokuBoard_boardColor,0);
            cellFillColor =arr.getInteger(R.styleable.SudokuBoard_cellFillColor,0);
            cellsHighlightColor =arr.getInteger(R.styleable.SudokuBoard_cellsHighlightColor,0);
            letterColor = arr.getInteger(R.styleable.SudokuBoard_letterColor,0);
            letterColorSolve = arr.getInteger(R.styleable.SudokuBoard_letterColorSolve,0);
            numberInput = arr.getInteger(R.styleable.SudokuBoard_numberInput, 0);
            wrongNumber = arr.getInteger(R.styleable.SudokuBoard_wrongNumber,0);

        }finally {
            arr.recycle();
        }
    }

    int height;
    int width;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);

        int dimension = Math.min(width, height);
        cellSize = dimension/9;

        setMeasuredDimension(dimension, dimension);
    }
    @Override
    protected void onDraw(Canvas canvas){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        cellFillColorPaint.setStyle(Paint.Style.FILL);
        boardColorPaint.setAntiAlias(true);
        cellFillColorPaint.setColor(cellFillColor);

        cellsHighlightColorPaint.setStyle(Paint.Style.FILL);
        boardColorPaint.setAntiAlias(true);
        cellsHighlightColorPaint.setColor(cellsHighlightColor);

        letterPaint.setStyle(Paint.Style.FILL);
        letterPaint.setAntiAlias(true);
        letterPaint.setColor(letterColor);

        numberInputPaint.setStyle(Paint.Style.FILL);
        numberInputPaint.setAntiAlias(true);
        numberInputPaint.setColor(numberInput);

        wrongNumberPaint.setStyle(Paint.Style.FILL);
        wrongNumberPaint.setAntiAlias(true);
        wrongNumberPaint.setColor(wrongNumber);

        colorCell(canvas, sInitilazer.getSelected_row(), sInitilazer.getSelected_column());
        canvas.drawRect(0,0,width,height, boardColorPaint);
        drawBoard(canvas);
        drawNumbers(canvas);
       // checkNumbers(canvas);

    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean isValid;

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            sInitilazer.setSelected_row((int)Math.ceil(y/cellSize));
            sInitilazer.setSelected_column((int)Math.ceil(x/cellSize));
            isValid = true;
        }
        else{
            isValid = false;
        }

        return isValid;
    }
    public void drawNumbers(Canvas canvas){

        letterPaint.setTextSize(cellSize);
        for (int row = 0; row < 9 ; row++){
            for (int col = 0; col < 9 ; col++){
                if(SudokuInitilazer.getBoard()[row][col] != 0){
                    String text = Integer.toString(SudokuInitilazer.getBoard()[row][col]);
                    float widthN;
                    float heightN;

                    letterPaint.getTextBounds(text,0,text.length(), letterPaintBounds);
                    widthN = letterPaint.measureText(text);
                    heightN = letterPaintBounds.height();

                    canvas.drawText(text,(col*cellSize) + ((cellSize-widthN)/2),
                            (row*cellSize+cellSize) - ((cellSize - heightN)/2),
                            letterPaint);
                }

            }
        }
        letterPaint.setColor(letterColorSolve);
    }

//    public void checkNumbers(Canvas canvas) {
//        float widthN;
//        float heightN;
//        String text;
//        numberInputPaint.setTextSize(cellSize);
//        if (SudokuActivity.ras) {
//                text = Integer.toString(SudokuInitilazer.getBoard()[sInitilazer.getSelected_row()][sInitilazer.getSelected_column()]);
//                numberInputPaint.getTextBounds(text, 0, text.length(), letterPaintBounds);
//                widthN = numberInputPaint.measureText(text);
//                heightN = letterPaintBounds.height();
//
//                canvas.drawText(text, (sInitilazer.getSelected_column() * cellSize) + ((cellSize - widthN) / 2),
//                        (sInitilazer.getSelected_row() * cellSize + cellSize) - ((cellSize - heightN) / 2),
//                        numberInputPaint);
//                numberInputPaint.setColor(numberInput);
//            } else {
//                text = Integer.toString(SudokuInitilazer.getBoard()[sInitilazer.getSelected_row()][sInitilazer.getSelected_column()]);
//                wrongNumberPaint.getTextBounds(text, 0, text.length(), letterPaintBounds);
//                widthN = wrongNumberPaint.measureText(text);
//                heightN = letterPaintBounds.height();
//
//                canvas.drawText(text, (sInitilazer.getSelected_column() * cellSize) + ((cellSize - widthN) / 2),
//                        (sInitilazer.getSelected_row() * cellSize + cellSize) - ((cellSize - heightN) / 2),
//                        wrongNumberPaint);
//                wrongNumberPaint.setColor(wrongNumber);
//            } }

    private void colorCell(Canvas canvas, int row, int col){
        if(sInitilazer.getSelected_column() != -1 && sInitilazer.getSelected_row() != -1){
            canvas.drawRect((col-1)*cellSize,0,col*cellSize, cellSize*9,
                    cellsHighlightColorPaint);
            canvas.drawRect(0,(row-1)*cellSize,cellSize*9, row*cellSize ,
                    cellsHighlightColorPaint);
            canvas.drawRect((col-1)*cellSize,(row-1)*cellSize,col*cellSize , row*cellSize ,
                    cellsHighlightColorPaint);
        }
        invalidate();
    }



    private void drawTickLine(){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(10);
        boardColorPaint.setColor(boardColor);
    }
    private void drawTinLine(){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(4);
        boardColorPaint.setColor(boardColor);
    }
    private void drawBoard(Canvas canvas){
        for (int c = 0; c < 10 ; c++) {
            if (c % 3 == 0) {
                drawTickLine();
            } else {
                drawTinLine();
            }
            canvas.drawLine(cellSize * c, 0,
                    cellSize*c, getWidth(), boardColorPaint);
        }
        for (int r = 0; r < 10 ; r++){
            if(r%3 == 0){
                drawTickLine();
            }
            else {
                drawTinLine();
            }
            canvas.drawLine(0, cellSize * r,
                    getWidth(), cellSize * r, boardColorPaint);
        }
    }

    public SudokuInitilazer getNumbers(){
        return this.sInitilazer;
    }

}
