package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.entity.alive.Organism;
import lombok.Setter;

public class TransferOrganism {

    public int heightStart;
    public int widthStart;

    public int heightEnd;
    public int widthEnd;

    @Setter
    public Organism organism;

    public void setAddressStart(int heightStart, int widthStart) {
        this.heightStart = heightStart;
        this.widthStart = widthStart;
    }

    public void setAddressEnd(int heightEnd, int widthEnd) {
        this.heightEnd = heightEnd;
        this.widthEnd = widthEnd;
    }

    @Override
    public String toString() {
        return "TransferOrganism{" +
                "[" + heightStart +
                "," + widthStart +
                "] -> [" + heightEnd +
                "," + widthEnd +
                "] " + organism +
                "}";
    }
}
