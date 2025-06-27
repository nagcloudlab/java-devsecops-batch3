package com.npci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableFutureExample {
    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(20);

        Callable<List<String>> fileReadTask = () -> {
            FileReader fileReader = new FileReader("/Users/nag/java-devsecops-batch4/week2/threads/file1.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        };

        Future<List<String>> future = executorService.submit(fileReadTask);
        System.out.println("Main thread is doing something else...");
        //..
        //..
        try {
            List<String> lines = future.get(); // blocks until the task is complete
            lines.forEach(System.out::println);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
}
