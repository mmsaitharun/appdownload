package oneapp.demo.dft.app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.murphy.dft.util.AppUtils;
import com.murphy.dft.util.FileDto;

/**
 * @author INC00718
 *
 */
public class TDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TDownloadFileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileType = request.getParameter("fileType");
		String fileF = request.getParameter("file");

		FileDto requestDto = null;
		FileDto fileDto = null;

		if (!AppUtils.isEmpty(fileType) && !AppUtils.isEmpty(fileF)) {
			try {
				requestDto = new FileDto();
				requestDto.setFileType(fileType);
				requestDto.setFileName(fileF);
				fileDto = AppUtils.getGenericFileDetail(requestDto);
				if(!AppUtils.isEmpty(fileDto)) {
					response = AppUtils.downloadFile(response, fileDto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
