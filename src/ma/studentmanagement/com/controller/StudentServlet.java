package ma.studentmanagement.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.studentmanagement.com.dao.StudentDao;
import ma.studentmanagement.com.model.Student;

@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;

	public void init() {
		studentDao = new StudentDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertStudent(request, response);
				break;
			case "/delete":
				deleteStudent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStudent(request, response);
				break;
			case "/search":
				searchStudent(request, response);
				break;
			default:
				listStudent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Student> listStudent = studentDao.selectAllStudents();
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/studentList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void searchStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String last_name = request.getParameter("last_name");
		List<Student> listStudent = studentDao.selectStudentName(last_name);
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/studentList.jsp");
		dispatcher.forward(request, response);
		//Student s1 = studentDao.findStudentByName(last_name);
		//request.setAttribute("oneStudent", s1);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("view/oneStudent.jsp");
		//dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/studentForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent = studentDao.selectStudent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/studentForm.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);
	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String student_id = request.getParameter("student_id");
		String school = request.getParameter("school");
		String study_option = request.getParameter("study_option");
		String registration_year = request.getParameter("registration_year");
		Student newStudent = new Student(first_name, last_name, student_id, school, study_option, registration_year);
		studentDao.insertStudent(newStudent);
		response.sendRedirect("list");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String student_id = request.getParameter("student_id");
		String school = request.getParameter("school");
		String study_option = request.getParameter("study_option");
		String registration_year = request.getParameter("registration_year");
		Student s1 = new Student(id, first_name, last_name, student_id, school, study_option, registration_year);
		studentDao.updateStudent(s1);
		response.sendRedirect("list");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.deleteStudent(id);
		response.sendRedirect("list");
	}


}
