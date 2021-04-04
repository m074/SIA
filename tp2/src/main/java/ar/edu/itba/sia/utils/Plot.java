package ar.edu.itba.sia.utils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class Plot extends ApplicationFrame {

    public Plot(String title) {
        super(title);
    }

    public void plotValues(List<Double> fitnessValues, String title){
        XYSeries series = new XYSeries("Fitness in Generation");
        AtomicInteger count = new AtomicInteger(1);
        fitnessValues.forEach(f -> series.add(count.getAndIncrement(), f));
        XYSeriesCollection d = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(title, "Generations", "Fitness", d, PlotOrientation.VERTICAL, true, true, false);
        final ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(500,450));
        setContentPane(panel);
    }
}
