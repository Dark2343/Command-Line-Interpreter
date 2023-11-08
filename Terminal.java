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
    
    // Creates a new directory with default start at the current user's folder 
    static File directory = new File(System.getProperty("user.dir"));
    
    // Saves every input the user has entered to show later
    static ArrayList<String> commandHistory = new ArrayList<String>();

    // echo takes 1 or more arguments and prints them to the screen.
    public void echo()
    {
        // Turns the arg array into a string and puts spaces between them
        String args = String.join(" ", parser.getArgs());

        // Checks if the user would like to save the file
        // 0 = no, 1 = overwrite, 2 = append
        if (parser.saveFile() == 0) {
            System.out.println(args);
        }
        else if (parser.saveFile() == 1) {
            try{
                // Creates a new file with the name the user entered
                File textFile = new File(parser.getFileName());
                
                // Checks if the file exists
                fileCheck(textFile);

                // Writes the string to the file
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
                // Creates a new file with the name the user entered
                File textFile = new File(parser.getFileName());

                // Checks if the file exists and sets the FileWriter to append
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

    
    // pwd takes no arguments and prints the current path.
    public void pwd()
    {
        if (parser.saveFile() == 0) {
            // Prints the current path
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

    // cd takes 1 argument which is either the full path or the relative (short) path and changes the current directory to the given path.
    // cd .. changes the current directory to the parent directory.
    // cd with no arguments changes the current directory to the user's home directory.
    // If the path is invalid, the program should print some error messages without terminating.
    public void cd()
    {
        // Case 1
        if (parser.getArgs().length == 0)
        {
            // Changes the directory to the user's home directory
            directory = new File(System.getProperty("user.home"));
            System.out.println("Current path: " + directory.getAbsolutePath());
            System.out.println();
        }    
        // Case 2
        else if (parser.getArgs()[0].equals(".."))
        {
            // Changes the directory to the parent directory
            directory = new File(directory.getParent());
            System.out.println("Current path: " + directory.getAbsolutePath());
            System.out.println();
        }
        // Case 3
        else
        {
            // Turns the arg array into a string and puts spaces between them
            String args = String.join(" ", parser.getArgs());
            
            try {
                // Changes the directory to the given path, the path is relative to the current directory
                Path path = Paths.get(directory.getAbsolutePath());
                Path argPath = path.resolve(args);
                
                // Checks if the path exists
                if (argPath.toFile().exists()) 
                {
                    // Changes the directory to the given path
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
    
    // ls takes 0 or 1 argument. If it takes no arguments, it prints the contents of the current directory. 
    // If it takes 1 argument, it must be -r and it prints the contents of the current directory in reverse order.
    public void ls()
    {
        // Gets the contents of the current directory
        File[] contents = directory.listFiles();
        
        if (parser.saveFile() == 0) {
            
            if (parser.getArgs().length == 0) 
            {
                // Prints the contents of the current directory
                for(File e : contents)
                {
                    // Checks if the file is a file or a directory
                    if (e.isFile()) {
                        System.out.println("<FILE> " + e.getName());
                    }
                    else if (e.isDirectory()) {
                        System.out.println("<DIR> " + e.getName());
                    }
                }
                System.out.println();
            }
            // Prints the contents of the current directory in reverse order
            else if (parser.getArgs()[0].equals("-r"))
            {
                // Prints the contents of the current directory in reverse order
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
        // Saves the output to a file
        else if (parser.saveFile() == 1) {
            try{
                File textFile = new File(parser.getFileName());
                fileCheck(textFile);

                FileWriter text = new FileWriter(textFile);
                String content = "";
                
                // Prints the contents of the current directory
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
        // Appends the output to a file
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

    // Takes 1 or more arguments and creates a directory for each argument. Each argument can be:
    // Directory name (in this case the new directory is created in the current directory)
    // Path (full/short) that ends with a directory name (in this case the new directory is created in the given path)
    public void mkdir()
    {
        // Gets the folder name from the arguments
        String[] args = parser.getArgs();
        // Creates the folder
        Path path = Paths.get(directory.getAbsolutePath());
        // Creates the folder, if it already exists, it prints an error message
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
    
    // Implement all these cases:
    // 1. rmdir takes 1 argument which is "*" (e.g. rmdir *) and removes all the empty directories in the current directory.
    // 2. rmdir takes 1 argument which is either the full path or the relative (short) path and removes the given directory only if it is empty.
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
            // Gets the contents of the current directory
            File[] contents = directory.listFiles();

            // Deletes all the empty directories in the current directory
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
            // Gets the folder name from the arguments
            Path path = Paths.get(directory.getAbsolutePath());
            Path argPath = path.resolve(args[0]);
            File folder = argPath.toFile();

            // Deletes the folder if it is empty
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

    // Takes 1 argument which is either the full path or the relative (short) path that ends with a file name and creates this file.
    public void touch()
    {
        // Gets the file name from the arguments
        String[] args = parser.getArgs();
        Path path = Paths.get(directory.getAbsolutePath());
        Path argPath = path.resolve(args[0]);
        File f = argPath.toFile();
        // Creates the file, if it already exists, it prints an error message
        if (f.exists()){
            System.out.println(args[0] + " file already exists.");
        } 
        else {
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

    // Takes 2 arguments, both are files and copies the first onto the second.
    public void cp()
    {
        String[] args = parser.getArgs();

        // Checks if the user entered more than 2 arguments
        if (args.length > 2){
            System.out.println("cp only accepts 2 arguments");
            return;
        }
        // Gets the file name from the arguments
        Path path = Paths.get(directory.getAbsolutePath());
        Path argPath = path.resolve(args[0]);
        File source = argPath.toFile();
        Path argPath2 = path.resolve(args[1]);
        File dest = argPath2.toFile();

        // Copies the file
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

    // Takes 1 argument which is a file name that exists in the current directory and removes this file.
    public void rm()
    {
        // Gets the file name from the arguments
        String[] args = parser.getArgs();
        if (args.length > 1){
            System.out.println("rm only accepts 1 argument");
            return;
        }
        // Gets the file name from the arguments
        Path path = Paths.get(directory.getAbsolutePath());
        Path argPath = path.resolve(args[0]);
        File file = argPath.toFile();
        
        // Deletes the file if it exists
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
    
    // Takes 1 argument and prints the file's content or takes 2 arguments and concatenates the content of the 2 files and prints it.
    public void cat()
    {
        // 1 File
        if (parser.getArgs().length == 1) 
        {
            try {
                // Gets the file name from the arguments
                Path path = Paths.get(directory.getAbsolutePath());
                Path argPath = path.resolve(parser.getArgs()[0]);
                File file = argPath.toFile();
                Scanner reader = new Scanner(file);
                // Prints the file's content
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
                // Gets the file name from the arguments
                Path path1 = Paths.get(directory.getAbsolutePath());
                Path argPath1 = path1.resolve(parser.getArgs()[0]);
                Path path2 = Paths.get(directory.getAbsolutePath());
                Path argPath2 = path2.resolve(parser.getArgs()[1]);
                File file1 = argPath1.toFile();
                File file2 = argPath2.toFile();
                Scanner reader1 = new Scanner(file1);
                Scanner reader2 = new Scanner(file2);
                // Prints the first file's content
                while (reader1.hasNextLine()) {
                    String line = reader1.nextLine();
                    System.out.println(line);
                }
                reader1.close();
                // Prints the second file's content
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
    
    // Displays four-columnar output. 
    // 1st column shows number of lines present in a file specified, 
    // 2nd column shows number of words present in the file, 
    // 3rd column shows number of characters present in file,
    // 4th column itself is the file name which are given as argument 
    public void wc()
    {
        try {
            // Gets the file name from the arguments
            int lineNum = 0, wordNum = 0, characterNum = 0;
            File file = new File(parser.getArgs()[0]);
            Scanner reader = new Scanner(file);
            
            // Counts the lines, words, and characters
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

            // Checks if the user would like to save the file
            if (parser.saveFile() == 0) {
                System.out.println(lineNum + " " + wordNum + " " + characterNum + " " + parser.getArgs()[0]);
            }
            // Saves the output to a file
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
            // Appends the output to a file
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

    // Takes no parameters and displays an enumerated list with the commands you've used in the current session.
    public void history()
    {
        // Checks if the user would like to save the file
        if (parser.saveFile() == 0) {
            // Prints the command history
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
        // Saves the output to a file
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
        // Appends the output to a file
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

    // Quits the program
    public void exit()
    {
        System.exit(0);
    }

    //This method will choose the suitable command method to be called 
    public void chooseCommandAction()
    {
        // Gets the command name from the parser
        String command = parser.getCommandName();
        try {
            // Calls the method with the same name as the command
            Method meth = this.getClass().getMethod(command);
            meth.invoke(this);
            
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            System.out.println();
        }
    }

    // Takes the user input and parses it then calls the chosen command
    public void execute()
    {
        System.out.print("TERMINAL>");
        String input = scanner.nextLine();
        commandHistory.add(input);
        parser.parse(input);
        chooseCommandAction();
    }

    // Checks if the file exists
    public void fileCheck(File textFile){
        try{
            if (textFile.createNewFile()) {
                System.out.println("New file " + parser.getFileName() + " created with given input");
                System.out.println();
            }
            else{
                System.out.println(parser.getFileName() + " successfully overwritten");
                System.out.println();
            }
        }
        catch (Exception e){
            System.out.println("A file error has occurred");
            System.out.println();
        }
    }
}