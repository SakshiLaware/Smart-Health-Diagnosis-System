<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="style.css">

    <style>
        .result-box {
            width: 80%;
            max-width: 700px;
            margin: 60px auto;
            padding: 30px;
            background: #fff;
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            margin: 10px 0;
        }

        a {
            display: block;
            width: 150px;
            margin: 20px auto 0;
            padding: 10px;
            text-decoration: none;
            background: #f2f4ff;
            border-radius: 10px;
        }

        a:hover {
            background: #e0e6ff;
        }
    </style>
</head>

<body>

<div class="result-box">
    <h2>Diagnosis Result</h2>

    <p><b>Disease:</b> ${disease}</p>
    <p><b>Condition:</b> ${severity}</p>
    <p><b>Doctor:</b> ${doctor}</p>
    <p><b>Suggested Action:</b><br>${treatment}</p>

    <a href="index.html">Back</a>
</div>

</body>
</html>