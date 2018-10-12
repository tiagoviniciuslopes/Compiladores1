public class ProcessControlBlock{
    private String state = "Criado";
    /*
    * Criado
    * Pronto
    * Executando
    * Bloqueado
    * Encerrado
    */
    private int id;
    private long timeStarted;
    private long timeUsed = 0;
    private int counter = -1;

    public void start(){
        this.timeStarted = System.currentTimeMillis();
    }
    public void trySleep(int quantum){
        if (System.currentTimeMillis() >= this.timeStarted + quantum*1000) sleep();
    }
    public void sleed(){
        this.timeUsed += System.currentTimeMillis() - this.timeStarted;
    }
    public double getTimeStarted(){
        return this.timeStarted / 1000;
    }
    public double getTimeUsed(){
        return this.timeUsed / 1000;
    }
    public void setStatePronto(){
        this.state = "Pronto";
    }
    public void setStateExecutando(){
        this.state = "Executando";
    }
    public void setStateBloqueado(){
        this.state = "Bloqueado";
    }
    public void setStateEncerrado(){
        this.state = "Encerrado";
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public int getCounter(){
        return this.counter;
    }
    public void execute(){
        ++this.counter;
    }
}