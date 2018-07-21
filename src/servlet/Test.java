package servlet;

import com.beiyou.dao.StudentInfoMapper;
import com.beiyou.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test {
    public static SqlSession getsession()throws Exception {
        String path = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(path);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession session = factory.openSession();
        return session;
    }
//    public static void  main (String[] args) throws Exception {

//            SqlSession session = getsession();
//            StudentInfoMapper mapper = session.getMapper(StudentInfoMapper.class);
//            List<Student> student = mapper.SelectAll();
//            for (Student S : student) {
//                System.out.println(S);
//            }
//            session.commit();
//        }
//    }
    public static void  main (String[] args) throws Exception {

        SqlSession session = getsession();
        StudentInfoMapper mapper = session.getMapper(StudentInfoMapper.class);
        Student student = mapper.SelectSingle(14);

            System.out.println(student);

        session.commit();
    }
}




