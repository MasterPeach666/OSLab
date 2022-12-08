//64050012
package lab7;

import java.util.Arrays;

public class Lab_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7, }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);

        // Q4
        Thread[][] thread = new Thread[matC_r][matC_c];
        for (int i = 0; i < matC_r; i++) {
            for (int j = 0; j < matC_r; j++) {
                thread[i][j] = new Thread(new MatrixMulThread(i, j, matA, matB, matC));
                thread[i][j].start();
            }
        }
        // Q5
        try {
            for (int i = 0; i < matC_c; i++) {
                for (int j = 0; j < matC_r; j++) {
                    thread[i][j].join();
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        matC.show();
    }
}

class MatrixMulThread implements Runnable {
    int processing_row;
    int processing_col;
    MyData datA;
    MyData datB;
    MyData datC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
        // Q1
        this.datA = a;
        this.datB = b;
        this.datC = c;
        this.processing_row = tRow;
        this.processing_col = tCol;
    }

    // Q2
    public void run() {
        // Q3
        for (int i = 0; i < datA.data[0].length; i++) {
            datC.data[processing_row][processing_col] += (datA.data[processing_row][i] * datB.data[i][processing_col]);
        }
        // datC.show();
    }
}

class MyData {
    int[][] data;

    MyData(int[][] m) {
        data = m;
    }

    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data) {
            Arrays.fill(aRow, 9);
        }
    }

    void show() {
        System.out.println(Arrays.deepToString(data));
    }
}
//1.6 From worker1 Thread 15 x value is 3
//    From worker2 Thread 16 x value is 7
//    From main x value is 7