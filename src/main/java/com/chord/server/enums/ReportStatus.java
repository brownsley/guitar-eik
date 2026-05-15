package com.chord.server.enums;

public enum ReportStatus {
    PENDING,
    UNDER_REVIEW,
    RESOLVED,
    REJECTED;

    public static ReportStatus fromInt(int value) {
        for (ReportStatus status : ReportStatus.values()) {

            if (status.ordinal() == value) {
                return status;
            }
        }
        return PENDING;
    }

}
