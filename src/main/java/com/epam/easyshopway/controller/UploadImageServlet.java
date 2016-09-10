package com.epam.easyshopway.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;
import com.epam.easyshopway.utils.CheckImage;

public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 5;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(MAX_REQUEST_SIZE);
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			FileItem item = (FileItem) iter.next();

			if (!item.isFormField()) {
				File deleteFile = new File(getServletContext().getRealPath("/" + user.getImage()));
				if (deleteFile.exists())
					deleteFile.delete();

				String type = "" + item.getName().substring(item.getName().lastIndexOf('.') + 1);
				String fName = "images/user/" + user.getId() + "." + type;
				String absoluteDiskPath = getServletContext().getRealPath("/" + fName);
				File uploadedFile = new File(absoluteDiskPath);
				boolean b = CheckImage.checkSignature(item.getInputStream(), type);
				System.out.println(b);
				if (b)
					item.write(uploadedFile);

				UserService.updatePicture(user.getId(), fName);
				user = UserService.getById(user.getId());
				session.invalidate();
				request.getSession(true).setAttribute("user", user);
			}

		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Before redirect");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/EasyShopWayNew/cabinet#/");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

}
