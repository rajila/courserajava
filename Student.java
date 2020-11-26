import java.util.Comparator;
import java.util.Arrays;


public class Student implements Comparable<Student> {
    private final String _name;
    private final Integer _section;
    private final Integer _id; // Orden por defecto

    public static final Comparator<Student> _sortByName = new sortName();
    public static final Comparator<Student> _sortBySelection = new sortSection();

    public static String _typeOrder = "ASC";

    public Student(int __id, String __name, int __section) {
        this._id = __id;
        this._name = __name;
        this._section = __section;
    }

    public static void printList(Student [] __students) {
        System.out.println("Id\t\tSection\t\tName");
        for(int i=0; i<__students.length; i++)
            System.out.println(__students[i]._id+"\t\t"+__students[i]._section+"\t\t"+__students[i]._name);
    }

    public int compareTo(Student __a) {
        //return this._id.compareTo(__a._id); // return -1, 0, 1
        return (Student._typeOrder == "ASC")?this._id.compareTo(__a._id):__a._id.compareTo(this._id); // return -1, 0, 1
    }

    // Class anonima for sorte Array of students for NAME
    private static class sortName implements Comparator<Student> {
        public int compare(Student __a, Student __b) {
            return __a._name.compareTo(__b._name); // return -1, 0, 1
        }
    }

    // Class anonima for sorte Array of students for SECTION
    private static class sortSection implements Comparator<Student> {
        public int compare(Student __a, Student __b) {
            return __a._section.compareTo(__b._section); // return -1, 1, 0
        }
    }

    public static void main(String [] args) {
        Student [] _students = new Student[5]; // array
        _students[0] = new Student(3, "Ronald", 25);
        _students[1] = new Student(1, "Fernanda", 10);
        _students[2] = new Student(2, "Jorge", 15);
        _students[3] = new Student(4, "Isabella", 4);
        _students[4] = new Student(5, "Mary", 12);
        System.out.println("Listado Original");
        Student.printList(_students);
        System.out.println("Listado ordenado ASC por SECTION");
        Arrays.sort(_students, Student._sortBySelection);
        Student.printList(_students);
        System.out.println("Listado ordenaso ASC por NAME");
        Arrays.sort(_students, Student._sortByName);
        Student.printList(_students);
        System.out.println("Listado ordenaso ASC por ID");
        Arrays.sort(_students); // Ordena por el ID
        Student.printList(_students);
        System.out.println("Listado ordenaso DESC por ID");
        Student._typeOrder = "DESC";
        Arrays.sort(_students); // Ordena por el ID
        Student.printList(_students);
    }
}
