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
        String path = "/home/wiss/CODES/TP-MetaHeuristic/ACO/Benchmarks/UF75.325.100/uf75-06.cnf";
        SATInstance instance = SATInstance.loadClausesFromDimacs(path);
        SATSolution solution = launchACS(30, 0.1, 500, 0.7, 1, 1, 0.8, instance);
//        SATSolution solution = launchAS(30,0.1,500,0.7,1,0,instance);


        System.out.println("SOLUTION IS \n" + solution);
        System.out.println(solution.getEvaluation());
        System.out.println(solution.getUnsatisfiedClauses());
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
