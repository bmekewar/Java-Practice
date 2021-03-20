package OtherPractice;

public interface InterfaceTest {
    public static final int constant = 21;
    public static int var = 20;
    default void testDefault(){ 
        System.out.println("testDefault");
    };
    void testMethod();
    abstract void testAbstract();

    interface Interface2 {
        public static final int constant = 12;
        public static int var = 123;
        default void testDefault(){ 
            System.out.println("int2 :: testDefault");
        };
        void testMethod();            
        abstract void testAbstract();

        class TestInterface implements InterfaceTest,Interface2 {

            @Override
            public void testMethod() {
                System.out.println(" class::testMethod");
            }

            @Override
            public void testAbstract() {
                System.out.println(" class::testAbstract");
            }
            public void testDefault(){
                InterfaceTest.super.testDefault();
            };

            public static void main(String[] args) {
                TestInterface t = new TestInterface();
                t.testDefault();
                t.testMethod();
                t.testAbstract();
            }
        }
    }
}
