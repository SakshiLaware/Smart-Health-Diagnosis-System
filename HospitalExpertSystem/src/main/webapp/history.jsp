<%@ page import="java.sql.*" %>
<%@ page import="com.hospital.dao.DBConnection" %>

<!DOCTYPE html>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" href="style.css">

    <style>
        body {
            font-family: Arial, sans-serif;
        }

        /* MAIN BOX */
        .history-box {
            width: 90%;
            max-width: 1000px;
            margin: 50px auto;
            padding: 30px;

            background: #ffffff;
            border-radius: 18px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.25);
        }

        h2 {
            text-align: center;
            font-size: 28px;
            margin-bottom: 25px;
            color: #333;
        }

        /* TABLE DESIGN */
        table {
            width: 100%;
            border-collapse: collapse;
            overflow: hidden;
            border-radius: 10px;
        }

        th {
            background: #4a6cf7;
            color: white;
            padding: 12px;
            font-size: 16px;
        }

        td {
            padding: 12px;
            text-align: center;
            font-size: 15px;
            border-bottom: 1px solid #eee;
        }

        tr:nth-child(even) {
            background: #f7f9ff;
        }

        tr:hover {
            background: #eaf0ff;
        }

        /* BACK LINK CENTER */
        a {
            display: block;
            width: 150px;
            margin: 20px auto 0;
            text-align: center;

            padding: 10px;
            background: #f2f4ff;
            border-radius: 10px;
            text-decoration: none;
            color: #333;
            transition: 0.3s;
        }

        a:hover {
            background: #e0e6ff;
            color: #4a6cf7;
        }
    </style>
</head>

<body>

<div class="history-box">

    <h2>Patient History</h2>

    <table>
        <tr>
            <th>Symptom</th>
            <th>Disease</th>
            <th>Treatment</th>
        </tr>

        <%
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patient_history");

            while(rs.next()){
        %>

        <tr>
            <td><%= rs.getString("symptom") %></td>
            <td><%= rs.getString("diagnosis") %></td>
            <td><%= rs.getString("treatment") %></td>
        </tr>

        <% } %>

    </table>

    <a href="index.html">Back</a>

</div>

</body>
</html>