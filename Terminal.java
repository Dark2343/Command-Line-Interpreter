import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;



public class Terminal{
    
    static Parser parser = new Parser();
    static Scanner scanner = new Scanner(System.in);
    static File directory = new File(System.getProperty("user.dir"));
    static ArrayList<String> commandHistory = new ArrayList<String>();

    // MOHAMED
    /**
     * Takes 1 argument and prints it.
     */
    public void echo()
    {
        String args = String.join(" ", parser.getArgs());
        if (parser.saveFile() == 0) {
            System.out.println(args);
        }
        else if (parser.saveFile() == 1) {
            try{
                File textFile = new File(parser.getFileName());
                fileCheck(textFile);

                FileWriter text = new FileWriter(textFile);
                String content = String.join(" ", parser.args);
                text.write(content + "\n");
                text.close();
            }
            catch (Exception e){
                System.out.println("An error has occurred");
                System.out.println();
            }
        }
        else if (parser.saveFile() == 2) {
            try{
                File textFile = new File(parser.getFileName());
                FileWriter text = new FileWriter(textFile, true);
                String content = String.join(" ", parser.args);
                text.write(content + "\n");
                text.close();
                System.out.println("Input appended to " + parser.getFileName());
                System.out.println();
            }
            catch (Exception e){
                System.out.println("An error has occurred");
                System.out.println();
            }
        }
    }

    // MOHAMED
    
    /**
     * Takes no arguments and prints the current path.
     */
    public void pwd()
    {
        if (parser.saveFile() == 0) {
            System.out.println("Current path: " + directory.getAbsolutePath());
            System.out.println();
        }
        else if (parser.saveFile() == 1) {
            try{
                File textFile = new File(parser.getFileName());
                fileCheck(textFile);

                FileWriter text = new FileWriter(textFile);
                String content = directory.getAbsolutePath();
                text.write(content + "\n");
                text.close();
            }
            catch (Exception e){
                System.out.println("An error has occurred");
                System.out.println();
            }
        }
        else if (parser.saveFile() == 2) {
            try{
                File textFile = new File(parser.getFileName());
                FileWriter text = new FileWriter(textFile, true);
                String content = directory.getAbsolutePath();
                text.write(content + "\n");
                text.close();
                System.out.println("Input appended to " + parser.getFileName());
                System.out.println();
            }
            catch (Exception e){
                System.out.println("An error has occurred");
                System.out.println();
            }
        }
    }

    // MOHAMED
    /**
     * Implement all these cases: 
     * 1. cd takes no arguments and changes the current path to the path of your home directory. 
     * 2. cd takes 1 argument which is ".." (e.g. cd ..) and changes the current directory to the previous directory. 
     * 3. cd  takes  1  argument  which  is  either  the  full  path  or  the relative (short) path and changes the current path to that path.
     */
    public void cd()
    {
        // Case 1
        if (parser.getArgs().length == 0)
        {
            directory = new File(System.getProperty("user.home"));
            System.out.println("Current path: " + directory.getAbsolutePath());
            System.out.println();
        }
        
        // Case 2
        else if (parser.getArgs()[0].equals(".."))
        {
            directory = new File(directory.getParent());
            System.out.println("Current path: " + directory.getAbsolutePath());
            System.out.println();
        }
        
        // Case 3
        else
        {
            String args = String.join(" ", parser.getArgs());
            
            try {
                Path path = Paths.get(directory.getAbsolutePath());
                Path argPath = path.resolve(args);
                
                if (argPath.toFile().exists()) 
                {
                    directory = new File(argPath.toString());
                    System.out.println("Current path: " + directory.getAbsolutePath());
                    System.out.println();
                }
                else
                {
                    Exception e = new Exception();
                    throw e;
                }
            } catch (Exception e) {
                System.out.println("Invalid path");
                System.out.println();
            }
        }
    }
    
    // MOHAMED
    /**
     * Takes  no  arguments  and  lists  the  contents  of  the  current  directory sorted alphabetically. (Reverse for ls -r)
     */
    public void ls()
    {
        File[] contents = directory.listFiles();
        
        if (parser.saveFile() == 0) {
            
            if (parser.getArgs().length == 0) 
            {
                for(File e : contents)
                {
                    if (e.isFile()) {
                        System.out.println("<FILE> " + e.getName());
                    }
                    else if (e.isDirectory()) {
                        System.out.println("<DIR> " + e.getName());
                    }
                }
                System.out.println();
            }
        
            else if (parser.getArgs()[0].equals("-r"))
            {
                for(int i = contents.length - 1; i >= 0; i--)
                {
                    File e = contents[i];
                    
                    if (e.isFile()) {
                        System.out.println("<FILE> " + e.getName());
                    }
                    else if (e.isDirectory()) {
                        System.out.println("<DIR> " + e.getName());
                    }
                }
                System.out.println();
            }
        
            else
            {
                System.out.println("This command takes no arguments");
                System.out.println();
            }
        }
        else if (parser.saveFile() == 1) {
            try{
                File textFile = new File(parser.getFileName());
                fileCheck(textFile);

                FileWriter text = new FileWriter(textFile);
                String content = "";
                
                if (parser.getArgs().length == 0) 
                {
                    for(File e : contents)
                    {
                        if (e.isFile()) {
                            content = "<FILE> " + e.getName();
                        }
                        else if (e.isDirectory()) {
                            content = "<DIR> " + e.getName();
                        }
                        text.write(content + "\n");
                    }
                }
                
                else if (parser.getArgs()[0].equals("-r"))
                {
                    for(int i = contents.length - 1; i >= 0; i--)
                    {
                        File e = contents[i];
                        
                        if (e.isFile()) {
                            content = "<FILE> " + e.getName();
                        }
                        else if (e.isDirectory()) {
                            content = "<DIR> " + e.getName();
                        }
                        text.write(content + "\n");
                    }
                }
            
                else
                {
                    System.out.println("This command takes no arguments");
                    System.out.println();
                }
                text.close();
            }
            catch (Exception e){
                System.out.println("An error has occurred");
                System.out.println();
            }
        }
        else if (parser.saveFile() == 2) {
            try{
                File textFile = new File(parser.getFileName());
                FileWriter text = new FileWriter(textFile, true);
                String content = "";
                
                if (parser.getArgs().length == 0) 
                {
                    for(File e : contents)
                    {
                        if (e.isFile()) {
                            content = "<FILE> " + e.getName();
                        }
                        else if (e.isDirectory()) {
                            content = "<DIR> " + e.getName();
                        }
                        text.write(content + "\n");
                    }
                }
                
                else if (parser.getArgs()[0].equals("-r"))
                {
                    for(int i = contents.length - 1; i >= 0; i--)
                    {
                        File e = contents[i];
                        
                        if (e.isFile()) {
                            content = "<FILE> " + e.getName();
                        }
                        else if (e.isDirectory()) {
                            content = "<DIR> " + e.getName();
                        }
                        text.write(content + "\n");
                    }
                }
            
                else
                {
                    System.out.println("This command takes no arguments");
                    System.out.println();
                }
                text.close();
                System.out.println("Input appended to " + parser.getFileName());
                System.out.println();
            }
            catch (Exception e){
                System.out.println("An error has occurred");
                System.out.println();
            }
        }

    }

    // ZIAD
    /**
     * Takes 1 or more arguments and creates a directory for each argument. Each argument can be:
     * Directory name (in this case the new directory is created in the current directory)
     * Path (full/short) that ends with a directory name (in this case the new directory is created in the given path)
     */
    public void mkdir()
    {
        String[] args = parser.getArgs();
        Path path = Paths.get(directory.getAbsolutePath());
        for (String arg : args){
            Path argPath = path.resolve(arg);
            File f = argPath.toFile();
            if (f.exists()){
                System.out.println(arg + " directory already exists.");
            } else if (f.mkdir()){
                System.out.println(arg + " directory created successfully.");
            } else {
                System.out.println(arg + " directory cannot be created.");
            }
        }
    }

    // ZIAD
    /**
     *  Implement all these cases:
     *  1.  rmdir  takes  1  argument  which  is  "*"  (e.g.  rmdir  *)  and
     *  removes all the empty directories  in the current directory.
     *  2. rmdir takes 1 argument which is either the full path or the
     *  relative (short) path and removes the given directory only if
     *  it is empty.
     */
    public void rmdir()
    {
        // Gets the folder name from the arguments
        String[] args = parser.getArgs();
        if (args.length > 1){
            System.out.println("rmdir only accepts 1 argument");
            return;
        }

        // Case 1: rmdir *
        if (args[0].equals("*")){
            File[] contents = directory.listFiles();

            if (contents != null){
                for (File file : contents) {
                    if (file.isDirectory()){
                        String folderName = file.getName();
                        if (file.delete()){
                            System.out.println("Deleted folder " + folderName);
                        } else {
                            System.out.println("Cannot delete non-empty folder " + folderName);

                        }
                    }
                }
            }

        }
        // Case 2: rmdir <folder>
        else {
            Path path = Paths.get(directory.getAbsolutePath());
            Path argPath = path.resolve(args[0]);
            File folder = argPath.toFile();

            String folderName = folder.getName();
            if (folder.exists() && folder.isDirectory()) {
                try {
                    if (folder.delete()){
                        System.out.println("Deleted folder " + folderName);
                    } else {
                        System.out.println("Cannot delete non-empty folder " + folderName);

                    }
                }
                catch (Exception e){
                    System.out.println("Cannot remove dir. Reason:" + e.getMessage());
                }
            }
            else {
                System.out.println(folderName + " folder doesn't exist");
            }


        }
    }
    
    // ZIAD
    /**
     * Takes  1 argument  which  is  either  the  full path  or  the
     * relative (short)  path  that  ends  with  a  file  name  and  creates this file.
     */
    public void touch()
    {
        String[] args = parser.getArgs();
        Path path = Paths.get(directory.getAbsolutePath());
        Path argPath = path.resolve(args[0]);
        File f = argPath.toFile();
        if (f.exists()){
            System.out.println(args[0] + " file already exists.");
        } else {
            try {
                if (f.createNewFile()){
                    System.out.println(args[0] + " file created successfully.");
                } else {
                    System.out.println(args[0] + " file cannot be created.");
                }
            } catch (java.io.IOException e) {
                System.out.println("An error occurred.");
            }
        }
    }
    
    // ZIAD

    /**
     * Takes  2  arguments,  both  are  files  and  copies  the  first  onto  the second
     * add -r option to copy directories
     */
    public void cp()
    {
        String[] args = parser.getArgs();
        if (args[0].equals("-r")){
            if (args.length > 3){
                System.out.println("cp -r only accepts 2 arguments");
                return;
            }

            Path path = Paths.get(directory.getAbsolutePath());
            Path argPath = path.resolve(args[1]);
            File source = argPath.toFile();
            Path argPath2 = path.resolve(args[2]);
            File dest = argPath2.toFile();
            if (source.exists() && source.isDirectory()){
                File[] contents = source.listFiles();
                if (contents != null){
                    // TODO: copy contents recursively
                }
            }
            else {
                System.out.println(source.getName() + " is not a directory");
            }
        }
        else {
            if (args.length > 2){
                System.out.println("cp only accepts 2 arguments");
                return;
            }
            Path path = Paths.get(directory.getAbsolutePath());
            Path argPath = path.resolve(args[0]);
            File source = argPath.toFile();
            Path argPath2 = path.resolve(args[1]);
            File dest = argPath2.toFile();

            if (source.exists() && source.isFile()){
                try {
                    Files.copy(source.toPath(), dest.toPath());
                    System.out.println("Copied " + source.getName() + " to " + dest.getName());
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
            }
            else {
                System.out.println(source.getName() + " file doesn't exist");
            }
        }
    }

    // ZIAD

    /**
     * Takes  1  argument  which  is  a  file  name  that  exists  in  the  current directory and removes this file.
     */
    public void rm()
    {
        // Gets the file name from the arguments
        String[] args = parser.getArgs();
        if (args.length > 1){
            System.out.println("rm only accepts 1 argument");
            return;
        }

        Path path = Paths.get(directory.getAbsolutePath());
        Path argPath = path.resolve(args[0]);
        File file = argPath.toFile();
         if (file.exists() && file.isFile()){
            try {
                if (file.delete()){
                    System.out.println("Deleted file " + file.getName());
                } else {
                    System.out.println("Cannot delete file " + file.getName());

                }
            }
            catch (Exception e){
                System.out.println("Cannot remove file. Reason:" + e.getMessage());
            }
         } else {
             System.out.println(file.getName() + " file doesn't exist");
         }

    }
    
    // MOHAMED
    /**
     * Takes 1 argument and prints the file's content or takes 2 arguments and concatenates the content of the 2 files and prints it.
     */
    public void cat()
    {
        // 1 File
        if (parser.getArgs().length == 1) 
        {
            try {
                Path path = Paths.get(directory.getAbsolutePath());
                Path argPath = path.resolve(parser.getArgs()[0]);
                File file = argPath.toFile();
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    System.out.println(line);
                }
                reader.close();
                System.out.println();
            } catch (Exception e) {
                System.out.println("File does not exist");
            }
        }
        
        // 2 Files
        else if (parser.getArgs().length == 2) 
        {
            try {
                Path path1 = Paths.get(directory.getAbsolutePath());
                Path argPath1 = path1.resolve(parser.getArgs()[0]);
                Path path2 = Paths.get(directory.getAbsolutePath());
                Path argPath2 = path2.resolve(parser.getArgs()[1]);
                File file1 = argPath1.toFile();
                File file2 = argPath2.toFile();
                Scanner reader1 = new Scanner(file1);
                Scanner reader2 = new Scanner(file2);
                while (reader1.hasNextLine()) {
                    String line = reader1.nextLine();
                    System.out.println(line);
                }
                reader1.close();
                
                while (reader2.hasNextLine()) {
                    String line = reader2.nextLine();
                    System.out.println(line);
                }
                reader2.close();
                System.out.println();
            } catch (Exception e) {
                System.out.println("One of the files does not exist");
            }
        }
        
        // More than 2 files
        else if (parser.getArgs().length > 2) 
        {
            System.out.println("1 or 2 files only allowed");
        }

        // No files
        else
        {
            System.out.println("No file specified");
        }
        
    }
    
    // MOHAMED
    /**
     * Wc stands for "word count," and as the name suggests, it is mainly used for counting purpose. By default, it displays four-columnar output. 
     * First column shows number of lines present in a file specified, 
     * second column shows number of words present in the file, 
     * third column shows number of characters present in file,
     * fourth column itself is the file name which are given as argument 
     * Example: wc file.txt 
     * Output: 9 79 483 file.txt 
     * Explanation: 
     * # 9 lines, 79 word, 483 character with spaces, file name
     */
    public void wc()
    {
        try {
            int lineNum = 0, wordNum = 0, characterNum = 0;
            File file = new File(parser.getArgs()[0]);
            Scanner reader = new Scanner(file);
            
            while (reader.hasNextLine()) 
            {
                lineNum++;
                String line = reader.nextLine();
                String[] words = line.split(" ");
                wordNum += words.length;
                for(int i = 0; i < words.length; i++)
                {
                    characterNum += words[i].length();
                }
            }
            reader.close();

            if (parser.saveFile() == 0) {
                System.out.println(lineNum + " " + wordNum + " " + characterNum + " " + parser.getArgs()[0]);
            }
            else if (parser.saveFile() == 1) {
                try{
                    File textFile = new File(parser.getFileName());
                    fileCheck(textFile);

                    FileWriter text = new FileWriter(textFile);
                    String content = lineNum + " " + wordNum + " " + characterNum + " " + parser.getArgs()[0];
                    text.write(content + "\n");
                    text.close();
                }
                catch (Exception e){
                    System.out.println("An error has occurred");
                    System.out.println();
                }
            }
            else if (parser.saveFile() == 2) {
                try{
                    File textFile = new File(parser.getFileName());
                    FileWriter text = new FileWriter(textFile, true);
                    String content = lineNum + " " + wordNum + " " + characterNum + " " + parser.getArgs()[0];
                    text.write(content + "\n");
                    text.close();
                    System.out.println("Input appended to " + parser.getFileName());
                    System.out.println();
                }
                catch (Exception e){
                    System.out.println("An error has occurred");
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println("File does not exist");
        }
    }

    // MOHAMED
    /**
     * Takes no parameters and displays an enumerated list with the commands you've used in the past 
     * Example: history 
     * Output: 
     * 1   ls 
     * 2   mkdir tutorial 
     * 3   history
     */
    public void history()
    {
        if (parser.saveFile() == 0) {
            if (commandHistory.size() != 0)
            {
                for(int i = 0; i < commandHistory.size(); i++)
                {
                    System.out.println((i + 1) + " - " + commandHistory.get(i));
                }
                System.out.println();
            }
            else
            {
                System.out.println("No commands were inputted yet");
                System.out.println();
            }
        }
        else if (parser.saveFile() == 1) {
            if (commandHistory.size() != 0)
            {
                try{
                    File textFile = new File(parser.getFileName());
                    fileCheck(textFile);

                    FileWriter text = new FileWriter(textFile);
                    String content;
                    
                    for(int i = 0; i < commandHistory.size(); i++)
                    {
                        content = (i + 1) + " - " + commandHistory.get(i) + "\n";
                        text.write(content + "\n");
                    }
                    text.close();
                }
                catch (Exception e){
                    System.out.println("An error has occurred");
                    System.out.println();
                }
            }
            else
            {
                System.out.println("No commands were inputted yet");
                System.out.println();
            }
        }
        else if (parser.saveFile() == 2) {
            if (commandHistory.size() != 0)
            {
                try{
                    File textFile = new File(parser.getFileName());
                    FileWriter text = new FileWriter(textFile, true);
                    String content;
                    
                    for(int i = 0; i < commandHistory.size(); i++)
                    {
                        content = (i + 1) + " - " + commandHistory.get(i) + "\n";
                        text.write(content + "\n");
                    }
                    text.close();
                    System.out.println("Input appended to " + parser.getFileName());
                    System.out.println();
                }
                catch (Exception e){
                    System.out.println("An error has occurred");
                    System.out.println();
                }
            }
            else
            {
                System.out.println("No commands were inputted yet");
                System.out.println();
            }
        }
    }

    public void exit()
    {
        // Quits the program
        System.exit(0);
    }

    /*
        If  the  user  enters  a  wrong  command  or  bad  parameters  (invalid  path,  file instead  of  directory  in  certain  commands,  etc.),
        the  program  should  print some error messages without terminating. 
    */

    //This method will choose the suitable command method to be called 
    public void chooseCommandAction()
    {
        
        String command = parser.getCommandName();
        try {
            Method meth = this.getClass().getMethod(command);
            meth.invoke(this);
            
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            System.out.println();
        }
    }

    public void execute()
    {
        System.out.print("TERMINAL>");
        String input = scanner.nextLine();
        commandHistory.add(input);
        parser.parse(input);
        chooseCommandAction();
    }

    public void fileCheck(File textFile){
        try{
            fileCheck(textFile);

        }
        catch (Exception e){
            System.out.println("A file error has occurred");
            System.out.println();
        }
    }
}

class Parser { 

    String commandName; 
    String[] args;
    Boolean arrow = false, doubleArrow = false;
    String fileName;
        
    public void parse(String input)
    {
        String[] tokens = input.split(" ");
        commandName = tokens[0];
        
        if (tokens.length > 2 && tokens[tokens.length - 2].equals(">")) {    
            arrow = true; doubleArrow = false;
            fileName = tokens[tokens.length - 1];
            args = new String[tokens.length - 3];
            System.arraycopy(tokens, 1, args, 0, tokens.length - 3);
        }
        else if (tokens.length > 2 && tokens[tokens.length - 2].equals(">>")) {
            arrow = false; doubleArrow = true;
            fileName = tokens[tokens.length - 1];
            args = new String[tokens.length - 3];
            System.arraycopy(tokens, 1, args, 0, tokens.length - 3);
        }
        else{
            arrow = false; doubleArrow = false;
            args = new String[tokens.length - 1];
            System.arraycopy(tokens, 1, args, 0, tokens.length - 1);
        }
        
    }
    
    public String getCommandName()
    {
        return commandName;
    }
    
    public String[] getArgs()
    {
        return args;
    }

    public String getFileName()
    {
        return fileName;
    }

    public int saveFile()
    {
        if (arrow) {
            return 1;
        }
        else if (doubleArrow)
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }
}