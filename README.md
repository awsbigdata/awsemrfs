# awsemrfs
awsemrfs

how to read s3 filesystem using hadoop api

1. change the fs.defaultFS to s3 path 
2. create a jar using gradle tool
3. copy the jar to emr master node
4. run following commad to list the files.

java -cp $(hadoop classpath):awsemrfs.jar com.awssupport.emrfilesystem.S3Operations  `<s3 path>`
