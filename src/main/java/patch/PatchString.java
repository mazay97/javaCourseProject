package patch;

public class PatchString {
    Integer deletedStringNumber;
    Integer addedStringNumber = new Integer(0);
    Character status;
    String value;

    public PatchString(PatchString patchString){
        deletedStringNumber = patchString.getDeletedStringNumber();
        status = patchString.getStatus();
        value = patchString.getValue();
        addedStringNumber = patchString.getAddedStringNumber();
    }

    public PatchString(Integer number, Character status1, String value1){
        deletedStringNumber = number;
        status = status1;
        value = value1;
    }

    public Character getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

    public Integer getDeletedStringNumber() {
        return deletedStringNumber;
    }

    public void setDeletedStringNumber(Integer stringNumber) {
        this.deletedStringNumber = stringNumber;
    }

    public void setAddedStringNumber(Integer stringNumber) {
        this.addedStringNumber = stringNumber;
    }

    public Integer getAddedStringNumber() {
        return addedStringNumber;
    }
}
