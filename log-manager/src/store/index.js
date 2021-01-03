/* eslint-disable */
import  Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
     state:{
         HOST: 'http://127.0.0.1:9998' ,
         NHOST: 'http://cww.chenweiwei.top/',
         isPlay: false,    // 是否播放中
         url: '',          // 歌曲的播放地址
         id: ''            // 歌曲的id
     } ,
})

export default store