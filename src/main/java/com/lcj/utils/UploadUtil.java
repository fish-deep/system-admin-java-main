package com.lcj.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;


@SuppressWarnings("all")//抑制警告
public class UploadUtil {
    public static String uploadImg(MultipartFile file){
        Calendar calendar = Calendar.getInstance();
        String prePath = "/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1);
        File trueFile = new File("E:\\img" + prePath);
        if (!trueFile.exists()){
            if (!trueFile.mkdirs()){
                return null;
            }
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StrUtil.isEmpty(extension)){
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        String filename = uuid + "." + extension;
        try {
            file.transferTo(new File("E:\\img" + prePath + "/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prePath + "/" + filename;
    }
}
