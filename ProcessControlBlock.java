public class ProcessControlBlock{
    private int id;
    private long timeStarted;
    private long timeUsed;
    private int counter; //quando chega ao limite do tipo, processo e encerrado
    private String state;
    /*
    * Criado
    * Pronto
    * Executando
    * Bloqueado
    * Encerrado
    */

    public ProcessControlBlock(){
        this.timeStarted = 0;
        this.timeUsed = 0;
        this.counter = -1;
        this.state = "Criado";
        this.state = "Pronto";
    }

    public void start(){
        this.timeStarted = System.currentTimeMillis()/1000;
    }
    public void trySleep(int quantum){
        if (System.currentTimeMillis()/1000 >= this.timeStarted + quantum) this.sleep();
    }
    public void sleep(){
        long aux = System.currentTimeMillis() - this.timeStarted;
        this.timeUsed += aux / 1000;
        this.setStatePronto();
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
    public void setId(String id){
        this.id = Integer.parseInt(id);
    }
    public int getId(){
        return this.id;
    }
    public int getCounter(){
        return this.counter;
    }
    public void execute(int quantum){
        if (this.counter < 5) {
            ++this.counter;
            this.trySleep(quantum);
        }else{
            this.setStateEncerrado();
        }
    }
}