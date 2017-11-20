package org.neuroph.contrib.neat.gen.operations;

import java.util.List;

import org.neuroph.contrib.neat.gen.FitnessScores;
import org.neuroph.contrib.neat.gen.Innovations;
import org.neuroph.contrib.neat.gen.NeatParameters;
import org.neuroph.contrib.neat.gen.Organism;

public interface MutationOperation {

	/**
	 * Performs the mutation operation on all of the provided
	 * <code>Organism</code>s.
	 * 
	 * @param neatParameters
	 *            the <code>NeatParameters</code> defining the environment in
	 *            which this operation is to be performed.
	 * @param innovations
	 *            the <code>Innovations</code> that is used for storing all new
	 *            connection and neuron genes. All values should be added to or
	 *            used from this cache.
	 * @param fitnessScores
	 *            the <code>FitnessScores</code> of the <code>Organism</code>s
	 *            in the current generation.
	 * @param generation
	 *            a <code>List</code> of <code>Organism</code>s this operation
	 *            is to be performed on.
	 * @param generationNumber
	 *            which generation this is.
	 * 
	 * @return the number of <code>Organism</code>s that were mutated as part of
	 *         this operation.
	 */
	public int mutate(NeatParameters neatParameters, Innovations innovations,
			FitnessScores fitnessScores, List<Organism> generation,
			int generationNumber);

}