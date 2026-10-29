package com.example.perfdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 报表文本序列化示例（变体场景：第 1 次提交在 joinTags 引入循环内字符串拼接）。
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
        return "name=" + name + ", count=" + count;
    }
}
