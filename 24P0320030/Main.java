import java.util.Scanner;

/**
 * Author: 
 */


public class Main {
   /**
    * Main class
    */
    public static void main(String[] args){
        int ch=1;
        boolean flag;
        Scanner sc= new Scanner(System.in);

        // GBS Ojects
        GBS conference1=new GBS("ICON");
        GBS conference2=new GBS("FIRE");

        // Cnnference Objects
        Conference ICON=new Conference(conference1.getConferenceName(),8,1,24,40);
        Conference FIRE=new Conference(conference2.getConferenceName(),0,2,18,20);
        

        // Scheduler
        Schedule day1 =new Schedule<>();
        Schedule day2 =new Schedule<>();
        Schedule day3 =new Schedule<>();
        Schedule day4 =new Schedule<>();
        Schedule day5 =new Schedule<>();

        /**
         * 14 december scheduler
         */
        day1.addSchedule(ICON);


        /**
         * 15 december scheduler
         */
        day2.addSchedule(ICON);
        day2.addSchedule(FIRE);

       
       /**
         * 16 december scheduler
         */
        day3.addSchedule(ICON);
        day3.addSchedule(FIRE);

        /**
         * 17 december scheduler
         */
        day4.addSchedule(FIRE);
        day4.addSchedule(ICON);


        /**
         * 18 december scheduler
         */
        day5.addSchedule(FIRE);
     
        System.out.println("Discipline of Compter SCience and Technology of Goa Univesity Conferences\n 1: All Confernces\n 2: Schedule\n 3: Exit");
        ch=sc.nextInt();
      
   
            switch(ch){
                case 1:
                    System.out.println(conference1.toString());
                    System.out.println(conference2.toString());
                    break;
                case 2:
                    System.out.println("\n------------------------Schdule for 14 December 2024------------------------\n");
                    day1.displaySchedule();
    
                    System.out.println("\n------------------------Schdule for 15 December 2024------------------------\n");
                    day2.displaySchedule();
    
                    System.out.println("\n-----------------------Schdule for 16 December 2024------------------------\n");
                    day3.displaySchedule();
    
                    System.out.println("\n------------------------Schdule for 17 December 2024------------------------\n");
                    day4.displaySchedule();
    
                    System.out.println("\n------------------------Schdule for 18 December 2024------------------------\n");
                    day5.displaySchedule();
                    break;
                case 3:
                    
                default:
                    System.out.println("Enter Valid Input");
    
            }
       
        
    }
}

