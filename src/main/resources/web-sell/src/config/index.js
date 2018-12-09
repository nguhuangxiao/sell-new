const domains = {
    dev: 'http://localhost:8088/sell',                   // 本地【开发】环境
    test: 'https://fs.pailizhuan.cn/fengshidev',    // 线上【测试】环境
    prod: 'https://fs.pailizhuan.cn/fengshi'        // 线上【生产】环境
}
module.exports = {
    BASE_URL: domains['dev']
}
