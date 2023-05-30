package com.mycompany.pa4;

/*
Author:         Brenden Johnson
Assignment:     Programming Assignment #4
Course-Section: 3310.02
Description:    Implementation of method 2 for the critical path.
*/
import java.util.*;

public class CriticalPath {
    
    // Number of tasks
    private int n; 
    //Arrays for Earliest Start, Earliest Finish, Latest Start, Latest Finish, Required Time, Task Sequence
    private int[] ES, EF, LS, LF, T, TS;
    //Graph represented by adjaceny matrix
    private int[][] A;

    public Project(int n, int[][] A, int[] T) {
        this.n = n;
        this.A = A;
        this.T = T;
        ES = new int[n];
        EF = new int[n];
        LS = new int[n];
        LF = new int[n];
        TS = new int[n];
    }

    public void findCriticalPath() {
        topologicalSort();
        computeEarliestTimes();
        computeLatestTimes();
        printResults();
    }
    
    
    // Topological sort to order the tasks according to their dependencies
    private void topologicalSort() {
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inDegree[j] += A[i][j];
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int task = queue.poll();
            TS[index++] = task;
            for (int i = 0; i < n; i++) {
                if (A[task][i] == 1) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }
    }
    
    // Compute the earliest start and finish times for each task
    private void computeEarliestTimes() {
        for (int task : TS) {
            int maxEF = 0;
            for (int i = 0; i < n; i++) {
                if (A[i][task] == 1 && EF[i] > maxEF) {
                    maxEF = EF[i];
                }
            }
            ES[task] = maxEF;
            EF[task] = maxEF + T[task];
        }
    }

    // Compute the latest start and finish times for each task
    private void computeLatestTimes() {
        for (int i = n - 1; i >= 0; i--) {
            int task = TS[i];
            if (i == n - 1) {
                LF[task] = EF[task];
                LS[task] = ES[task];
            } else {
                int minLS = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (A[task][j] == 1 && LS[j] < minLS) {
                        minLS = LS[j];
                    }
                }
                LF[task] = minLS;
                LS[task] = minLS - T[task];
            }
        }
    }

    private void printResults() {
        List<Integer> criticalPath = new ArrayList<>();
        String formatString = "%-5s %-15s %-15s %-12s %-15s %-8s %-9s%n";
        System.out.println("==============================================================================================");
        System.out.printf(formatString, "Task", "Earliest Start", "Earliest Finish", "Latest Start", "Latest Finish", "Slack", "Critical");
        System.out.println("==============================================================================================");

        for (int i = 0; i < n; i++) {
            int slack = LS[i] - ES[i];
            boolean isCritical = slack == 0;
            System.out.printf(formatString, (i + 1), ES[i], EF[i], LS[i], LF[i], slack, isCritical);
            if (isCritical) {
                criticalPath.add(i + 1);
            }
        }
        System.out.println("==============================================================================================");
        System.out.println("Critical Path: " + criticalPath.toString());
        System.out.println("Minimum project completion time: " + EF[criticalPath.get(criticalPath.size() - 1) - 1]+ " units");
    }

    public static void main(String[] args) {
        // int[0...12] corresponds to task
        // int[i][0...12] corresponds to weather i is a predecessor of 0...12
        int[][] adjacencyMatrix = {
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        //Time need for tasks = taskdurations[0..12]
        int[] taskDurations = {2, 4, 5, 9, 3, 2, 1, 10, 11, 6, 9, 8, 7};
        
        //Initialize our project with Task count, adjacency matrix, and task durations
        CriticalPath project = new Project(13, adjacencyMatrix, taskDurations);
        
        //Find / Display critical path
        System.out.println("Test Data:");
        project.findCriticalPath();
    }
}


/* Sample Output

Test Data:
==============================================================================================
Task  Earliest Start  Earliest Finish Latest Start Latest Finish   Slack    Critical 
==============================================================================================
1     0               2               0            2               0        true     
2     2               6               9            13              7        false    
3     2               7               8            13              6        false    
4     2               11              2            11              0        true     
5     6               9               21           24              15       false    
6     11              13              13           15              2        false    
7     13              14              24           25              11       false    
8     13              23              15           25              2        false    
9     11              22              11           22              0        true     
10    23              29              25           31              2        false    
11    22              31              22           31              0        true     
12    22              30              23           31              1        false    
13    31              38              31           38              0        true     
==============================================================================================
Critical Path: [1, 4, 9, 11, 13]
Minimum project completion time: 38 units

*/
