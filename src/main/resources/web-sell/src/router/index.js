import Vue from 'vue';
import Router from 'vue-router';
import goods from 'components/goods/goods';
import ratings from 'components/ratings/ratings';
import seller from 'components/seller/seller';
import order from 'components/order/order';

Vue.use(Router);

const routes = [{
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
    linkActiveClass: 'active',
    routes
});
