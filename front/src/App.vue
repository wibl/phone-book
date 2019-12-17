<script>
export default {
  created() {
    this.$store.dispatch('loadRecords')
  }
}
</script>

<template>
  <div class="container" id="app">
    <h2>Phone Book</h2>
    <router-view/>
  </div>
</template>