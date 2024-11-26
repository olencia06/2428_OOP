// Parent Class
public class GBS {
    private String conference_name;
    public GBS(String conference_name){
        this.conference_name=conference_name;
    }

    public String getConferenceName(){
        return conference_name;
    }
    public void setConferenceName(String conference_name){
        this.conference_name=conference_name;
    }

    @Override
    public String toString(){
        return "Conference Name: "+conference_name;
    }
}
