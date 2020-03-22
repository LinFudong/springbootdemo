package org.linfd.interview.util;

import org.linfd.interview.entity.FileDO;
import org.springframework.util.ClassUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * file utils
 * including read and write
 */
public class FileUtil {

    private static final String FILE_SUFFIX = ".txt";

    /**
     * write files
     * @param fileDO
     */
    public static void write(FileDO fileDO) {
        try {
            String absolutePath = getAbsolutePath();
            String filePath = absolutePath + fileDO.getName() + FILE_SUFFIX;
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getName(),false);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(fileDO.getContent());
            bufferWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * returns absolute path
     * @return
     */
    public static String getAbsolutePath(){
        String absolutePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        return absolutePath;
    }

    /**
     * returns real file name
     * @param fileName
     * @return
     */
    public static String getRealFileName(String fileName){
        return getAbsolutePath() + fileName + FILE_SUFFIX;
    }
}
