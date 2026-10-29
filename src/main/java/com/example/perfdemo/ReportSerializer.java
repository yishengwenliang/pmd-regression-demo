package com.example.perfdemo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 报表文本序列化示例（变体场景：第 2 次提交仍在 joinTags，再引入 SimpleDateFormat）。
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
        SimpleDateFormat stamp = new SimpleDateFormat("HH:mm:ss");
        return result + "@" + stamp.format(new Date());
    }

    public String describe(String name, int count) {
        return "name=" + name + ", count=" + count;
    }
}
