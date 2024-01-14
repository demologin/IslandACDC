package com.javarush.island.motyrev.api;


import com.javarush.island.motyrev.entities.Entity;
import com.javarush.island.motyrev.entities.animals.herbivores.*;
import com.javarush.island.motyrev.entities.animals.predators.*;
import com.javarush.island.motyrev.entities.plants.Grass;

import java.util.HashMap;
import java.util.Map;

public class Parameters {

    //=======================================<<< Set game field size >>>=======================================
    public static final int HORIZONTAL_GAME_FIELD_SIZE = 100;
    public static final int VERTICAL_GAME_FIELD_SIZE = 20;

    //=======================================<<< Set entity parameters >>>=======================================

    public static final Map<Class<? extends Entity>, String> DISPLAY_ENTITIES = new HashMap<>();
    static {
        DISPLAY_ENTITIES.put(Boar.class, "\uD83D\uDC17");
        DISPLAY_ENTITIES.put(Buffalo.class, "\uD83D\uDC03");
        DISPLAY_ENTITIES.put(Caterpillar.class, "\uD83D\uDC1B");
        DISPLAY_ENTITIES.put(Deer.class, "\uD83E\uDD8C");
        DISPLAY_ENTITIES.put(Duck.class, "\uD83E\uDD86");
        DISPLAY_ENTITIES.put(Goat.class, "\uD83D\uDC10");
        DISPLAY_ENTITIES.put(Horse.class, "\uD83D\uDC0E");
        DISPLAY_ENTITIES.put(Mouse.class, "\uD83D\uDC01");
        DISPLAY_ENTITIES.put(Rabbit.class, "\uD83D\uDC07");
        DISPLAY_ENTITIES.put(Sheep.class, "\uD83D\uDC11");
        DISPLAY_ENTITIES.put(Bear.class, "\uD83D\uDC3B");
        DISPLAY_ENTITIES.put(Boa.class, "\uD83D\uDC0D");
        DISPLAY_ENTITIES.put(Eagle.class, "\uD83E\uDD85");
        DISPLAY_ENTITIES.put(Fox.class, "\uD83E\uDD8A");
        DISPLAY_ENTITIES.put(Wolf.class, "\uD83D\uDC3A");
        DISPLAY_ENTITIES.put(Grass.class, "\uD83C\uDF3F");
    }

    public static final Map<Class<? extends Entity>, Integer> LIMIT_ENTITIES_IN_CELL = new HashMap<>();
    static {
        LIMIT_ENTITIES_IN_CELL.put(Grass.class, 200);
        LIMIT_ENTITIES_IN_CELL.put(Wolf.class, 30);
        LIMIT_ENTITIES_IN_CELL.put(Fox.class, 30);
        LIMIT_ENTITIES_IN_CELL.put(Eagle.class, 20);
        LIMIT_ENTITIES_IN_CELL.put(Boa.class, 30);
        LIMIT_ENTITIES_IN_CELL.put(Bear.class, 5);
        LIMIT_ENTITIES_IN_CELL.put(Sheep.class, 140);
        LIMIT_ENTITIES_IN_CELL.put(Rabbit.class, 150);
        LIMIT_ENTITIES_IN_CELL.put(Mouse.class, 500);
        LIMIT_ENTITIES_IN_CELL.put(Horse.class, 20);
        LIMIT_ENTITIES_IN_CELL.put(Goat.class, 140);
        LIMIT_ENTITIES_IN_CELL.put(Duck.class, 200);
        LIMIT_ENTITIES_IN_CELL.put(Deer.class, 20);
        LIMIT_ENTITIES_IN_CELL.put(Caterpillar.class, 1000);
        LIMIT_ENTITIES_IN_CELL.put(Buffalo.class, 10);
        LIMIT_ENTITIES_IN_CELL.put(Boar.class, 50);
    }

    public static final Map<Class<? extends Entity>, Double> ENTITY_WEIGHT = new HashMap<>();
    static {
        ENTITY_WEIGHT.put(Grass.class, 1.0);
        ENTITY_WEIGHT.put(Wolf.class, 50.0);
        ENTITY_WEIGHT.put(Fox.class, 8.0);
        ENTITY_WEIGHT.put(Eagle.class, 6.0);
        ENTITY_WEIGHT.put(Boa.class, 15.0);
        ENTITY_WEIGHT.put(Bear.class, 500.0);
        ENTITY_WEIGHT.put(Sheep.class, 70.0);
        ENTITY_WEIGHT.put(Rabbit.class, 2.0);
        ENTITY_WEIGHT.put(Mouse.class, 0.05);
        ENTITY_WEIGHT.put(Horse.class, 400.0);
        ENTITY_WEIGHT.put(Goat.class, 60.0);
        ENTITY_WEIGHT.put(Duck.class, 1.0);
        ENTITY_WEIGHT.put(Deer.class, 300.0);
        ENTITY_WEIGHT.put(Caterpillar.class, 0.01);
        ENTITY_WEIGHT.put(Buffalo.class, 700.0);
        ENTITY_WEIGHT.put(Boar.class, 400.0);
    }

    public static final Map<Class<? extends Entity>, Integer> ENTITY_SPEED = new HashMap<>();
    static {
        ENTITY_SPEED.put(Wolf.class, 3);
        ENTITY_SPEED.put(Fox.class, 2);
        ENTITY_SPEED.put(Eagle.class, 3);
        ENTITY_SPEED.put(Boa.class, 1);
        ENTITY_SPEED.put(Bear.class, 2);
        ENTITY_SPEED.put(Sheep.class, 3);
        ENTITY_SPEED.put(Rabbit.class, 2);
        ENTITY_SPEED.put(Mouse.class, 1);
        ENTITY_SPEED.put(Horse.class, 4);
        ENTITY_SPEED.put(Goat.class, 3);
        ENTITY_SPEED.put(Duck.class, 4);
        ENTITY_SPEED.put(Deer.class, 4);
        ENTITY_SPEED.put(Caterpillar.class, 0);
        ENTITY_SPEED.put(Buffalo.class, 3);
        ENTITY_SPEED.put(Boar.class, 2);
    }

    public static final Map<Class<? extends Entity>, Double> ENTITY_CAN_EAT_FOR_ONE_TIME = new HashMap<>();
    static {
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Wolf.class, 8.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Fox.class, 2.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Eagle.class, 1.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Boa.class, 3.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Bear.class, 80.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Sheep.class, 15.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Rabbit.class, 0.45);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Mouse.class, 0.01);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Horse.class, 60.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Goat.class, 10.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Duck.class, 0.15);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Deer.class, 50.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Caterpillar.class, 0.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Buffalo.class, 100.0);
        ENTITY_CAN_EAT_FOR_ONE_TIME.put(Boar.class, 50.0);
    }

    public static final Map<Class<? extends Entity>, Integer> BIRTH_PROBABILITY = new HashMap<>();
    static {
        BIRTH_PROBABILITY.put(Grass.class, 100);
        BIRTH_PROBABILITY.put(Wolf.class, 18);
        BIRTH_PROBABILITY.put(Fox.class, 64);
        BIRTH_PROBABILITY.put(Eagle.class, 64);
        BIRTH_PROBABILITY.put(Boa.class, 64);
        BIRTH_PROBABILITY.put(Bear.class, 9);
        BIRTH_PROBABILITY.put(Sheep.class, 91);
        BIRTH_PROBABILITY.put(Rabbit.class, 91);
        BIRTH_PROBABILITY.put(Mouse.class, 82);
        BIRTH_PROBABILITY.put(Horse.class, 91);
        BIRTH_PROBABILITY.put(Goat.class, 91);
        BIRTH_PROBABILITY.put(Duck.class, 82);
        BIRTH_PROBABILITY.put(Deer.class, 91);
        BIRTH_PROBABILITY.put(Caterpillar.class, 91);
        BIRTH_PROBABILITY.put(Buffalo.class, 91);
        BIRTH_PROBABILITY.put(Boar.class, 73);
    }

    public static final Map<Class<? extends Entity>, Integer> BIRTH_MAX_AMOUNT = new HashMap<>();
    static {
        BIRTH_MAX_AMOUNT.put(Grass.class, 10);
        BIRTH_MAX_AMOUNT.put(Wolf.class, 1);
        BIRTH_MAX_AMOUNT.put(Fox.class, 10);
        BIRTH_MAX_AMOUNT.put(Eagle.class, 1);
        BIRTH_MAX_AMOUNT.put(Boa.class, 1);
        BIRTH_MAX_AMOUNT.put(Bear.class, 1);
        BIRTH_MAX_AMOUNT.put(Sheep.class, 10);
        BIRTH_MAX_AMOUNT.put(Rabbit.class, 50);
        BIRTH_MAX_AMOUNT.put(Mouse.class, 50);
        BIRTH_MAX_AMOUNT.put(Horse.class, 10);
        BIRTH_MAX_AMOUNT.put(Goat.class, 10);
        BIRTH_MAX_AMOUNT.put(Duck.class, 30);
        BIRTH_MAX_AMOUNT.put(Deer.class, 10);
        BIRTH_MAX_AMOUNT.put(Caterpillar.class, 50);
        BIRTH_MAX_AMOUNT.put(Buffalo.class, 10);
        BIRTH_MAX_AMOUNT.put(Boar.class, 10);
    }

    public static final Map<Class<? extends Entity>, Double> ENTITY_LOSE_WEIGHT = new HashMap<>();
    static {
        ENTITY_LOSE_WEIGHT.put(Wolf.class, 2.67);
        ENTITY_LOSE_WEIGHT.put(Fox.class, 1.0);
        ENTITY_LOSE_WEIGHT.put(Eagle.class, 0.33);
        ENTITY_LOSE_WEIGHT.put(Boa.class, 3.0);
        ENTITY_LOSE_WEIGHT.put(Bear.class, 40.0);
        ENTITY_LOSE_WEIGHT.put(Sheep.class, 5.0);
        ENTITY_LOSE_WEIGHT.put(Rabbit.class, 0.225);
        ENTITY_LOSE_WEIGHT.put(Mouse.class, 0.01);
        ENTITY_LOSE_WEIGHT.put(Horse.class, 15.0);
        ENTITY_LOSE_WEIGHT.put(Goat.class, 3.33);
        ENTITY_LOSE_WEIGHT.put(Duck.class, 0.0375);
        ENTITY_LOSE_WEIGHT.put(Deer.class, 12.5);
        ENTITY_LOSE_WEIGHT.put(Caterpillar.class, 0.0);
        ENTITY_LOSE_WEIGHT.put(Buffalo.class, 33.33);
        ENTITY_LOSE_WEIGHT.put(Boar.class, 25.0);
    }
    
    public static final Map<Class<? extends Entity>, Integer> BOAR_PROBABILITY_EATING = new HashMap<>();
    static {
        BOAR_PROBABILITY_EATING.put(Grass.class, 100);
        BOAR_PROBABILITY_EATING.put(Mouse.class, 50);
        BOAR_PROBABILITY_EATING.put(Caterpillar.class, 90);
    }

    public static final Map<Class<? extends Entity>, Integer> BUFFALO_PROBABILITY_EATING = new HashMap<>();
    static {
        BUFFALO_PROBABILITY_EATING.put(Grass.class, 100);
    }

    public static final Map<Class<? extends Entity>, Integer> CATERPILLAR_PROBABILITY_EATING = new HashMap<>();
    static {
        CATERPILLAR_PROBABILITY_EATING.put(Grass.class, 100);
    }

    public static final Map<Class<? extends Entity>, Integer> DEER_PROBABILITY_EATING = new HashMap<>();
    static {
        DEER_PROBABILITY_EATING.put(Grass.class, 100);
    }

    public static final Map<Class<? extends Entity>, Integer> DUCK_PROBABILITY_EATING = new HashMap<>();
    static {
        DUCK_PROBABILITY_EATING.put(Grass.class, 100);
        DUCK_PROBABILITY_EATING.put(Caterpillar.class, 90);
    }

    public static final Map<Class<? extends Entity>, Integer> GOAT_PROBABILITY_EATING = new HashMap<>();
    static {
        GOAT_PROBABILITY_EATING.put(Grass.class, 100);
    }

    public static final Map<Class<? extends Entity>, Integer> HORSE_PROBABILITY_EATING = new HashMap<>();
    static {
        HORSE_PROBABILITY_EATING.put(Grass.class, 100);
    }

    public static final Map<Class<? extends Entity>, Integer> MOUSE_PROBABILITY_EATING = new HashMap<>();
    static {
        MOUSE_PROBABILITY_EATING.put(Grass.class, 100);
        MOUSE_PROBABILITY_EATING.put(Caterpillar.class, 90);
    }

    public static final Map<Class<? extends Entity>, Integer> RABBIT_PROBABILITY_EATING = new HashMap<>();
    static {
        RABBIT_PROBABILITY_EATING.put(Grass.class, 100);
    }

    public static final Map<Class<? extends Entity>, Integer> SHEEP_PROBABILITY_EATING = new HashMap<>();
    static {
        SHEEP_PROBABILITY_EATING.put(Grass.class, 100);
    }

    public static final Map<Class<? extends Entity>, Integer> BEAR_PROBABILITY_EATING = new HashMap<>();
    static {
        BEAR_PROBABILITY_EATING.put(Boa.class, 80);
        BEAR_PROBABILITY_EATING.put(Horse.class, 40);
        BEAR_PROBABILITY_EATING.put(Deer.class, 80);
        BEAR_PROBABILITY_EATING.put(Rabbit.class, 80);
        BEAR_PROBABILITY_EATING.put(Mouse.class, 90);
        BEAR_PROBABILITY_EATING.put(Goat.class, 70);
        BEAR_PROBABILITY_EATING.put(Sheep.class, 70);
        BEAR_PROBABILITY_EATING.put(Boar.class, 50);
        BEAR_PROBABILITY_EATING.put(Buffalo.class, 20);
        BEAR_PROBABILITY_EATING.put(Duck.class, 10);
    }

    public static final Map<Class<? extends Entity>, Integer> BOA_PROBABILITY_EATING = new HashMap<>();
    static {
        BOA_PROBABILITY_EATING.put(Fox.class, 15);
        BOA_PROBABILITY_EATING.put(Rabbit.class, 20);
        BOA_PROBABILITY_EATING.put(Mouse.class, 40);
        BOA_PROBABILITY_EATING.put(Duck.class, 10);
    }

    public static final Map<Class<? extends Entity>, Integer> EAGLE_PROBABILITY_EATING = new HashMap<>();
    static {
        EAGLE_PROBABILITY_EATING.put(Fox.class, 10);
        EAGLE_PROBABILITY_EATING.put(Rabbit.class, 90);
        EAGLE_PROBABILITY_EATING.put(Mouse.class, 90);
        EAGLE_PROBABILITY_EATING.put(Duck.class, 80);
    }

    public static final Map<Class<? extends Entity>, Integer> FOX_PROBABILITY_EATING = new HashMap<>();
    static {
        FOX_PROBABILITY_EATING.put(Rabbit.class, 70);
        FOX_PROBABILITY_EATING.put(Mouse.class, 90);
        FOX_PROBABILITY_EATING.put(Duck.class, 60);
        FOX_PROBABILITY_EATING.put(Caterpillar.class, 40);
    }

    public static final Map<Class<? extends Entity>, Integer> WOLF_PROBABILITY_EATING = new HashMap<>();
    static {  
        WOLF_PROBABILITY_EATING.put(Horse.class, 10);
        WOLF_PROBABILITY_EATING.put(Deer.class, 15);
        WOLF_PROBABILITY_EATING.put(Rabbit.class, 60);
        WOLF_PROBABILITY_EATING.put(Mouse.class, 80);
        WOLF_PROBABILITY_EATING.put(Goat.class, 60);
        WOLF_PROBABILITY_EATING.put(Sheep.class, 70);
        WOLF_PROBABILITY_EATING.put(Boar.class, 15);
        WOLF_PROBABILITY_EATING.put(Buffalo.class, 10);
        WOLF_PROBABILITY_EATING.put(Duck.class, 40);
    }

    //=======================================<<< System constants >>>=======================================
    public static final Map<Class<? extends Entity>, Map<Class<? extends Entity>, Integer>> EAT_PROBABILITY = new HashMap<>();
    static {
        EAT_PROBABILITY.put(Wolf.class, WOLF_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Fox.class, FOX_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Eagle.class, EAGLE_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Boa.class, BOA_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Bear.class, BEAR_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Sheep.class, SHEEP_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Rabbit.class, RABBIT_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Mouse.class, MOUSE_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Horse.class, HORSE_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Goat.class, GOAT_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Duck.class, DUCK_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Deer.class, DEER_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Caterpillar.class, CATERPILLAR_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Buffalo.class, BUFFALO_PROBABILITY_EATING);
        EAT_PROBABILITY.put(Boar.class, BOAR_PROBABILITY_EATING);
    }
    
    public static int counter = 0;
}
