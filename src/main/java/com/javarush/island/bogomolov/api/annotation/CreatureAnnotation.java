package com.javarush.island.bogomolov.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatureAnnotation {
    String name();

    double weight();

    int maxCountPerCell();

    int speedPerCell();

    double requiredFood();


}
