package visualization;

import patch.PatchString;

public class StringWrapper implements Wrapper{
    private final String OPENED_TR = "<tr>\n";
    private final String ADDED_ELEMENT = "<tr class=\"added\">\n";
    private final String DELETED_ELEMENT = "<tr class=\"deleted\">\n";
    private final String ClOSED_TR = "</tr>\n";
    private final String OPENED_TD = "<td>";
    private final String ClOSED_TD = "</td>\n";

    public String wrap(PatchString toWrap){
        String htmlStr = new String();

        if (toWrap.getStatus().equals('+')){
            htmlStr += ADDED_ELEMENT;
            htmlStr += OPENED_TD + toWrap.getDeletedStringNumber() + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getValue() + ClOSED_TD;
            htmlStr += ClOSED_TR;
        } else if (toWrap.getStatus().equals('-')){
            htmlStr += DELETED_ELEMENT;
            htmlStr += OPENED_TD + toWrap.getDeletedStringNumber() + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getValue() + ClOSED_TD;
            htmlStr += ClOSED_TR;
        } else {
            htmlStr += OPENED_TR;
            htmlStr += OPENED_TD + toWrap.getDeletedStringNumber() + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getValue() + ClOSED_TD;
            htmlStr += ClOSED_TR;
        }

        return htmlStr;
    }

    public String wrap(PatchString toWrap, Integer num){
        String htmlStr = new String();

        if (toWrap.getStatus().equals('+')){
            htmlStr += ADDED_ELEMENT;
            htmlStr += OPENED_TD + " " + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getDeletedStringNumber() + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getValue() + ClOSED_TD;
            htmlStr += ClOSED_TR;
        } else if (toWrap.getStatus().equals('-')){
            htmlStr += DELETED_ELEMENT;
            htmlStr += OPENED_TD + toWrap.getDeletedStringNumber() + ClOSED_TD;
            htmlStr += OPENED_TD + " " + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getValue() + ClOSED_TD;
            htmlStr += ClOSED_TR;
        } else {
            htmlStr += OPENED_TR;
            htmlStr += OPENED_TD + num + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getDeletedStringNumber() + ClOSED_TD;
            htmlStr += OPENED_TD + toWrap.getValue() + ClOSED_TD;
            htmlStr += ClOSED_TR;
        }

        return htmlStr;
    }
}
