package ACOSAT.Ant;

import SATDpendencies.SATInstance;

import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CREATED BY wiss ON 00:36
 **/

public class ACSAntSAT extends AntSAT
{
    private double[][] addedPheromons;
    private double qProba;

    public ACSAntSAT(SATInstance instance, double qProba)
    {
        super(instance);
        this.qProba = qProba;
        this.addedPheromons = new double[instance.getLiteralsBitSet()[0].length][2];
    }

    @Override
    public void constructSolution()
    {

        boolean[] done = new boolean[solution.length()];
        for (int i = 0; i < instance.getLiteralsBitSet()[0].length; i++)
        {
            if (done[i])
                continue;
            double proba = getProba(i, 1);
            double probaNot = getProba(i, 0);
            double q = ThreadLocalRandom.current().nextDouble();
//            System.out.println("PROBA " + proba + " PROBA NOT " + probaNot + " GOT " + q);

            if (q <= qProba)
            {
                int[] argmax = getArgmax();
                done[argmax[1]] = true;
                System.out.println(argmax[0]);
                if (argmax[0] == 1)
                {
                    solution.set(argmax[1]);
                }
                else
                {
                    System.out.println("CHOSED EXPLOITATION");
                    solution.clear(argmax[1]);
                }
            } else
            {
                done[i] = true;
                q = ThreadLocalRandom.current().nextDouble(1);
                if (q < proba)
                {
                    solution.set(i);
                    onlineStepByStepPheromonUpdate(i, 1);
                } else
                {
                    System.out.println("CHOSED exploration ");
                    solution.clear(i);
                    onlineStepByStepPheromonUpdate(i, 0);
                }
            }
        }
    }

    private int[] getArgmax()
    {

        int[] argMax = {0, 0};
        BitSet[][] literals = instance.getLiteralsBitSet();
        for (int i = 0; i < literals.length; i++)
        {
            for (int j = 0; j < literals[i].length; j++)
            {

                if (getPherHeur(j, i) >= getPherHeur(argMax[1], argMax[0]))
                {
                    argMax[0] = i;
                    argMax[1] = j;
                }
            }
        }
        return argMax;
    }


    private void onlineStepByStepPheromonUpdate(int variable, int literal)
    {
        double Ti = instance.getPheromons().getPheromonValues()[variable][literal];
        double cost = variable;
//        var deltaTi = (double) 1 / cost;
//        instance.getPheromonsSAT().getPheromonValues()[variable][literal] = (1 - instance.getPheromonsSAT().getRo()) * Ti;
        instance.getPheromons().getPheromonValues()[variable][literal] = (1 - instance.getPheromons().getRo()) * Ti + instance.getPheromons().getRo() * instance.getPheromons().getTo();
        addedPheromons[variable][literal] = Ti - instance.getPheromons().getPheromonValues()[variable][literal];
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
