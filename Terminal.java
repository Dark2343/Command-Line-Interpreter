public class Terminal {
    Parser parser; 

    //Implement each command in a method, for example: 

    public void echo(String args)
    {
        // Takes 1 argument and prints it.
        System.out.println(args);
    }

    public void pwd()
    {
        // Takes no arguments and prints the current path.
        
    }

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

    public void ls()
    {
        // Takes  no  arguments  and  lists  the  contents  of  the  current  directory sorted alphabetically.
    }

    public void ls_r()
    {
        // Takes no arguments and lists the contents of the current directory in reverse order.
    }

    public void mkdir()
    {
        /*
            Takes  1 or more  arguments  and creates  a directory  for each 
            argument. Each argument can be: 
            • Directory  name  (in  this  case  the  new  directory  is  created  in 
            the current directory) 
            Path (full/short) that ends with a directory name (in this case the 
            new directory is created in the given path)
         */
    }

    public void rmdir()
    {
        /*
            Implement all these cases: 
            1.  rmdir  takes  1  argument  which  is  "*"  (e.g.  rmdir  *)  and 
            removes all the empty directories  in the current directory. 
            • rmdir takes 1 argument which is either the full path or the 
            relative (short) path and removes the given directory only if 
            it is empty.
         */
    }

    public void touch()
    {
        /*
            Takes  1 argument  which  is  either  the  full path  or  the 
            relative (short)  path  that  ends  with  a  file  name  and  creates 
            this file.
         */
    }

    public void cp()
    {
        // Takes  2  arguments,  both  are  files  and  copies  the  first  onto  the second
    }

    public void cp_r()
    {
        // Takes  2  arguments, both  are  directories  (empty  or  not)  and  copies the first directory (with all its content) into the second one
    }

    public void rm()
    {
        // Takes  1  argument  which  is  a  file  name  that  exists  in  the  current directory and removes this file.
    }

    public void cat()
    {
        // Takes 1 argument and prints the file's content or takes 2 arguments and concatenates the content of the 2 files and prints it.
    }

    public void we()
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

    public void command1()
    {
        /*
            Format: command  > FileName 
            Redirects the output of the first command to be written to a file. If the 
            file doesn't exist, it will be created. 
            If the file exists, its original content will be replaced. 
            Example: echo Hello World > myFile.txt 
            ls > file 
         */
    }

    public void command2()
    {
        // like command 1 but appends to the file if exists
    }

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
    }

    /*
        If  the  user  enters  a  wrong  command  or  bad  parameters  (invalid  path,  file instead  of  directory  in  certain  commands,  etc.),
        the  program  should  print some error messages without terminating. 
    */

    //This method will choose the suitable command method to be called 
    public void chooseCommandAction()
    {

    }

    public static void main(String[] args)
    {
        System.out.println(System.getProperty("user.home"));
        Parser parser = new Parser();
        parser.parse("echo this is a retarded mess");
    } 
}