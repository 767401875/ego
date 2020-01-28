package com.qsq.ego.beans;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FtpUtils {
    public static boolean uploadFile(String hostname, int port, String username, String password, String pathname, String remote, InputStream local){
        boolean flag = false;
        FTPClient client = new FTPClient();
        try {
            client.connect(hostname);
            client.login(username, password);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();

            if(!client.changeWorkingDirectory(pathname)){
                boolean t = client.makeDirectory(pathname);
                if(client.makeDirectory(pathname)){
                    client.changeWorkingDirectory(pathname);
                }
            }
            boolean tt = client.storeFile(remote, local);
            System.out.println("tt:" + tt);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                local.close();
                client.logout();
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("已上传至FTP服务器路径！");

        }
        return flag;
    }

    public static void main(String[] args) {
        String hostname = "106.12.34.249";
        int port = 21;
        String username = "ftpuser";
        String password = "Uestc2013";
        String pathname = "/home/ftpuser/jd123";
        String remote = "demo.jpg";
        try {
            InputStream local = new FileInputStream("C:\\Users\\qinshenqiang\\Downloads\\2.jpg");
            uploadFile(hostname, port, username, password, pathname, remote, local);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
