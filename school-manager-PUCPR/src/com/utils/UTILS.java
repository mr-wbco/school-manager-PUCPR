package com.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class UTILS {

    public static final String MESSAGE_BUNDLE = "resources/messages_pt_BR";

    public static String translate(String string) {
        return ResourceBundle.getBundle(MESSAGE_BUNDLE, Locale.getDefault()).getString(string);
    }
}
