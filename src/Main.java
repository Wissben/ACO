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
        String path = "/home/wiss/CODES/TP-MetaHeuristic/ACO/Benchmarks/uf75-01.cnf";
        SATInstance instance = SATInstance.loadClausesFromDimacs(path);
        SATSolution solution = launchACS(30, 0.1, 160, 0.7, 1, 0, 0.9, instance);
//        SATSolution solution = launchAS(30,0.1,200,0.7,1,0,instance);
        System.out.println("SOLUTION IS \n" + solution);
        System.out.println(solution.getEvaluation());
        System.out.println(solution.getUnsatisfiedClauses());
//        var sol = SATSolution.generateRandomSolution(instance);
//        System.out.println(sol);
//        System.out.println(sol.getEvaluation());
//        System.out.println(sol.getUnsatisfiedClauses());
//        sol.getValues().flip(0);
//        sol.getValues().flip(4);
//        System.out.println(sol);
//        System.out.println(sol.getEvaluation());
//        System.out.println(sol.getUnsatisfiedClauses());
    }

    public static SATSolution launchACS(int numberOfAnts, double initValue, int MAXITTER, double ro, double alpha, double beta, double q, SATInstance instance) throws Exception
    {
        ACSSAT acssat = new ACSSAT(numberOfAnts, initValue, MAXITTER, ro, alpha, beta, q, instance);
        return acssat.startResearch();
    }

    public static SATSolution launchAS(int numberOfAnts, double initValue, int MAXITTER, double ro, double alpha, double beta, SATInstance instance) throws Exception
    {
        ASSAT assat = new ASSAT(numberOfAnts, initValue, MAXITTER, ro, alpha, beta, instance);
        return assat.startResearch();
    }
}
