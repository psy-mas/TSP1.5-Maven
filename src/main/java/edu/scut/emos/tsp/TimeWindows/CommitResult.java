package edu.scut.emos.tsp.TimeWindows;

import java.util.LinkedList;

public class CommitResult {
    private LinkedList<CacheResult> cacheResults;
    private LinkedList<String> recomputationOrderIDs;

    public CommitResult() {
    }

    public CommitResult(LinkedList<CacheResult> cacheResults, LinkedList<String> recomputationOrderIDs) {
        this.cacheResults = new LinkedList<>();
        this.cacheResults.addAll(cacheResults);
        this.recomputationOrderIDs = new LinkedList<>();
        this.recomputationOrderIDs.addAll(recomputationOrderIDs);
    }

    public LinkedList<CacheResult> getCacheResults() {
        return cacheResults;
    }

    public void setCacheResults(LinkedList<CacheResult> cacheResults) {
        this.cacheResults = cacheResults;
    }

    public LinkedList<String> getRecomputationOrderIDs() {
        return recomputationOrderIDs;
    }

    public void setRecomputationOrderIDs(LinkedList<String> recomputationOrderIDs) {
        this.recomputationOrderIDs = recomputationOrderIDs;
    }
}
