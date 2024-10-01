/*
package com.vernon.poppy.util;

import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class JobApi {




    static  JenkinsServer jenkinsServer ;

    */
/**
     * 连接jerkins
     *//*

    private static JenkinsServer build(Jenkins jenkins){
        try {
            if(jenkinsServer == null){
//                jenkinsServer = new JenkinsServer(new URI("http://stuq.ceshiren.com:8080/"),
//                        "hogwarts", "hogwarts123");
                jenkinsServer = new JenkinsServer(new URI(jenkins.getUrl()),
                        jenkins.getUsername(), jenkins.getPassword());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return jenkinsServer;
    }

    //        jenkinsServer.createJob("atest11111111",jobConfigXml,true);
//        jenkinsServer.getJob("atest11111111").build(true);

    */
/**
     * 创建job
     *//*


    public static void createJob(Jenkins jenkins){

        try {
            ClassPathResource classPathResource = new ClassPathResource(jenkins.getXmlpath());
            InputStream inputStream = classPathResource.getInputStream();
            String text = FileUtil.getText(inputStream);
            build(jenkins).createJob(jenkins.getJobName(),text,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //构建无参数job
    public static void buildJob(Jenkins jenkins){

        try {
            build(jenkins).getJob(jenkins.getJobName()).build(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //构建有参数的job
    public static void buildWithParamJob(Jenkins jenkins){

        try {
            build(jenkins).getJob(jenkins.getJobName()).build(jenkins.getParam(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

*/
