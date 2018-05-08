package ACOSAT.AntSAT;

import SATDpendencies.SATInstance;

import java.util.concurrent.ThreadLocalRandom;

/**
 * CREATED BY wiss ON 01:20
 **/

public class AntSATAS extends AntSAT
{
    public AntSATAS(SATInstance instance)
    {
        super(instance);
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
            done[i] = true;
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

    private void onlineStepByStepPheromonUpdate(int variable, int literal)
    {
        double Ti = instance.getPheromons().getPheromonValues()[variable][literal];
        double cost = variable;
        instance.getPheromons().getPheromonValues()[variable][literal] = (1 - instance.getPheromons().getRo()) * Ti +
                instance.getPheromons().getRo() * this.getDelta(variable, literal);
    }

}
