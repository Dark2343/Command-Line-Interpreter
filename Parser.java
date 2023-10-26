class Parser { 

    String commandName; 
    String[] args; 
 
    //This method will divide the input into commandName and args 
    //where "input" is the string command entered by the user

    public boolean parse(String input)
    {
        String[] tokens = input.split(" ");
        
        return true;
    }
    
    public String getCommandName()
    {
        String[] commands = {"echo", "pwd", "cd", "ls", "mkdir", "rmdir", "touch", "cp", "rm", "cat", "we", "exit"};
        return "lol";
    }
     
    public String[] getArgs()
    {
        String[] placeholder = {"car","bmw"};
        return placeholder;
    } 
}