package edu.scut.emos.tsp.model;

public class DWindowCommitFailedOrder {
    private String commitfailedorderid;

    private String commitresultid;

    private String failedorderid;

    private Integer type;

    public String getCommitfailedorderid() {
        return commitfailedorderid;
    }

    public void setCommitfailedorderid(String commitfailedorderid) {
        this.commitfailedorderid = commitfailedorderid == null ? null : commitfailedorderid.trim();
    }

    public String getCommitresultid() {
        return commitresultid;
    }

    public void setCommitresultid(String commitresultid) {
        this.commitresultid = commitresultid == null ? null : commitresultid.trim();
    }

    public String getFailedorderid() {
        return failedorderid;
    }

    public void setFailedorderid(String failedorderid) {
        this.failedorderid = failedorderid == null ? null : failedorderid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}