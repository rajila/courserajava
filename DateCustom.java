public class DateCustom implements Comparable<DateCustom> {
    private final int _year;
    private final int _month;
    private final int _day;

    /**
     * __day Day current
     * __month Month current
     * __year Year current
     * @return
     */
    public DateCustom(int __day, int __month, int __year) {
        this._day = __day;
        this._month = __month;
        this._year = __year;
    }

    /**
     * __date DateCustom Object
     * @return int value
     */
    public int compareTo(DateCustom __date) {
        if (this._year < __date._year) return -1;
        if (this._year > __date._year) return 1;
        if (this._month < __date._month) return -1;
        if (this._month > __date._month) return 1;
        if (this._day < __date._day) return -1;
        if (this._day > __date._day) return 1;
        return 0;
    }

    private void compareDate(int __value) {
        if (__value == 0) System.out.println("Fechas iguales");
        else if (__value == -1) System.out.println("FechaOne es menor FechaTwo");
        else if (__value == 1) System.out.println("FechaOne is mayor FechaTwo");
    }

    public static void main(String [] args) {
        DateCustom _dateOne = new DateCustom(10, 12, 2020);
        {
            DateCustom _dateTwo = new DateCustom(01, 01, 2020);
            _dateOne.compareDate(_dateOne.compareTo(_dateTwo));
        }

        {
            DateCustom _dateTwo = new DateCustom(10, 12, 2020);
            _dateOne.compareDate(_dateOne.compareTo(_dateTwo));
        }

        {
            DateCustom _dateTwo = new DateCustom(25, 12, 2020);
            _dateOne.compareDate(_dateOne.compareTo(_dateTwo));
        }
    }
}
