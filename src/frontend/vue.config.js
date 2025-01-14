const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '/app/',
  devServer: {
    client: {
      overlay: false,
    },
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://192.168.43.28:8080',
        ws: true,
        changeOrigin: true
      }
    }
  }
})
