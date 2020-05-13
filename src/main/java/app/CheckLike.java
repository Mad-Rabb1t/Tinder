package app;

public class CheckLike {
    public String check(String value){

        if(value.equals("Like")){
           return "Like";
        }else if(value.equals("Dislike")) return "Dislike";
        return "Something went wrong";
    }
}
