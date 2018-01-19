if (status === 'REGISTERED') {
  this.login(token, res.data)
  this.$router.push({name: 'profile'})
} else if (status === 'UNREGISTERED') {
  this.login(token, res.data)
  this.$router.push({name: 'register'})
} else {
  this.$router.push({name: 'home'})
}
