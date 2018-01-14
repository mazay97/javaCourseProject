package visualization;

import patch.PatchString;

public class BasicStringWrapper implements StringWrapper {
    private static final String OPENED_TR = "<tr>\n";
    private static final String ADDED_ELEMENT = "<tr class=\"added\">\n";
    private static final String DELETED_ELEMENT = "<tr class=\"deleted\">\n";
    private static final String CLOSED_TR = "</tr>\n";
    private static final String OPENED_TD = "<td>";
    private static final String CLOSED_TD = "</td>\n";

    public String wrap(PatchString toWrap) {
        StringBuilder htmlStr = new StringBuilder();

        if (toWrap.getStatus().equals('+')) {
            htmlStr.append(ADDED_ELEMENT);
            htmlStr.append(OPENED_TD).append(toWrap.getDeletedStringNumber()).append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getValue()).append(CLOSED_TD);
            htmlStr.append(CLOSED_TR);
        } else if (toWrap.getStatus().equals('-')) {
            htmlStr.append(DELETED_ELEMENT);
            htmlStr.append(OPENED_TD).append(toWrap.getDeletedStringNumber()).append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getValue()).append(CLOSED_TD);
            htmlStr.append(CLOSED_TR);
        } else {
            htmlStr.append(OPENED_TR);
            htmlStr.append(OPENED_TD).append(toWrap.getDeletedStringNumber()).append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getValue()).append(CLOSED_TD);
            htmlStr.append(CLOSED_TR);
        }

        return htmlStr.toString();
    }

    public String wrap(PatchString toWrap, Integer num) {
        StringBuilder htmlStr = new StringBuilder();

        if (toWrap.getStatus().equals('+')) {
            htmlStr.append(ADDED_ELEMENT);
            htmlStr.append(OPENED_TD).append(" ").append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getDeletedStringNumber()).append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getValue()).append(CLOSED_TD);
            htmlStr.append(CLOSED_TR);
        } else if (toWrap.getStatus().equals('-')) {
            htmlStr.append(DELETED_ELEMENT);
            htmlStr.append(OPENED_TD).append(toWrap.getDeletedStringNumber()).append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(" ").append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getValue()).append(CLOSED_TD);
            htmlStr.append(CLOSED_TR);
        } else {
            htmlStr.append(OPENED_TR);
            htmlStr.append(OPENED_TD).append(num).append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getDeletedStringNumber()).append(CLOSED_TD);
            htmlStr.append(OPENED_TD).append(toWrap.getValue()).append(CLOSED_TD);
            htmlStr.append(CLOSED_TR);
        }

        return htmlStr.toString();
    }
}
