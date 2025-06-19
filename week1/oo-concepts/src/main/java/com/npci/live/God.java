package com.npci.live;

// Open/Closed Principle (OCP)
public class God {

//    public void manageHuman(Human human) {
//        human.eat();
//        human.sleep();
//        human.study();
//        human.work();
//    }
//
//    public void manageAnimal(Animal animal) {
//        animal.eat();
//        animal.sleep();
//        animal.work();
//    }

    public void managerLT(LivingThing lt) {
        lt.eat();
        lt.sleep();
        if(lt instanceof Human) {
            Human human = (Human) lt;
            human.study();
        }
        lt.work();
    }

}
