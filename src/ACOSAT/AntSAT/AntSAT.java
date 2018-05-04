package ACOSAT.AntSAT;

import ACOAbstract.Ant;
import SATDpendencies.SATInstance;
import SATDpendencies.SATSolution;
import SATDpendencies.SATTabuSearch;

import java.util.LinkedList;
import java.util.Random;

/**
 * CREATED BY wiss ON 22:05
 **/

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
//        System.out.println("pherueur " + variable + "|" + literal + "=>" + getPherHeur(variable, literal) + "/total " + getTotalPherHeur(variable));
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
//        System.out.println("alpha " + muAlpha);
        double ti = (double) instance.getPheromons().getPheromonValues()[variable][literal];
        double tiAlpha = Math.pow(ti, instance.getPheromons().getAlpha());
//        System.out.println("BETA " + muBeta);
        return tiAlpha * muBeta;
    }


    @Override
    public int compareTo(Ant<SATSolution> o)
    {
        return Double.compare(this.solution.getEvaluation(), o.solution.getEvaluation());
    }


    @Override
    public void improveSolution()
    {
        Random rand = new Random();
        SATSolution newS = this.solution.copy();
        LinkedList<Integer> unsatisfied = solution.getUnsatisfiedClauses();
//        System.out.println(solution);
//        System.out.println(unsatisfied);
//        System.out.println(this.solution.getEvaluation());
        if (unsatisfied.size() > 0)
        {
            int clause = unsatisfied.get(rand.nextInt(unsatisfied.size()));
            LinkedList<Integer> varsOfUnsatisfied = solution.getVariablesOfClause(clause);
            int var = varsOfUnsatisfied.get(rand.nextInt(varsOfUnsatisfied.size()));
            newS.flip(var);
            if (newS.getEvaluation() < solution.getEvaluation())
            {
//                System.out.println("YES");
                this.solution = newS;
            }
        } else return;

    }

    public double getDelta(int variable, int literal)
    {
        return (double) (instance.getLiteralsBitSet()[literal][variable].cardinality());

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
