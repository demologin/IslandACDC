package com.javarush.island.boyarinov.service;

import com.javarush.island.boyarinov.view.View;

public class ViewService implements Runnable {

    private final View view;

    public ViewService(View view) {
        this.view = view;
    }

    @Override
    public void run() {
        view.showStatistic();
    }
}
