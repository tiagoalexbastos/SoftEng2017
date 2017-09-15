$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    Highcharts.chart('container', {
        chart: {
            type: 'spline',
            animation: {
		duration: 200
            },
            marginRight: 10,
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    //setInterval(function () {
                    //    var x = (new Date()).getTime(), // current time
                    //        y = Math.random();
                    //    series.addPoint([x, y], true, true);
                    //}, 1000);
                }
            }
        },
        title: {
            text: 'Temperatura dos Narcisos'
        },
        credits: {
            enabled: false
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Valor (ºC)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Random data',
             data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime() - 1000*60*10,
                    i;

                for (i = -9; i <= 0; i += 1) {
                    data.push({
                      x: time + i * 1000,
                      y: 0
                    });
                }
                return data;
            }()),
        }]
    });
    
    
    Highcharts.chart('container2', {
        chart: {
            type: 'spline',
            animation: {
		duration: 200
            },
            marginRight: 10,
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    //setInterval(function () {
                    //    var x = (new Date()).getTime(), // current time
                    //        y = Math.random();
                    //    series.addPoint([x, y], true, true);
                    //}, 1000);
                }
            }
        },
        title: {
            text: 'Humidade da Cave'
        },
        credits: {
            enabled: false
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Valor (%)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Random data',
             data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime() - 1000*60*10,
                    i;

                for (i = -9; i <= 0; i += 1) {
                    data.push({
                      x: time + i * 1000,
                      y: 0
                    });
                }
                return data;
            }()),
            color: '#9ab87a'
        }]
    });

        Highcharts.chart('container3', {
        chart: {
            type: 'spline',
            animation: {
		duration: 200
            },
            marginRight: 10,
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    //setInterval(function () {
                    //    var x = (new Date()).getTime(), // current time
                    //        y = Math.random();
                    //    series.addPoint([x, y], true, true);
                    //}, 1000);
                }
            }
        },
        title: {
            text: 'Luminosidade da Estufa'
        },
        credits: {
            enabled: false
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Valor (lux)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Random data',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime() - 1000*60*10,
                    i;

                for (i = -9; i <= 0; i += 1) {
                    data.push({
                      x: time + i * 1000,
                      y: 0
                    });
                }
                return data;
            }()),
            color: '#525252'
        }]
    });
    
    
    Highcharts.chart('container4', {
        chart: {
            type: 'spline',
            animation: {
		duration: 200
            },
            marginRight: 10,
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    //setInterval(function () {
                    //    var x = (new Date()).getTime(), // current time
                    //        y = Math.random();
                    //    series.addPoint([x, y], true, true);
                    //}, 1000);
                }
            }
        },
        title: {
            text: 'Sensor de Fumo'
        },
        credits: {
            enabled: false
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Valor (ppm)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Random data',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime() - 1000*60*10,
                    i;

                for (i = -9; i <= 0; i += 1) {
                    data.push({
                      x: time + i * 1000,
                      y: 0
                    });
                }
                return data;
            }()),
            color: '#b5446e'
        }]
    });
    
    

});