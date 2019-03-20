package main;

import static by.epam.javatraining.rassolko.maintask01.view.OutputWorker.printlnToConsole;
import static by.epam.javatraining.rassolko.maintask01.view.printToConsole;
import static by.epam.javatraining.rassolko.maintask01.view.insertEmptyLine;

import by.epam.javatraining.rassolko.maintask01.model.entity.*;
import by.epam.javatraining.rassolko.maintask01.model.logic.*;


public class Main 
{

    public static void main(String[] args) 
    {
        printlnToConsole("--- Vector ---");        
        insertEmptyLine();
        
        Vector vector = new Vector( new double[] {44.0, 951.7, 30.0, 80.2, 40.3} );
        printToConsole("initial vector: ");
        printlnToConsole(vector);
        

        
        printToConsole("calculating geometric mean: ");
        double gMean = VectorWorker.calcGeometricMean(vector);
        printlnToConsole(gMean);
        
        
        printToConsole("calculating arythmetic mean: ");
        double aMean = VectorWorker.calcArithmeticMean(vector);
        printlnToConsole(aMean);
        
        
        printToConsole("getting first local minimum position: ");
        int minPos = VectorWorker.getFirstLocalMinPosition(vector);
        printlnToConsole(minPos);
        
        printToConsole("getting first local maximum position: ");
        int maxPos = VectorWorker.getFirstLocalMaxPosition(vector);
        printlnToConsole(maxPos);
        
        printToConsole("getting minimal value: ");
        double min = VectorWorker.getMin(vector);
        printlnToConsole(min);
        
        printToConsole("getting maximal value: ");
        double max = VectorWorker.getMax(vector);
        printlnToConsole(max);
        
        printToConsole("reversed vector: ");
        VectorWorker.reverse(vector);
        printlnToConsole(vector);
        
        printToConsole("check if vector is sorted: ");
        boolean sorted = VectorWorker.isSorted(vector);
        printlnToConsole(sorted);
        
        printToConsole("sorting vector, ascending: ");
        ArraysWorker.sort(vector.asArray(), 0, vector.length(), 
                ArraysWorker.Order.Ascending);
        printlnToConsole(vector);
        
        printToConsole("check if vector is sorted: ");
        sorted = VectorWorker.isSorted(vector);
        printlnToConsole(sorted);
        
        printToConsole("sorting vector, descending: ");
        ArraysWorker.sort(vector.asArray(), 0, vector.length(), 
                ArraysWorker.Order.Descending);
        printlnToConsole(vector);
        
        printToConsole("check if vector is sorted: ");
        sorted = VectorWorker.isSorted(vector);
        printlnToConsole(sorted);
        
        insertEmptyLine();
        
        printlnToConsole("--- Matrix ---");        
        insertEmptyLine();
        
        Matrix mt = new Matrix(5, 5);
        mt.set(
        new double[][]
        {
            {1, 2, 3, 4, 20},
            {5, 6, 7, 8, 17},
            {9, 10, 110, 12, 5},
            {13, 14, 15, 16, 2},
            {17, 18, 19, 20, 1}
        }
        );
        
        printlnToConsole(mt);
        
        printToConsole("getting minimal value: ");
        double minMatr = MatrixWorker.getMin(mt);
        printlnToConsole(minMatr);
        
        printToConsole("getting maximal value: ");
        double maxMatr = MatrixWorker.getMax(mt);
        printlnToConsole(maxMatr);
        
        printToConsole("checking if symmetric: ");
        boolean symmetric = MatrixWorker.isSymmetric(mt);
        printlnToConsole(symmetric);
        
        printlnToConsole("making matrix symmetric: ");
        mt.set(
        new double[][]
        {
            {1, 2, 3, 4, 20},
            {2, 6, 7, 8, 17},
            {3, 7, 110, 12, 5},
            {4, 8, 12, 2, 20},
            {20, 17, 5, 20, 1}
        }
        );
        printlnToConsole(mt);
        printToConsole("checking if symmetric: ");
        symmetric = MatrixWorker.isSymmetric(mt);
        printlnToConsole(symmetric);
        
        printToConsole("getting first local minimum position: ");
        int minPosMt = MatrixWorker.getFirstLocalMinPosition(mt);
        printlnToConsole(minPosMt);
        
        printToConsole("getting first local maximum position: ");
        int maxPosMt = MatrixWorker.getFirstLocalMaxPosition(mt);
        printlnToConsole(maxPosMt);
        
        printlnToConsole("transponsing matrix: ");
        mt.transpose();
        printlnToConsole(mt);
        
    }

}
