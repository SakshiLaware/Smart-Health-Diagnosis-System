<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" href="style.css">

    <style>
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

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
        }

        th {
            background: #4a6cf7;
            color: white;
            padding: 12px;
        }

        td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #eee;
        }

        tr:nth-child(even) {
            background: #f7f9ff;
        }

        tr:hover {
            background: #eaf0ff;
        }

        a {
            display: block;
            width: 150px;
            margin: 20px auto;
            text-align: center;
            padding: 10px;
            background: #f2f4ff;
            border-radius: 10px;
            text-decoration: none;
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
            <th>Doctor</th>
            <th>Severity</th>
        </tr>

        <%
            ArrayList<String[]> list =
                (ArrayList<String[]>) request.getAttribute("historyList");

            if (list != null && !list.isEmpty()) {
                for (String[] row : list) {
        %>

        <tr>
            <td><%= row[0] %></td>
            <td><%= row[1] %></td>
            <td><%= row[2] %></td>
            <td><%= row[3] %></td>
            <td><%= row[4] %></td>
        </tr>

        <%
                }
            } else {
        %>

        <tr>
            <td colspan="5">No history found</td>
        </tr>

        <%
            }
        %>

    </table>

    <a href="index.html">Back</a>

</div>

</body>
</html>
