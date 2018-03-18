package com.netcracker.matrixOperations;

import com.netcracker.entities.MatrixEntity;

import java.util.logging.Logger;

public class OneThreadMultiply {
    private static Logger log = Logger.getLogger(OneThreadMultiply.class.getName());


    public OneThreadMultiply(){
    }


    public MatrixEntity multiplyMatrix(MatrixEntity left, MatrixEntity right){
        log.info("start calculating...");

        MatrixEntity result = new MatrixEntity(left.getRow(), right.getCol());

        int temp=0;
        for(int i=0; i< left.getRow(); i++){
            for(int j=0; j < right.getCol(); j++){
                for(int k=0; k < right.getRow(); k++){
                    temp += left.getElem(i,k)*right.getElem(k,j);
                }
                result.setElem(i, j, temp);
                temp=0;
            }
        }

        log.info("end calculating...");
        return result;
    }

}
