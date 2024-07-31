import { createApp } from 'vue'
import App from './App.vue'

import Vuesax from 'vuesax3'
import 'material-icons/iconfont/material-icons.css';

import 'vuesax3/dist/vuesax.css'
import './styles/base.css'
import router from './router'

import { VueQueryPlugin } from '@tanstack/vue-query'

const app = createApp(App).use(router)
app.use(Vuesax)
app.use(VueQueryPlugin)

app.mount('#app')
