package sunil.project2;

/**
 * Created by sunil on 7/26/16.
 */
public class Incidents {

    private String report;
    private String location;
    private String ownerStatus;
    private boolean fatal;

    public Incidents(String r, String l, String os, boolean f){
        report = r;
        location = l;
        ownerStatus = os;
        fatal = f;

    }

    public String getOwnerStatus() {return ownerStatus;}

    public void setOwnerStatus(String ownerStatus) {this.ownerStatus = ownerStatus;}

    public String getReport() {return report;}

    public void setReport(String report) {this.report = report;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}

    public boolean isFatal() {return fatal;}

    public void setFatal(boolean fatal) {this.fatal = fatal;}

}
