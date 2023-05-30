package com.mycompany.p1;
//import java.util.Random;
//        int[] array = new int[300];
//        Random rand = new Random();
//        for (int i = 0; i < array.length; i++) {
//            array[i] = rand.nextInt(-100, 100);
//            System.out.printf("%d, ", array[i]);
//            if((i + 1) % 10 == 0 )
//                System.out.println();
//        }
//        System.out.println();

//Class for results
class Results {

    private int lowerBoundary;
    private int upperBoundary;
    private int maxSubSeqSum;
    private long CPUTime;

    public int getLower() {
        return this.lowerBoundary;
    }

    public void setLower(int index) {
        this.lowerBoundary = index;
    }

    public int getUpper() {
        return this.upperBoundary;
    }

    public void setUpper(int index) {
        this.upperBoundary = index;
    }

    public int getSubSum() {
        return this.maxSubSeqSum;
    }

    public void setSubSum(int sum) {
        this.maxSubSeqSum = sum;
    }

    public long getTime() {
        return this.CPUTime;
    }

    public void setTime(long time) {
        this.CPUTime = time;
    }
}

public class MaxSubsequence {

    static final int MIN_VALUE = -2147483648;

    public static void main(String[] args) {

        //Initialize Test Array
        int[] array = {
            -73, 8, 5, 76, -2, 0, 22, -1, -52, 3,
            25, 32, 4, -93, 54, -68, 28, -8, 91, 71,
            74, 55, 51, 87, -87, 2, -10, -96, 17, 37,
            -14, 50, -4, 34, -89, -28, -4, -62, 95, -53,
            -70, -7, -52, 27, 25, -52, -68, -40, -79, -46,
            -96, 25, -16, 4, -6, -38, -54, 43, 69, 69,
            73, 36, 22, 21, 9, 34, -41, -50, -46, -16,
            5, -76, -8, -31, -81, -90, 63, -18, 63, -66,
            79, 51, -43, 81, 31, 39, 79, 77, -58, -73,
            77, 60, -19, -74, -32, 12, -15, 58, -77, 40,
            -12, 61, 73, 54, -5, -74, -90, -43, 34, -3,
            18, -77, 91, 8, -32, 81, 37, -44, 84, 4,
            33, 3, -5, -55, 56, -18, -91, 36, 17, 95,
            21, -52, -82, -10, -23, 39, 30, -2, 60, 45,
            30, -61, -79, 27, 88, -69, -45, 7, -75, -58,
            39, -77, -2, 51, -93, -96, -26, 16, 8, 24,
            92, -11, 58, -56, -83, -2, 64, 48, -50, 59,
            7, -39, -18, 62, 41, 66, 58, 49, -77, -74,
            -69, -71, 68, 5, -6, 19, -8, -18, 35, -63,
            53, 14, 87, 32, 22, 87, 83, 2, 74, -88,
            -58, -93, -23, 37, 96, -14, 85, -61, -50, -98,
            -29, 34, 83, -94, -59, 79, -24, -34, -32, 37,
            27, 60, 56, 96, 85, 84, -68, 77, 38, 36,
            84, -79, -77, 56, 95, 37, -65, -70, -23, -93,
            -64, 54, 57, 87, 32, 36, -32, -67, 69, 82,
            -75, 91, 82, -11, -42, -78, 59, -83, -79, 39,
            48, -85, -47, 88, -23, 47, -44, -99, -80, 26,
            73, 93, -5, 68, -1, 66, -32, -37, 48, -14,
            -76, 66, 28, -93, -76, 41, 26, -14, 45, 87,
            -31, 63, 30, -48, -67, -15, -53, 68, -50, -6
        };

        //Run our four algorithms
        Results Algo1 = maxSubSum1(array);
        Results Algo2 = maxSubSum2(array);
        Results Algo3 = maxSumRec(array, 0, array.length - 1);
        Results Algo4 = maxDynamic(array);

        //Display results for the four algorithms
        System.out.printf("Algorithm 1 cpu time: %d\nMax Sub Sequence: %d\nLower Boundary Index: %d\nUpper Boundary Index: %d\n\n", Algo1.getTime(), Algo1.getSubSum(), Algo1.getLower(), Algo1.getUpper());
        System.out.printf("Algorithm 2 cpu time: %d\nMax Sub Sequence: %d\nLower Boundary Index: %d\nUpper Boundary Index: %d\n\n", Algo2.getTime(), Algo2.getSubSum(), Algo2.getLower(), Algo2.getUpper());
        System.out.printf("Algorithm 3 cpu time: %d\nMax Sub Sequence: %d\nLower Boundary Index: %d\nUpper Boundary Index: %d\n\n", Algo3.getTime(), Algo3.getSubSum(), Algo3.getLower(), Algo3.getUpper());
        System.out.printf("Algorithm 4 cpu time: %d\nMax Sub Sequence: %d\nLower Boundary Index: %d\nUpper Boundary Index: %d\n\n", Algo4.getTime(), Algo4.getSubSum(), Algo4.getLower(), Algo4.getUpper());
    }

    //Algorithm 1
    static Results maxSubSum1(int[] a) {
        long Start = System.nanoTime();
        Results res = new Results();
        int maxSum = MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;

                for (int k = i; k <= j; ++k) {
                    thisSum += a[k];
                }

                if (thisSum > maxSum) {
                    res.setLower(i);
                    res.setUpper(j);
                    maxSum = thisSum;
                }
            }
        }

        res.setSubSum(maxSum);
        long End = System.nanoTime();
        res.setTime(End - Start);
        return res;
    }

    //Algorithm 2
    static Results maxSubSum2(int[] a) {
        long Start = System.nanoTime();
        Results res = new Results();
        int maxSum = MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    res.setLower(i);
                    res.setUpper(j);
                    maxSum = thisSum;
                }
            }
        }

        res.setSubSum(maxSum);
        long End = System.nanoTime();
        res.setTime(End - Start);
        return res;
    }

    //Algorithm 3
    static Results maxSumRec(int[] a, int left, int right) {
        Results res = new Results();
        long start = System.nanoTime();

        if (left == right) {
            res.setSubSum(a[left]);
            res.setLower(left);
            res.setUpper(right);
            return res;
        }

        int center = (left + right) / 2;
        Results leftResults = maxSumRec(a, left, center);
        int maxLeftSum = leftResults.getSubSum();
        int maxLeftLowerIndex = leftResults.getLower();
        int maxLeftUpperIndex = leftResults.getUpper();
        Results rightResults = maxSumRec(a, center + 1, right);
        int maxRightSum = rightResults.getSubSum();
        int maxRightLowerIndex = rightResults.getLower();
        int maxRightUpperIndex = rightResults.getUpper();

        int maxLeftBorderSum = MIN_VALUE, leftBorderSum = 0, maxLeftBorderIndex = 0;

        //maxLeftSum 
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderIndex = i;
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = MIN_VALUE, rightBorderSum = 0, maxRightBorderIndex = 0;
        for (int j = center + 1; j <= right; j++) {
            rightBorderSum += a[j];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderIndex = j;
                maxRightBorderSum = rightBorderSum;
            }
        }

        res.setSubSum(max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum));
        if (max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum) == maxLeftSum) {
            res.setLower(maxLeftLowerIndex);
            res.setUpper(maxLeftUpperIndex);
        } else if (max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum) == maxRightSum) {
            res.setLower(maxRightLowerIndex);
            res.setUpper(maxRightUpperIndex);
        } else if (max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum) == maxLeftBorderSum + maxRightBorderSum) {
            res.setLower(maxLeftBorderIndex);
            res.setUpper(maxRightBorderIndex);
        }

        long End = System.nanoTime();
        res.setTime(End - start);
        return res;
    }

    // Max function needed for algorithm 3
    public static int max3(int first, int second, int third) {
        return Math.max(Math.max(first, second), third);
    }

    //Algorithm 4
    static Results maxDynamic(int[] a) {
        long Start = System.nanoTime();
        Results res = new Results();
        int[] MS = new int[a.length];
        MS[0] = a[0];
        int maxSubSum = MIN_VALUE;
        int j = 0;

        for (int i = 1; i <= a.length - 1; i++) {
            MS[i] = Math.max(MS[i - 1] + a[i], a[i]);
            if (MS[i - 1] + a[i] < a[i]) {
                j = i;
            }
            if (MS[i - 1] + a[i] > MS[res.getUpper()]) {
                res.setUpper(i);
            }
            if (MS[i] > maxSubSum) {
                maxSubSum = MS[i];
            }
            if (j <= res.getUpper()) {
                res.setLower(j);
            }
        }
        res.setSubSum(maxSubSum);
        long End = System.nanoTime();
        res.setTime(End - Start);
        return res;
    }
}

/* Output

Algorithm 1 cpu time: 7460000
Max Sub Sequence: 1148
Lower Boundary Index: 76
Upper Boundary Index: 252

Algorithm 2 cpu time: 526900
Max Sub Sequence: 1148
Lower Boundary Index: 76
Upper Boundary Index: 252

Algorithm 3 cpu time: 450400
Max Sub Sequence: 1148
Lower Boundary Index: 76
Upper Boundary Index: 252

Algorithm 4 cpu time: 32100
Max Sub Sequence: 1148
Lower Boundary Index: 76
Upper Boundary Index: 252
*/
