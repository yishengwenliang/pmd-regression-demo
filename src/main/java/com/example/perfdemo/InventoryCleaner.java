package com.example.perfdemo;

import java.util.List;

/**
 * 标签/序列号清理示例（Semgrep 行内规则 inhouse.java-loop-remove-violation 触发代码）。
 */
public class InventoryCleaner {

    public void removeExpiredTags(List<String> tags) {
        for (String tag : tags) {
            if (tag.startsWith("expired-")) {
                tags.remove(tag);
            }
        }
    }

    public void removeDuplicatedSerials(List<String> serials) {
        for (int i = 0; i < serials.size(); i++) {
            if (serials.indexOf(serials.get(i)) != i) {
                serials.remove(i);
            }
        }
    }
}
