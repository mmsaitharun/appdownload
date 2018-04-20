package com.murphy.iop.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.murphy.iop.util.IOPAppUtils;
import com.murphy.iop.util.IOPDBConnector;

/**
 * @author INC00718
 *
 */
public class IOPUploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IOPUploadFileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		IOPAppUtils.appendGenericForm(response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = "";
		String fileType = "";

		InputStream fileContent = null;

		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					switch (fieldName) {
					case ("fileName"):
						fileName = fieldValue;
						break;
					case ("fileType"):
						fileType = fieldValue;
						break;
					}
				} else {
					fileContent = item.getInputStream();
					if ((item.getInputStream() == null) && (item.getSize() <= 0)) {
						fileContent = null;
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
		}
		if(!IOPAppUtils.isEmpty(fileName) && !IOPAppUtils.isEmpty(fileType) && !IOPAppUtils.isEmpty(fileContent)) {
			try {
				PreparedStatement pstmt = IOPDBConnector.getInstance()
						.insert("INSERT INTO APP_FILE_IOP(ID, FILE_NAME, FILE_TYPE, FILE) VALUES(?, ?, ?, ?)");
				pstmt.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
				pstmt.setString(2, fileName);
				pstmt.setString(3, fileType.toUpperCase());
				pstmt.setBlob(4, fileContent);
				int update = pstmt.executeUpdate();
				System.err.println("Update : " + update);

				response.getWriter()
						.write("Inserting Application Successful! FileName : " + fileName + " File Type : " + fileType);
			} catch (Exception e) {
				System.err.println("Exception : " + e.getMessage());
			}
		}
	}
}
