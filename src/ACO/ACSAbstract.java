package ACO;

/**
 * CREATED BY wiss ON 22:35
 **/

public abstract class ACSAbstract<T> extends ACOAbstract<T>
{

    public ACSAbstract(int numberOfAnts, int MAXITER)
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
                ant.improveSolution();
//                ant.exploreNeighbors();
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

    public abstract boolean isValidSolution(T solution);

    public abstract void offlinePheromonUpdate(Ant<T> bestAnt);

}
