package org.linfd.interview.service;

import org.linfd.interview.entity.FileDO;
import org.linfd.interview.repository.FileRepository;
import org.linfd.interview.util.CommonUtil;
import org.linfd.interview.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    //one minute
    private static final Long MINUTE = 60000L;


    /**
     * save entity
     * @param file
     */
    public void save(FileDO file){
        FileUtil.write(file);
        fileRepository.save(file);
    }

    /**
     * edit entity
     * @param newFile
     */
    public boolean edit(FileDO newFile){
        if(!isEditableBeforeEdit(newFile)) return false;
        FileDO oldFile = fileRepository.findOne(newFile.getId());
        BeanUtils.copyProperties(oldFile,newFile, new String[]{"content"});
        FileUtil.write(newFile);
        newFile.setLatestEditTime(null);
        newFile.setUuid(null);
        fileRepository.save(newFile);
        return true;
    }

    /**
     * returns all files
     * @return
     */
    public List<FileDO> list(){
        return fileRepository.findAll();
    }

    /**
     * returns specific entity
     * @param fileId
     * @return
     */
    public FileDO getOne(Long fileId){
        FileDO fileDO =  fileRepository.findOne(fileId);
        fileDO.setUuid(CommonUtil.getUUID32());
        //if the file can be edited, save uuid and edit time
        if(isEditable(fileDO)){
            fileDO.setLatestEditTime(System.currentTimeMillis());
            fileRepository.save(fileDO);
            fileRepository.flush();
        }
        return fileDO;
    }

    /**
     * Whether the file is editable
     * @param fileDO
     * @return
     */
    public Boolean isEditable(FileDO fileDO){
        if(null == fileDO.getLatestEditTime()){
            return true;
        }
        Long latestEditTime = fileDO.getLatestEditTime();
        Long now = System.currentTimeMillis();
        if(now - latestEditTime > MINUTE){
            return true;
        }
        return false;
    }

    /**
     * validates before edit
     * @param fileDO
     * @return
     */
    public Boolean isEditableBeforeEdit(FileDO fileDO){
        FileDO oldFile =  fileRepository.findOne(fileDO.getId());
        if(fileDO.getUuid().equals(oldFile.getUuid())){
            return true;
        }
        return isEditable(oldFile);
    }

}
