package SATII;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ressay on 29/03/18.
 */
public class SATSolution implements Cloneable
{
    private BitSet values;
    private SATInstance instance;
    private double evaluation = -1;

    public SATSolution(SATInstance instance)
    {
        this.instance = instance;
        values = new BitSet(instance.getNumberOfVariables());
    }


//    public SATSolution clone()
//    {
//        SATSolution o = null;
//        try
//        {
//            o = (SATSolution) super.clone();
//        } catch (CloneNotSupportedException cnse)
//        {
//            cnse.printStackTrace(System.err);
//        }
//        return o;
//    }

    public static SATSolution generateRandomSolution(SATInstance instance)
    {
        SATSolution solution = new SATSolution(instance);

        for (int i = 0; i < solution.length(); i++)
            if (ThreadLocalRandom.current().nextInt(0, 2) == 1)
                solution.set(i);
            else
                solution.clear(i);

        return solution;
    }


    public ArrayList<Integer> getVariablesOfClause(int clause)
    {
        ArrayList<Integer> vars = new ArrayList<>();
        for (int i = 0; i < this.length(); i++)
        {
            int invLit = get(i) ? 0 : 1;
            if (instance.literalsBitSet[invLit][i].get(clause))
                vars.add(i);
        }
        return vars;
    }


    public ArrayList<Integer> getUnsatisfiedClauses()
    {
        ArrayList<Integer> unsatisfied = new ArrayList<>();
        for (int i = 0; i < instance.getNumberOfClauses(); i++)
        {
            if (clauseSatisfied(i))
            {
                continue;
            }
            unsatisfied.add(i);
        }
        return unsatisfied;
    }


    private boolean clauseSatisfied(int clause)
    {

        for (int k = 0; k < this.length(); k++)
        {
            int lit = get(k) ? 1 : 0;
            if (instance.literalsBitSet[lit][k].get(clause))
            {
                return true;
            }
        }
        return false;
    }

    public double getCost()
    {
        return (double) (instance.getNumberOfClauses() - instance.getNumberOfClausesSatisfied(this));
    }


    public SATSolution copy()
    {
        BitSet copyBitset = new BitSet(values.length());
        copyBitset.clear();
        copyBitset.or(values);
        SATSolution solution = new SATSolution(instance);
        solution.values = copyBitset;
        return solution;
    }

    public SATSolution flip(int index)
    {
        values.flip(index);
        return this;
    }

    public SATSolution set(int index)
    {
        values.set(index);
        return this;
    }

    public SATSolution clear(int index)
    {
        values.clear(index);
        return this;
    }

    public SATSolution setIndexValue(int index, boolean value)
    {
        if (value)
            values.set(index);
        else
            values.clear(index);
        return this;
    }

    public boolean get(int index)
    {
        return values.get(index);
    }

    public int length()
    {
        return instance.getNumberOfVariables();
    }

    @Override
    public boolean equals(Object solution)
    {
        return values.equals(((SATSolution) solution).values);
    }

    @Override
    public String toString()
    {
        String output = "";
        for (int i = 0; i < length(); i++)
        {
            if (get(i))
                output += (i + 1) + " ";
            else
                output += "-" + (i + 1) + " ";
        }
        return output;
    }

    public int distance(SATSolution solution)
    {
        BitSet dis = copy().values;
        dis.xor(solution.values);
        return dis.cardinality();
    }

    public double getEvaluation()
    {
        if (evaluation == -1)
            evaluation = instance.getNumberOfClauses() - instance.getNumberOfClausesSatisfied(this);
        return evaluation;
    }

    public void setEvaluation(double evaluation)
    {
        this.evaluation = evaluation;
    }

    public double getCost(int variable, int litral)
    {
        return (double) instance.getLiteralsBitSet()[litral][variable].length() / instance.getNumberOfClauses();
    }

    public BitSet getValues()
    {
        return values;
    }

    public void setValues(BitSet values)
    {
        this.values = values;
    }

    public SATInstance getInstance()
    {
        return instance;
    }

    public void setInstance(SATInstance instance)
    {
        this.instance = instance;
    }
}