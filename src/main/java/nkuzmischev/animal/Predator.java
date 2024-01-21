package nkuzmischev.animal;

import nkuzmischev.Island;

public class Predator extends Animal {
    public Predator(Island island, int x, int y) {
        super(island, x, y);
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void reproduce() {
        super.reproduce();
    }

    public void eat(Animal prey) {
        double chance = Math.random();
        if (prey instanceof Deer && chance <= 0.15) {
            island.removeAnimal(prey);
        } else if (prey instanceof Rabbit && chance <= 0.6) {
            island.removeAnimal(prey);
        } else if (prey instanceof Mouse && chance <= 0.8) {
            island.removeAnimal(prey);
        } else if (prey instanceof Goat && chance <= 0.5) {
            island.removeAnimal(prey);
        } else if (prey instanceof Sheep && chance <= 0.8) {
            island.removeAnimal(prey);
        } else if (prey instanceof Boar && chance <= 0.14) {
            island.removeAnimal(prey);
        } else if (prey instanceof Buffalo && chance <= 0.1) {
            island.removeAnimal(prey);
        } else if (prey instanceof Duck && chance <= 0.4) {
            island.removeAnimal(prey);
        } else if (prey instanceof Caterpillar && chance <= 0.6) {
            island.removeAnimal(prey);
        }
    }
}
