package org.linfd.interview.controller;

import org.linfd.interview.entity.FileDO;
import org.linfd.interview.service.FileService;
import org.linfd.interview.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * forward to add page
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "file/index";
    }

    /**
     * save
     * @param file
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(FileDO file) {
        fileService.save(file);
        return "success";
    }

    /**
     * forward to the view page
     * @param map
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Map<String, Object> map) {
        List<FileDO> list = fileService.list();
        map.put("list", list);
        return "file/view";
    }

    /**
     * forward to the edit page
     * @param fileId
     * @param map
     * @return
     */
    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    public String toEdit(Long fileId, Map<String, Object> map){
        FileDO entity = fileService.getOne(fileId);
        map.put("entity", entity);
        return "file/toEdit";
    }


    /**
     * edit
     * @param file
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(FileDO file) {
        if(fileService.edit(file)) return "success";
        return "Other user is editing, please wait unit next minute!";
    }

    /**
     * download file
     * @param fileName
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/download")
    public String downloadFile(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {

        File file = new File(FileUtil.getRealFileName(fileName));

        // if file exists, then download it;
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the file successfully!");
            } catch (Exception e) {
                System.out.println("Download the file failed!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }
}
