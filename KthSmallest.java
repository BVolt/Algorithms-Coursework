package com.mycompany.algop2;

import java.util.Random;
import java.util.*;


public class KthSmallest {

    private static Random random = new Random();

    public static void main(String[] args) {
        
        
        int[] array = new int[500];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(0, 1000);
        }
        System.out.println("Tested Array: ");
        dA(array);
        int k = 459;
        ArrayList<Integer> al = new ArrayList<>();
        for(int num: array){
            al.add(num);
        }
        
//        int[] array = {2, 7, 8, 9, 1, 3 , 4, 6, 5};
        
        
//
        //Kth Smallest 1
        long start = System.nanoTime();
        int result1 = poop.select(al, k);
        long end = System.nanoTime();
        long Time1 = end - start;
//
        //Kth Smallest 2
        long start2 = System.nanoTime();
        int result2 = poop.selectLargest(al, k);
        long end2 = System.nanoTime();
        long Time2 = end2 - start2;
        
        //Kth Smallest 3
        System.out.println(array.length-k);
        long start3 = System.nanoTime();
        int result3 = kthSmallest1(array, array.length+1-k);
        long end3 = System.nanoTime();
        long Time3 = end3 - start3;
                
        //Kth Smallest 3
        System.out.println(array.length-1);
        long start4 = System.nanoTime();
        int result4 =  poop.kthLargest(al, al.size(),k);
        long end4 = System.nanoTime();
        long Time4 = end4 - start4;
//
//        //Display Results
        System.out.println("\nKth Smallest Algorithm 1\nk = " + k + " result: " + result1 + "\nRun Time:" + Time1);
        System.out.println("\nKth Smallest Algorithm 2\nk = " + k + " result: " + result2 + "\nRun Time:" + Time2);
        System.out.println("\nKth Smallest Algorithm 3\nk = " + k + " result: " + result3 + "\nRun Time:" + Time3);
        System.out.println("\nKth Smallest Algorithm 4\nk = " + k + " result: " + result4 + "\nRun Time:" + Time4);

    }

    public static int kthSmallest1(int[] s, int k) {
        if (s.length == 1) {
            return s[0];
        } else {
            int m = s[random.nextInt(s.length)];
            List<Integer> s1 = new ArrayList<>();
            List<Integer> s2 = new ArrayList<>();
            List<Integer> s3 = new ArrayList<>();

            for (int i = 0; i < s.length; i++) {
                if (s[i] < m) {
                    s1.add(s[i]);
                } else if (s[i] == m) {
                    s2.add(s[i]);
                } else {
                    s3.add(s[i]);
                }
            }
            if (s1.size() >= k) {
                int[] s1a = new int[s1.size()];

                int i = 0;
                for (int ele : s1) {
                    s1a[i] = ele;
                    i++;
                }
                return kthSmallest1(s1a, k);
            } else if (s1.size() + s2.size() >= k) {
                return m;
            } else {
                int[] s3a = new int[s3.size()];
                int i = 0;
                for (int ele : s3) {
                    s3a[i] = ele;
                    i++;
                }
                return kthSmallest1(s3a, k - s1.size() - s2.size());
            }
        }
    }

    public static int kthSmallest2(int s[], int left, int right, int k) {
        if (s.length < 50) {
            sort(s, left, right);
            return s[k - 1];
        }
        int n = right - left + 1;
        int i;
        int[] M = new int[(n + 4) / 5];
        for (i = 0; i < n / 5; i++) {
            M[i] = getMed(s, left + i * 5, 5);
        }
        if (i * 5 < n) {
            M[i] = getMed(s, left + i * 5, n % 5);
            i++;
        }
        int m = (i == 1) ? M[i - 1] : getMed(M, 0, M.length);
        int pivot = partition(s, left, right, m);
        if (pivot == k - 1) {
            return s[pivot];
        } else if (pivot > k - 1) {
            return kthSmallest2(s, left, pivot - 1, k);
        } else {
            return kthSmallest2(s, pivot + 1, right, k);
        }
    }

    public static int partition(int[] s, int left, int right, int m) {
        int i;
        for (i = left; i < right; i++) {
            if (s[i] == m) {
                break;
            }
        }
        swap(s, i, right);
        i = left;
        for (int j = left; j <= right - 1; j++) {
            if (s[j] <= m) {
                swap(s, i, j);
                i++;
            }
        }
        swap(s, i, right);
        return i;
    }

    public static int getMed(int s[], int i, int n) {
        sort(s, i, i + n - 1);
        return s[i + n / 2];
    }

    public static void swap(int[] s, int i, int j) {
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void sort(int[] s, int left, int right) {
        if (left < right) {
            int pivot = s[right];
            int i = (left - 1);
            for (int j = left; j < right; j++) {
                if (s[j] <= pivot) {
                    i++;
                    swap(s, i, j);
                }
            }
            swap(s, i + 1, right);
            int partitionIndex = i + 1;
            sort(s, left, partitionIndex - 1);
            sort(s, partitionIndex + 1, right);
        }
    }
    
    public static void dA(int[] a){
        for(int i = 0; i < a.length; i++){
            if(i%10 == 0)
                System.out.println();
            System.out.print(a[i]+ ", ");
        }
        System.out.println();
    }
}


/*
Example Output

Tested Array: 

154, 969, 872, 53, 812, 221, 134, 179, 91, 390, 
52, 110, 863, 22, 216, 37, 288, 514, 179, 74, 
444, 847, 682, 664, 342, 950, 28, 816, 747, 739, 
998, 80, 357, 272, 506, 849, 434, 745, 102, 979, 
383, 860, 329, 295, 710, 975, 448, 321, 192, 901, 
919, 432, 355, 110, 875, 763, 971, 34, 548, 601, 
271, 418, 279, 549, 251, 268, 857, 300, 531, 441, 
393, 877, 855, 532, 412, 317, 153, 433, 148, 299, 
699, 839, 762, 982, 966, 731, 472, 41, 966, 675, 
163, 64, 473, 940, 686, 715, 734, 489, 962, 604, 

Kth Smallest Algorithm 1
k = 90 result: 940
Run Time:243000

Kth Smallest Algorithm 2
k = 90 result: 940
Run Time:67200
*/
