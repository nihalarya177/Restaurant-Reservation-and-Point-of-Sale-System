import java.util.Date;
public interface Print{
    static final double GST = .07;
    static final double SERVICECHARGE = .10;
    static final double MEMBERDISCOUNT = .05;
    public abstract void print(Order order, Restaurant restaurant);
    public abstract void print(Date startDate, Date endDate, Restaurant restaurant);
}