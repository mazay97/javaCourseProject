package patch;

public interface PatchString {
    Character getStatus();

    String getValue();

    Integer getDeletedStringNumber();

    void setDeletedStringNumber(Integer stringNumber);

    void setAddedStringNumber(Integer stringNumber);

    Integer getAddedStringNumber();
}
