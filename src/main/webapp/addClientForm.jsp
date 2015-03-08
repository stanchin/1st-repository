
<form id = "login" action="adminServlet" method="get">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" maxlength="30"></td>
        </tr>
        <tr>
            <td> Surname:</td>
            <td><input type="text" name="surname" maxlength="30"></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td> <input type="date" name="birthday"></td>
        </tr>
        <tr>
            <td>Passport:</td>
            <td><input type="text" name="passport" maxlength="15"></td>
        </tr>
        <tr>
            <td> Address:</td>
            <td><input type="text" name="address" maxlength="255"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td> <input type="email" name="email" maxlength="30"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" name="password" maxlength="30"></td>
        </tr>
        <tr>
            <td>Role</td>
            <td><input type="radio" name="roleId" value="1">Adm
            <input type="radio" name="roleId" value="2">Cl</td>
        </tr>
    </table>
    <input type="SUBMIT" name="action" value="addClient" class="myButton">
    <input type="hidden" name="action" value="addClient">
</form>
