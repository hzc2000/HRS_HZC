package org.hzc.editor;

import java.beans.PropertyEditorSupport;

public class DoubleEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if (text == null || text.equals("")) {
            text = "0";
        }
        setValue(Double.parseDouble(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
