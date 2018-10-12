import java.nio.file.*;

public class Trab{
    public static void main(String args[]) throws Exception{
        String arquivo = new String(Files.readAllBytes(FileSystems.getDefault().getPath(".", args[0])));
        int length = arquivo.length();
        int quantidade = 0;
        int quantum = 0;
        int marcador = 0;

        for(int i = 0 ; i < length ; ++i)
		{
             if(arquivo.charAt(i) == ';'){
                 if(i-1 != 0)
                    quantidade = Integer.parseInt(arquivo.substring(0,i-1));
                 else
                    quantidade = Character.getNumericValue(arquivo.charAt(0)); 
                 marcador = i;
                 break;
             }
        }
        
        for(int i = marcador + 1; i < length ; ++i)
		{
             if(arquivo.charAt(i) == '\n'){
                 quantum = Integer.parseInt(arquivo.substring(marcador + 1,i));
                 marcador = i ;
                 break;
             }
        }
        
        ProcessControlBlock pcb[] = new ProcessControlBlock[quantidade];
        int pos = 0;
        for(int i = marcador + 1; i < length+1  && pos < quantidade ; ++i)
		{
             if(arquivo.charAt(i) == ';' || arquivo.charAt(i) == -1 || arquivo.charAt(i) == '\n'){
                 pcb[pos] = new ProcessControlBlock();
                 if ( marcador + 1 != i-1 ) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
                    pcb[pos].setId(arquivo.substring(marcador + 1,i-1));
                 } else {
                    pcb[pos].setId(Character.getNumericValue(arquivo.charAt(i-1)));
                 } 
                 marcador = i ;
                 ++pos;
             }
        }
        
    }
}