<script>
export default {
  name: 'EditPage',
  props: [
    'recordId'
  ],
  computed: {
    record() {
        let id = parseInt(this.recordId)
        if (id === -1) { 
          return {id: id, name: "", number: ""}
        } else {
          return this.$store.state.records.find(r => r.id === id)
        }
    }
  },
  methods: {
    onSaveButtonClick () {
        this.$store.dispatch('updateRecord', this.record)
          .then(() => {
            this.$router.push({name: "home"})
          }).catch((err) => {
            //TODO: add errors handling
            //this.errors.push(err)
          })
    },
    onCancelButtonClick () {
      this.$router.push({name: "home"})        
    },
  },
}
</script>

<template>
  <form>
    <input name="id" v-model="record.id" hidden>
    <div class="form-group">
      <label for="name">Name</label>
      <input class="form-control" name="name" v-model="record.name">
    </div>
    <div class="form-group">
      <label for="number">Number</label>
      <input class="form-control" name="number" v-model="record.number">
    </div>
    <div class="btn-group">
      <button class="btn btn-success" v-on:click.prevent="onSaveButtonClick()">Save</button>
      <button class="btn btn-secondary" v-on:click.prevent="onCancelButtonClick()">Cancel</button>
    </div>
  </form>
</template>