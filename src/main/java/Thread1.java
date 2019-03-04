import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
//爬取小说章节目录url的线程类
    public class Thread1 implements Runnable{
    //一些公用变量及构造方法
    SqlSession sqlSession;
    Mysql mysql = new Mysql();
    Document doc = Until.getDoc(Until.novelUrl);
    Elements elements = Until.getNovelLink(doc);
    public Thread1() {
        try {
            sqlSession = mysql.getSqlSessionOfNovel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        //不断爬取直到全部爬完
        while (!elements.isEmpty()){
                Novel novel = Until.getNovel(elements.first());
                mysql.insertNovel(sqlSession,novel.getNovelName(),
                        novel.getUrl(),novel.getAuthor(),
                        novel.getCover(),novel.getIntroduction());
            elements.remove(elements.first());
        }
    }
}
