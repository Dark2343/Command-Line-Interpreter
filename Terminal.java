import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;

public class Terminal{
    
    static Parser parser = new Parser();
    static Scanner scanner = new Scanner(System.in);

    // MOHAMED
    public void echo()
    {
        // Takes 1 argument and prints it.
        String args = String.join(" ", parser.getArgs());
        System.out.println(args);
    }

    // MOHAMED
    public void pwd()
    {
        // Takes no arguments and prints the current path.
        System.out.println(System.getProperty("user.dir"));
    }

    // MOHAMED
    public void cd(String[] args)
    {
        /*
        * Implement all these cases: 
            1.  cd takes no arguments and changes the current path to the path 
            of your home directory. 
            2.  cd takes 1 argument which is ".." (e.g. cd ..) and changes the 
            current directory to the previous directory. 
            3.  cd  takes  1  argument  which  is  either  the  full  path  or  the 
            relative (short) path and changes the current path to that path.
         */
    }

    // MOHAMED
    public void ls()
    {
        // Takes  no  arguments  and  lists  the  contents  of  the  current  directory sorted alphabetically.
        // Make a case for ls - r
        File directory = new File(System.getProperty("user.dir"));
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
    public void mkdir()
    {
        String[] args = parser.getArgs();
        for (String arg : args){
            if (new File(arg).mkdirs()){
                System.out.println(arg + " directory created successfully.");
            } else {
                System.out.println(arg + " directory cannot be created.");
            }
        }
        /*
            Takes  1 or more  arguments  and creates  a directory  for each 
            argument. Each argument can be: 
            • Directory  name  (in  this  case  the  new  directory  is  created  in 
            the current directory) 
            Path (full/short) that ends with a directory name (in this case the 
            new directory is created in the given path)
         */
    }

    // ZIAD
    public void rmdir()
    {
        //https://stackoverflow.com/questions/20281835/how-to-delete-a-folder-with-files-using-java
        // answer 2
        /*
            Implement all these cases: 
            1.  rmdir  takes  1  argument  which  is  "*"  (e.g.  rmdir  *)  and 
            removes all the empty directories  in the current directory. 
            • rmdir takes 1 argument which is either the full path or the 
            relative (short) path and removes the given directory only if 
            it is empty.
         */
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

    // ZIAD
    public void cat()
    {
        // Takes 1 argument and prints the file's content or takes 2 arguments and concatenates the content of the 2 files and prints it.
    }

    public void wc()
    {
        /*
            Wc stands for "word count," and as the name suggests, it is mainly 
            used for counting purpose. By default, it displays four-columnar 
            output. 
            First column shows number of lines present in a file specified, 
            second column shows number of words present in the file, third 
            column shows number of characters present in file and fourth 
            column itself is the file name which are given as argument 
            Example: 
            wc file.txt 
            Output: 
            9 79 483 file.txt 
            Explanation: 
            # 9 lines, 79 word, 483 character with spaces, file name
         */
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
    public void history()
    {
        /*
            Takes no parameters and displays an enumerated list with the 
            commands you've used in the past 
            Example: history 
            Output: 1   ls 
            2   mkdir tutorial 
            3   history
         */
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