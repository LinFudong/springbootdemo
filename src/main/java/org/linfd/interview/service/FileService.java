package org.linfd.interview.service;

import org.linfd.interview.entity.FileDO;
import org.linfd.interview.repository.FileRepository;
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
    public void edit(FileDO newFile){
        FileDO oldFile = fileRepository.findOne(newFile.getId());
        BeanUtils.copyProperties(oldFile,newFile, new String[]{"content"});
        FileUtil.write(newFile);
        fileRepository.save(newFile);
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
        fileDO.setLatestEditTime(System.currentTimeMillis());
        fileRepository.save(fileDO);
        return fileDO;
    }

    /**
     * Whether the file is editable
     * @param fileId
     * @return
     */
    public Boolean isEditable(Long fileId){
        FileDO fileDO =  fileRepository.findOne(fileId);
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
}
