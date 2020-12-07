package hashtable;
class Emp {
    public int id;
    public String name;
    public Emp next;

    /**
     * @param id
     * @param name
     */
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
}

class EmpLinkedList {
    private Emp head;

    public void add(Emp emp){
        if(head == null) {
            head = emp;
            return;
        }
        Emp cur = head;
        while(cur.next != null){
            cur = cur.next;
        }   
        cur.next = emp;
    }
    public Emp findEmpById(int id) {
        if(head == null) {
            throw new RuntimeException("is empty");
        }
        Emp cur = head;
        while(cur.id != id) {
            if(cur.next == null) {
                cur = null;
                break;
            }
            cur = cur.next;
        }
        return cur;
    }
    public void empList(){
        if (head == null) {
            // throw new RuntimeException("is empty");
            System.out.println("為空");
            return;
        }
        Emp cur = head;
        while(cur != null){
            System.out.printf("id=%d, name=%s -->\t", cur.id, cur.name);
            cur = cur.next;
        }
        System.out.println();
    }
}

class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size;
    public HashTable(int size){
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        empLinkedLists = initTable(empLinkedLists);
    }

    public void add(Emp emp){
        int no = hash(emp.id);
        empLinkedLists[no].add(emp);
    }
    public void hashList() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].empList();
        }
    }
    public void findEmpById(int id){
        int no = hash(id);
        Emp emp = empLinkedLists[no].findEmpById(id);
        if (emp != null) {
            System.out.println("id= "+ id);
        } else {
            System.out.println("Not Found");
        }
    }
    private int hash(int id){
        return id%size;
    }

    private EmpLinkedList[] initTable(EmpLinkedList[] emp) {
        for (int i = 0; i < emp.length; i++) {
            emp[i] = new EmpLinkedList();
        }
        return emp;
    }
}
public class HashTables {
    public static void main(String[] args) {
        HashTable ht = new HashTable(7);

        Emp emp = new Emp(1, "tom");
        ht.add(emp);
        Emp emp2 = new Emp(2, "jack");
        ht.add(emp2);
        Emp emp3 = new Emp(8, "itachi");
        ht.add(emp3);
        Emp emp4 = new Emp(19, "Naruto");
        ht.add(emp4);
        Emp emp5 = new Emp(26, "Lin");
        ht.add(emp5);
        ht.hashList();
        ht.findEmpById(19);
    }
}