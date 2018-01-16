package patch;

public class BasicPatchString implements PatchString{
    private Integer mDeletedStringNumber;
    private Integer mAddedStringNumber = 0;
    private Character mStatus;
    private String mValue;

    public BasicPatchString(PatchString patchString){
        mDeletedStringNumber = patchString.getDeletedStringNumber();
        mStatus = patchString.getStatus();
        mValue = patchString.getValue();
        mAddedStringNumber = patchString.getAddedStringNumber();
    }

    public BasicPatchString(Integer number, Character status1, String value1){
        mDeletedStringNumber = number;
        mStatus = status1;
        mValue = value1;
    }

    public Character getStatus() {
        return mStatus;
    }

    public String getValue() {
        return mValue;
    }

    public Integer getDeletedStringNumber() {
        return mDeletedStringNumber;
    }

    public void setDeletedStringNumber(Integer stringNumber) {
        this.mDeletedStringNumber = stringNumber;
    }

    public void setAddedStringNumber(Integer stringNumber) {
        this.mAddedStringNumber = stringNumber;
    }

    public Integer getAddedStringNumber() {
        return mAddedStringNumber;
    }
}
