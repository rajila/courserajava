public class InsertionCustom {
    public static void sort(Comparable [] arrayData) {
        for (int pivote = 0; pivote < arrayData.length; pivote++) {
            for (int index = pivote; index > 0; index--) {
                if (SorterCustom.less(arrayData[index], arrayData[index-1]))
                    SorterCustom.swapping(arrayData, index, index-1);
                else break;
            }
        }
    }
}
