const posList = [
    'left',
    'right',
    'top',
    'bottom',
    'inside',
    'insideTop',
    'insideLeft',
    'insideRight',
    'insideBottom',
    'insideTopLeft',
    'insideTopRight',
    'insideBottomLeft',
    'insideBottomRight'
];
app.configParameters = {
    rotate: {
        min: -90,
        max: 90
    },
    align: {
        options: {
            left: 'left',
            center: 'center',
            right: 'right'
        }
    },
    verticalAlign: {
        options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
        }
    },
    position: {
        options: posList.reduce(function (map, pos) {
            map[pos] = pos;
            return map;
        }, {})
    },
    distance: {
        min: 0,
        max: 100
    }
};
app.config = {
    rotate: 90,
    align: 'left',
    verticalAlign: 'middle',
    position: 'insideBottom',
    distance: 15,
    onChange: function () {
        const labelOption = {
            rotate: app.config.rotate,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            position: app.config.position,
            distance: app.config.distance
        };
        myChart.setOption({
            series: [
                {
                    label: labelOption
                },
                {
                    label: labelOption
                },
                {
                    label: labelOption
                },
                {
                    label: labelOption
                }
            ]
        });
    }
};
const labelOption = {
    show: true,
    position: app.config.position,
    distance: app.config.distance,
    align: app.config.align,
    verticalAlign: app.config.verticalAlign,
    rotate: app.config.rotate,
    formatter: '{c}',
    fontSize: 12,
    rich: {
        name: {}
    }
};
///


let option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar', 'stack']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: 'Forest',
            type: 'bar',
            barGap: 0,
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: [320, 332, 301, 334, 390]
        },
        {
            name: 'Steppe',
            type: 'bar',
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: [220, 182, 191, 234, 290]
        },
        {
            name: 'Desert',
            type: 'bar',
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: [150, 232, 201, 154, 190]
        },
        {
            name: 'Wetland',
            type: 'bar',
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: [98, 77, 101, 99, 40]
        }
    ]
};

function getCommonOpt(indicators, json) {
    json.data[indicators[0]] = json.data[indicators[0]].map(x => x = x.replace(/,/g, ''))
    json.data[indicators[1]] = json.data[indicators[1]].map(x => x = x.replace(/,/g, ''))
    let series = [
        {
            name: indicators[0],
            type: 'bar',
            barGap: 0,
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: json.data[indicators[0]]
        },
        {
            name: indicators[1],
            type: 'bar',
            label: labelOption,
            emphasis: {
                focus: 'series'
            },
            data: json.data[indicators[1]]
        },
    ]
    let xAxis = [
        {
            type: 'category',
            interval: 1,
            data: json.data['Period End Date'],
            axisLabel: {fontSize:9}
        }
    ]
    let opt = Object.assign(option)
    opt.series = series
    opt.xAxis = xAxis
    opt.legend = {
        data: indicators
    }
    return opt
}

export function getStatementsOption(json) {
    return getCommonOpt(['Total Revenue', 'Net Income'], json)


}

export function getgetBalanceSheetOption(json) {
    return getCommonOpt(['Total Assets', 'Total Liabilities'], json)
}

export function getCashFlowOptin(json) {
    return getCommonOpt(['Net Income', 'Net Change in Cash'], json)
}