package ACOAbstract;

import java.util.TreeSet;

/**
 * CREATED BY wiss ON 00:25
 **/

public abstract class AS<T> extends ACO<T>
{
    public AS(int numberOfAnts, int MAXITER)
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
            System.out.println("THE BEST WAS " + evaluateSolution(this.bestAnt.solution));
            if (end(this.bestAnt.solution))
            {
                return this.bestAnt.solution;
            }
        }
    }

    public void deamons(Ant<T> ant)
    {
        ant.exploreNeighbors();
        ant.improveSolution();
    }

}
