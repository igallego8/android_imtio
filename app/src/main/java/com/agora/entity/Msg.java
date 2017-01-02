package com.agora.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Ivan on 18/11/15.
 */
public class Msg implements Serializable{

    private Target[] targets;
    private Long origin;
    private String text;
    private String imageURL;
    private Long needKey;


    public void addTarget(Target target){
        if (targets==null){
            targets= new Target[1];
            targets[0]=target;
        }else{
            targets=Arrays.copyOf(targets,targets.length+1);
            targets[targets.length-1]=target;
        }

    }

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("msg:{");
        if (targets!=null && targets.length>0) {
            buffer.append("targets:").append("[{");
            for (int index=0;index<targets.length;index++){
                buffer.append(targets[index].toString());
                if(index!=targets.length-1){
                    buffer.append(",");
                }
            }
            buffer.append("}]");
        }
        buffer.append(",");
        if (origin!=null) {
            buffer.append("origin:").append(origin.toString()).append(",");
        }

        if (imageURL!=null) {
            buffer.append("imageURL:").append(imageURL).append(",");
        }

        if (needKey!=null) {
            buffer.append("needKey:").append(needKey.toString()).append(",");
        }

        if (text!=null) {
            buffer.append("text:").append(text);
        }

        buffer.append("}");
        return buffer.toString();
    }

    public Target[] getTargets() {
        return targets;
    }

    public void setTargets(Target[] targets) {
        this.targets = targets;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getNeedKey() {
        return needKey;
    }

    public void setNeedKey(Long needKey) {
        this.needKey = needKey;
    }
}
