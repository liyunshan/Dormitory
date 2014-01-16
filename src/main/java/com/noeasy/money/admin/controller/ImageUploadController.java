/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.admin.controller;

import com.noeasy.money.constant.Constants;
import com.noeasy.money.util.servlet.HttpServletUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author acer
 */
@Controller
public class ImageUploadController {

    private Log logging = LogFactory.getLog(ImageUploadController.class);

    @RequestMapping(value = "/admin/uploadImage.html")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        if (HttpServletUtil.isPost(request)) {
            logging.info("============================= Upload Image Start =============================");
            Map jsonMap = new HashMap();
            jsonMap.put("status", "SUCCESS");
            Long itemID = Long.valueOf(request.getParameter("itemID"));
            logging.info("itemID: " + itemID);
            List<File> uploadFiles = new ArrayList<File>();
            long fileSizeMax = 1024 * 1000; // Image File max size 100kb.
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(fileSizeMax);
            FileItem fileItem = null;
            // TODO: config FILE_TEMPORAY_PATH
            // Item item = itemService.get(itemId);
            // String filePath = item.getItemTempPath();
            String webRoot = WebUtils.getRealPath(request.getSession().getServletContext(), "/");
            String tempFilePath = webRoot + Constants.IMAGE_STORAGE_PATH + itemID.toString()
                    + File.separator;// Path to store file on local system
            logging.info("Temp file path: " + tempFilePath);
            List uploadedItems = null;
            try {
                uploadedItems = upload.parseRequest(request); //if files are too big, this line would blow up

                Iterator i = uploadedItems.iterator();

                while (i.hasNext()) {
                    fileItem = (FileItem) i.next();
                    if (fileItem.isFormField() == false) {
                        if (fileItem.getSize() > 0) {
                            File uploadedFile = null;
                            String fieldName = fileItem.getFieldName();
                            logging.info("File fieldName: " + fieldName);// *.png
                            logging.info("File name: " + fileItem.getName());
                            // Create new File object
                            File tempDirctory = new File(tempFilePath);
                            if (!tempDirctory.exists()) {
                                tempDirctory.mkdirs();
                            }
//                            String fileName = fieldName + fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
                            uploadedFile = new File(tempFilePath, fieldName);
                            if (!uploadedFile.exists()) {
                                uploadedFile.createNewFile();
                            }
                            logging.debug("uploadedFile path: " + uploadedFile.getPath());
                            uploadFiles.add(uploadedFile);
                            // Write the uploaded file to the system
                            fileItem.write(uploadedFile);
                        }
                    }
                }
                if (null != uploadFiles && uploadFiles.size() > 0) {
                    File tempImageFile = uploadFiles.get(0);
                    String imageURL = HttpServletUtil.getWebsite(request)
                            + Constants.IMAGE_STORAGE_PATH + itemID.toString()
                            + "/" + tempImageFile.getName();
                    jsonMap.put("imageURL", imageURL);

                } else {
                    jsonMap.put("status", "FAILED");
                    jsonMap.put("errorMessage", "No File Upload");
                }
            } catch (FileSizeLimitExceededException ex) {
                // logging
                logging.error(ex, ex);
                jsonMap.put("status", "FAILED");
                jsonMap.put("errorMessage", "File overSize. Max size is 100KB");
            } catch (Exception ex) {
                logging.error(ex, ex);
                jsonMap.put("status", "FAILED");
                jsonMap.put("errorMessage", "Upload failed");
            }
            StringBuilder xmlStr = new StringBuilder();
            xmlStr.append("<result>");
            xmlStr.append("<status id='status'>");
            xmlStr.append(jsonMap.get("status"));
            xmlStr.append("</status>");
            xmlStr.append("<imageURL id='imageURL'>");
            xmlStr.append(jsonMap.get("imageURL"));
            xmlStr.append("</imageURL>");
            xmlStr.append("<errorMessage id='errorMessage'>");
            xmlStr.append(jsonMap.get("errorMessage"));
            xmlStr.append("</errorMessage>");
            xmlStr.append("</result>");
            HttpServletUtil.populateWithXMl(response, xmlStr.toString());
            logging.info("============================= Upload Image End =============================");
        }
    }
}
