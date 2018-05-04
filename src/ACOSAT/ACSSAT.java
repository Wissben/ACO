package ACOSAT;

import ACOAbstract.ACSAbstract;
import ACOAbstract.Ant;
import SATDpendencies.SATInstance;
import SATDpendencies.SATSolution;

/**
 * CREATED BY wiss ON 10:35
 **/

public class ACSSAT extends ACSAbstract<SATSolution>
{
    private double ro;
    private double alpha;
    private double beta;
    private double initValue;
    private SATInstance instance;

    public ACSSAT(int numberOfAnts, double initValue, int MAXITER, double ro, double alpha, double beta, SATInstance instance) throws Exception
    {
        super(numberOfAnts, MAXITER);
        this.initValue = initValue;
        this.ro = ro;
        this.alpha = alpha;
        this.beta = beta;
        this.instance = instance;
        PheromonsSAT pheromons = new PheromonsSAT(instance.getNumberOfVariables(), initValue, ro, alpha, beta);
        for (int i = 0; i < numberOfAnts; i++)
        {
            ACSAntSAT ant = new ACSAntSAT(instance);
            ant.getInstance().setPheromons(pheromons);
            ants.add(ant);
        }

    }

    @Override
    public ACSAntSAT getBestAnt()
    {
        ACSAntSAT bestSoFar = (ACSAntSAT) ants.getFirst();
        for (Ant<SATSolution> ant : ants)
        {
            if (ant.solution.getEvaluation() < ant.solution.getEvaluation())
                bestSoFar = (ACSAntSAT) ant;
        }
        return bestSoFar;
    }


    @Override
    protected boolean end(SATSolution solution)
    {
        return (isValidSolution(solution) || numberOfItterations++ > MAXITER);
    }


    @Override
    public boolean isValidSolution(SATSolution solution)
    {
        return solution.getEvaluation() == 0;
    }

    @Override
    public void offlinePheromonUpdate(Ant<SATSolution> bestAnt)
    {
        for (int i = 0; i < bestAnt.solution.getInstance().getPheromons().getPheromonValues().length; i++)
        {
            for (int j = 0; j < bestAnt.solution.getInstance().getPheromons().getPheromonValues()[i].length; j++)
            {
                double Ti = instance.getPheromons().getPheromonValues()[i][j];
                instance.getPheromons().getPheromonValues()[i][j] = (1 - instance.getPheromons().getRo()) * Ti + instance.getPheromons().getRo() *
                        getDelta(bestAnt, j, i);

            }
        }
    }

    public double getDelta(Ant<SATSolution> bestAnt, int variable, int literal)
    {
        return (double) bestAnt.solution.getEvaluation();

    }


    private boolean end(int itter, double eval)
    {
        return itter >= MAXITER || eval == 0;
    }

}
