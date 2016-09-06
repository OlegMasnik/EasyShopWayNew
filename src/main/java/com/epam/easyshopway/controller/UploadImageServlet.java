package com.epam.easyshopway.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		
		String uploadFolder = "C:\\Users\\kubic\\git\\EasyShopWayNew\\data";

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(MAX_REQUEST_SIZE);
		try {
			List items = upload.parseRequest(request);
			System.out.println(items);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					String type = "" + item.getName().substring(item.getName().lastIndexOf('.') + 1);
					String fileName = user.getId().toString();
					StringBuilder sBuilder = new StringBuilder();
					sBuilder.append(uploadFolder);
					sBuilder.append(File.separator);
					sBuilder.append(fileName);
					sBuilder.append(".");
					sBuilder.append(type);
					File uploadedFile = new File(sBuilder.toString());
					UserService.updatePicture(user.getId(), sBuilder.toString());
					System.out.println(sBuilder.toString());
					item.write(uploadedFile);
				}
			}


		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

}
