package OtherPractice;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueueTest pqt1 = new PriorityQueueTest();

        Queue<Person1> pq = new PriorityQueue<Person1>();
        pq.add(pqt1.new Person1("p1", "add1", 34));
        pq.add(pqt1.new Person1("p23", "add23", 23));
        pq.add(pqt1.new Person1("p3", "add3", 35));
        pq.add(pqt1.new Person1("p5", "add5", 55));
        pq.add(pqt1.new Person1("p11", "add11", 44));
        System.out.println("Priority queue: " + pq);
    }

    class Person1 implements Comparable {
        private String name;
        private String address;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Person1() {
            super();
        }

        public Person1(String name, String address, int age) {
            super();
            this.name = name;
            this.address = address;
            this.age = age;
        }


        @Override
        public int compareTo(Object o) {
            Person1 p1 = null;
            if(o instanceof Person1){
                p1 = (Person1)o;
            }
            return this.getName().compareTo(p1.getName());
        }

        @Override
        public String toString() {
            return "Person1 [address=" + address + ", age=" + age + ", name=" + name + "]";
        }
        
    }
}
