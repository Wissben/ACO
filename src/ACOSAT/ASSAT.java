package ACOSAT;

import ACOAbstract.AS;
import ACOSAT.AntSAT.AntSAT;
import ACOSAT.AntSAT.AntSATAS;
import SATDpendencies.SATInstance;
import SATDpendencies.SATSolution;

/**
 * CREATED BY wiss ON 13:57
 **/

public class ASSAT extends AS<SATSolution>
{
    private final double initValue;
    private final double ro;
    private final double alpha;
    private final double beta;
    private final SATInstance instance;

    public ASSAT(int numberOfAnts, double initValue, int MAXITER, double ro, double alpha, double beta, SATInstance instance) throws Exception
    {
        super(numberOfAnts, MAXITER);
        this.initValue = initValue;
        this.ro = ro;
        this.alpha = alpha;
        this.beta = beta;
        this.instance = instance;
        PheromonsSAT pheromons = new PheromonsSAT(instance.getNumberOfVariables(), initValue, ro, alpha, beta);
        this.instance.setPheromons(pheromons);
        this.bestAnt = AntSAT.generateRandomAnt(instance);

    }

    @Override
    public double evaluateSolution(SATSolution solution)
    {
        return solution.getEvaluation();
    }

    @Override
    public void initAnts()
    {
        for (int i = 0; i < numberOfAnts; i++)
        {
            AntSATAS ant = new AntSATAS(instance);
            ants.add(ant);
        }
    }

    @Override
    public boolean isValidSolution(SATSolution solution)
    {
        return solution.getEvaluation() == 0;
    }


}
