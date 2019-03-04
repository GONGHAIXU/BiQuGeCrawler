import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
//数据库操作类
public class Mysql {
    //获得SqlSession对象
    public SqlSession getSqlSessionOfNovel()throws IOException {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
    //根据id获得Novel对象
    public Novel selectNovelById(SqlSession sqlSession,int x)throws IOException{
        Novel novel = sqlSession.selectOne("NovelMapper.getNovelById",x);
        return novel;
    }
    //插入NOvel
    public void insertNovel(SqlSession sqlSession,String novelName,String url,
                               String author, String cover,String introduction){
        Novel novel = new Novel(novelName,url, author, cover,introduction);
        sqlSession.insert("NovelMapper.insertNovel",novel);
        sqlSession.commit();
    }
    //插入Chapter
    public void insertChapter(SqlSession sqlSession,Chapter chapter){
        sqlSession.insert("ChapterMapper.insertChapter",chapter);
        sqlSession.commit();
    }
}
