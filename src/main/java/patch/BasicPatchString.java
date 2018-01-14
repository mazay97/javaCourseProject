package patch;

public class BasicPatchString implements PatchString {
    Integer deletedStringNumber;
    Integer addedStringNumber = 0;
    Character mStatus;
    String mValue;

    public BasicPatchString(PatchString patchString){
        deletedStringNumber = patchString.getDeletedStringNumber();
        mStatus = patchString.getStatus();
        mValue = patchString.getValue();
        addedStringNumber = patchString.getAddedStringNumber();
    }

    public BasicPatchString(Integer number, Character status, String value){
        deletedStringNumber = number;
        mStatus = status;
        mValue = value;
    }

    public Character getStatus() {
        return mStatus;
    }

    public String getValue() {
        return mValue;
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
