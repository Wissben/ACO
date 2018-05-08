package ACOAbstract;

/**
 * CREATED BY wiss ON 00:12
 **/

/**
 * Abstract structure of the Pheromon information
 *
 * @param <T> the initial value of all the pheromons
 * @param <S> the type of structure where the pheromons are stocked
 */
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
