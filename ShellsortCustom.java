public class ShellsortCustom {
    public static void sort(Comparable [] arrayData) {
        int sizeArray = arrayData.length;
        int h = 1;
        while (h < sizeArray/3) h = 3*h + 1; // Range maximo
        while (h >= 1) {
            for (int i = h; i < sizeArray; i++) {
                // Ro (Start range) -> j-h
                // Rf (End range) -> j
                for (int j = i; j >= h && SorterCustom.less(arrayData[j], arrayData[j-h]); j -= h) {
                    SorterCustom.swapping(arrayData, j, j-h);
                }
            }
            h /= 3; // Range desc, hasta 1
        }
    }
}
