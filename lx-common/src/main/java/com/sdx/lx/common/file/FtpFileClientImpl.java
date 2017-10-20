package com.sdx.lx.common.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FtpFileClientImpl extends AbstractFileClientImpl {

	/**
	 * 路径分隔符
	 */
	private static final String DIR_SEPARATOR = "/";

	/**
	 * 
	 */
	@Autowired
	Properties properties;

	@Override
	public boolean upLoad(String localFileName, String path, String fileName) {
		boolean ftpFlag = true;
		String remotePath = path;
		if (!remotePath.endsWith(DIR_SEPARATOR)) {
			remotePath = remotePath + DIR_SEPARATOR;
		}
		String hostValue = properties.getProperty("ftp.host");
		String[] hosts = hostValue.split(",");

		try {
			for (String host : hosts) {
				InputStream in = null;
				try {
					in = new FileInputStream(localFileName);
					FTPClient ftpClient = new FTPClient();
					ftpClient.setControlEncoding("GBK");
					ftpClient.connect(host, Integer.parseInt(properties
							.getProperty("ftp.port")));
					boolean login = ftpClient.login(
							properties.getProperty("ftp.user"),
							properties.getProperty("ftp.password"));
					ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
					System.out.println("ftpClient  login:" + login);
					mkdir(ftpClient, remotePath);
					System.out.println("ftpClient  mkdir");
					ftpFlag = ftpClient.storeFile(fileName, in);
					System.out.println("ftpClient  storeFile:" + ftpFlag);
					if (ftpClient.isConnected()) {
						ftpClient.disconnect();
					}
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							// TODO log
						}
					}
				}
			}
		} catch (NumberFormatException | IOException e) {
			// TODO log
			ftpFlag = false;
		}
		return ftpFlag;

	}

	private void mkdir(FTPClient ftpClient, String path) throws IOException {
		if (!ftpClient.changeWorkingDirectory(path)) {
			int start = 0;
			int end = 0;
			// 相对路径是否以“/”开头
			if (path.startsWith(DIR_SEPARATOR)) {
				start = 1;
			} else {
				start = 0;
			}
			// 获取从start下标开始出现的第一个"/"的下标
			end = path.indexOf(DIR_SEPARATOR, start);
			while (true) {
				String subDirectory = path.substring(start, end);
				// 当前目录不存在
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					// 创建目录成功（makeDirectory创建成功会返回true）
					if (ftpClient.makeDirectory(subDirectory)) {
						// 指向当前新创建的目录
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						break;
					}
				}
				// 设置下级目录起始和结束下标
				start = end + 1;
				end = path.indexOf(DIR_SEPARATOR, start);
				// 已是最后一级目录
				if (end <= start) {
					break;
				}
			}
		}
	}

	@Override
	public boolean deleteFile(String localPathName) {
		// TODO Auto-generated method stub
		return false;
	}
}
