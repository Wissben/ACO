package ACOAbstract;

import java.util.TreeSet;

/**
 * CREATED BY wiss ON 22:35
 **/

/**
 * Abstract behaviour of the ACS algorithm according to a solution of type T
 *
 * @param <T> the nature of the solution
 */
public abstract class ACS<T> extends ACO<T>
{

    public ACS(int numberOfAnts, int MAXITER)
    {
        super(numberOfAnts, MAXITER);
    }

    @Override
    public T startResearch()
    {
        this.numberOfItterations = 0;
        for (; ; )
        {
            ants = new TreeSet<>();
            initAnts();
            for (Ant<T> ant : ants)
            {
                ant.constructSolution();
                deamons(ant);
                if (isValidSolution(ant.solution))
                {
                    return ant.solution;
                }

            }
            Ant<T> bestAnt = getBestAnt();
            if (bestAnt.compareTo(this.bestAnt) < 0)
                this.bestAnt = bestAnt;
            System.out.println("THE BEST SO FAR IS " + evaluateSolution(this.bestAnt.solution));
            offlinePheromonUpdate(this.bestAnt);
            if (end(this.bestAnt.solution))
            {
                return this.bestAnt.solution;
            }
        }
    }

    @Override
    public void deamons(Ant<T> ant)
    {
        ant.exploreNeighbors();
        ant.improveSolution();
    }

    /**
     * Specific to the ACS algorithm
     *
     * @param bestAnt
     */
    public abstract void offlinePheromonUpdate(Ant<T> bestAnt);

}
