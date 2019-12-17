import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import EditPage from '../views/EditPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
  },
  {
    path: '/edit/:recordId',
    name: 'edit',
    component: EditPage,
    props: true
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
