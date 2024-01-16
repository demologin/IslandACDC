package com.javarush.island.alimova;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.exception.GameException;
import com.javarush.island.alimova.services.ManagerZoo;


public class ConsoleRunner {

    public static void main(String[] args) {
        ManagerZoo managerZoo = new ManagerZoo();
        try {
            managerZoo.bootstrap();
            managerZoo.startLive();
        } catch (GameException e) {
            System.err.println(e.getMessage());
        } catch (RuntimeException e) {
            System.err.println(DefaultSettings.MESSAGE_FATAL_ERROR);
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

    }
}
