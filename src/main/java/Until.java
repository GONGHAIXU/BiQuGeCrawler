import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
//工具类
public class Until {
    static final String novelUrl = "http://www.xbiquge.la/xiaoshuodaquan/";
    static final String baseUrl = "http://www.xbiquge.la/";
    //获得Document对象
    public static Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return doc;
        }
    }
    //根据主页面获得小说的url
    public static Elements getNovelLink(Document doc) {
        Element element = doc.getElementById("main");
        Elements links = element.getElementsByTag("a");
        return links;
    }
    //获取Novel对象
    public static Novel getNovel(Element link) {
        Novel novel = new Novel();
        novel.setUrl(link.attr("href"));
        novel.setNovelName(link.text());
        Document doc1 = Until.getDoc(novel.getUrl());
        Element element1 = doc1.head();
        novel.setAuthor(element1.select("meta[property=\"og:novel:author\"]").
                get(0).attr("content"));
        novel.setCover(element1.select("meta[property=\"og:image\"]").
                get(0).attr("content"));
        novel.setIntroduction(element1.select("meta[property=\"og:description\"]").
                get(0).attr("content"));
        return novel;
    }
    //根据小说页面获取章节链接
    public static Elements getChapterLink(Document doc){
        Element element = doc.getElementById("list");
        Elements links = element.getElementsByTag("a");
        return links;
    }
    //获取Chapter对象
    public static Chapter getChapter(Element element){
        Chapter chapter = new Chapter();
        chapter.setChapterName(element.text());
        chapter.setUrl(element.attr("href"));
        return chapter;
    }
    //获取Chapter具体内容
    public static String getChapterText(String url){
        Document doc =Until.getDoc(url);
        Element element = doc.getElementById("content");
        return element.text();
    }
}
