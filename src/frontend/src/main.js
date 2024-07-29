import { createApp } from 'vue'
import App from './App.vue'

import Vuesax from 'vuesax3'
import 'vuesax3/dist/vuesax.css'
import './styles/base.css'
import router from './router'

const app = createApp(App).use(router)
app.use(Vuesax)

app.mount('#app')
