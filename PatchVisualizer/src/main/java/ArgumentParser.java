public interface ArgumentParser {
    void parse(String[] args);
    void addFlag(String flag);
    String getValue(String flag);
}
