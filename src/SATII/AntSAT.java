package SATII;

import ACO.Ant;

import java.util.ArrayList;
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
//        System.out.println("pherueur " + variable + "|" + literal + "=>" + getPherHeur(variable, literal) + "/total" + getTotalPherHeur(variable));
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
        double mu = (double) instance.getLiteralsBitSet()[literal][variable].cardinality() / instance.getNumberOfClauses();
        double muAlpha = Math.pow(mu, instance.getPheromons().getAlpha());
//        System.out.println("alpha " + muAlpha);
        double ti = (double) instance.getPheromons().getPheromonValues()[variable][literal];
        double tiBeta = Math.pow(ti, instance.getPheromons().getBeta());
//        System.out.println("BETA " + tiBeta * muAlpha);
        return tiBeta * muAlpha;
    }


    @Override
    public int compareTo(Ant<SATSolution> o)
    {
        //TODO USE IT Leul
        if (this.solution.getEvaluation() < o.solution.getEvaluation()) return -1;
        if (this.solution.getEvaluation() > o.solution.getEvaluation()) return 1;
        return 0;
    }


    @Override
    public void improveSolution()
    {
        Random rand = new Random();
        SATSolution newS = this.solution.copy();
        ArrayList<Integer> unsatisfied = solution.getUnsatisfiedClauses();
//        System.out.println(solution);
//        System.out.println(unsatisfied);
//        System.out.println(this.solution.getEvaluation());
        if (unsatisfied.size() > 0)
        {
            int clause = unsatisfied.get(rand.nextInt(unsatisfied.size()));
            ArrayList<Integer> varsOfUnsatisfied = solution.getVariablesOfClause(clause);
            int var = varsOfUnsatisfied.get(rand.nextInt(varsOfUnsatisfied.size()));
            newS.flip(var);
            if (newS.getEvaluation() < solution.getEvaluation())
                this.solution = newS;
        } else return;

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
