<template>
    <div class="order-container">
        <div class="choose-address">
            选择收货地址>
        </div>
        <div class="estimate-info">
            <div class="time-box">
                <div class="estimate-time">
                    <div>送达时间</div>
                    <span>准时达PLU服务：超过十分钟起享现金服务</span>
                </div>
                <div class="time">
                    尽快送达（11:27送达）
                </div>
            </div>
            <div class="pay-type">
                支付方式
                <div class="type-label">支付宝></div>
            </div>
        </div>

        <div class="product-info">
            <div class="address">米多面多（藏龙岛店）</div>
            <div class="product" v-for="item in order">
                <img :src="item.icon"/>
                <span class="name">{{item.name}}</span>
                <span class="number">x<i>{{item.count}}</i></span>
                <div class="single-totle">¥{{item.singleTotle}}</div>
            </div>
            <ul class="other-cost" >
                <li>
                    <label>包装费</label>餐盒<span>¥{{packingPrice}}</span>
                </li>
                <li>
                    <label>配送费</label>蜂鸟专送<span>¥{{deliveryPrice}}</span>
                </li>
            </ul>
        </div>

        <div class="totle-price">
            <div>¥{{totlePrice}}</div>
            <div class="receiveOrder" @click="goPay">确认支付</div>
        </div>

    </div>
</template>

<script type="text/ecmascript-6">

    import config from 'config';
    import index from '../../router';

    export default {
        name: 'Order',
        components: {

        },
        props: {

        },
        data() {
            return {
                totlePrice: 0,
                deliveryPrice: 4,
                packingPrice: 2,
                order: [],
                urlParams: {},
                detailShow: false
            };
        },
        methods: {
            reserveOrder() {
                let num = 0;
                let carArr = [];
                let order = this.$route.params.order;
                console.log(order)
                order.forEach((item, index) => {
                    let productObj = {};
                    item.singleTotle = item.count * item.price;
                    num += item.singleTotle;

                    //购物车信息
                    productObj.productId = item.id;
                    productObj.productQuantity = item.count;
                    carArr.push(productObj);

                })
                this.totlePrice = num + this.deliveryPrice + this.packingPrice;
                this.order = order;

                this.urlParams = {
                    name: '黄潇',
                    phone: '13317194243',
                    address: '浙江省金华市婺城区',
                    openId: 'SJHDA221JH213123',
                    item: carArr
                }
            },
            goPay() {
                const url = config.BASE_URL +'/sell/buyer/order/create'
                this.$http.post(url, this.urlParams).then((response) => {
                    response = response.body;
                    console.log(response);
                });
            }
        },
        created() {

        },
        mounted() {
            this.reserveOrder();
        }

    };
</script>

<style lang="stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/mixin";
    body{
        background: #e3e5e6;
    }
    .order-container{
        padding 20px;
    }
    .choose-address{
        color: #fff;
        font-size:20px;
    }
    .estimate-info{
        background: #fff;
        font-size: 14px;
        padding: 10px;
        margin: 10px 0;
        .time-box{
            display flex;
            justify-content space-between;
        }
        .pay-type{
            display flex;
            justify-content space-between;
        }
    }
    .product-info{
        background: #fff;
        padding: 10px;
        .product{
            display flex;
            align-items center;
            margin: 10px 0;
            img{
                width 60px;
                height 60px;
                display block;
            }
            .name{
                width: 50%;
                margin: 0 10px;
            }
        }
    }
</style>
