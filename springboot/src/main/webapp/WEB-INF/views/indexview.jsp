<!DOCTYPE html>
<html>
<head>
<title>Hello Spring Boot!</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
  <div class="create">
	<form role="form" action="create" method="post">
		<table>
			<tr>
				<td><label for="name">Name</label></td>
				<td><input type="text" class="form-control" id="name"
					name="name" value="" placeholder="Enter name" /></td>
			</tr>
			<tr>
				<td><label for="InputEmail1">phonenumber</label></td>
				<td><input class="form-control" type="text" name="phoneNumber"
					value="" placeholder="Email" /></td>
			</tr>

			<tr>
				<td><label>Address</label></td>
				<td><textarea class="form-control" placeholder="Enter ..."
						rows="3" name="address"></textarea></td>
			</tr>
			<tr>
				<td><label for="dateofjoin">Age</label></td>
				<td><input type="text" class="form-control" id="Datejoin"
					name="age" value="" placeholder="age" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-primary" value="submit" /></td>
			</tr>
		</table>
	</form>
	</div>
	<div class="list">

	</div>
</body>
</html>