import ACOSAT.ACSSAT;
import ACOSAT.ASSAT;
import SATDpendencies.SATInstance;
import SATDpendencies.SATSolution;

/**
 * CREATED BY wiss ON 14:34
 **/

public class Main
{

    public static void main(String[] args) throws Exception
    {
        SATInstance instance = SATInstance.loadClausesFromDimacs("/home/wiss/CODES/TP-MetaHeuristic/ACO/Benchmarks/uf20-01.cnf");
        SATSolution solution = launchACS(instance);
        System.out.println("SOLUTION IS \n" + solution);
        System.out.println(solution.getEvaluation());
//        var sol = SATSolution.generateRandomSolution(instance);
//        System.out.println(sol.getEvaluation());
//        System.out.println(sol);
//        System.out.println(sol.getUnsatisfiedClauses());
    }

    public static SATSolution launchACS(SATInstance instance) throws Exception
    {
        ACSSAT acssat = new ACSSAT(30, 0.1, 200, 0.1, 2, 1, 0.9, instance);
        return acssat.startResearch();
    }

    public static SATSolution launchAS(SATInstance instance) throws Exception
    {
        ASSAT assat = new ASSAT(30, 0.1, 200, 0.1, 2, 1, instance);
        return assat.startResearch();
    }
}
