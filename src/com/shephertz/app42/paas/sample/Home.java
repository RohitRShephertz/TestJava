package com.shephertz.app42.paas.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.shephertz.app42.paas.sample.db.DBManager;

/**
 * Servlet implementation class Log This class fetches data from the database
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      This function fetches all data from the user table
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		new Thread() {
			public void run() {
				testCPU();
			}
		}.start();

		new Thread() {
			public void run() {
				testMem();
			}
		}.start();

	}

	private void testCPU() {
		try {
			calculate("rohit");
		} catch (Exception e) {
			calculate("deo");
		}
	}

	private void calculate(String i) {
		i = i.getBytes() + i;
		try {
			while (true) {
				i = i.concat("hello");
				i = i.getBytes() + i;
				System.out.println(i);
			}
		} catch (Exception e) {
			System.out.println(i);
			calculate("hello");
		}
	}

	private void testMem() {
		try {
			while (true) {
				new Thread().run();
			}
		} catch (Exception e) {
			Thread.dumpStack();
			testMem();
		}
	}

	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\

	}

}
