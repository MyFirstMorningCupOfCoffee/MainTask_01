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

}
