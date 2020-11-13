/**
    Compilar (W10):
        javac -cp "algs4.jar" QuickFindUF.java
    Ejecutar (W10):
        java -cp ".;algs4.jar" QuickFindUF inputQuickFind20.txt
*/

import java.util.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;


public class QuickFindUF {
    private final int [] _id;
    private final int [] _idCount;
    private final int _length;

    public QuickFindUF(int __length) {
        this._id = new int[__length];
        this._idCount = new int[__length];
        this._length = __length;
        init();
    }

    private void init() {
        for(int _i=0; _i < _length; _i++) {
            _id[_i] = _i;
            _idCount[_i] = 1;
        }
    }

    public boolean connected(int __indexP, int __indexQ) {
        return _id[__indexP] == _id[__indexQ];
    }

    public void union(int __indexP, int __indexQ) {
        int _valueP = _id[__indexP];
        int _valueQ = _id[__indexQ];
        for(int _i=0; _i < _length; _i++) if (_valueP == _id[_i]) _id[_i] = _valueQ;
    }

    public void unionCustom(int __indexP, int __indexQ) {
        int _valueP = _id[__indexP];
        int _valueQ = _id[__indexQ];
        if (_idCount[_valueP] > 0 && _idCount[_valueQ] == 1) {
            _id[__indexQ] = _valueP;
            _idCount[_valueP]++;
        }else if (_idCount[_valueP] == 1 && _idCount[_valueQ] != 1) {
            _id[__indexP] = _valueQ;
            _idCount[_valueQ]++;
        } else {
            int _flag = _idCount[_valueP];
            for(int _i=0; _i < _length && _flag > 0; _i++) {
                if (_valueP == _id[_i]) {
                    _idCount[_valueQ]++;
                     _id[_i] = _valueQ;
                     _flag--;
                }
            }
        }
    }

    public void printArray(int[] __dataArray) {
        System.out.print(" ");
        for(int _i=0; _i < 2*_length; _i++) System.out.print("----");
        System.out.print("\n |");
        for(int _i=0; _i < _length; _i++) StdOut.printf("  %d\t|", _i);
        System.out.print("\n ");
        for(int _i=0; _i < 2*_length; _i++) System.out.print("----");
        System.out.print("\n |");
        for(int _i=0; _i < _length; _i++) StdOut.printf("  %d\t|", __dataArray[_i]);
        System.out.print("\n ");
        for(int _i=0; _i < 2*_length; _i++) System.out.print("----");
        System.out.print("\n\n");
    }

    public static void main(String[] args) {
        In _fileIn = new In(args[0]);
        int _lengthIn = _fileIn.readInt();
        QuickFindUF _qF = new QuickFindUF(_lengthIn);
        StdOut.printf(" Start\n");
        // _qF.printArray(_qF._id);
        Stopwatch _timer = new Stopwatch();
        while (!_fileIn.isEmpty()) {
            int _pIn = _fileIn.readInt();
            int _qIn = _fileIn.readInt();
            if(!_qF.connected(_pIn, _qIn)) {
                // _qF.union(_pIn, _qIn); // for 10.000.000 is less fast: 0.04600
                _qF.unionCustom(_pIn, _qIn); // for 10.000.000 is more fast: 0.00200
                // StdOut.printf(" union(%d, %d)\n", _pIn, _qIn);
                // _qF.printArray(_qF._id);
            }
        }
        double _diff = _timer.elapsedTime();
        StdOut.printf(" End\n");
        // _qF.printArray(_qF._id);
        // StdOut.printf("\n Count Array\n");
        // _qF.printArray(_qF._idCount);
        StdOut.printf("Time -> %.6f\n", _diff);
    }
}
