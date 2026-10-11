package io.legado.app.feature.reader.core.transition

import io.legado.app.feature.reader.core.transition.ReaderPageTurnSpeed.Companion.BASE_DURATION_MILLIS
import io.legado.app.feature.reader.core.transition.ReaderPageTurnSpeed.Companion.Default


/**
 * 翻页动画速度挡位。
 *
 * 覆盖 / 滑动 / 渐变 / 滚动翻屏共用同一套「按位移折算」规则，挡位只决定折算的**基准时长**，
 * 也就是一整页 / 一整屏翻页从第 0 帧走完所需的时间。
 *
 * 仿真折页另有一份略快的基准（比整页基准快 40ms，默认挡 360 → 320）：折页角翻要滑约
 * 1.9 屏，若与整页共用基准，封顶后仍会被拉成同挡位下最慢的一条，收尾拖出长尾。
 *
 * 挡位给的是绝对时长而不是倍率：倍率乘完再取整会在 300 / 360 这类比例上被截断
 * （`360 * 0.8333f = 299.99…`），绝对时长没有这个问题。
 *
 * 落盘值是 [ordinal]，因此**不要重排枚举顺序**——重排会改变已有设置的含义。
 */
enum class ReaderPageTurnSpeed(
    val baseDurationMillis: Int,
    /** 仿真折页收尾专用基准：同挡位下比 [baseDurationMillis] 快 40ms。 */
    val simulationBaseDurationMillis: Int,
) {
    /** 240ms / 折页 200ms：最快，几乎瞬时完成。 */
    FASTEST(240, 200),

    /** 300ms / 折页 260ms：比默认略快，接近旧 View `defaultAnimationSpeed = 300` 的手感。 */
    FAST(300, 260),

    // 枚举项初始化的早于 companion，这里不能写 BASE_DURATION_MILLIS；
    // 两者相等由 ReaderPageTurnSpeedTest 的断言守住。
    /** 360ms / 折页 320ms：统一基准，也是默认挡。 */
    MODERATE(360, 320),

    /** 540ms / 折页 500ms：明显放慢，突出折页/滑动的过程感。 */
    ELEGANT(540, 500);

    companion object {
        /**
         * 全动画共用的原始基准时长（毫秒），等于 [MODERATE] 的时长。
         *
         * 覆盖 / 滑动 / 渐变、滚动翻屏一律以此为默认基准；仿真折页用
         * [SIMULATION_BASE_DURATION_MILLIS]。不要再按旧 View 的
         * `ReadView.defaultAnimationSpeed = 300` 另开一份，否则同一次翻页会在不同动画下快慢不一。
         */
        const val BASE_DURATION_MILLIS = 360

        /** 仿真折页收尾的默认基准（适中挡），= [BASE_DURATION_MILLIS] - 40。 */
        const val SIMULATION_BASE_DURATION_MILLIS = 320

        val Default: ReaderPageTurnSpeed = MODERATE

        /** 越界（老配置缺字段、脏数据）回落到 [Default]。 */
        fun fromValue(value: Int): ReaderPageTurnSpeed = entries.getOrNull(value) ?: Default
    }
}
