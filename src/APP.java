
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;




public class APP {
    static File currentDir = new File("");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean flag=false;
        Handler handler=new Handler(new Method() ,new Operation());
        while (!flag){
            System.out.print(currentDir.getCanonicalPath()+"> ");
            String command;
            command=scanner.nextLine();
            switch (command){
                case"help":
                    System.out.print("pwd \tPrints the working directory \n" +
                            "cd \tChanges the current directory \n" +
                            "ls \tlist the content of current director \n" +
                            "ls [-r] reverse order\n" +
                            "ls [-a] display all contents even entries starting with \n" +
                            "mkdir \tmake new directory \n" +
                            "rmdir \tremove the directory \n" +
                            "touch \tCreates a file with each given name \n" +
                            "cat \tConcatenates the content of the files and prints it \n" +
                            "mv \tmove one or more directory \n" +
                            "rm \tRemoves each given file \n" +
                            "> \tRedirects the output of the first command to be written to a file \n" +
                            ">> \tit append to the file");
                    break;
                case "exit":
                    flag=true;
                    break;
                default:
                    try{
                        handler.execute(command);
                    }
                    catch(Exception err){
                        System.err.println(err.getMessage() + "\n");
                    }
                    break;
            }
        }
    }
}
