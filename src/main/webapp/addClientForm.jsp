
<form id = "login" action="adminServlet" method="get">
    Name:<input type="text" name="name" maxlength="30"><br>
    Surname:<input type="text" name="surname" maxlength="30"><br>
    Birthday:<input type="date" name="birthday"><br>
    Passport:<input type="text" name="passport" maxlength="15"><br>
    Address:<input type="text" name="address" maxlength="255"><br>
    Email:<input type="email" name="email" maxlength="30"><br>
    Password:<input type="text" name="password" maxlength="30"><br>
    <input type="SUBMIT" name="action" value="Add Client" class="myButton">
    <input type="hidden" name="action" value="addClient">
</form>
