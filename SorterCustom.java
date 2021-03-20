public class SorterCustom {
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void swapping(Comparable [] arrayData, int indexA, int indexB) {
        Comparable tmp = arrayData[indexA];
        arrayData[indexA] = arrayData[indexB];
        arrayData[indexB] = tmp;
    }
}
