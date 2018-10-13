import java.nio.file.*;
import java.lang.StringBuffer;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class Trab{
    public static void main(String args[]) throws Exception{
        String arquivo = new String(Files.readAllBytes(FileSystems.getDefault().getPath(".", args[0])));
        int length = arquivo.length();
        int quantidade = 0;
        long quantum = 0;
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
                 quantum = Long.parseLong(arquivo.substring(marcador + 1,i));
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
        quantum = quantum * 10;

        Trab trab = new Trab();

        if(args[1].equals("preemptivo")){
            trab.preemptivo(quantidade, pcb, quantum);
        }else{
            trab.naoPreemptivo(quantidade, pcb);
        }
    }

    //Round robin
    public void preemptivo(int quantidade, ProcessControlBlock pcb[], long quantum) throws Exception{
        int quantidadeInicial = quantidade;
        StringBuffer buffer = new StringBuffer("");
        while(quantidade > 0){
            for(int i = 0 ; i < quantidade ; ++i){
                if(pcb[i].getState().equals("Pronto")){
                    buffer.append("Id: " + pcb[i].getId() + "          Estado: Executando\n");
                    pcb[i].setStateExecutando();
                    for(int j = 0 ; j < quantidadeInicial ; ++j){
                        if(pcb[j].getState().equals("Pronto")){
                            buffer.append("          Id: " + pcb[j].getId() + "          Estado: Pronto\n");
                        }
                    }
                    pcb[i].execute(quantum);
                    
                    if(pcb[i].getState().equals("Encerrado")){
                        --quantidade;
                        buffer.append("Id: " + pcb[i].getId() + "          ENCERRADO!!!!!!!!!!\n");
                    }
                }
            }

            for(int i = 0 ; i < quantidadeInicial ; ++i){
                for(int j = 0 ; j < quantidadeInicial - 1 ; ++j){
                    if(pcb[j].getState().equals("Encerrado")){
                        ProcessControlBlock aux = pcb[j+1];
                        pcb[j+1] = pcb[j];
                        pcb[j] = aux;
                    }
                }
            }
        }

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("preemptivo.txt"), "utf-8"));
		writer.write(buffer.toString());
		writer.close();
    }

    //FIFO
    public void naoPreemptivo(int quantidade, ProcessControlBlock pcb[]) throws Exception{
        int quantidadeInicial = quantidade;
        StringBuffer buffer = new StringBuffer("");
        while(quantidade > 0){
            for(int i = 0 ; i < quantidade ; ++i){
                if(pcb[i].getState().equals("Pronto")){
                    buffer.append("Id: " + pcb[i].getId() + "          Estado: Executando\n");
                    pcb[i].setStateExecutando();
                    for(int j = 0 ; j < quantidadeInicial ; ++j){
                        if(pcb[j].getState().equals("Pronto")){
                            buffer.append("          Id: " + pcb[j].getId() + "          Estado: Pronto\n");
                        }
                    }
                    pcb[i].execute();
                    
                    if(pcb[i].getState().equals("Encerrado")){
                        --quantidade;
                        buffer.append("Id: " + pcb[i].getId() + "          ENCERRADO!!!!!!!!!!\n");
                    }
                }
            }

            for(int i = 0 ; i < quantidadeInicial ; ++i){
                for(int j = 0 ; j < quantidadeInicial - 1 ; ++j){
                    if(pcb[j].getState().equals("Encerrado")){
                        ProcessControlBlock aux = pcb[j+1];
                        pcb[j+1] = pcb[j];
                        pcb[j] = aux;
                    }
                }
            }
        }

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("naoPreemptivo.txt"), "utf-8"));
		writer.write(buffer.toString());
		writer.close();
    }
}
