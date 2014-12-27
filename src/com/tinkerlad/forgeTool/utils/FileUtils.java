package com.tinkerlad.forgeTool.utils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class FileUtils {

    /**
     * By default File#delete fails for non empty directories, it works like "rm"
     * We need something a little more brutal - this does the equivalent of "rm -r"
     * @param path Root File Path
     * @return true iff the file and all sub files/directories have been removed
     * @throws FileNotFoundException
     */
    public static boolean deleteRecursive(File path) throws FileNotFoundException{
        if(!path.exists()) throw new FileNotFoundException(path.getAbsolutePath());
        boolean ret =true;
        if(path.isDirectory()){
            for(File f: path.listFiles()){
                ret = ret && FileUtils.deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }
}
