package servlet;

import com.alibaba.fastjson.JSONObject;
import com.beiyou.dao.StudentInfoMapper;
import com.beiyou.pojo.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import page.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/info")
public class info extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");
        //回应的编码格式
        response.setContentType("application/json;charset=utf8");
        //设置跨域问题
//		response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out=response.getWriter();
        //设置请求的编码格式
        request.setCharacterEncoding("utf-8");
         SqlSession session = null;
        try {
            session = getsession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentInfoMapper mapper = session.getMapper(StudentInfoMapper.class);

        Integer type = Integer.valueOf(request.getParameter("type"));


        System.out.println(type);
        switch(type){
            case 1:
                Integer n= Integer.valueOf(request.getParameter("param"));
                Student stu= mapper.SelectSingle(n);
                System.out.println(stu);
                out.print(JSONObject.toJSONString(stu));

                break;
            case 2:
                Integer i = Integer.valueOf(request.getParameter("page"));
                Integer j = Integer.valueOf(request.getParameter("limit"));
                PageHelper.startPage(i,j);
                PageInfo info = new PageInfo(mapper.SelectAll());

                Page p = new Page();
                p.setCode(0);
                p.setMsg("传输成功");
                p.setCount(info.getTotal());
                p.setData(info.getList());
                out.print(JSONObject.toJSONString(p));
                break;
            case 3:
                int b = Integer.parseInt(request.getParameter("id"));
                 out.print(delete(b));
                break;
            case 4:
                System.out.println("safdasf");
                String name = request.getParameter("name");
                String sex = request.getParameter("sex");
               Integer seat= Integer.valueOf(request.getParameter("seat"));
                String desc = request.getParameter("desc");
                boolean c = addStudent(name,sex,seat,desc);
                out.print(c);
                break;
        }


    }
    public boolean delete(Integer id){
        SqlSession session = null;
        try {
            session = getsession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentInfoMapper mapper = session.getMapper(StudentInfoMapper.class);

        int x = mapper.deleteById(id);
        session.commit();
        if (x !=0){
            return true;
        }else{
            return false;
        }

    }
    public boolean addStudent(String name,String sex,Integer seat,String desc){
        SqlSession session = null;
        try {
            session = getsession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentInfoMapper mapper = session.getMapper(StudentInfoMapper.class);

       int n = mapper.addStudent(name,sex,seat,desc);
        session.commit();
        if (n !=0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    public static SqlSession getsession()throws Exception {
        String path = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(path);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession session = factory.openSession();
        return session;

    }
}
