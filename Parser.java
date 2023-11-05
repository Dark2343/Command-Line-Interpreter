// This class will parse the user input and save the command name and arguments
public class Parser { 

    String commandName; 
    String[] args;
    Boolean arrow = false, doubleArrow = false;
    String fileName;

    // Parses the user input
    public void parse(String input)
    {
        // Splits the input into tokens
        String[] tokens = input.split(" ");
        commandName = tokens[0];
        
        // Checks if the user would like to save the file
        if (tokens.length > 2 && tokens[tokens.length - 2].equals(">")) {    
            arrow = true; doubleArrow = false;
            fileName = tokens[tokens.length - 1];
            args = new String[tokens.length - 3];
            System.arraycopy(tokens, 1, args, 0, tokens.length - 3);
        }
        // Checks if the user would like to append to the file
        else if (tokens.length > 2 && tokens[tokens.length - 2].equals(">>")) {
            arrow = false; doubleArrow = true;
            fileName = tokens[tokens.length - 1];
            args = new String[tokens.length - 3];
            System.arraycopy(tokens, 1, args, 0, tokens.length - 3);
        }
        // No file
        else{
            arrow = false; doubleArrow = false;
            args = new String[tokens.length - 1];
            System.arraycopy(tokens, 1, args, 0, tokens.length - 1);
        }
        
    }
    
    // Getters
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

    // Checks if the user would like to save the file
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
