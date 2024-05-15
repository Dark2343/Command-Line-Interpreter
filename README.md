# Command-Line-Interpreter

CMD-like Java program that allows you to execute a variety of commands.

## Command List

1. **echo**: 
   - Takes one or more arguments and prints them to the screen.
   
2. **pwd**: 
   - Takes no arguments and prints the current path.
   
3. **cd**: 
   - Takes `..` as an argument and changes the current directory to the parent directory.
   - Takes no arguments and changes the current directory to the user's home directory.
   - Takes one argument which is either the full path or the relative (short) path and changes the current directory to the given path.
   
4. **ls**: 
   - Takes no arguments and prints the contents of the current directory.
   - Takes one argument `-r` and prints the contents of the current directory in reverse order.
   
5. **mkdir**: 
   - Takes one or more arguments and creates a directory for each argument. Each argument can be:
     - Directory name (the new directory is created in the current directory).
     - Path (full/short) that ends with a directory name (the new directory is created in the given path).
   
6. **rmdir**: 
   - Takes one argument `*` (e.g., `rmdir *`) and removes all the empty directories in the current directory.
   - Takes one argument which is either the full path or the relative (short) path and removes the given directory only if it is empty.
   
7. **touch**: 
   - Takes one argument which is either the full path or the relative (short) path that ends with a file name and creates this file.
   
8. **cp**: 
   - Takes two arguments, both are files, and copies the first onto the second.
   
9. **rm**: 
   - Takes one argument which is a file name that exists in the current directory and removes this file.
   
10. **cat**: 
    - Takes one argument and prints the file's content.
    - Takes two arguments and concatenates the content of the two files and prints it.
    
11. **wc**: 
    - Takes a file name as input and displays four-columnar output:
      - 1st column: Number of lines present in the file.
      - 2nd column: Number of words present in the file.
      - 3rd column: Number of characters present in the file.
      - 4th column: The file name given as argument.
      
12. **history**: 
    - Takes no parameters and displays an enumerated list with the commands you've used in the current session.
    
13. **exit**: 
    - Takes no parameters and quits the program.

## Command Structure

### General Structure of a Command

*Note: the "+" isn't written.*

<pre>commandName + parameters</pre>


The following commands can have a different structure that allows you to save their output into a new file or append it to an existing file:

### Eligible Commands:

- echo
- pwd
- ls
- wc
- history

### Structure

#### Saving content into a new file

<pre>commandName + parameters + > + fileName</pre>

#### Appending content into an existing file

<pre>commandName + parameters + >> + fileName</pre>
