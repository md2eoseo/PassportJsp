<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.passport.dao.DBConnection" %> <!-- DB���� Ŭ������ import�Ѵ�. -->
<html>
 
<head>
</head>
<body>
    <center>
        <table border="3" bordercolor="skyblue">
        <tr bgcolor="skyblue"><td>�̸�<td>����</tr>
        
        <%
        // ������
        String query="select ename, job from emp";
        
        // Ŀ�ؼ� ����
        Connection conn = DBConnection.getConnection();
        
        // DB�� �������� ������.
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        // �������� ������� rs�� ��´�.
        ResultSet rs = pstmt.executeQuery();
        
        // ������� ����Ѵ�.
        while(rs.next()){
            out.println("<tr>");
            out.println("<td>"+rs.getString("ename"));
            out.println("<td>"+rs.getString("job"));
            out.println("</tr>");
        }
        
        %>
        </table>
    </center>
</body>
</html>