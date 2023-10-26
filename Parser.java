class Parser { 

    String commandName; 
    String[] args; 
 
        
    public boolean parse(String input)
    {
        String[] tokens = input.split(" ");
        commandName = tokens[0];
        args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, tokens.length - 1);
        return true;
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