package test_data.computer;

public class ComputerData {
    private String processorType;
    private String ram;
    private String os;
    private String hdd;
    private String software;

    public ComputerData(String processorType, String ram, String os, String hdd, String software) {
        this.processorType = processorType;
        this.ram = ram;
        this.os = os;
        this.hdd = hdd;
        this.software = software;
    }

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    @Override
    public String toString() {
        return "ComputerData{" +
                "processorType='" + processorType + '\'' +
                ", ram='" + ram + '\'' +
                ", os='" + os + '\'' +
                ", hdd='" + hdd + '\'' +
                ", software='" + software + '\'' +
                '}';
    }
}
