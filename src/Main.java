import SATII.ACSSAT;
import SATII.SATInstance;

/**
 * CREATED BY wiss ON 14:34
 **/

public class Main
{

    public static void main(String[] args) throws Exception
    {
        var instance = SATInstance.loadClausesFromDimacs("/home/wiss/CODES/TP-MetaHeuristic/ACO/Benchmarks/uf75-01.cnf");
        ACSSAT ACSSAT = new ACSSAT(30, 0.1, 160, 0.7, 1, 0, instance);
        var solution = ACSSAT.startResearch();
        System.out.println("SOLUTION IS \n" + solution);
        System.out.println(solution.getEvaluation());
//        var sol = SATSolution.generateRandomSolution(instance);
//        System.out.println(sol.getEvaluation());
//        System.out.println(sol);
//        System.out.println(sol.getUnsatisfiedClauses());
    }
}
