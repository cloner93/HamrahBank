package com.pmb.account.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.account.presentation.balance.DepositsChartModel
import com.pmb.account.utils.mapToDepositsChartModel
import com.pmb.ballon.component.annotation.AppPreview

@Composable
internal fun PieChart(
    modifier: Modifier = Modifier,
    charts: List<DepositsChartModel> = listOf(),
    size: Dp = 200.dp,
    strokeWidth: Dp = 20.dp
) {
    Canvas(
        modifier = modifier
            .size(size)
            .padding(20.dp), onDraw = {

            var startAngle = 0f
            var sweepAngle = 0f

            charts.forEach {

                sweepAngle = (it.value / 100) * 360

                drawArc(
                    color = it.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(
                        width = strokeWidth.toPx(), cap = StrokeCap.Butt, join = StrokeJoin.Miter
                    )
                )

                startAngle += sweepAngle
            }
        })

}

@AppPreview
@Composable
private fun PieChartPreview() {
    val depositsChartList = listOf<DepositModel>(
        DepositModel(
            "حساب قرض الحسنه",
            "",
            "123456",
            500_000.0,
            "ریال",
            "IR1234567890098765432112",
            "6219861920241234",
        ),
        DepositModel(
            "حساب سرمایه گذاری بلند مدت",
            "",
            "97974632",
            300_000.0,
            "ریال",
            "IR1234567890098765432112",
            "6219861920241234"
        ),
        DepositModel(
            "حساب مشترک",
            "",
            "82768947",
            200_000.0,
            "ریال",
            "IR1234567890098765432112",
            "6219861920241234"
        ),
    ).mapToDepositsChartModel()
    PieChart(
        modifier = Modifier, charts = depositsChartList, size = 100.dp, strokeWidth = 5.dp
    )
}