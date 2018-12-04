/*
* The MIT License
*
* Copyright (c) 2011 John Svazic
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/
package edu.neu.info6205.ga;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit 4 tests for <code>net.auxesia.Chromosome</code>.
 *
 * @see net.auxesia.Chromosome
 *
 * @author John Svazic
 * @version 1.0
 */
public class ChromosomeTest {

	/**
	 * Method used to test <code>Chromosome.getFitness()</code>.
	 *
	 * @see net.auxesia.Chromosome#getFitness()
	 */
	@Test
	public void testGetFitness() {

		Chromosome c1 = new Chromosome(new ImageSource("./images/4956.jpg").getRGBArray());
		assertEquals(4956, c1.getFitness());

		Chromosome c2 = new Chromosome(new ImageSource("./images/5180.jpg").getRGBArray());
		assertEquals(5180, c2.getFitness());

		Chromosome c3 = new Chromosome(new ImageSource("./images/5294.jpg").getRGBArray());
		assertEquals(5294, c3.getFitness());

		Chromosome c4 = new Chromosome(new ImageSource("./images/6084.jpg").getRGBArray());
		assertEquals(6084, c4.getFitness());
	}

	/**
	 * Method used to test <code>Chromosome.generateRandom()</code>.
	 *
	 * @see net.auxesia.Chromosome#generateRandom()
	 */
	@Test
	public void testRandomGene() {
		Chromosome c;
		for (int i = 0; i < 1000; i++) {
			c = Chromosome.generateRandom();
			assertTrue(c.getFitness() >= 0);
			assertEquals(13130, c.getGene().length);
			for (Integer ch : c.getGene()) {
				assertTrue("Character code : " + ((int) ch), ch == -16777216 || ch == -1);
			}
		}
	}

	/**
	 * Method to test <code>Chromosome.mutate()</code>.
	 *
	 * @see net.auxesia.Chromosome#mutate()
	 */
	@Test
	public void testMutate() {
		Chromosome c1, c2;
		int[] arr1, arr2;
		for (int i = 0; i < 1000; i++) {
			c1 = Chromosome.generateRandom();
			arr1 = c1.getGene();

			c2 = c1.mutate();
			arr2 = c2.getGene();

			assertEquals(arr1.length, arr2.length);

			int diff = 0;
			for (int j = 0; j < arr1.length; j++) {
				if (arr1[j] != arr2[j]) {
					++diff;
				}
			}

			assertTrue(diff <= 1);
		}
	}

	/**
	 * Method to test <code>Chromosome.mate(Chromosome)</code>.
	 *
	 * @see net.auxesia.Chromosome#mate(Chromosome)
	 */
	@Test
	public void testMate() {
		Chromosome c1 = Chromosome.generateRandom();
		Chromosome c2 = Chromosome.generateRandom();
		int[] arr1 = c1.getGene();
		int[] arr2 = c2.getGene();

		// Check to ensure the right number of children are returned.
		Chromosome[] children = c1.mate(c2);
		assertEquals(2, children.length);

		// Check the resulting child gene lengths
		assertEquals(13130, children[0].getGene().length);
		assertEquals(13130, children[1].getGene().length);

		// Determine the pivot point for the mating
		int[] tmpArr = children[0].getGene();
		int pivot;
		for (pivot = 0; pivot < arr1.length; pivot++) {
			if (arr1[pivot] != tmpArr[pivot]) {
				break;
			}
		}

		// Check the first child.
		tmpArr = children[0].getGene();
		for (int i = 0; i < tmpArr.length; i++) {
			if (i < pivot) {
				assertEquals(arr1[i], tmpArr[i]);
			} else {
				assertEquals(arr2[i], tmpArr[i]);
			}
		}

		// Check the second child.
		tmpArr = children[1].getGene();
		for (int i = 0; i < tmpArr.length; i++) {
			if (i < pivot) {
				assertEquals(arr2[i], tmpArr[i]);
			} else {
				assertEquals(arr1[i], tmpArr[i]);
			}
		}
	}

	/**
	 * Method to test <code>Chromosome.compareTo(Chromosome)</code>.
	 *
	 * @see net.auxesia.Chromosome#compareTo(Chromosome)
	 */
	@Test
	public void testCompareTo() {
		Chromosome c1 = new Chromosome(new ImageSource("./images/4956.jpg").getRGBArray());
		assertEquals(4956, c1.getFitness());

		Chromosome c2 = new Chromosome(new ImageSource("./images/5180.jpg").getRGBArray());
		assertEquals(5180, c2.getFitness());

		Chromosome c3 = new Chromosome(new ImageSource("./images/5294.jpg").getRGBArray());
		assertEquals(5294, c3.getFitness());

		Chromosome c4 = new Chromosome(new ImageSource("./images/6084.jpg").getRGBArray());
		assertEquals(6084, c4.getFitness());

		assertEquals(0, c1.compareTo(c1));
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c1.compareTo(c3) < 0);
		assertTrue(c1.compareTo(c4) < 0);

		assertEquals(0, c2.compareTo(c2));
		assertTrue(c2.compareTo(c1) > 0);
		assertTrue(c2.compareTo(c3) < 0);
		assertTrue(c2.compareTo(c4) < 0);

		assertEquals(0, c3.compareTo(c3));
		assertTrue(c3.compareTo(c1) > 0);
		assertTrue(c3.compareTo(c2) > 0);
		assertTrue(c3.compareTo(c4) < 0);

		assertEquals(0, c4.compareTo(c4));
		assertTrue(c4.compareTo(c1) > 0);
		assertTrue(c4.compareTo(c2) > 0);
		assertTrue(c4.compareTo(c3) > 0);
	}

	/**
	 * Method to test <code>Chromosome.equals(Object)</code>.
	 *
	 * @see net.auxesia.Chromosome#equals(Object)
	 */
	@Test
	public void testEquals() {
		Chromosome c1 = new Chromosome(new ImageSource("./images/4956.jpg").getRGBArray());
		assertEquals(4956, c1.getFitness());
		Chromosome c2 = new Chromosome(new ImageSource("./images/6084.jpg").getRGBArray());
		assertEquals(6084, c2.getFitness());

		assertTrue(c1.equals(c1));
		assertTrue(c2.equals(c2));

		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(null));
		assertFalse(c1.equals(new Object()));

		assertFalse(c2.equals(c1));
		assertFalse(c2.equals(null));
		assertFalse(c2.equals(new Object()));
	}
}
