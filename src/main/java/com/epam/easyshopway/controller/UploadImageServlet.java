package com.epam.easyshopway.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
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
	private static final int MAX_REQUEST_SIZE = -1 ;//1024 * 1024 * 10;

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
		System.out.println(upload.getSizeMax());
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			FileItem item = (FileItem) iter.next();

			if (!item.isFormField()) {
//				File deleteFile = null;
//				try {
				File deleteFile = new File(getServletContext().getRealPath("/" + user.getImage()));
//				} catch (NullPointerException e) {
//					System.out.println("no image");
//				}
				String type = "" + item.getName().substring(item.getName().lastIndexOf('.') + 1);
				String fName = "images/user/" + user.getId() + "." + type;
				String absoluteDiskPath = getServletContext().getRealPath("/" + fName);
				File uploadedFile = new File(absoluteDiskPath);
				try (InputStream input = item.getInputStream()) {
					// It's an image (only BMP, GIF, JPG and PNG are
					// recognized).
					ImageIO.read(input).toString();
					item.write(uploadedFile);
					request.getSession(false).setAttribute("message", "Image loaded successfully");
					System.out.println("Image download successful");
				} catch (Exception e) {
					// It's not an image.
					System.out.println("Image download failed");
					request.getSession(false).setAttribute("message", "Image loading failed");
					throw e;
				}
				
				if (uploadedFile.exists() && deleteFile.exists())
					deleteFile.delete();

				UserService.updatePicture(user.getId(), fName);
				user = UserService.getById(user.getId());
				request.getSession(false).setAttribute("user", user);
			}

		} catch (FileUploadException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Before redirect");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/EasyShopWayNew/cabinet#/");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

}
