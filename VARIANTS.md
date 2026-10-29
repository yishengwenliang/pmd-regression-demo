# 变体场景：同一方法两次提交 + 只修复其中一条

从 `step1-baseline` 拉出的独立分支线，专门验证「同一方法内多次提交的违规聚合」与「部分修复」：

| 提交 | 分支指针 | 内容 | 预期 PMD 违规 |
|---|---|---|---|
| 基线 | `step1-baseline` | 干净实现 | 0 |
| 第 1 次 | `variant-rule-a` | `joinTags` 循环内字符串拼接 | `AvoidConcatInLoop`（Medium High） |
| 第 2 次 | `variant-two-commits` | 同一方法再加 `SimpleDateFormat` | `AvoidConcatInLoop` + `AvoidSimpleDateFormat`（均 Medium High） |
| 第 3 次 | `variant-partial-fix` | 只把 `SimpleDateFormat` 换成静态 `DateTimeFormatter` | 仅剩 `AvoidConcatInLoop` |
| 第 4 次 | `variant-fixed` | 改回 `StringBuilder` 拼接 | 0 |

## 验证要点

1. 扫描 base=`step1-baseline`、target=`variant-two-commits`：两次提交都改了 `joinTags`，diff 聚合后同一方法应出 **2 条**不同规则的问题。
2. 一键回归到 `variant-partial-fix`：只有 `AvoidSimpleDateFormat` 判定为已解决，`AvoidConcatInLoop` 保持未解决（**部分解决**）。
3. 再回归到 `variant-fixed`：剩余的 `AvoidConcatInLoop` 也判定为已解决。
