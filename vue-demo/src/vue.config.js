module.exports = {
    lintOnSave: false,
    devServer: {
      port: 8000,
      proxy: {
        //配置跨域
        '/api': {
            target: 'https://localhost:8080',
            changeOrigin: true,
            pathRewrite: {
                '^/api': ''
            }
          }
    }
    },
  }
  