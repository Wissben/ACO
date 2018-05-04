package ACOAbstract;

/**
 * CREATED BY wiss ON 00:12
 **/

public abstract class Pheromons<T, S>
{
    public T initValue;
    public S pheromonValues;

    public Pheromons(T initValue)
    {
        this.initValue = initValue;
    }

    public abstract void init(T initValue);
}
