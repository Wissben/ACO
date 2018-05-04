package ACOSAT.AntSAT;

import SATDpendencies.SATInstance;

import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CREATED BY wiss ON 00:36
 **/

public class AntSATACS extends AntSAT
{
    private double[][] addedPheromons;
    private double qProba;
    private boolean[] done;

    public AntSATACS(SATInstance instance, double qProba)
    {
        super(instance);
        this.qProba = qProba;
        this.addedPheromons = new double[instance.getLiteralsBitSet()[0].length][2];
        this.done = new boolean[instance.getNumberOfVariables()];
    }

    @Override
    public void constructSolution()
    {

        Arrays.fill(done, Boolean.FALSE);
//        System.out.println("BEFORE  => \n"+instance.getPheromons());
        for (int i = 0; i < instance.getNumberOfVariables(); i++)
        {
            if (done[i])
            {
//                System.out.println("DONE WITH " + i + " BY " + this.toString());
                continue;
            }
            double proba = getProba(i, 1);
            double probaNot = getProba(i, 0);
            double q = ThreadLocalRandom.current().nextDouble();
//            System.out.println("PROBA " + proba + " PROBA NOT " + probaNot + " GOT " + q);

            if (q <= qProba)
            {
                int[] argmax = getArgmax();
//                System.out.println(argmax[1] + "=>" + argmax[0] + " BY " + this.toString());
                if (argmax[0] == 1)
                {
                    solution.set(argmax[1]);
                } else
                {
                    solution.clear(argmax[1]);
                }
            } else
            {
//                System.out.println(i+ "=> BY " + this.toString());
                done[i] = true;
                q = ThreadLocalRandom.current().nextDouble(1);
                if (q < proba)
                {
                    solution.set(i);
                    onlineStepByStepPheromonUpdate(i, 1);
                } else
                {
                    solution.clear(i);
                    onlineStepByStepPheromonUpdate(i, 0);
                }
            }
        }
//        System.out.println("AFTER  => \n"+instance.getPheromons());
//        System.out.println(this);


    }

    private int[] getArgmax()
    {

        int[] argMax = {0, 0};
        BitSet[][] literals = instance.getLiteralsBitSet();
        for (int i = 0; i < literals.length; i++)
        {
            for (int j = 0; j < literals[i].length; j++)
            {

                if (!done[j] && getPherHeur(j, i) >= getPherHeur(argMax[1], argMax[0]))
                {
//                    System.out.println("MAX CHOSED IS " + j + "=>" + i);
                    argMax[0] = i;
                    argMax[1] = j;
                }
            }
        }
        done[argMax[1]] = true;
        return argMax;
    }


    private void onlineStepByStepPheromonUpdate(int variable, int literal)
    {
        double Ti = instance.getPheromons().getPheromonValues()[variable][literal];
        double cost = variable;
//        var deltaTi = (double) 1 / cost;
//        instance.getPheromonsSAT().getPheromonValues()[variable][literal] = (1 - instance.getPheromonsSAT().getRo()) * Ti;
        instance.getPheromons().getPheromonValues()[variable][literal] = (1 - instance.getPheromons().getRo()) * Ti + instance.getPheromons().getRo() * instance.getPheromons().getTo();
//        addedPheromons[variable][literal] = Ti - instance.getPheromons().getPheromonValues()[variable][literal];
    }


    public double[][] getAddedPheromons()
    {
        return addedPheromons;
    }

    public void setAddedPheromons(double[][] addedPheromons)
    {
        this.addedPheromons = addedPheromons;
    }

}
