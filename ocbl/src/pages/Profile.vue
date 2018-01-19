<template>
  <div class="me">
    <h2>My Profile</h2>
    <div>{{userInfo.user.email}}</div>
    <div>{{userInfo.user.status}}</div>
    <button type="button" @click="logOut()" class="btn btn-default btn-sm">
    <span class="glyphicon glyphicon-ok-circle"></span> logOut
    </button>
  </div>
</template>
<script>
  import { mapGetters, mapActions } from 'vuex'

  export default {
    data () {
      return {
        accesstoken: ''
      }
    },
    methods: {
      ...mapActions({ signOut: 'signOut'
      }),
      logOut () {
        console.log('into logOut()')
        this.signOut()
        this.$router.push({name: 'home'})
      }
    },
    computed: {
      ...mapGetters({
        userInfo: 'getUserInfo'
      })
    },
    //
    created: function () {
      // const token = this.$route.params.token
      // console.log('called github created() ' + token)
      // this.login(token)
      // this.$router.push({name: 'profile'})
    }
  }
</script>

<style>
</style>
