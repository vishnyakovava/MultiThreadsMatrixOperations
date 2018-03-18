package com.netcracker.matrixOperations;

import com.netcracker.entities.MatrixEntity;

import java.util.logging.Logger;

public class MultiThreadMultiply extends Thread {
    private static Logger log = Logger.getLogger(MultiThreadMultiply.class.getName());

    /** исходные и результирующая матрицы*/
    private  MatrixEntity leftMatrix, rightMatrix, resultMatrix;
    /** ячейка с начальным индексом вычисляется, с коненым - нет*/
    private int startRow, endRow;
    /**  для вычисления суммы в строке */
    private int rowLength;

    public MultiThreadMultiply(){

    }


    public MultiThreadMultiply(MatrixEntity leftMatrix, MatrixEntity rightMatrix, MatrixEntity resultMatrix, int startRow, int endRow){
        setLeftMatrix(leftMatrix);
        setRightMatrix(rightMatrix);
        setResultMatrix(resultMatrix);
        setStartRow(startRow);
        setEndRow(endRow);
        setRowLength(rightMatrix.getRow());
    }

    @Override
    public void run(){
        System.out.println("Thread "+getName()+" started calculation from "+startRow+" row to "+endRow+" row...");
       // log.info("start calculating by thread " + getName());
        for (int i = startRow; i <= endRow ; i++) {
            for (int j = 0; j <resultMatrix.getCol(); j++) {
                resultMatrix.setElem(i, j, getCellValue(i, j));
            }
        }
       // log.info("end calculating by thread "+getName());
    }

    /**
     * @param row номер строки
     * @param col номер колонки
     * @return значение в ячейке с номером [row, col]
     */
    public int getCellValue(int row, int col){
        int result = 0;
        for(int i=0; i<rowLength; i++){
            result+=leftMatrix.getElem(row, i)*rightMatrix.getElem(i, col);
        }
        return result;
    }

    /**
     * @param leftMatrix левая матрица
     * @param rightMatrix правая матрица
     * @param threadCount количество потоков
     * @return результирующая матрица
     */
    public MatrixEntity multiplyMultiThreads(MatrixEntity leftMatrix, MatrixEntity rightMatrix, int threadCount){
        log.info("start calculating...");
        if (leftMatrix.getCol()!= rightMatrix.getRow()) { /**если размерности не ползволяют перемножить матрицы*/
            throw new IllegalArgumentException("Dimension of matrices not allow to multiply");
        }

        if(threadCount > leftMatrix.getRow()) { /*если потоков больше, чем строк*/
            threadCount = leftMatrix.getRow();
        }

        /** считаем количетсво строк для подсчета одним потоком */
        int countRowsToCalc = leftMatrix.getRow() / threadCount;
        /** если строк больше, чем потоков, то добавляем оставшиеся строки в первый поток */
        int addToCalc = leftMatrix.getRow() % threadCount;
        Thread threads[] = new Thread[threadCount];
        resultMatrix = new MatrixEntity(leftMatrix.getRow(), rightMatrix.getCol());
        int startIndex=0;

        for (int i = 0; i < threadCount; i++) {
            int countIndex = ((i==0) ? countRowsToCalc+addToCalc : countRowsToCalc );
            threads[i] = new MultiThreadMultiply(leftMatrix, rightMatrix, resultMatrix, startIndex, startIndex+countIndex-1);
            startIndex+=countIndex;
            threads[i].start();
        }
        try{
            for(Thread thread : threads){
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + getName() + " was interrupted.");
            e.printStackTrace();
        }
        log.info("end calculating...");
        return resultMatrix;
    }

    public MatrixEntity getLeftMatrix() {
        return leftMatrix;
    }

    public void setLeftMatrix(MatrixEntity leftMatrix) {
        this.leftMatrix = leftMatrix;
    }

    public MatrixEntity getRightMatrix() {
        return rightMatrix;
    }

    public void setRightMatrix(MatrixEntity rightMatrix) {
        this.rightMatrix = rightMatrix;
    }

    public MatrixEntity getResultMatrix() {
        return resultMatrix;
    }

    public void setResultMatrix(MatrixEntity resultMatrix) {
        this.resultMatrix = resultMatrix;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }
}
