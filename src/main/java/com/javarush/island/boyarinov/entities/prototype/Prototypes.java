package com.javarush.island.boyarinov.entities.prototype;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.exceptions.AppException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Prototypes {

    private static Map<Class<? extends Organisms>, Organisms> prototype;

    private static void initPrototype() {
        prototype = new HashMap<>();
        Class<? extends Organisms>[] animalClassName = Constants.ANIMAL_CLASS_NAME;
        String[] animalNames = Constants.ANIMAL_NAME;
        for (int i = 0; i < animalClassName.length; i++) {
            try {
                Constructor<? extends Organisms> constructor = animalClassName[i].getConstructor(String.class);
                Organisms organisms = constructor.newInstance(animalNames[i]);
                prototype.put(animalClassName[i], organisms);

            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new AppException(e);
            }
        }
    }

    public static Map<Class<? extends Organisms>, Organisms> getPrototype() {
        if (prototype == null) {
            initPrototype();
            return prototype;
        }
        return prototype;
    }
}
