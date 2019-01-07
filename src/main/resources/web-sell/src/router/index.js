import Vue from 'vue';
import Router from 'vue-router';
import goods from 'components/goods/goods';
import ratings from 'components/ratings/ratings';
import seller from 'components/seller/seller';
import order from 'components/order/order';
import login from 'components/login/login';

Vue.use(Router);

const routes = [{
    path: '/login',
    component: login
}, {
    path: '/',
    redirect: '/goods'
}, {
    path: '/goods',
    component: goods
}, {
    path: '/ratings',
    component: ratings
}, {
    path: '/seller',
    component: seller
}, {
    path: '/order',
    name: 'Order',
    component: order
}];

export default new Router({
    mode: 'history',
    linkActiveClass: 'active',
    routes
});
