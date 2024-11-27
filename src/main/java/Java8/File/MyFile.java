package Java8.File;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MyFile {
    /*
        1. File 类的一个对象，代表一个文件或者文件夹
        2. File 声明在 java.io 包下

        本文件绝对路径 /Users/kanangao/Java/src/main/java/Java8/File/
        相对路径：src/main/java/Java8/File/
     */

    private static final String RELATIVE_PATH = "src/main/java/Java8/File/";

    public static void main(String[] args) throws IOException {
        /*
            注意在main方法中，默认路径是相对于整个工程
            如果在测试方法中，路径是相对于当前模块

            根据这个工作路径决定的
            System.out.println(System.getProperty("user.dir"));
         */
        System.out.println(System.getProperty("user.dir"));
        File file = new File("hello.txt");
        if(!file.exists()){
            file.createNewFile();

        }
        System.out.println(file.getAbsolutePath());
        new MyFile().t();

    }

    public void t(){
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());

    }

    @Test
    public void test0(){
        /*
            创建File实例
            一个表示文件或目录路径的 File 对象。需要注意的是，File 构造器不会直接创建文件或目录本身，
            它只是一个路径的抽象表示，提供了一组操作文件和目录的接口
            Constructor
                File(String filePath) 带后缀是文件，不带后缀是文件夹
                File(String parentPath, String childPath)
                File(File parentFile, String childPath)

            相对路径：相对于当前工程
            绝对路径：包含盘符在内的文件或文件夹的路径

            路径分隔符可以获取一个常量：File.separator，用来在不同系统上使用相同的路径分隔符
         */
        File file1 = new File("hello.txt"); // 相对路径
        System.out.println(file1);

        File file2 = new File("File/", "Folder"); // 相对路径
        System.out.println(file2);

        File file3 = new File(file2, "hi.txt"); // 相对路径
        System.out.println(file3);
    }

    @Test
    public void test1(){
        /*
            File 类的获取功能
            public String getAbsolutePath()：获取绝对路径
            public String getPath()：获取路径
            public String getName()：获取名称
            public String getParent()：获取上层文件目录路径。若无，返回 null
            public long length()：获取文件长度（字节数）。不能获取目录的长度
            public long lastModified()：获取最后一次的修改时间，毫秒值

            如下的两个方法适用于文件目录
            public String[] list()：获取指定目录下的所有文件或者文件目录的名称数组
            public File[] listFiles()：获取指定目录下的所有文件或者文件目录的 File 数组
         */
        File file1 = new File(RELATIVE_PATH + "hello.txt");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));
        System.out.println("******************************");

        File file = new File("src/main/java/Java8");
        String[] list = file.list();
        for(String l: list){
            System.out.println(l);
        }
        System.out.println("******************************");
        File[] files = file.listFiles();
        for(File f: files){
            System.out.println(f);
        }
    }

    @Test
    public void test2(){
        /*
            File 类的重命名功能
            public boolean renameTo(File dest):
                1. 用于将文件或目录重命名
                2. 或移动到新的路径
                    比如：file1.renameTo(file2) 为例：
                    要想保证返回 true，需要 file1 在硬盘中是存在的，且 file2 不能在硬盘中存在
                3. File类并未涉及文件的写入或读取，必须使用IO流来完成
                4. File对象常会作为参数传入到IO流的构造器中
         */
        File file1 = new File(RELATIVE_PATH + "hello.txt");
        File file2 = new File(RELATIVE_PATH + "hello2.txt");
//        File file2 = new File( "src/main/java/Java8/hello2.txt");
        boolean b = file2.renameTo(file1);
        System.out.println(b);
    }

    @Test
    public void test3(){
        /*
            File 类的判断功能
            public boolean isDirectory()：判断是否是文件目录
            public boolean isFile()：判断是否是文件
            public boolean exists()：判断是否存在
            public boolean canRead()：判断是否可读
            public boolean canWrite()：判断是否可写
            public boolean isHidden()：判断是否隐藏
         */
        File file1 = new File(RELATIVE_PATH + "files");
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());
    }

    @Test
    public void test4(){
        /*
            File 类的创建功能
            public boolean createNewFile()：创建文件。若文件存在，则不创建，返回 false
            public boolean mkdir()：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建
            public boolean mkdirs()：创建文件目录。如果上层文件目录不存在，一并创建
            注意事项：如果你创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下

            删除功能
            public boolean delete()：删除文件或者文件夹, 无法直接删除非空文件夹。
            如果你尝试删除一个包含文件或子目录的文件夹，它会失败并返回 false

            删除注意事项：Java 中的删除不走回收站
         */
        File file1 = new File(RELATIVE_PATH + "hi.txt");
        if(!file1.exists()) {
            try {
                boolean newFile = file1.createNewFile();
                if (newFile) {
                    System.out.println("hi.txt 创建成功");
                }
            } catch (Exception e) {
                System.out.println("创建失败");
            }
        }else{
            boolean delete = file1.delete();
            if(delete){
                System.out.println("hi.txt 删除成功");
            }
        }

        File file2 = new File(RELATIVE_PATH + "files");
        if(!file2.exists()) {
            boolean mkdir = file2.mkdir();
            if (mkdir) {
                System.out.println("files 创建成功");
            }
        }else{
            boolean delete = file2.delete();
            if(delete){
                System.out.println("files 删除成功");
            }
        }

        // 如果上层文件目录不存在，一并创建
        File file3 = new File(RELATIVE_PATH + "tests/files3");
        if(!file3.exists()) {
            boolean mkdirs = file3.mkdirs();
            if (mkdirs) {
                System.out.println("tests/files3 创建成功");
            }
        }else{
            boolean delete = file3.delete();
            if(delete){
                System.out.println("tests/files3 删除成功");
            }
        }

    }

    // 递归删除目录中所有的子文件和子目录，最后删除目标目录本身
    public static boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles(); // 列出所有子文件和子目录
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file); // 递归删除子文件或子目录
                }
            }
        }
        // 删除文件或空目录
        return directory.delete();
    }
}
