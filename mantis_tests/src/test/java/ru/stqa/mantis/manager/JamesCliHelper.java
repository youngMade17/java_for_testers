package ru.stqa.mantis.manager;

import org.openqa.selenium.io.CircularOutputStream;

public class JamesCliHelper extends HelperBase {

    // Запуск сервера
    // java -Dworking.directory=. -jar james-server-jpa-app.jar

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) {
        //Альтернативный вариант работы с консолью через актуальный класс CommandLine
        //java -cp "james-server-jpa-app.lib/*" org.apache.james.cli.ServerCmd ListUsers
//        CommandLine cmd = new CommandLine("java");
//        cmd.addArgument("-cp");
//        cmd.addArgument("\"james-server-jpa-app.lib/*\"");
//        cmd.addArgument("org.apache.james.cli.ServerCmd");
//        cmd.addArgument("AddUser");
//        cmd.addArgument(email);
//        cmd.addArgument(password);
//        DefaultExecutor executor = DefaultExecutor.builder().get();
//        executor.setWorkingDirectory(new File(manager.property("james.workingDir")));
//        try {
//            int exitValue = executor.execute(cmd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //
        org.openqa.selenium.os.CommandLine cmd = new org.openqa.selenium.os.CommandLine(
                "java",
                "-cp",
                "\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd",
                "AddUser",
                email,
                password
        );
        cmd.setWorkingDirectory(manager.property("james.workingDir"));
        CircularOutputStream out = new CircularOutputStream();
        cmd.copyOutputTo(out);
        cmd.execute();
        cmd.waitFor();
        System.out.println(out);
        //
    }
}
