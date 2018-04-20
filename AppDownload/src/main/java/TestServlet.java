import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String filePath = "\\src\\main\\resources\\Textfile.zip";
		File file = new File(System.getProperty("user.dir")+filePath);
		FileInputStream fi = new FileInputStream(file);
		String fileName = "TextFile";
		String fileTy = "zip";
		
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "_1.0"+"."+ fileTy.toLowerCase() + "\"");
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(fi.toString());
		BufferedInputStream bufferInStream = new BufferedInputStream(fis);
		int cnt;
		while ((cnt = bufferInStream.read()) != -1) {
		    os.write(cnt);
		}
		bufferInStream.close();
        response.flushBuffer();
        fi.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
