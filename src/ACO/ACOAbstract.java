package ACO;

import java.util.LinkedList;

/**
 * CREATED BY wiss ON 22:22
 **/

public abstract class ACOAbstract<T>
{
    protected LinkedList<Ant<T>> ants;
    protected int MAXITER;
    protected int numberOfAnts;
    protected int numberOfItterations = 0;

    public ACOAbstract(int numberOfAnts, int MAXITER)
    {
        this.MAXITER = MAXITER;
        this.numberOfAnts = numberOfAnts;
        this.ants = new LinkedList<>();
    }

    public abstract Ant<T> getBestAnt();

    public abstract T startResearch();

    protected abstract boolean end(T solution);
}
