
// Subclass 
public class Conference extends GBS {
    // Variables
    private int tutorial, workshop, paper_presentation,poster_presentation;
     
    
    // constructor
    public Conference(String conference_name,int tutorial,int workshop,int paper_presentation, int poster_presentation){
        super(conference_name);
        this.tutorial=tutorial;
        this.workshop=workshop;
        this.paper_presentation=paper_presentation;
        this.poster_presentation=poster_presentation;
    }
    // Getter and Setters
    public int getTutorial(){
        return tutorial;
    }

    public void setTutorial(int tutorial){
        this.tutorial=tutorial;
    }

    public int getWorkshop(){
        return workshop;
    }

    public void setWorkshop(int workshop){
        this.workshop=workshop;
    }

    public int getPaperPresentation(){
        return paper_presentation;
    }

    public void setPaperPresentation(int paper_presentation){
        this.paper_presentation=paper_presentation;
    }

    public int getPosterPresentation(){
        return poster_presentation;
    }

    public void setPosterPresentation(int poster_presentation){
        this.poster_presentation=poster_presentation;
    }
    
    
    // To display the details
    @Override
    public String toString(){
        return "Conference Name: "+getConferenceName()+"\nTutorials: "+tutorial+", Workshops: "+workshop+", Paper Presentations: "+paper_presentation+", Poster Presentations: "+poster_presentation;
    }
}
