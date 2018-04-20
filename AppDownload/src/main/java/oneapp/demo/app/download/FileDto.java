package oneapp.demo.app.download;

import java.sql.Blob;

/**
 * @author INC00718
 *
 */
public class FileDto {

	private String id;
	private String fileName;
	private String fileType;
	private Blob file;
	private String fileBase64;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the file
	 */
	public Blob getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(Blob file) {
		this.file = file;
	}

	/**
	 * @return the fileBase64
	 */
	public String getFileBase64() {
		return fileBase64;
	}

	/**
	 * @param fileBase64
	 *            the fileBase64 to set
	 */
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileDto [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", file=" + file
				+ ", fileBase64=" + fileBase64 + "]";
	}

}
