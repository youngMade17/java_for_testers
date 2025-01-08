//Лекция 1.6


import java.io.File;

public class Hello {
    public static void main(String[] args) {
        //System.out.println("Hello, world");

        //"C:\\Users\\gunde\\IdeaProjects\\java_for_testers\\sandbox\\build.gradle"
        var configFile = new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());
    }
}
