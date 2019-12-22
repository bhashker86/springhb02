<jsp:include page="include/header.jsp"></jsp:include>

<div class="container">

	<h3>${page_header}</h3>
	<p>
		<strong>${form_info}</strong>
	</p>


	<form:form method="post" action="add_employee_submit">
	<table >
	<tbody>
	<tr>
		<td> <label for="first_name">First Name:</label></td>
		<td> <form:input path="first_name" /></td>
	</tr>
	
	<tr>
		<td> <label for="last_name">Last Name:</label></td>
		<td> <form:input path="last_name" /></td>
	</tr>
	
	<tr>
		<td> <label for="email">Email:</label></td>
		<td> <form:input path="email" /></td>
	</tr>
	
	<tr>
		<td> <label for="demp">Department:</label></td>
		<td> <form:input path="demp" /></td>
	</tr>
	
	<tr>
		<td colspan="2"> <input type="submit" value="Add"    /></td>
		
	</tr>
	
	
	
	
	</tbody>
	
	</table>
	

	</form:form>
	
	 
</div>

<jsp:include page="include/footer.jsp"></jsp:include>
