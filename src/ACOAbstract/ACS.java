package ACOAbstract;

/**
 * CREATED BY wiss ON 22:35
 **/

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
            for (Ant<T> ant : ants)
            {
                ant.constructSolution();
//                ant.exploreNeighbors();
//                ant.improveSolution();
                if (isValidSolution(ant.solution))
                {
                    return ant.solution;
                }

            }
            Ant<T> bestAnt = getBestAnt();
            offlinePheromonUpdate(bestAnt);
            if (end(bestAnt.solution))
            {
                return bestAnt.solution;
            }
        }
    }


    public abstract void offlinePheromonUpdate(Ant<T> bestAnt);

}
