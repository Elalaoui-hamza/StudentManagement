<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Management</title>
<style>
.card {
	padding: 30px 350px;
}

.card-body {
	width: 500px;
	height: 800px;
	border: 1px solid black;
}

h2 {
	text-align: center;
	font-family: Arial
}

.form-group {
	padding: 15px;
	border: none;
}

.form-input {
	display: block;
	width: 100%;
	height: calc(1.5em+.75rem+2px);
	font-size : 18px;
	font-family : Arial;
	color: #495057;
	background-color : #fff;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	margin-left: 2px;
}

label {
	font-family: Arial;
	font-size: 20px;
}

.button {
	background-color: #4CAF50; /* Green */
	color: white;
	padding: 10px 32px;
	text-align: center;
	text-decoration: none;
	display: inline -block;
	font-size: 16px;
	font-family: Arial;
	margin: 6px 2px;
	cursor: pointer;
	border-radius: 10px;
	border: 2px solid #1c7430
}

.button:hover {
	background-color: #1e7e34;
}
</style>
</head>
<body>
	<br>
	<div class="card">
		<div class="card-body">
			<c:if test="${student != null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${student == null}">
				<form action="insert" method="post">
			</c:if>
			<caption>
				<h2>
					<c:if test="${student != null}">
						Edit Student
					</c:if>
					<c:if test="${student == null}">
						Add New Student
					</c:if>
				</h2>
			</caption>
			<c:if test="${student != null}">
				<input type="hidden" name="id" value="<c:out value='${student.id}'/>"/>
			</c:if>
			<fieldset class="form-group">
				<label>First Name</label> <input type="text" class="form-input"
					value="<c:out value='${student.firstName}' />" name="first_name"
					required="required">
			</fieldset>
			<fieldset class="form-group">
				<label>Last Name</label> <input type="text" class="form-input"
					value="<c:out value='${student.lastName}' />" name="last_name"
					required="required">
			</fieldset>
			<fieldset class="form-group">
				<label>Student ID</label> <input type="text" class="form-input"
					value="<c:out value='${student.studentId}' />" name="student_id"
					required="required">
			</fieldset>
			<fieldset class="form-group">
				<label>School</label> <input type="text" class="form-input"
					value="<c:out value='${student.school}' />" name="school"
					required="required">
			</fieldset>
			<fieldset class="form-group">
				<label>Study Option</label> <input type="text" class="form-input"
					value="<c:out value='${student.studyOption}' />"
					name="study_option" required="required">
			</fieldset>
			<fieldset class="form-group">
				<label>Registration Year</label> <select type="text"
					class="form-input" name="registration_year" required="required">
					<c:forEach begin="2015" end="2021" var="year">
						<option value="${year}"
							<c:if test="${student.registrationYear == year}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</fieldset>
			<fieldset class="form-group">
				<button type="submit" class="button">Save</button>
			</fieldset>
			</form>
		</div>
	</div>
</body>
</html>