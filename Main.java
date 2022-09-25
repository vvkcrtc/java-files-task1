import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static String[] rootDirs = {"src", "res", "savegames", "temp"};
    static String[] srcDirs = {"main", "test"};
    static String[] resDirs = {"drawables", "vectors", "icons"};
    static String[] mainFiles = {"Main.java", "Utils.java"};
    static StringBuilder log = new StringBuilder();

    static void createDir(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            putLog("Каталог : " + path + " уже существует ");
        } else if (dir.mkdir()) {
            putLog("Каталог : " + path + " создан");
        } else {
            putLog("Не возможно создать каталог : " + path);
        }
    }

    static void createFile(String pathName) {
        File myFile = new File(pathName);
        if (myFile.isFile()) {
            putLog("Файл : " + pathName + " уже  существует ");
        } else {

            try {
                if (myFile.createNewFile()) {
                    putLog("Файл : " + pathName + " создан");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                putLog(ex.getMessage());
            }
        }
    }

    static void putLog(String msg) {
        log.append(msg + "\n");
    }

    static void saveLog(String pathName, StringBuilder sb) {
        char[] text;
        try (FileWriter writer = new FileWriter(pathName, false)) {
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        String rootPath = "/home/vvk/Games";
        String tempPath = "";
        File rootDir = new File(rootPath);
        if (rootDir.isDirectory()) {
            for (String dir : rootDirs) {
                createDir(rootPath + '/' + dir);
                if (dir.equals(rootDirs[0])) {
                    for (String srcDir : srcDirs) {
                        createDir(rootPath + '/' + dir + '/' + srcDir);

                    }
                }
                if (dir.equals(rootDirs[1])) {
                    for (String resDir : resDirs) {
                        createDir(rootPath + '/' + dir + '/' + resDir);
                    }
                }
                if (dir.equals(rootDirs[2])) {
                    for (String srcDir : srcDirs) {
                        createDir(rootPath + '/' + dir + '/' + srcDir);
                        if (srcDir.equals(srcDirs[0])) {
                            for (String fileName : mainFiles) {
                                createFile(rootPath + '/' + dir + '/' + srcDir + '/' + fileName);
                            }
                        }
                    }
                }
                if (dir.equals(rootDirs[3])) {
                    tempPath = rootPath + '/' + dir + "/temp.txt";
                    createFile(tempPath);
                }

            }
        } else {
            putLog("Каталога " + rootPath + " не существует ");
        }
        System.out.println(log);
        saveLog(tempPath, log);
    }
}
