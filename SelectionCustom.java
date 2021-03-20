public class SelectionCustom {
    public static void sort(Comparable [] arrayData) {
        for (int i = 0; i < arrayData.length; i++) {
            int min = i;
            for (int j = i + 1; j < arrayData.length; j++ ) {
                if (SorterCustom.less(arrayData[j], arrayData[min])) min = j;
            }
            SorterCustom.swapping(arrayData, i, min);
        }
    }
}
