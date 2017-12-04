package edu.scut.emos.tsp.model;

import java.util.Date;

public class DWindowCommitResult {
    private String commitresultid;

    private Date committime;

    public String getCommitresultid() {
        return commitresultid;
    }

    public void setCommitresultid(String commitresultid) {
        this.commitresultid = commitresultid == null ? null : commitresultid.trim();
    }

    public Date getCommittime() {
        return committime;
    }

    public void setCommittime(Date committime) {
        this.committime = committime;
    }
}