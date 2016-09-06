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

/**
 * Servlet implementation class UploadImageServlet
 */
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
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to
		// disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		// constructs the folder where uploaded file will be stored
		String uploadFolder = "C:\\Users\\Admin\\git\\EasyShopWayNew\\data";

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);
		try {
			// Parse the request
			List items = upload.parseRequest(request);
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

			// displays done.jsp page after upload finished
			// getServletContext().getRequestDispatcher("/done.jsp").forward(
			// request, response);

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
