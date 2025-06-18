package com.npci.live1;

public class Application {
    public static void main(String[] args) {

        God god = new God();

        //LivingThing livingThing = new LivingThing();
        Human human = new Human();
        Animal animal = new Animal();
        Robot robot = new Robot();

        //god.manageHuman(human);
        god.managerLT(human); // Upcasting
        System.out.println();
        //god.manageAnimal(animal);
        god.managerLT(animal);
        System.out.println();
        //god.manageRobot(robot);
        god.managerLT(robot);


    }
}
