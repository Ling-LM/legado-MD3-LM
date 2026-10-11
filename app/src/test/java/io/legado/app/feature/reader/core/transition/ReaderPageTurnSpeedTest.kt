package io.legado.app.feature.reader.core.transition

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 翻页动画速度挡位：四挡都是绝对基准时长（240 / 300 / **360 默认** / 540），
 * 且每个挡位都必须整体作用于覆盖/滑动/渐变、仿真折页、滚动翻屏三条路径。
 *
 * 仿真折页另有一份更快的基准（同挡位下为整页基准的 2/3，默认 240），
 * 折算规则与挡位整体性一并钉在这里。
 */
class ReaderPageTurnSpeedTest {

    @Test
    fun `四挡时长与声明顺序一致`() {
        assertEquals(240, ReaderPageTurnSpeed.FASTEST.baseDurationMillis)
        assertEquals(300, ReaderPageTurnSpeed.FAST.baseDurationMillis)
        assertEquals(360, ReaderPageTurnSpeed.MODERATE.baseDurationMillis)
        assertEquals(540, ReaderPageTurnSpeed.ELEGANT.baseDurationMillis)
        assertEquals(160, ReaderPageTurnSpeed.FASTEST.simulationBaseDurationMillis)
        assertEquals(200, ReaderPageTurnSpeed.FAST.simulationBaseDurationMillis)
        assertEquals(240, ReaderPageTurnSpeed.MODERATE.simulationBaseDurationMillis)
        assertEquals(360, ReaderPageTurnSpeed.ELEGANT.simulationBaseDurationMillis)
    }

    @Test
    fun `默认挡是 360ms 且等于统一基准`() {
        assertEquals(360, ReaderPageTurnSpeed.BASE_DURATION_MILLIS)
        assertEquals(240, ReaderPageTurnSpeed.SIMULATION_BASE_DURATION_MILLIS)
        assertEquals(ReaderPageTurnSpeed.MODERATE, ReaderPageTurnSpeed.Default)
        assertEquals(
            ReaderPageTurnSpeed.BASE_DURATION_MILLIS,
            ReaderPageTurnSpeed.Default.baseDurationMillis,
        )
        assertEquals(
            ReaderPageTurnSpeed.SIMULATION_BASE_DURATION_MILLIS,
            ReaderPageTurnSpeed.Default.simulationBaseDurationMillis,
        )
    }

    @Test
    fun `仿真折页基准是同挡位整页基准的 2/3`() {
        ReaderPageTurnSpeed.entries.forEach { speed ->
            assertEquals(
                "${speed.name} 挡仿真基准应为整页基准的 2/3",
                speed.baseDurationMillis * 2 / 3,
                speed.simulationBaseDurationMillis,
            )
        }
    }

    @Test
    fun `三条翻页路径的默认基准被钉住`() {
        // 覆盖/滑动/渐变、滚动的默认基准 = 统一基准 360；仿真折页 = 240（整页基准的 2/3）。
        // 任一路径另开一份常量，或仿真基准偏离统一基准，这条断言就会红。
        assertEquals(360, ReaderPageTransitionPolicy.settleDurationMillis(0f, -800f, 800f))
        assertEquals(240, ReaderCurlTouchPolicy.settleDurationMillis(0f, 800f, 800f))
        assertEquals(360, ReaderScrollPolicy.stepDurationMillis(-800f, 800f))
    }

    @Test
    fun `任一挡位都整体作用于三条路径`() {
        ReaderPageTurnSpeed.entries.forEach { speed ->
            val base = speed.baseDurationMillis
            val curlBase = speed.simulationBaseDurationMillis
            val actual = mapOf(
                "收尾" to ReaderPageTransitionPolicy.settleDurationMillis(
                    0f, -800f, 800f, baseDurationMillis = base,
                ),
                "折页" to ReaderCurlTouchPolicy.settleDurationMillis(
                    0f, 800f, 800f, baseDurationMillis = curlBase,
                ),
                "滚动" to ReaderScrollPolicy.stepDurationMillis(
                    -800f, 800f, animationSpeedMillis = base,
                ),
            )
            actual.forEach { (path, millis) ->
                val expected = if (path == "折页") curlBase else base
                assertEquals("${speed.name} 挡的${path}时长不等于该路径基准", expected, millis)
            }
        }
    }

    @Test
    fun `挡位越高时长越长且互不相等`() {
        val durations = ReaderPageTurnSpeed.entries.map { it.baseDurationMillis }
        assertEquals("挡位顺序与时长顺序不一致：$durations", durations.sorted(), durations)
        assertTrue("挡位之间存在重复时长：$durations", durations.toSet().size == durations.size)
        val curlDurations = ReaderPageTurnSpeed.entries.map { it.simulationBaseDurationMillis }
        assertEquals("仿真挡位顺序与时长顺序不一致：$curlDurations", curlDurations.sorted(), curlDurations)
        assertTrue("仿真挡位之间存在重复时长：$curlDurations", curlDurations.toSet().size == curlDurations.size)
    }

    @Test
    fun `越界配置值回落到默认适中挡`() {
        assertEquals(ReaderPageTurnSpeed.Default, ReaderPageTurnSpeed.fromValue(Int.MIN_VALUE))
        assertEquals(ReaderPageTurnSpeed.Default, ReaderPageTurnSpeed.fromValue(Int.MAX_VALUE))
        assertEquals(ReaderPageTurnSpeed.FASTEST, ReaderPageTurnSpeed.fromValue(0))
        assertEquals(ReaderPageTurnSpeed.FAST, ReaderPageTurnSpeed.fromValue(1))
        assertEquals(ReaderPageTurnSpeed.MODERATE, ReaderPageTurnSpeed.fromValue(2))
        assertEquals(ReaderPageTurnSpeed.ELEGANT, ReaderPageTurnSpeed.fromValue(3))
    }
}
