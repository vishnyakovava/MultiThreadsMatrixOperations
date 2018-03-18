package com.netcracker.entities;


public class MatrixEntity {

    private int col, row;
    private int[][]matrix;

    public MatrixEntity(int row, int col){
        setCol(col);
        setRow(row);
        matrix = new int[row][col];
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j< matrix[i].length; j++){
                matrix[i][j]= (int)(Math.random()*11)+1;
            }
        }
    }

    public void printMatrix(){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public int getElem(int row, int col){
        int value=matrix[row][col];
        return value;
    }

    public void setElem(int row, int col, int value){
        matrix[row][col] = value;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
