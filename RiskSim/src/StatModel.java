
public class StatModel {
    private int count;
    private int sum;
    private int sum_of_squares;
    private double variance;
    
    public StatModel(int count, int sum, int sum_of_squares)
    {
        this.count = count;
        this.sum = sum;
        this.sum_of_squares = sum_of_squares;
        if (count > 1)
        {
            this.variance = (sum_of_squares - (double)sum * sum / count) / (count - 1);
        }
        else
        {
            this.variance = Double.NaN;
        }
    }

    public  String ToString()
    {
        return String.format("Mean: %.3f Deviation: %.5f", (double)sum / count, Math.sqrt(variance));
    }

    public double Mean()
    {
        if (count > 0)
            return (double)sum / count;
        else
            return Double.NaN;
    }

    public Boolean IsCloseTo(StatModel other)
    {
        double compare = 1.00001;
        if (Double.isNaN(this.variance)) return false;
        if (Double.isNaN(other.variance)) return false;
        double ratio = this.Mean() / other.Mean();
        if ((ratio > compare) || (1 / ratio > compare)) return false;
        ratio = this.variance / other.variance;
        if ((ratio > compare) || (1 / ratio > compare)) return false;
        return true;
    }

}
