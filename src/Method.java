import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Method implements IMethod {
    Exception e;
    public void makeDiractory(String name)  {
        File newFile = new File(APP.currentDir.getAbsoluteFile()+ "/"+name);
        newFile.mkdir();
    }
    public void removeDiractory(String name){
        File removeFile = new File(APP.currentDir.getAbsoluteFile()+"/"+name);
        if(!removeFile.exists()) {
            System.out.print("directory not found");
        }
        else if(removeFile.list().length != 0){
            System.out.println("directory have children");
        }
        else
            removeFile.delete();
    }
    public String concatenateFiles (String src) throws Exception {
        boolean append = false;
        if(src.charAt(0)=='>'){

            if(src.charAt(1)=='>'){
                src = src.substring(2,src.length());
                append = true;
            }

            else
                src = src.substring(1,src.length());

            File myFile = new File(APP.currentDir.getAbsolutePath() + "/" + src);

            if(!myFile.exists())
                if(!myFile.createNewFile())
                    throw new Exception("error creating file");

            String input = "";

            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);

            input = scanner.nextLine();

            if(!myFile.canWrite())
                throw new Exception("file can not be modified");

            FileWriter fileWriter = new FileWriter(myFile.getAbsoluteFile(),append);
            fileWriter.write("\n" + input);
            fileWriter.close();
            return "written succefully";

        }
        else{
            String result = "";
            BufferedReader reader = new BufferedReader(new FileReader(src));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();
            return result;
        }
    }

    }

