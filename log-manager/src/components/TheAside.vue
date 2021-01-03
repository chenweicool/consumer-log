<template>
   <div class="sidebar">
     <el-menu
          class="sidebar-el-menu"
          :default-active="onRoutes"
          :collapse="collapse"
          background-color="#324157"
          text-color="#ffffff"
          active-text-color="#20a0ff"
          unique-opened
          router
        >
         <template v-for="item in items">
            <template>
                <el-menu-item :index="item.index" :key="item.index">
                    <i :class="item.icon"></i>
                    <span slot="title">{{item.title }}</span>
                </el-menu-item>
            </template>
        </template>
     </el-menu>
   </div>
</template>

<script>
import bus from '../assets/js/bus'
export default {
    data () {
        return {
          collapse: false,
          items: [
              {
                  icon: "el-icon-document",
                  index: 'info',
                  title:  '综合信息统计'
              },
              {
                  icon: "el-icon-document",
                  index: 'consumer',
                  title:  '用户行为分析'
              },
              {
                  icon: "el-icon-document",
                  index: 'location',
                  title:  '用户地域分布'
              },
              {
                  icon: "el-icon-document",
                  index: 'order',
                  title:  '用户下单信息'
              },
            //   {
            //       icon: "el-icon-document",
            //       index: 'test',
            //       title:  '测试的组件'
            //   }
          ] 
        }
    },
    computed: {
        onRoutes (){
            return this.$route.path.replace('/','')
        }
    },
    created(){
        // 通果bus来完成折叠侧边栏
        bus.$on('collapse',msg => {
            this.collapse = msg
        })
    }
}
</script>

<style scoped>
.sidebar{
    display: block;
    position: absolute;
    background-color: #334256;
    left: 0;
    top: 70px;
    bottom: 0px;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 150px;
}
.sidebar > ul {
  height: 100%;
}
</style>