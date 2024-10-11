public class Tables {
    public static void main(String[] args) {
      
        MultiplicationTable table5 = new MultiplicationTable(5);
        MultiplicationTable table6 = new MultiplicationTable(6);
        // MultiplicationTable table7 = new MultiplicationTable(7);

       
        table5.start();
        table6.start();
        // table7.start();
        
        
        try {
            //Join is used to add wait before executing each part 
            table5.join();
            table6.join();
            // table7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
