//Лекция 1.6


public class Hello {
    public static void main(String[] args) {
        //
        try {
            var x = 1;
            var y = 0;
            var z = devide(x, y);
            System.out.println("Hello, world");
        } catch (ArithmeticException e) {
            System.out.println("Division by zero is not allowed");
        }

        //"C:\\Users\\gunde\\IdeaProjects\\java_for_testers\\sandbox\\build.gradle"
//        var configFile = new File("sandbox/build.gradle");
//        System.out.println(configFile.getAbsolutePath());
//        System.out.println(configFile.exists());
    }

    private static int devide(int x, int y) {
        var z = x / y;
        return z;
    }
}
