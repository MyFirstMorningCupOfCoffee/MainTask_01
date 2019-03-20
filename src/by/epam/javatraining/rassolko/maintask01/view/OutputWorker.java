package by.epam.javatraining.rassolko.maintask01.view;

public class OutputWorker 
{
    
    private OutputWorker()
    { }
    
    // print several lines to console
    public static void printlnToConsole(Object ... text)
    {
        for(Object s : text)
        {
            System.out.println(s);
        }
    }
    
    // print single line to console
    public static void printToConsole(Object text)
    {
        System.out.print(text);
    }
    
    // print empty line
    public static void insertEmptyLine()
    {
        System.out.println();
    }
    
    // print to console 2 rows table
    public static void printToConsoleTwoRowsTable(String ... lines)
    {
        // checking input
        if(lines.length == 0 || lines.length % 2 != 0)
        {
            return;
        }
        
        int leftMaxLength = 0, rightMaxLength = 0;
        
        // working on the left side of the table
        for(int i = 0; i < lines.length; i += 2)
        {
            if(lines[i].length() > leftMaxLength)
            {
                leftMaxLength = lines[i].length();
            }
        }
        
        for(int i = 0; i < lines.length; i += 2)
        {
            if(lines[i].length() < leftMaxLength)
            {
                int spacesToAdd = leftMaxLength - lines[i].length();
                lines[i] = addSomeSpacesToTheLeft(lines[i], spacesToAdd);
            }
        }
        
        // working on the right side of the table
        for(int i = 1; i < lines.length; i += 2)
        {
            if(lines[i].length() > rightMaxLength)
            {
                rightMaxLength = lines[i].length();
            }
        }
        
        for(int i = 1; i < lines.length; i += 2)
        {
            if(lines[i].length() < rightMaxLength)
            {
                int spacesToAdd = rightMaxLength - lines[i].length();
                lines[i] = addSomeSpacesToTheLeft(lines[i], spacesToAdd);
            }
        }
        
        // printing
        for(int i = 0; i < lines.length; i += 2)
        {
            printToConsole(lines[i]);
            printToConsole("  ");
            printToConsole(lines[i + 1]);
            printToConsole("\n");
        }
    }
    
    
    
    //// ancillary methods ////

    private static String addSomeSpacesToTheLeft(String text,  int spaces)
    {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < spaces; i++)
        {
            sb.append(" ");
        }
        
        sb.append(text);
        
        return sb.toString();
    }
    
    private static String addSomeSpacesToTheRight(String text,  int spaces)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        
        for(int i = 0; i < spaces; i++)
        {
            sb.append(" ");
        }
        
        return sb.toString();
    }

}
