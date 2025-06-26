package com.npci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTask {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<List<String>> callableTask = () -> {
            FileReader fileReader = new FileReader("/Users/nag/java-devsecops-batch3/week2/threads/lunch-menu.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            fileReader.close();
            return lines;
        };

        Future<List<String>> future = executorService.submit(callableTask);
        // ..
        // ..
        try {
            List<String> lunchMenuItems = future.get();// blocking call
            System.out.println(lunchMenuItems);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
