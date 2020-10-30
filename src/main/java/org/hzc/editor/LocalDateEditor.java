package org.hzc.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

public class LocalDateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //setValue(LocalDate.now());
        // 2020-4-9
        String[] parts = text.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("日期格式应该为: yyyy-MM-dd");
        }
        try {
            LocalDate value = LocalDate.of(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]));
            setValue(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("日期格式应该为: yyyy-MM-dd");
        }
    }

    @Override
    public String getAsText() {
        return "2019-1-1";
    }
}

