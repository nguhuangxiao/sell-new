<template>
  <div>
    <keep-alive>
      <router-view :seller="seller"></router-view>
    </keep-alive>
  </div>
</template>

<script type="text/ecmascript-6">
  import { urlParse } from 'common/js/util';
  import config from 'config';
  import header from 'components/header/header.vue';

  //const ERR_OK = 0;
  //const debug = process.env.NODE_ENV !== 'production';

  export default {
    components: {
      'v-header': header
    },
    data() {
          return {
            seller: {

            }
          };
    },
    created() {

    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "./common/stylus/mixin.styl"

  .tab
    display: flex
    width: 100%
    height: 40px
    line-height: 40px
    border-1px(rgba(7, 17, 27, 0.1))
    .tab-item
      flex: 1
      text-align: center
      & > a
        display: block
        font-size: 14px
        color: rgb(77, 85, 93)
        &.active
          color: rgb(240, 20, 20)
</style>
