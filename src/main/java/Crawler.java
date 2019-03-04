//爬虫类，开了两个线程分别爬取小说和章节内容
public class Crawler {
    public static void main(String[] args){
        Thread1 m1 = new Thread1();
        Thread t1 = new Thread(m1);
        Thread2 m2 = new Thread2();
        Thread t2 = new Thread(m2);
        t1.start();
        t2.start();
    }
}
