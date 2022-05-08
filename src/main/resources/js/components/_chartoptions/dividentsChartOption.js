let option = {
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '30%']
    },
    tooltip: {
        trigger: 'axis'
    }
};

export function getDividentsOptions(json) {
    let dividents = json.chart.result[0].events.dividends
    let keys = Object.keys(dividents)
    let vals = Object.values(dividents)
    keys = keys.map(x => new Date(new Date(0).setUTCSeconds(x)).toLocaleDateString("en-US"))
    vals = vals.map(x => x.amount)
    let xAxis = {
        type: 'category',
        boundaryGap: false,
        data: keys
    }
    let data = {
        data: vals,
        type: 'line',
        areaStyle: {}
    }
    option.xAxis = xAxis
    option.series = []
    option.series.push(data)
    option.title = {
        left: 'center',
        text: 'Dividents'
    }
    return option
}