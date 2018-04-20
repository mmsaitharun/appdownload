package com.murphy.iop.app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.murphy.iop.util.IOPAppUtils;
import com.murphy.iop.util.IOPFileDto;

/**
 * @author INC00718
 *
 */
public class IOPDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IOPDownloadFileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileType = request.getParameter("fileType");
		String fileF = request.getParameter("file");

		IOPFileDto requestDto = null;
		IOPFileDto fileDto = null;

		if (!IOPAppUtils.isEmpty(fileType) && !IOPAppUtils.isEmpty(fileF)) {
			try {
				requestDto = new IOPFileDto();
				requestDto.setFileType(fileType);
				requestDto.setFileName(fileF);
				fileDto = IOPAppUtils.getGenericFileDetail(requestDto);
				if(!IOPAppUtils.isEmpty(fileDto)) {
					response = IOPAppUtils.downloadFile(response, fileDto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
