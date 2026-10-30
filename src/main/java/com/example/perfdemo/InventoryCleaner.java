package com.example.perfdemo;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 标签/序列号清理示例（Semgrep 行内规则 inhouse.java-loop-remove-violation 修复后）。
 */
public class InventoryCleaner {

    public void removeExpiredTags(List<String> tags) {
        tags.removeIf(tag -> tag.startsWith("expired-"));
    }

    public void removeDuplicatedSerials(List<String> serials) {
        Set<String> seen = new LinkedHashSet<>();
        serials.removeIf(serial -> !seen.add(serial));
    }
}
