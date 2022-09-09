package com.most.web.resources.downloader.utils;

import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.SQLException;

@Component
public class BlobUtil {

    public byte[] retrieveBytes(Blob data) {
        byte[] result = new byte[0];
        if (data != null){
            try {
                result = data.getBytes(1, (int) data.length());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
