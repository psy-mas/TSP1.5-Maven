package edu.scut.emos.tsp.model;

public class DWindowCommitCacheResult {
    private String commitcacheresultid;

    private String commitresultid;

    private String cacheresultid;

    private Byte isvalid;

    public String getCommitcacheresultid() {
        return commitcacheresultid;
    }

    public void setCommitcacheresultid(String commitcacheresultid) {
        this.commitcacheresultid = commitcacheresultid == null ? null : commitcacheresultid.trim();
    }

    public String getCommitresultid() {
        return commitresultid;
    }

    public void setCommitresultid(String commitresultid) {
        this.commitresultid = commitresultid == null ? null : commitresultid.trim();
    }

    public String getCacheresultid() {
        return cacheresultid;
    }

    public void setCacheresultid(String cacheresultid) {
        this.cacheresultid = cacheresultid == null ? null : cacheresultid.trim();
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }
}