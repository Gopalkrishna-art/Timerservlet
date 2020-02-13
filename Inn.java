import p3.Id;
import p5.Out;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Inn extends HttpServlet{
public void service(HttpServletRequest rq,HttpServletResponse rs)throws IOException,ServletException{
try{
HttpSession s=rq.getSession();
int id=Integer.parseInt(s.getAttribute("id").toString());
Class.forName("oracle.jdbc.driver.OracleDriver");
String url="jdbc:oracle:thin:@localhost:1521:xe";
String dbun="ram",dbps="123";
Connection c=DriverManager.getConnection(url,dbun,dbps);
PrintWriter pw=rs.getWriter();
String o="select id,inn from dur where id="+id+" and inn is null";
ResultSet rp=c.createStatement().executeQuery(o);
if(rp.next())
{
//pw.print(rp.getString(1)+"<br>");
if(rp.getInt(1)==id&&rp.getString(2)!=null)
pw.print("u r already in");
else{
Id sk=new Id();
String dat=sk.show();
String k="select max(out) from dur where id="+id+" and inn is null";
ResultSet rskk=c.createStatement().executeQuery(k);
String q="update dur set inn=? where inn is null";
PreparedStatement p=c.prepareStatement(q);
Out ss=new Out();
String report=ss.show();
p.setString(1,report);
p.executeUpdate();
String i="select out,inn from dur where id="+id+" and durr is null";
ResultSet z=c.createStatement().executeQuery(i);
int out1=0;
int inn1=0;
if(z.next()){
out1=z.getInt(1);
inn1=z.getInt(2);
int so=(z.getInt(2)-z.getInt(1));
String w="update dur set durr=? where durr is null";
PreparedStatement e=c.prepareStatement(w);
e.setInt(1,so);
e.executeUpdate();
pw.print("time entered");
}
else{
pw.print("sorry:)");
}
}
}
}
catch(Exception e){
System.out.print(e.getMessage());
}
}
}
