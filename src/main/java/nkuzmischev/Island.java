package nkuzmischev;

import nkuzmischev.animal.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Island {
    private int width = 100;
    private int height = 20;
    private Object[][] grid;
    private List<Animal> animals;
    private Wolf[][] wolves = new Wolf[width][height];
    private Python[][] pythons = new Python[width][height];
    private Fox[][] foxes = new Fox[width][height];
    private Bear[][] bears = new Bear[width][height];
    private Eagle[][] eagles = new Eagle[width][height];
    private Deer[][] deers = new Deer[width][height];
    private Rabbit[][] rabbits = new Rabbit[width][height];
    private Mouse[][] mouses = new Mouse[width][height];
    private Goat[][] goats = new Goat[width][height];
    private Sheep[][] sheeps = new Sheep[width][height];
    private Boar[][] boars = new Boar[width][height];
    private Buffalo[][] buffalos = new Buffalo[width][height];
    private Duck[][] ducks = new Duck[width][height];
    private Caterpillar[][] caterpillars = new Caterpillar[width][height];
    private Plant[][] plants = new Plant[width][height];
    private Random random = new Random();
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    public void startSimulation() {
        for (int i = 0; i < 100; i++) {
            int wolfCount = 0;
            int pythonsCount = 0;
            int foxesCount = 0;
            int bearsCount = 0;
            int eaglesCount = 0;
            int deerCount = 0;
            int mouseCount = 0;
            int goatCount = 0;
            int sheepCount = 0;
            int boarCount = 0;
            int buffaloCount = 0;
            int duckCount = 0;
            int caterpillarCount = 0;
            int rabbitsCount = 0;

            for (int j = 0; j < 20; j++) {
                if (wolves[i][j] != null) {
                    executor.scheduleAtFixedRate(wolves[i][j], 0, 500, TimeUnit.MILLISECONDS);
                    wolfCount++;
                }
                if (pythons[i][j] != null) {
                    executor.scheduleAtFixedRate(pythons[i][j], 0, 500, TimeUnit.MILLISECONDS);
                    pythonsCount++;
                }
                if (foxes[i][j] != null) {
                    executor.scheduleAtFixedRate(foxes[i][j], 0, 500, TimeUnit.MILLISECONDS);
                    foxesCount++;
                }
                if (bears[i][j] != null) {
                    executor.scheduleAtFixedRate(bears[i][j], 0, 500, TimeUnit.MILLISECONDS);
                    bearsCount++;
                }
                if (eagles[i][j] != null) {
                    executor.scheduleAtFixedRate(eagles[i][j], 0, 500, TimeUnit.MILLISECONDS);
                    eaglesCount++;
                }
                if (deers[i][j] != null) {
                    executor.scheduleAtFixedRate(deers[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    deerCount++;
                }
                if (rabbits[i][j] != null) {
                    executor.scheduleAtFixedRate(rabbits[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    rabbitsCount++;
                }
                if (mouses[i][j] != null) {
                    executor.scheduleAtFixedRate(mouses[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    mouseCount++;
                }
                if (goats[i][j] != null) {
                    executor.scheduleAtFixedRate(goats[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    goatCount++;
                }
                if (sheeps[i][j] != null) {
                    executor.scheduleAtFixedRate(sheeps[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    sheepCount++;
                }
                if (boars[i][j] != null) {
                    executor.scheduleAtFixedRate(boars[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    boarCount++;
                }
                if (buffalos[i][j] != null) {
                    executor.scheduleAtFixedRate(buffalos[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    buffaloCount++;
                }
                if (ducks[i][j] != null) {
                    executor.scheduleAtFixedRate(ducks[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    duckCount++;
                }
                if (caterpillars[i][j] != null) {
                    executor.scheduleAtFixedRate(caterpillars[i][j], 0, 700, TimeUnit.MILLISECONDS);
                    caterpillarCount++;
                }
                if (plants[i][j] != null) {
                    executor.scheduleAtFixedRate(plants[i][j], 0, 1000, TimeUnit.MILLISECONDS);
                }
            }

            System.out.println("Cycle " + i + ": Wolf count = " + wolfCount + ", Python count = " + pythonsCount +
                    ", Fox count = " + foxesCount + ", Bear count = " + bearsCount + ", Eagle count = " + eaglesCount +
                    ", Deer count = " + deerCount + ", Rabbit count = " + rabbitsCount + ", Mouse count = " + mouseCount +
                    ", Goat count = " + goatCount + ", Sheep count = " + sheepCount + ", Boar count = " + boarCount +
                    ", Buffalo count = " + buffaloCount + ", Duck count = " + duckCount + ", Caterpillar count = " + caterpillarCount);
        }
    }


    public void placeInitialAnimalsAndPlants() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Place wolves
                if (random.nextDouble() < 0.3) {
                    wolves[i][j] = new Wolf(this, i, j);
                }
                // Place pythons
                if (random.nextDouble() < 0.3) {
                    pythons[i][j] = new Python(this, i, j);
                }
                // Place foxes
                if (random.nextDouble() < 0.3) {
                    foxes[i][j] = new Fox(this, i, j);
                }
                // Place bears
                if (random.nextDouble() < 0.05) {
                    bears[i][j] = new Bear(this, i, j);
                }
                // Place eagles
                if (random.nextDouble() < 0.2) {
                    eagles[i][j] = new Eagle(this, i, j);
                }
                // Place deers
                if (random.nextDouble() < 0.2) {
                    deers[i][j] = new Deer(this, i, j);
                }
                // Place rabbits
                if (random.nextDouble() < 0.6) {
                    rabbits[i][j] = new Rabbit(this, i, j);
                }
                // Place mouse
                if (random.nextDouble() < 0.7) {
                    mouses[i][j] = new Mouse(this, i, j);
                }
                // Place goat
                if (random.nextDouble() < 0.6) {
                    goats[i][j] = new Goat(this, i, j);
                }
                // Place sheep
                if (random.nextDouble() < 0.6) {
                    sheeps[i][j] = new Sheep(this, i, j);
                }
                // Place boar
                if (random.nextDouble() < 0.5) {
                    boars[i][j] = new Boar(this, i, j);
                }
                // Place buffalo
                if (random.nextDouble() < 0.2) {
                    buffalos[i][j] = new Buffalo(this, i, j);
                }
                // Place duck
                if (random.nextDouble() < 0.8) {
                    ducks[i][j] = new Duck(this, i, j);
                }
                // Place caterpiller
                if (random.nextDouble() < 0.99) {
                    caterpillars[i][j] = new Caterpillar(this, i, j);
                }
                // Place plants
                int numPlants = random.nextInt(200);
                plants[i][j] = new Plant(this, i, j, numPlants);
            }
        }
    }


    public boolean isValidLocation(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Animal[] getNeighbors(int x, int y) {
        List<Animal> neighbors = new ArrayList<>();
        if (isValidLocation(x - 1, y)) {
            if (grid[x - 1][y] instanceof Animal) {
                neighbors.add((Animal) grid[x - 1][y]);
            }
        }
        // Check other neighboring cells and add to the list if they contain animals
        return neighbors.toArray(new Animal[0]);
    }

    public void placeNewAnimal(Class<? extends Animal> animalClass, int x, int y) {
        if (isValidLocation(x, y)) {
            try {
                Animal newAnimal = animalClass.getConstructor(Island.class, int.class, int.class).newInstance(this, x, y);
                // Place the new animal on the island
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeAnimal(Animal animal) {
        if (animals.contains(animal)) {
            animals.remove(animal);
        }
    }




}

