package nkuzmischev.animal;

import nkuzmischev.Island;

public class Fox extends Predator {
    public Fox(Island island, int x, int y) {
        super(island, x, y);
    }

    @Override
    public void run() {
        super.run(); // Вызываем метод run из класса Predator
    }

    public void eat(Animal prey) {
        if (prey instanceof Deer) {
            super.eat(prey); // Вызываем метод eat для deer из класса Predator
        } else if (prey instanceof Rabbit) {
            super.eat(prey); // Вызываем метод eat для rabbit из класса Predator
        } else if (prey instanceof Mouse) {
            super.eat(prey); // Вызываем метод eat для mouse из класса Predator
        } else if (prey instanceof Goat) {
            super.eat(prey); // Вызываем метод eat для goat из класса Predator
        } else if (prey instanceof Sheep) {
            super.eat(prey); // Вызываем метод eat для sheep из класса Predator
        } else if (prey instanceof Boar) {
            super.eat(prey); // Вызываем метод eat для Boar из класса Predator
        } else if (prey instanceof Buffalo) {
            super.eat(prey); // Вызываем метод eat для buffalo из класса Predator
        } else if (prey instanceof Duck) {
            super.eat(prey); // Вызываем метод eat для duck из класса Predator
        } else if (prey instanceof Caterpillar) {
            super.eat(prey); // Вызываем метод eat для cateprillar из класса Predator
        }
        else {
            System.out.println("Wolf cannot eat this animal.");
        }
    }
}
