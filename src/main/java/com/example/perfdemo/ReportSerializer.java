package com.example.perfdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 报表文本序列化示例（第 2 次提交：故意引入性能问题）。
 */
public class ReportSerializer {

    private static final DateTimeFormatter DAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String buildTitle(String server, LocalDate day) {
        return "perf-report-" + server + "-" + DAY_FORMAT.format(day);
    }

    public String joinTags(List<String> tags) {
        String result = "";
        for (String tag : tags) {
            result = result + "[" + tag + "]";
        }
        return result;
    }

    public String describe(String name, int count) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("name=" + name + ", count=" + count);
        return buffer.toString();
    }
}
