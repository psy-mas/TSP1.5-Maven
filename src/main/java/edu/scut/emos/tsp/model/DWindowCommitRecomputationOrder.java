package edu.scut.emos.tsp.model;

/**
 * @author emos
 *
 */
public class DWindowCommitRecomputationOrder {
    private String commitrecomputationorderid;

    private String commitresultid;

    private String recomputationorderid;

    public String getCommitrecomputationorderid() {
        return commitrecomputationorderid;
    }

    public void setCommitrecomputationorderid(String commitrecomputationorderid) {
        this.commitrecomputationorderid = commitrecomputationorderid == null ? null : commitrecomputationorderid.trim();
    }

    public String getCommitresultid() {
        return commitresultid;
    }

    public void setCommitresultid(String commitresultid) {
        this.commitresultid = commitresultid == null ? null : commitresultid.trim();
    }

    public String getRecomputationorderid() {
        return recomputationorderid;
    }

    public void setRecomputationorderid(String recomputationorderid) {
        this.recomputationorderid = recomputationorderid == null ? null : recomputationorderid.trim();
    }
}