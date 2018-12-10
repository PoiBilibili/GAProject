package edu.neu.info6205.ga;

public final class Constants {

  private Constants() {
    // restrict instantiation
  }

  // The size of the simulation population
  public static final int populationSize = 2048;

  // The maximum number of generations for the simulation.
  public static final int maxGenerations = 500000;

  // The probability of crossover for any member of the population,
  // where 0.0 <= crossoverRatio <= 1.0
  public static final float crossoverRatio = 0.8f;

  // The portion of the population that will be retained without change
  // between evolutions, where 0.0 <= elitismRatio < 1.0
  public static final float elitismRatio = 0.1f;

  // The probability of mutation for any member of the population,
  // where 0.0 <= mutationRatio <= 1.0
  public static final float mutationRatio = 0.03f;
}