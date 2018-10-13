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
        this.state = "Pronto";
    }

    public void start(){
        this.timeStarted = System.currentTimeMillis();
    }
    public boolean trySleep(long quantum){
        /*System.out.println("System:  " + System.currentTimeMillis());
        System.out.println("Time:    " + this.timeStarted);
        System.out.println("Quantum: " + quantum);
        System.out.println("T+Q    : " + (this.timeStarted + quantum));*/
        if (System.currentTimeMillis() >= (this.timeStarted + quantum)) {
            this.sleep();
            return true;
        }
        return false;
    }
    public void sleep(){
        this.timeUsed += System.currentTimeMillis() - this.timeStarted;
        this.setStatePronto();
    }
    public double getTimeStarted(){
        return this.timeStarted;
    }
    public double getTimeUsed(){
        return this.timeUsed;
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
    public void execute(long quantum){
        this.start();
        do{
            if (this.counter < 10000) {
                ++this.counter;
            }else{
                this.sleep();
                this.setStateEncerrado();
                break;
            }
        }while(!this.trySleep(quantum));
    }
}