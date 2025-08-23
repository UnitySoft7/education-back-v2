package com.system.education.skill.core.common;

import java.util.Date;

public class LogCreated {
    public static String At() { return String.format("%tY-%tm-%td", new Date(),new Date(),new Date()); }
    public static String Date() { return String.format("%tY-%tm-%td %tH:%tM:%tS", new Date(), new Date(), new Date(), new Date(), new Date(), new Date()); }
    public static String Month() { return String.format("%tm", new Date()); }
    public static String Year() { return String.format("%tY", new Date()); }
}
