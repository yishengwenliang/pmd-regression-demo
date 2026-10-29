# pmd-regression-demo

用于验证「扫描 → 问题清单 → 一键回归」链路的演示仓库：线性三次提交，
第 1 次为性能友好的基线，第 2 次故意引入 PMD 性能规则违规，第 3 次修复。

## 提交与分支

| 提交 | 分支指针 | 内容 |
|---|---|---|
| 1 `chore: 基线实现` | `step1-baseline` | `ReportSerializer` 使用 `StringBuilder` / `DateTimeFormatter`，无 PMD 违规 |
| 2 `feat: 引入性能问题` | `step2-violation` | 循环内字符串拼接、`StringBuffer`、`append` 内拼接 |
| 3 `fix: 修复性能问题` | `step3-fix`（`main`） | 恢复 `StringBuilder` 拼接，移除 `StringBuffer` |

第 2 次提交预期触发的内置 jpinpoint 规则（共 4 条，均已本地 PMD 7.26 实测）：

- `AvoidConcatInLoop`（Medium High，第 21 行）— `joinTags` 循环内 `result = result + ...`
- `AvoidUnnecessaryStringBuilderCreation`（Medium，第 27 行）— `describe` 的字符串缓冲只用于一条语句
- `AvoidStringBuffer`（Medium，第 27 行）— `describe` 使用 `new StringBuffer()`
- `AvoidConcatInAppend`（Medium High，第 28 行）— `describe` 在 `append` 参数里做字符串拼接

## 演示步骤

1. 仓库管理新增该仓库，Java 版本选 **17**（`pom.xml` 声明 `maven.compiler.release=17`），执行仓库检查。
2. 新建迭代 A：基准分支 `step1-baseline`，目标分支 `step2-violation`，执行扫描 → 报告/问题清单出现上述 3 条问题。
3. 一键问题回归：目标选迭代 A 的扫描成功记录，仓库目标分支切到 `step3-fix`，执行回归 → 3 条问题全部判定为已解决。
4. 如需同时验证「新迭代扫描干净」：再建迭代 B，基准 `step2-violation`、目标 `step3-fix` 扫描即可。

> 扫描引擎只分析 base→target diff 命中的方法，因此迭代的基准分支必须是问题引入前的提交（`step1-baseline`）。
