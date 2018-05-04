package ACOAbstract;

/**
 * CREATED BY wiss ON 22:24
 **/

public abstract class Ant<T> implements Comparable<Ant<T>>
{

    public T solution;

    public abstract void constructSolution();

    public abstract void improveSolution();

    public abstract void exploreNeighbors();
}
