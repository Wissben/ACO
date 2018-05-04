package ACOAbstract;


import java.util.LinkedList;

/**
 * CREATED BY wiss ON 22:22
 **/

public abstract class ACO<T>
{
    protected LinkedList<Ant<T>> ants;
    protected int MAXITER;
    protected int numberOfAnts;
    protected int numberOfItterations = 0;

    public ACO(int numberOfAnts, int MAXITER)
    {
        this.MAXITER = MAXITER;
        this.numberOfAnts = numberOfAnts;
        this.ants = new LinkedList<>();
    }

    public Ant<T> getBestAnt()
    {
        Ant<T> bestSoFar = ants.getFirst();
        for (Ant<T> ant : ants)
        {
            if (ant.compareTo(bestSoFar) < 0)
                bestSoFar = ant;
        }
        return bestSoFar;
    }

    protected boolean end(T solution)
    {
        return (isValidSolution(solution) || numberOfItterations++ > MAXITER);
    }

    public abstract T startResearch();

    public abstract boolean isValidSolution(T solution);
}
