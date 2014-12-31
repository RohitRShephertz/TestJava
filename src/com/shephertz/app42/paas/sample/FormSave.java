package com.shephertz.app42.paas.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shephertz.app42.paas.sample.db.DBManager;

/**
 * Servlet implementation class FormSave This class saves the data into database
 */
public class FormSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormSave() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String startTime = new java.sql.Timestamp(
				new java.util.Date().getTime()).toString();

		try {
			String query = "select * from user";
			ArrayList<Map<String, Object>> list = DBManager.getInstance()
					.select(query);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> obj = (Map<String, Object>) list.get(i);
				out.print(obj);
				out.print("</br>");
			}
		} catch (Exception e) {

		}
		String stopTime = new java.sql.Timestamp(new java.util.Date().getTime())
				.toString();

		System.out.println("======Request Time: " + startTime
				+ " ======= Response Time: " + stopTime + " ========="
				+ getTotalHours(startTime, stopTime));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      This functions saves the data into user table
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get request parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String description = request.getParameter("description");

		try {
			// insert query
			String query = "INSERT INTO user(name,description,email) VALUES('"
					+ name + "', '" + description + "', '" + email + "')";
			System.out.println("Query: " + query);
			// Database Manager called
			DBManager db = new DBManager();
			db.insert(query);
			// Redirect to new url
			String newUrl = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/home";
			response.setStatus(response.SC_MOVED_PERMANENTLY);
			response.setHeader("Location", newUrl);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!doctype html><html><head><meta charset='utf-8'><title>App42 Sample Java-MySql Application</title><link href='css/style-User-Input-Form.css' rel='stylesheet' type='text/css'></head><body><div class='App42PaaS_header_wrapper'><div class='App42PaaS_header_inner'><div class='App42PaaS_header'><div class='logo'><a href='http://app42paas.shephertz.com'><img border='0' alt='App42PaaS' src='images/logo.png'></img></a></div></div></div></div><div class='App42PaaS_body_wrapper'><div class='App42PaaS_body'><div class='App42PaaS_body_inner'><div class='contactPage_title'>");
			out.print("<h2 align='center'>Error occured. See Logs.</h2><br/><br/>");
			out.print("<br/><a href='/' style='font-size: 18px;'>Back</a>");
			out.print("</div></div></div></div></body></html>");
		}

	}

	public static String getTotalHours(String dateStart, String dateStop) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date d1 = null;
		Date d2 = null;
		long result = 0;
		String res = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			Calendar instance1 = Calendar.getInstance();
			instance1.setTime(d1);
			// instance1.set(Calendar.MINUTE, 0);
			// instance1.set(Calendar.SECOND, 0);
			// System.out.println(d1+" : "+instance1.getTime());

			Calendar instance2 = Calendar.getInstance();
			instance2.setTime(d2);
			// instance2.add(Calendar.HOUR_OF_DAY, 1);
			// instance2.set(Calendar.MINUTE, 0);
			// instance2.set(Calendar.SECOND, 0);
			// System.out.println(d2+" : "+instance2.getTime());

			// System.out.println(d1);
			// System.out.println(d2);
			// in milliseconds

			long diff = instance2.getTimeInMillis()
					- instance1.getTimeInMillis();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			// System.out.print(diffDays + " days, ");
			// System.out.print(diffHours + " hours, ");
			// System.out.print(diffMinutes + " minutes, ");
			// System.out.print(diffSeconds + " seconds.");

			res = "Days: " + diffDays + " Hours: " + diffHours + " Minutes: "
					+ diffMinutes + " Seconds: " + diffSeconds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
