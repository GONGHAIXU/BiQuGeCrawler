import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//爬取章节内容的线程类
public class Thread2 implements Runnable {
    //一些公用变量和构造方法
    List<Novel> novelList = new ArrayList<>();
    SqlSession sqlSession;
    Mysql mysql = new Mysql();
    int i = 2
    public Thread2() {
        try {
            sqlSession = mysql.getSqlSessionOfNovel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        try {
            Thread.sleep(10000);
            novelList.add(mysql.selectNovelById(sqlSession,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        //爬取章节直到结束
            while(!novelList.isEmpty()){
                Document doc = Until.getDoc(novelList.get(0).getUrl());
                Elements elements = Until.getChapterLink(doc);
                long id = novelList.get(0).getId();
                novelList.remove(novelList.get(0));
                for (Element element:elements) {
                    Chapter chapter = Until.getChapter(element);
                    chapter.setId(id);
                    chapter.setText(Until.getChapterText(Until.baseUrl+chapter.getUrl()));
                    mysql.insertChapter(sqlSession,chapter);
                }
                try {
                    if(mysql.selectNovelById(sqlSession,i) != null){
                        novelList.add(mysql.selectNovelById(sqlSession,i));
                        i ++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
