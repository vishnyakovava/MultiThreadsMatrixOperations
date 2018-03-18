package com.netcracker;

import com.netcracker.entities.MatrixEntity;
import com.netcracker.matrixOperations.MultiThreadMultiply;
import com.netcracker.matrixOperations.OneThreadMultiply;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MatrixEntity leftMatrix = new MatrixEntity(2000, 900);
        MatrixEntity rightMatrix = new MatrixEntity(900, 2000);
        OneThreadMultiply oper  = new OneThreadMultiply();

//        System.out.println("left:");
//        leftMatrix.printMatrix();
//        System.out.println("right:");
//        rightMatrix.printMatrix();

        System.out.println("multiply one thread result:");
        oper.multiplyMatrix(leftMatrix, rightMatrix);//.printMatrix();


        System.out.println("multiply multythreads result:");
        MultiThreadMultiply test = new MultiThreadMultiply();
        MatrixEntity result = test.multiplyMultiThreads(leftMatrix, rightMatrix, 10);
        //result.printMatrix();

    }
}
