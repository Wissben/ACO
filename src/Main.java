import ACOSAT.ACSSAT;
import SATDpendencies.SATInstance;
import SATDpendencies.SATSolution;

/**
 * CREATED BY wiss ON 14:34
 **/

public class Main
{

    public static void main(String[] args) throws Exception
    {
        SATInstance instance = SATInstance.loadClausesFromDimacs("/home/wiss/CODES/TP-MetaHeuristic/ACO/Benchmarks/uf75-01.cnf");
        ACSSAT ACSSAT = new ACSSAT(30, 0.1, 160, 0.7, 1, 0, instance);
        SATSolution solution = ACSSAT.startResearch();
        System.out.println("SOLUTION IS \n" + solution);
        System.out.println(solution.getEvaluation());
//        var sol = SATSolution.generateRandomSolution(instance);
//        System.out.println(sol.getEvaluation());
//        System.out.println(sol);
//        System.out.println(sol.getUnsatisfiedClauses());
    }
}
