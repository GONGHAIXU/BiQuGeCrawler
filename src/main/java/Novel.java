//实体类
public class Novel {
    private String novelName;
    private String url;
    private String author;
    private String introduction;
    private String cover;
    private long id ;
    public Novel(String novelName,String url,
                 String author,String cover,String introduction){
        this.author = author;
        this.cover = cover;
        this.introduction = introduction;
        this.url = url;
        this.novelName = novelName;
    }
    public Novel(){ }
    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

