package edu.scut.emos.tsp.TimeWindows;

import java.util.LinkedList;

public class CommitResult {
    private LinkedList<CacheResult> cacheResults;
    private LinkedList<String> recomputationOrderIDs;
    private LinkedList<String> noMatchingOrderIDs;     // 没有车辆可匹配的订单列表

    public CommitResult() {
        cacheResults = new LinkedList<>();
        recomputationOrderIDs = new LinkedList<>();
        noMatchingOrderIDs = new LinkedList<>();
    }

    public CommitResult(LinkedList<CacheResult> cacheResults, LinkedList<String> recomputationOrderIDs, LinkedList<String> noMatchingOrderIDs) {
        this.cacheResults = new LinkedList<>();
        if (cacheResults != null) {
            this.cacheResults.addAll(cacheResults);
        }

        this.recomputationOrderIDs = new LinkedList<>();
        if (recomputationOrderIDs != null) {
            this.recomputationOrderIDs.addAll(recomputationOrderIDs);
        }

        this.noMatchingOrderIDs = new LinkedList<>();
        if (noMatchingOrderIDs != null) {
            this.noMatchingOrderIDs.addAll(noMatchingOrderIDs);
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

    public LinkedList<String> getRecomputationOrderIDs() {
        return recomputationOrderIDs;
    }

    public void setRecomputationOrderIDs(LinkedList<String> recomputationOrderIDs) {
        if (recomputationOrderIDs != null) {
            this.recomputationOrderIDs.addAll(recomputationOrderIDs);
        }
    }

    public LinkedList<String> getNoMatchingOrderIDs() {
        return noMatchingOrderIDs;
    }

    public void setNoMatchingOrderIDs(LinkedList<String> noMatchingOrderIDs) {
        if (noMatchingOrderIDs != null) {
            this.noMatchingOrderIDs.addAll(noMatchingOrderIDs);
        }
    }

    @Override
    public String toString() {
        return "CommitResult {\n" +
                "   cacheResults: " + cacheResults.toString() + "\n" +
                "   recomputationOrderIDs: " + recomputationOrderIDs.toString() + "\n" +
                "   noMatchingOrderIDs: " + noMatchingOrderIDs.toString() + "\n" +
                '}';
    }
}
