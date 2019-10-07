package com.awssupport.emrfilesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class S3Operations {


    private static String s3DefaultFS="s3://s3bucket";
    public static void main(String[] args) throws IOException, URISyntaxException {

        if(args.length <1){
            System.out.println("Please pass the path");
            System.exit(-1);
        }

        String path=args[0];
        FileSystem fs = getEMRFSFileSystem();

        FileStatus[] fileStatus = fs.listStatus(new Path(path));
        for(FileStatus status : fileStatus){
            System.out.println(status.getPath().toString());
        }

    }


    private static FileSystem getEMRFSFileSystem() throws IOException {
       // logger.info(BigdataYawlCodes.JOB_EVENT, Constants.MESSAGE, "Intializing HDFS File System.....");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", s3DefaultFS);
        conf.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", LocalFileSystem.class.getName());
        return FileSystem.get(URI.create(s3DefaultFS), conf);
    }
}
