package edu.scut.emos.tsp.time_windows;

import java.util.LinkedList;

public class CommitResult {
    private LinkedList<CacheResult> cacheResults;
    private LinkedList<FailedOrder> failedOrderIDs;

    public CommitResult() {
        cacheResults = new LinkedList<>();
        failedOrderIDs = new LinkedList<>();
    }

    public CommitResult(LinkedList<CacheResult> cacheResults, LinkedList<FailedOrder> failedOrderIDs) {
        this.cacheResults = new LinkedList<>();
        if (cacheResults != null) {
            this.cacheResults.addAll(cacheResults);
        }

        this.failedOrderIDs = new LinkedList<>();
        if (failedOrderIDs != null) {
            this.failedOrderIDs.addAll(failedOrderIDs);
        }
    }

    public LinkedList<CacheResult> getCacheResults() {
        return cacheResults;
    }

    public void setCacheResults(LinkedList<CacheResult> cacheResults) {
        if (cacheResults != null) {
            this.cacheResults.addAll(cacheResults);
        }
    }

    public LinkedList<FailedOrder> getFailedOrderIDs() {
        return failedOrderIDs;
    }

    public void setFailedOrderIDs(LinkedList<FailedOrder> failedOrderIDs) {
        if (failedOrderIDs == null) {
            this.failedOrderIDs = new LinkedList<>();
        } else {
            this.failedOrderIDs = failedOrderIDs;
        }
    }

    @Override
    public String toString() {
        return "CommitResult {\n" +
                "   cacheResults: " + cacheResults.toString() + "\n" +
                "   failedOrderIDs: " + failedOrderIDs.toString() + "\n" +
                '}';
    }
}
