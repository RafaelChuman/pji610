package br.univesp.pji610.ui.activity

import android.R
import android.graphics.Color
import android.os.Bundle
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import br.univesp.pji610.databinding.ActivityChartBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.launch


class ChartActivity : AppCompatActivity() {
    lateinit var barChart: BarChart

    lateinit var pieChart: PieChart

    lateinit var linearChart: LineChart

    val purple_700  =  0xFF3700B3
    val purple_200 = 0xFFBB86FC
    val purple_500 = 0xFF6200EE

    private val binding by lazy {
        ActivityChartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        lifecycleScope.launch {
            setBarChar()
            setPieChar()
            setLineChar()
        }

    }


    private suspend fun setBarChar() {

        val typedValue = android.R.attr.colorPrimary
        val colorPrimary = typedValue.toInt()

        barChart = binding.chartActivityBarChart


        val yAxis = barChart.axisLeft
        yAxis.axisMinimum = 0f // Start at zero
        yAxis.axisMaximum = 250f // Set the maximum value to 100

        val list: ArrayList<BarEntry> = ArrayList()

        list.add(BarEntry(1f, 219f))
        list.add(BarEntry(2f, 220f))
        list.add(BarEntry(3f, 220f))
        list.add(BarEntry(4f, 220f))
        list.add(BarEntry(5f, 219f))
        list.add(BarEntry(6f, 220f))

        var barDataSet = BarDataSet(list, "List")

        barDataSet.setColors(purple_200.toInt());
        barDataSet.valueTextColor = Color.WHITE

        val barData = BarData(barDataSet)

        barChart.setFitBars(true)
        barChart.data = barData
        barChart.description.text = "Tensão"
        barChart.setBackgroundColor( colorPrimary)
        barChart.legend.isEnabled = false
        barChart.animateY(2000)
    }


    private suspend fun setLineChar() {

        linearChart = binding.chartActivityLinearChart

        val yAxis = linearChart.axisLeft
        yAxis.axisMinimum = 0f // Start at zero
        yAxis.axisMaximum = 50f // Set the maximum value to 100



        val list: ArrayList<Entry> = ArrayList()

        list.add(Entry(1f, 22.5f))
        list.add(Entry(2f, 22.5f))
        list.add(Entry(3f, 22.6f))
        list.add(Entry(4f, 22.5f))
        list.add(Entry(5f, 22.4f))
        list.add(Entry(6f, 22.3f))

        var lineDataSet = LineDataSet(list, "Temperatura")

        lineDataSet.setColors(purple_500.toInt());
        lineDataSet.valueTextColor = Color.WHITE
        lineDataSet.valueTextSize = 14f
        lineDataSet.lineWidth = 2f
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(purple_200.toInt());
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(false);

        val lineData = LineData(lineDataSet)

        linearChart.data = lineData
        linearChart.description.text = "Temperatura"
        linearChart.setGridBackgroundColor(R.color.white)
        linearChart.legend.isEnabled = false
        linearChart.animateY(2000)
    }


    private suspend fun setPieChar() {



        pieChart = binding.chartActivityPieChart




        val list: ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(81f, "81%" ))
        list.add(PieEntry(19f, "19%" ))

        var pieDataSet = PieDataSet(list, "List")

        pieDataSet.setColors(purple_500.toInt(), purple_200.toInt()); // Customize colors here
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.valueTextSize = 15f


        val pieData = PieData(pieDataSet)

        pieChart.data = pieData
        pieChart.description.text = ""
        pieChart.centerText = "Humidade"
        pieChart.legend.isEnabled = false
        pieChart.setDrawEntryLabels(false);
        pieChart.setContentDescription("");
        pieChart.animateY(2000)
    }
}