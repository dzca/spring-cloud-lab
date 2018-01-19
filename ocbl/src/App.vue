<template>
  <div id="app">
    <ul class="nav nav-tabs">
      <li class="nav-item">
        <router-link class="nav-link" to='/'>home</router-link>
      </li>
      <li v-show="loginStatus" class="nav-item">
        <router-link class="nav-link" to="/profile">profile</router-link>
      </li>
    </ul>
    <router-view/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'app',
  data () {
    return {
      msg: 'some_string'
    }
  },
  computed: {
    ...mapGetters(
      { loginStatus: 'getloginStatus' }
    )
  }
}
</script>

<style>

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
