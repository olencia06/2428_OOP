import java.util.ArrayList;
import java.util.List;
// Generic Class
public class Schedule<T extends Conference> {
    private List<T> schedule;

    public Schedule() {
        schedule =new ArrayList<>();
    }
     public void addSchedule(T item){
        schedule.add(item);
     }

     public void displaySchedule(){
        
        for (T item: schedule){

            // schedule tutorial  
            int tutorial=item.getTutorial();
            int temp2=tutorial;
           
            tutorial=tutorial/4;
            item.setTutorial(tutorial);

            // schedule poster presentations
            int poster_presentation=item.getPosterPresentation();
            int temp=poster_presentation;
            
            poster_presentation=poster_presentation/4;
            item.setPosterPresentation(poster_presentation); 

            // set paper Presentations
            int paper_presentation=item.getPaperPresentation();
            int temp1=paper_presentation;
            
           

                paper_presentation=paper_presentation/4;
                item.setPaperPresentation(paper_presentation); 

            System.out.println(item.toString());
            System.out.println("\n----------------------------------------------------------------------------\n");
            item.setPosterPresentation(temp);
            item.setPaperPresentation(temp1);
            item.setTutorial(temp2);
           
        }
     }
}