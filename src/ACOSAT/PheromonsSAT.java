package ACOSAT;

/**
 * CREATED BY wiss ON 10:44
 **/

public class PheromonsSAT extends ACOAbstract.Pheromons<Double, Double[][]>
{
    private double ro; // evaporation rate
    private double to;
    private double alpha;
    private double beta;

    public PheromonsSAT(int numberOfVariables, double initValue, double ro, double alpha, double beta)
    {
        super(initValue);
        this.ro = ro;
        this.to = initValue;
        this.alpha = alpha;
        this.beta = beta;
        this.pheromonValues = new Double[numberOfVariables][2];
        init(initValue);
    }

    @Override
    public void init(Double initValue)
    {
        for (int i = 0; i < pheromonValues.length; i++)
        {
            for (int j = 0; j < pheromonValues[i].length; j++)
            {
                pheromonValues[i][j] = initValue;
            }
        }
    }


    public Double[][] getPheromonValues()
    {
        return pheromonValues;
    }

    @Override
    public String toString()
    {
        String ret = "";
        for (int i = 0; i < 2; i++)
        {
            ret += "[";
            for (int j = 0; j < pheromonValues.length; j++)
            {
                ret += "'" + String.format("%.8f", pheromonValues[j][i]) + "' ,";
            }
            ret += "]\n";
        }
        return ret;
    }

    public double getRo()
    {
        return ro;
    }

    public void setRo(double ro)
    {
        this.ro = ro;
    }

    public double getTo()
    {
        return to;
    }

    public void setTo(double to)
    {
        this.to = to;
    }

    public double getAlpha()
    {
        return alpha;
    }

    public void setAlpha(double alpha)
    {
        this.alpha = alpha;
    }

    public double getBeta()
    {
        return beta;
    }

    public void setBeta(double beta)
    {
        this.beta = beta;
    }


}
