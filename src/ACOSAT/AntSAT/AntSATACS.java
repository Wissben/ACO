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
    private double qProba;
    private boolean[] done;

    public AntSATACS(SATInstance instance, double qProba)
    {
        super(instance);
        this.qProba = qProba;
        this.done = new boolean[instance.getNumberOfVariables()];
    }

    @Override
    public void constructSolution()
    {

        Arrays.fill(done, Boolean.FALSE);
        for (int i = 0; i < instance.getNumberOfVariables(); i++)
        {
            if (done[i])
            {
                continue;
            }
            double proba = getProba(i, 1);
            double probaNot = getProba(i, 0);
            double q = ThreadLocalRandom.current().nextDouble();

            if (q <= qProba)
            {
                int[] argmax = getArgmax();
                if (argmax[0] == 1)
                {
                    solution.set(argmax[1]);
                } else
                {
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

                if (!done[j] && getPherHeur(j, i) >= getPherHeur(argMax[1], argMax[0]))
                {
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
        instance.getPheromons().getPheromonValues()[variable][literal] = (1 - instance.getPheromons().getRo()) * Ti + instance.getPheromons().getRo() * instance.getPheromons().getTo();
    }


}
