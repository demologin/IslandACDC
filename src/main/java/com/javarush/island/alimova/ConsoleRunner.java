package com.javarush.island.alimova;


import com.javarush.island.alimova.services.ManagerZoo;


public class ConsoleRunner {

    public static void main(String[] args) {
        ManagerZoo managerZoo = new ManagerZoo();
        managerZoo.bootstrap();
        //managerZoo.startLive();
        managerZoo.startTest();

    }
}
