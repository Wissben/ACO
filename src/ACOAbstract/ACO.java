package ACOAbstract;


import java.util.Collections;
import java.util.TreeSet;

/**
 * CREATED BY wiss ON 22:22
 **/

/**
 * Abstract class describing any implementation of the ACO algorithm
 *
 * @param <T> the type of ants (i.e the nature of a solution that the ants will build)
 */
public abstract class ACO<T>
{
    protected TreeSet<Ant<T>> ants;
    protected Ant<T> bestAnt;
    protected int MAXITER;
    protected int numberOfAnts;
    protected int numberOfItterations;

    /**
     * @param numberOfAnts
     * @param MAXITER
     */
    public ACO(int numberOfAnts, int MAXITER)
    {
        this.numberOfItterations = 0;
        this.MAXITER = MAXITER;
        this.numberOfAnts = numberOfAnts;
        this.ants = new TreeSet<>();
    }

    /**
     * @return the best ant based on the implementation of the compareTo method in the {@link Ant} subclasses
     */
    public Ant<T> getBestAnt()
    {
        return Collections.min(ants);
    }

    /**
     * Basic conditions for the algorithm to stop, one can also redefine this method in the subclasses of {@link ACO}
     *
     * @param solution testing the solution
     * @return true or false
     */
    protected boolean end(T solution)
    {
        numberOfItterations++;
        return (isValidSolution(solution) || numberOfItterations >= MAXITER);
    }

    public abstract void initAnts();

    public abstract T startResearch();

    public abstract boolean isValidSolution(T solution);

    public abstract double evaluateSolution(T solution);

    /**
     * Actions to perform when the ant has finished bulding a solution
     *
     * @param ant
     */
    protected abstract void deamons(Ant<T> ant);
}
