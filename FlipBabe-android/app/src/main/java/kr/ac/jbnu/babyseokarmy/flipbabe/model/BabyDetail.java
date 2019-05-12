package kr.ac.jbnu.babyseokarmy.flipbabe.model;

public class BabyDetail {
    String startTime;
    String endTime;
    String problemType;

    public BabyDetail() {
    }

    public BabyDetail(String startTime, String endTime, String problemType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.problemType = problemType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }
}