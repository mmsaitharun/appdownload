package oneapp.demo.dft.app;

import java.io.IOException;

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
public class TAppDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TAppDownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileType = request.getParameter("fileType");
		if(!AppUtils.isEmpty(fileType)) {
			FileDto fileDto = null;
			FileDto responseFileDto = null;
			try {
				fileDto = new FileDto();
				fileDto.setFileType(fileType);
				responseFileDto = AppUtils.getFileDetail(fileDto);
				if(!AppUtils.isEmpty(responseFileDto)) {
					response = AppUtils.downloadFile(response, responseFileDto);
				}
			} catch (Exception e) {
				System.err.println("Exception : "+e.getMessage());
			}
		}
	}
}
