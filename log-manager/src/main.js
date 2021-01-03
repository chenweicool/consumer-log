// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
/* eslint-disable */
import Vue from 'vue'
import App from './App'
import router from './router/index'
import store from './store/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/main.css'
import 'babel-polyfill'
import VCharts from 'v-charts'
import echarts from 'echarts'
import 'echarts/map/js/china'

Vue.prototype.$echarts = echarts
Vue.use(ElementUI)
Vue.use(VCharts)
// Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  /*
  components: {App},
  template: '<App/>' */
  render: h => h(App)
})
