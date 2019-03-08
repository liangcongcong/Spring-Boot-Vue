import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import 'iview/dist/styles/iview.css'
import iView from 'iview';
Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.use(iView);
new Vue({
  render: h => h(App),
}).$mount('#app')
