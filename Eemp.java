import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Eemp extends HttpServlet{
public void service(HttpServletRequest rq,HttpServletResponse rs)throws IOException,ServletException{
try{
String name=rq.getParameter("name");
int id=Integer.parseInt(rq.getParameter("id"));
HttpSession s=rq.getSession();
s.setAttribute("id",id+"");          
Class.forName("oracle.jdbc.driver.OracleDriver");
String url="jdbc:oracle:thin:@localhost:1521:xe";
String dbun="ram",dbps="123";
Connection c=DriverManager.getConnection(url,dbun,dbps);
PrintWriter pw=rs.getWriter();
String k="select name from emp where name='"+name+"'";
ResultSet rsk=c.createStatement().executeQuery(k);
if(rsk.next())
pw.print("Account name already exist");
else{
String q="insert into emp values(?,?)";
PreparedStatement p=c.prepareStatement(q);
p.setInt(1,id);
p.setString(2,name);
p.execute();
pw.print("data saved");
}
}
catch(Exception e){
System.out.print(e.getMessage());
}
}
}

