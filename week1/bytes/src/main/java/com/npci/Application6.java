package com.npci;

interface Subject {
}

class Java implements Subject {
}

class JavaScript implements Subject {
}

class Trainer<T> {
    private T subject; // HAS-A

    public Trainer(T subject) {
        this.subject = subject;
    }

    public void setSubject(T subject) {
        this.subject = subject;
    }

}

public class Application6 {
    public static void main(String[] args) {

        Java java = new Java();
        JavaScript javaScript = new JavaScript();

        Trainer<Java> javaTrainer = new Trainer<Java>(java);
        Trainer<JavaScript> javascriptTrainer = new Trainer<>(javaScript);

        Trainer<Subject> javaAndJavaScriptTrainer = new Trainer<>(java);
        javaAndJavaScriptTrainer.setSubject(javaScript);


    }
}
