package ACOAbstract;

/**
 * CREATED BY wiss ON 22:24
 **/

/**
 * Abstract structure of any Ant
 *
 * @param <T> nature of the solution the ant is building
 */
public abstract class Ant<T> implements Comparable<Ant<T>>
{

    public T solution;

    public abstract void constructSolution();

    public abstract void improveSolution();

    public abstract void exploreNeighbors();
}
