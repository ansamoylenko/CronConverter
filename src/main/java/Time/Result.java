package Time;

public class Result
{
    public Result(boolean flag, String message)
    {
        this.flag = flag;
        this.message = message;
    }

    public String toString()
    {
        return message;
    }

    private boolean flag;
    private String message;

    public boolean getFlag() {
        return flag;
    }

    public String getMessage() {
        return message;
    }
}
