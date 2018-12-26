package dialog;

public class Dynamic {
    private String author;
    private String time;
    private String content;

    public Dynamic(String author,String time,String content){
        this.author=author;
        this.content=content;
        this.time=time;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
