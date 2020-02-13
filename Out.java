package p5;
import java.util.*;
import java.sql.*;
public class Out{
public String show()throws Exception
{
Calendar c=Calendar.getInstance();
String hr=c.get(Calendar.HOUR)+"";
String mt=c.get(Calendar.MINUTE)+"";
String se=c.get(Calendar.SECOND)+"";
String q="select nummm from tiii where dttt='"+se+"-"+mt+"-"+hr+"'";
Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ram","123");
ResultSet rs=cn.createStatement().executeQuery(q);
int n=1;
if(rs.next()){
n=rs.getInt(1);
n++;
PreparedStatement ps=cn.prepareStatement("update tiii set nummm=? where dttt=?");
ps.setInt(1,n);
ps.setString(2,se+"-"+mt+"-"+hr);
ps.execute();
}
else{
PreparedStatement ps=cn.prepareStatement("insert into tiii values(?,?)");
ps.setInt(2,n);
ps.setString(1,se+"-"+mt+"-"+hr);
ps.execute();
}
if(Integer.parseInt(hr)<10)
hr="0"+hr;
if(Integer.parseInt(mt)<10)
mt="0"+mt;
if(Integer.parseInt(se)<10)
se="0"+se;
String report=hr+mt+se;
return report;
}
}
