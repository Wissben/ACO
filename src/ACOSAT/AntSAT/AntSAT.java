package ACOSAT.AntSAT;

import ACOAbstract.Ant;
import SATDpendencies.SATInstance;
import SATDpendencies.SATSolution;
import SATDpendencies.SATTabuSearch;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * CREATED BY wiss ON 22:05
 **/

/**
 * SAT problem implementation for an {@link Ant}
 */
public abstract class AntSAT extends Ant<SATSolution>
{
    public static SATInstance instance;

    public AntSAT(SATInstance instance)
    {
        this.instance = instance;
        this.solution = new SATSolution(this.instance);
    }


    @Override
    public void exploreNeighbors()
    {
        SATTabuSearch searcher = new SATTabuSearch(30, 0, instance);
        this.solution = searcher.search(solution);
    }


    public double getProba(int variable, int literal)
    {
        return (double) getPherHeur(variable, literal) / getTotalPherHeur(variable);
    }

    protected double getTotalPherHeur(int variable)
    {
        double sum = 0;
        for (int j = 0; j < 2; j++)
        {
            sum += getPherHeur(variable, j);
        }
        return sum;
    }

    protected double getPherHeur(int variable, int literal)
    {
        double mu = (double) (instance.getNumberOfClauses() - instance.getLiteralsBitSet()[literal][variable].cardinality());/// instance.getNumberOfClauses();
        double muBeta = Math.pow(mu, instance.getPheromons().getBeta());
        double ti = (double) instance.getPheromons().getPheromonValues()[variable][literal];
        double tiAlpha = Math.pow(ti, instance.getPheromons().getAlpha());
        return tiAlpha * muBeta;
    }


    @Override
    public int compareTo(Ant<SATSolution> o)
    {
        return Double.compare(this.solution.getEvaluation(), o.solution.getEvaluation());
    }


    public static AntSAT generateRandomAnt(SATInstance instance)
    {
        return new AntSAT(instance)
        {
            @Override
            public void constructSolution()
            {
                solution = SATSolution.generateRandomSolution(instance);
            }
        };
    }

    @Override
    public void improveSolution()
    {

        for (int i = 0; i < 30; i++)
        {
            Random rand = new Random();
            SATSolution newS = this.solution.copy();
            TreeSet<Integer> unsatisfied = solution.getUnsatisfiedClauses();
            if (unsatisfied.size() > 0)
            {
                int clause = getRandomFromSet(unsatisfied);
                TreeSet<Integer> varsOfUnsatisfied = solution.getVariablesOfClause(clause);
                int var = getRandomFromSet(varsOfUnsatisfied);
                newS.flip(var);
                if (newS.getEvaluation() < solution.getEvaluation())
                {
                    System.out.println("YES");
                    this.solution = newS;
                }
            }
        }

    }

    public double getDelta(int variable, int literal)
    {
        return (double) (instance.getLiteralsBitSet()[literal][variable].cardinality());

    }

    /**
     * private method to be used only internally
     *
     * @param unsatisfied
     * @return random element of a {@link Set} of {@link Integer}
     */
    private Integer getRandomFromSet(Set<Integer> unsatisfied)
    {
        int size = unsatisfied.size();
        int item = new Random().nextInt(size);
        int index = 0;
        for (Integer obj : unsatisfied)
        {
            if (index == item)
            {
                return obj;
            }
            index++;
        }
        return 0;
    }

    public SATInstance getInstance()
    {
        return instance;
    }

    public void setInstance(SATInstance instance)
    {
        this.instance = instance;
    }

    public SATSolution getSolution()
    {
        return solution;
    }

    public void setSolution(SATSolution solution)
    {
        this.solution = solution;
    }

}
