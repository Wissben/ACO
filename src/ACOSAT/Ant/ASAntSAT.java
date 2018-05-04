package ACOSAT.Ant;

import SATDpendencies.SATInstance;

import java.util.concurrent.ThreadLocalRandom;

/**
 * CREATED BY wiss ON 01:20
 **/

public class ASAntSAT extends AntSAT
{
    public ASAntSAT(SATInstance instance)
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
//            System.out.println("PROBA " + proba + " PROBA NOT " + probaNot + " GOT " + q);
            done[i] = true;
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

    private void onlineStepByStepPheromonUpdate(int variable, int literal)
    {
        double Ti = instance.getPheromons().getPheromonValues()[variable][literal];
        double cost = variable;
//        var deltaTi = (double) 1 / cost;
//        instance.getPheromonsSAT().getPheromonValues()[variable][literal] = (1 - instance.getPheromonsSAT().getRo()) * Ti;
        instance.getPheromons().getPheromonValues()[variable][literal] = (1 - instance.getPheromons().getRo()) * Ti +
                instance.getPheromons().getRo() * this.getDelta(variable, literal);
    }

}
