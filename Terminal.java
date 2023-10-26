import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

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
        System.out.println(args);
    }

    // MOHAMED
    
    /**
     * Takes no arguments and prints the current path.
     */
    public void pwd()
    {
        System.out.println("Current path: " + directory.getAbsolutePath());
        System.out.println();
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
                directory = new File(args);
                System.out.println("Current path: " + directory.getAbsolutePath());
                System.out.println();
            } catch (Exception e) {
                System.out.println("Invalid path");
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

    // ZIAD
    /**
     * Takes 1 or more arguments and creates a directory for each argument. Each argument can be:
     * Directory name (in this case the new directory is created in the current directory)
     * Path (full/short) that ends with a directory name (in this case the new directory is created in the given path)
     */
    public void mkdir()
    {
        String[] args = parser.getArgs();
        for (String arg : args){
            File f = new File(arg);
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
        //https://stackoverflow.com/questions/20281835/how-to-delete-a-folder-with-files-using-java
        String[] args = parser.getArgs();
        if (args[0].equals("*")){
            //Delete all
        }
        else {
            File folder = new File(args[0]);
            if (folder.exists()){
                try {
                    if (folder.delete()){
                        System.out.println("Deleted folder " + args[0]);
                    } else {
                        System.out.println("Cannot delete folder " + args[0]);

                    }
                }
                catch (Exception e){
                    System.out.println("Cannot remove dir. Reason:" + e.getMessage());
                }
            }
        }
    }
    
    // ZIAD
    public void touch()
    {
        /*
            Takes  1 argument  which  is  either  the  full path  or  the 
            relative (short)  path  that  ends  with  a  file  name  and  creates 
            this file.
            */
    }
    
    // ZIAD
    public void cp()
    {
        //
        // Takes  2  arguments,  both  are  files  and  copies  the  first  onto  the second
        // Make a case for cp - r
    }

    // ZIAD
    public void rm()
    {
        // https://www.w3schools.com/java/java_files_delete.asp
        // Takes  1  argument  which  is  a  file  name  that  exists  in  the  current directory and removes this file.
        
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
                File file = new File(parser.getArgs()[0]);
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    System.out.println(line);
                }
                reader.close();
            } catch (Exception e) {
                System.out.println("File does not exist");
            }
        }
        
        // 2 Files
        else if (parser.getArgs().length == 2) 
        {
            try {
                File file1 = new File(parser.getArgs()[0]);
                File file2 = new File(parser.getArgs()[1]);
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

            System.out.println(lineNum + " " + wordNum + " " + characterNum + " " + parser.getArgs()[0]);
        } catch (Exception e) {
            System.out.println("File does not exist");
        }
    }

/*     // MIGHT NOT IMPLEMENT
    public void command1()
    {
        
        Format: command  > FileName 
        Redirects the output of the first command to be written to a file. If the 
        file doesn't exist, it will be created. 
        If the file exists, its original content will be replaced. 
        Example: echo Hello World > myFile.txt 
        ls > file 
    
    }

    // MIGHT NOT IMPLEMENT
    public void command2()
    {
        // like command 1 but appends to the file if exists
    }
*/

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
        if (commandHistory.size() != 0)
        {
            for(int i = 0; i < commandHistory.size(); i++)
            {
                System.out.println((i + 1) + "   " + commandHistory.get(i));
            }
        }
        else
        {
            System.out.println("No commands were inputted yet");
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
}

class Parser { 

    String commandName; 
    String[] args;
        
    public void parse(String input)
    {
        String[] tokens = input.split(" ");
        commandName = tokens[0];
        args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, tokens.length - 1);
    }
    
    public String getCommandName()
    {
        return commandName;
    }
    
    public String[] getArgs()
    {
        return args;
    } 
}