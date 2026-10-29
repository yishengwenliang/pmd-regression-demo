package com.example.perfdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 报表文本序列化示例（第 1 次提交：性能友好的基线实现）。
 */
public class ReportSerializer {

    private static final DateTimeFormatter DAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String buildTitle(String server, LocalDate day) {
        return "perf-report-" + server + "-" + DAY_FORMAT.format(day);
    }

    public String joinTags(List<String> tags) {
        StringBuilder builder = new StringBuilder();
        for (String tag : tags) {
            builder.append('[').append(tag).append(']');
        }
        return builder.toString();
    }

    public String describe(String name, int count) {
        return "name=" + name + ", count=" + count;
    }
}
