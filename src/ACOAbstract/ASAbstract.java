package ACOAbstract;

/**
 * CREATED BY wiss ON 00:25
 **/

public abstract class ASAbstract<T> extends ACOAbstract<T>
{
    public ASAbstract(int numberOfAnts, int MAXITER)
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
//                ant.improveSolution();
//                ant.exploreNeighbors();
            }
            Ant<T> bestAnt = getBestAnt();
            if (end(bestAnt.solution))
            {
                System.out.println(bestAnt.solution);
                return bestAnt.solution;
            }
        }
    }

    public abstract void offlinePheromonUpdate(Ant<T> bestAnt);

}
