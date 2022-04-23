import echarts from 'echarts'

export default {

    props: {
        option: {}
    },

    mounted() {
        this.mountEchart()
    },

    render: a => a('div', {class: 'echart'}),

    methods: {
        mountEchart() {
            let c = echarts.init(this.$el)
            c.setOption(this.option)
        }
    }
}