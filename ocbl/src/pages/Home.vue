<template>
  <div>
    <h2>Home</h2>
    <div v-show="!loginStatus">
      <a :href="githubUrl">
      <img src="../assets/github.png" class="img-responsive login-icon" alt="github.com">
      </a>
    </div>
  </div>
</template>

<script>

import { mapGetters } from 'vuex'

export default {
  name: 'Home',
  data () {
    return {
      githubUrl: 'https://github.com/login/oauth/authorize?client_id=1a30eaa741eb75611177&scope=user'
    }
  },
  computed: {
    ...mapGetters(
      { loginStatus: 'getloginStatus' }
    )
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login-icon {
    width: 40px;
}

h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
