<template>
  <div class="container">
  </div>
</template>
<script>
  import { mapActions } from 'vuex'
  import axios from 'axios'

  export default {
    data () {
      return {
        accesstoken: ''
      }
    },
    methods: {
      ...mapActions({ setUserInfo: 'setUserInfo' }),
      login (token, user) {
        console.log('into login, token =' + token)
        this.setUserInfo({ accesstoken: token, user: user })
      }
    },
    // test if token is valid by request to
    // HTTP GET ocbl.ca/auth/api/github/access_token?access_token=xxxxx
    // if taoken is good, set tokens and redirect to profile page
    // else set signOut and goto home page
    created: function () {
      const token = this.$route.params.token
      console.log('called github created() ' + token)

      axios.get('http://api.skyler.ca/auth/rest/github/access_token/' + token).then(res => {
        // success callback
        console.log('res=%j', res)
        this.login(token, res.data)
        this.$router.push({name: 'profile'})
      }, err => {
        // error callback
        console.log('err=%j', err)
        this.$router.push({name: 'home'})
      })
    }
  }
</script>
