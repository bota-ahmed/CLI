import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class APPTest {
@Test
    void makeDirectoryTest() {
    Method testing= new Method();
    File file= new File(APP.currentDir.getAbsolutePath()+"/test2");
    testing.makeDiractory("test2");
    Assertions.assertTrue(file.exists());
}
@Test
    void removeDirectoryTest(){
    Method testing =new Method();
    File file=new File(APP.currentDir.getAbsoluteFile()+"/test2");
    testing.removeDiractory("test2");
    Assertions.assertFalse(file.exists());
}
@Test
    void catTest() throws IOException,Exception {
    Method testing =new Method();
    Assertions.assertEquals("yarab yarab",testing.concatenateFiles("test1.txt"));
}
}