package edu.neu.info6205.ga;

import java.util.List;
import java.util.Random;

/**
 * MyThread
 */
public class MyThread extends Thread {

  private int start;
  private int end;
  private Chromosome[] buffer;
  private Chromosome[] popArr;
  private float crossover;
  private float mutation;
  private static final Random rand = new Random(System.currentTimeMillis());
  private static final int TOURNAMENT_SIZE = 3;

  public MyThread(int start, int end, Chromosome[] buffer, Chromosome[] popArr, float crossover, float mutation) {
    super();
    this.start = start;
    this.end = end;
    this.buffer = buffer;
    this.popArr = popArr;
    this.crossover = crossover;
    this.mutation = mutation;
  }

  private Chromosome[] selectParents() {
    Chromosome[] parents = new Chromosome[2];

    // Randomly select two parents via tournament selection.
    for (int i = 0; i < 2; i++) {
      parents[i] = popArr[rand.nextInt(popArr.length)];
      for (int j = 0; j < TOURNAMENT_SIZE; j++) {
        int idx = rand.nextInt(popArr.length);
        if (popArr[idx].compareTo(parents[i]) < 0) {
          parents[i] = popArr[idx];
        }
      }
    }

    return parents;
  }

  @Override
  public void run() {

    // System.out.println(Thread.currentThread().getName() + "正在执行… …");
    // System.out.println(this.start + " " + this.end + " " + this.buffer.length);

    int idx = start;

    while (idx < end) {
      // Check to see if we should perform a crossover.
      if (rand.nextFloat() <= crossover) {

        // Select the parents and mate to get their children
        Chromosome[] parents = selectParents();
        Chromosome[] children = parents[0].mate(parents[1]);

        // Check to see if the first child should be mutated.
        if (rand.nextFloat() <= mutation) {
          buffer[idx++] = children[0].mutate();
        } else {
          buffer[idx++] = children[0];
        }

        // Repeat for the second child, if there is room.
        if (idx < buffer.length) {
          if (rand.nextFloat() <= mutation) {
            buffer[idx] = children[1].mutate();
          } else {
            buffer[idx] = children[1];
          }
        }
      } else { // No crossover, so copy verbatium.
        // Determine if mutation should occur.
        if (rand.nextFloat() <= mutation) {
          buffer[idx] = popArr[idx].mutate();
        } else {
          buffer[idx] = popArr[idx];
        }
      }

      // Increase our counter
      ++idx;
    }


    // System.out.println(Thread.currentThread().getName() + "执行完毕");
  }
}