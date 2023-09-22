<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Admin</title>
  </head>
  <body>
    <h1 style="color: green">${pos}</h1>
    <h1 style="color: red">${neg}</h1>
    <h1>This is Admin Page</h1>

    <form action="/admin/login" method="post">
      <fieldset>
        <legend>Login Here,</legend>
        <table>
          <tr>
            <th>Email:</th>
            <th><input type="email" name="email" required="required" /></th>
          </tr>
          <tr>
            <th>Password:</th>
            <th>
              <input type="password" name="password" required="required" />
            </th>
          </tr>
          <tr>
            <th><button>Login</button></th>
            <th><button type="reset">Cancel</button></th>
          </tr>
        </table>
      </fieldset>
    </form>
    <br />
    <a href="/"><button>Back</button></a>
  </body>
</html>
