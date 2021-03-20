package OtherPractice;

public class BuilderTest {

    public static void main(String[] args) {

        BuilderTest bt = new BuilderTest();
        System.out.println(bt.new Person2().setAddress("address").setName("p2").setAge(12));
    }

    class Person2 {
        private String name;
        private Integer age;
        private String address;

        public String getName() {
            return name;
        }

        public Person2 setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public Person2 setAge(Integer age) {
            this.age = age;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public Person2 setAddress(String address) {
            this.address = address;
            return this;
        }

        public Person2() {
        }

        public Person2(String name, Integer age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Person2 [address=" + address + ", age=" + age + ", name=" + name + "]";
        }

    }
}
