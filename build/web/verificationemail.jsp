<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/verificationemailCSS.css">
        <title>Email Verification</title>
    </head>
    <body>
        <div class="container">
            <h2>Email Verification</h2>
            <p>Please enter the verification code sent to your email address.</p>
            <form action="./verify" method="post">
                <div class="input-container">
                    <input type="text" name="code1" maxlength="1" required>
                    <input type="text" name="code2" maxlength="1" required>
                    <input type="text" name="code3" maxlength="1" required>
                    <input type="text" name="code4" maxlength="1" required>
                </div>
                <h4 style="color: red; font-weight: 400">${requestScope.error}</h4>
                <button type="submit">Verify</button>
            </form>
            <div style="display: flex; justify-content: space-around" class="nav-container">
                <form action="./resendemail" method="post" >
                    <button type="submit">Resend Code</button>
                </form>
                <c:if test="${not empty sessionScope.username}">
                    <a href="./signup">Change email</a>
                </c:if>
            </div>
        </div>
    </body>
</html>
